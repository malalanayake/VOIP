CREATE PROCEDURE getCountryService 
    @code int, 
    @serviceName nvarchar(20) 
AS 
  
    SELECT CountryService.*
    FROM CountryService Join Service on CountryService.service_id=Service.id
    WHERE Service.name= @serviceName and CountryService.country_code=@code
GO

--execute getCountryService 1, 'Spectra';

CREATE PROCEDURE getCountryServiceByCName 
    @countryName nvarchar(20), 
    @serviceName nvarchar(20) 
AS 
  
    SELECT CountryService.*
    FROM CountryService Join Service 
    on CountryService.service_id=Service.id Join Country
    on CountryService.country_code = Country.code
    WHERE Service.name= @serviceName and Country.name=@countryName
GO

--execute getCountryServiceByCName 'USA', 'Spectra';

CREATE PROCEDURE getSalesRepByCode 
    @code int

AS 
  
    SELECT *
    FROM SalesRep
    where SalesRep.code =@code
GO

--execute getSalesRepByCode 12

CREATE PROCEDURE getCountryByName 
    @countryName nvarchar(20) 
AS 
  
    SELECT *
    FROM Country
    Where name=@countryName
GO

---
---Stored Precures for Reports----
CREATE PROCEDURE findLatestRate 
    @countryServiceId int,
    @date date
AS 
		select distinct cr.*,c.name into #T2
		from CallRate cr 
		Join 
		( 
			select country_service_id , dest_country_code,MAX(effectiveFrom) as maxEffectiveFrom
			from CallRate
			where effectiveFrom<=@date
			group by country_service_id , dest_country_code
		)
	    as T on cr.country_service_id=T.country_service_id and cr.dest_country_code=T.dest_country_code 
		Join Country c on c.code = cr.dest_country_code
		where T.maxEffectiveFrom=cr.effectiveFrom and cr.country_service_id=1
		order by c.name
		
		SELECT T.id,T.effectiveFrom,T.effectiveTo,T.offPeakRate,T.peakRate,T.country_service_id,T.dest_country_code
		From #T2 as T
    
GO
--execute findLatestRate 1,'2013-10-05
--execute findLatestRate 1

------
CREATE PROCEDURE getMonthlyReport
    @reportDate date,
    @customerId numeric(19, 0) 
AS 
	 SET NOCOUNT ON;
	--Drop All Row First
	truncate table CustomerMonthly;
	delete from CustomerMonthlyTotalReport;
	insert into CustomerMonthlyTotalReport values (0);
	
	--Insert Query Result 
	Insert into CustomerMonthly
	select distinct  cd.duration*  dbo.getCallRate(cr.country_service_id,cd.toCountry_code,callDate,callTime) as cost,
	Country.name as country,cd.callDate as date,cd.duration,cd.toTel as phoneno,cd.callTime as time, (select top 1 id from CustomerMonthlyTotalReport) as report_id 
	 
	from CallDetail cd join Customer c
	on c.phoneNumber =cd.fromCustomer_phoneNumber join CallRate cr
	on c.countryService_id=cr.country_service_id join Country
	on cd.toCountry_code=Country.code
	where c.phoneNumber=@customerId and YEAR(callDate)=YEAR(@reportDate) and MONTH(callDate)=MONTH(@reportDate);
    
    update CustomerMonthlyTotalReport
    set totalCost = ( select SUM(cost) from CustomerMonthly);
    
	select * from CustomerMonthlyTotalReport;
    
GO

--execute getMonthlyReport '2014-12-05',71393754


------
CREATE PROCEDURE getMonthlyTraffic 
    @monthDate date
AS 
  
    select IDENTITY(int, 1,1) AS id,s.name as serviceName,sC.name as fromCountry,dC.name as toCountry,SUM(duration)/60 as minutesOfCalls into #T1
	From CallDetail cd 
	Join Country sC on cd.fromCountry_code=sC.code 
	Join Country dC on cd.fromCountry_code=dC.code 
	Join Customer c on cd.fromCustomer_phoneNumber=c.phoneNumber
	Join CountryService cs on c.countryService_id = cs.service_id
	Join Service s on cs.service_id= s.id
	where YEAR(cd.callDate)=YEAR(@monthDate) and MONTH(cd.callDate)=MONTH(@monthDate)
	group by s.name,sC.name,dC.name
	
	select * from #T1
GO

--exec getMonthlyTraffic '2014-12-1'


----------------------------------
--Create Monthly Commission Report
----------------------------------

CREATE PROCEDURE getSalesReport 
    @reportDate date,
    @salesRepCode numeric(19, 0) 
AS 
	
	SET NOCOUNT ON;
	--Drop All Row First
	truncate table SalesCommission;
	delete from SalesCommissionTotalReport;
	insert into SalesCommissionTotalReport values (0);
	
	--Get All Customers for salesRep
	SELECT  c.phoneNumber into #T1
	FROM SalesRep sr 
	Join SalesCustomer sc on sr.id = sc.salesRep_id
	Join Customer c on c.phoneNumber=sc.customer_phoneNumber
	WHERE sr.code=@salesRepCode
	
	--Get Call Cost for all call for phone numbers in #T1
	select distinct cd.id, c.phoneNumber,
	 cd.duration*  dbo.getCallRate(cr.country_service_id,cd.toCountry_code,callDate,callTime) as cost
	 into #T2
	from CallDetail cd 
	join Customer c on c.phoneNumber =cd.fromCustomer_phoneNumber 
	join CallRate cr on c.countryService_id=cr.country_service_id 
	Join Country on cd.toCountry_code=Country.code
	Join #T1 t on t.phoneNumber= c.phoneNumber
	where YEAR(callDate)=YEAR(@reportDate) and MONTH(callDate)=MONTH(@reportDate)
	
	-- Get total cost by each customer
	select phoneNumber,SUM(cost) as cost  into #T3
	From #T2  group by phoneNumber
	


	-- Join all the data to produce result
	insert into SalesCommission
	select sc.commission*t.cost/100 as commission,t.cost,cnt.name+'_'+s.name as countryservice,c.name as customer,(select top 1 id from SalesCommissionTotalReport) as report_id
	
	From #T3 t 
	Join Customer c on t.phoneNumber=c.phoneNumber
	Join CountryService cs on cs.id=c.countryService_id
	Join Country cnt on cnt.code=cs.country_code
	Join SalesCustomer sc on sc.customer_phoneNumber=c.phoneNumber
	Join SalesRep sr on sr.id=sc.salesRep_id
	Join Service s on s.id=cs.service_id
	
	update SalesCommissionTotalReport
	set totalCommission = ( select SUM(commission) from SalesCommission)
	
	select * from SalesCommissionTotalReport;
Go

--exec getSalesReport '2014-12-05',23



---------------------------------------
CREATE FUNCTION  getCallRate(
	@country_serviceId numeric(19, 0),
	@dest_country_code int,
	@callDate date,
	@calltime int
	)
returns float
as
BEGIN
	DECLARE @call_rate  float;
    Declare @peakRate float;
    Declare @offPeakRate float;
    Declare @peakTime int;
    Declare @offPeakTime int;
    declare @maxdate date;
    
	select @peakRate=cr.peakRate,@offPeakRate=cr.offPeakRate
	from CallRate cr,
	(select MAX(cr.effectiveFrom)as maxDate
	from CallRate cr
	where cr.country_service_id=@country_serviceId and cr.dest_country_code=@dest_country_code
	and cr.effectiveFrom < @callDate) as T
	where T.maxDate=cr.effectiveFrom and  cr.country_service_id=@country_serviceId and cr.dest_country_code=@dest_country_code
	
	select @peakTime=Country.peakTime,@offPeakTime=Country.offPeakTime 
	from Country,CountryService
	where  CountryService.country_code=Country.code and CountryService.id=@country_serviceId
	
	IF( @calltime > @peakTime and @calltime < @offPeakTime) 
		set @call_rate= @peakRate

	IF( @calltime < @peakTime or @calltime > @offPeakTime  ) 
		set @call_rate= @offPeakRate	

	return @call_rate
	
END
--select dbo.getCallRate(1,597,'2014-12-05',929)









--CREATE PROCEDURE getMonthlyReport 
--    @reportDate date,
--    @customerId numeric(19, 0) 
--AS 
--	select distinct cd.id, cd.callDate as date,cd.callTime as time,cd.duration,Country.name as country,cd.toTel as phoneno, cd.duration* 2 as cost
--	from CallDetail cd join Customer 
--	on Customer.phoneNumber =cd.fromCustomer_phoneNumber join CallRate
--	on Customer.countryService_id=CallRate.country_service_id join Country
--	on cd.toCountry_code=Country.code
--	where Customer.phoneNumber=@customerId and YEAR(callDate)=YEAR(@reportDate) and MONTH(callDate)=MONTH(@reportDate)
--    
--GO

--execute getMonthlyReport '2014-12-05',7139375437

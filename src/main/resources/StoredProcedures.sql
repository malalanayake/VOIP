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
    @countryServiceId int 
AS 
  
     SELECT distinct cr.*
    FROM CallRate cr Join 
    ( select country_service_id , dest_country_code,MAX(effectiveFrom) as maxEffectiveFrom
	  from CallRate
	  group by country_service_id , dest_country_code
	  ) as T on cr.country_service_id=T.country_service_id and cr.dest_country_code=T.dest_country_code 
	  Where T.maxEffectiveFrom=cr.effectiveFrom and cr.country_service_id=@countryServiceId
GO
--execute findLatestRate 1

------
CREATE PROCEDURE getMonthlyReport 
    @reportDate date,
    @customerId numeric(19, 0) 
AS 
	select distinct cd.id, cd.callDate as date,cd.callTime as time,cd.duration,Country.name as country,cd.toTel as phoneno,
	 cd.duration*  dbo.getCallRate(cr.country_service_id,cd.toCountry_code,callDate,callTime) as cost
	from CallDetail cd join Customer c
	on c.phoneNumber =cd.fromCustomer_phoneNumber join CallRate cr
	on c.countryService_id=cr.country_service_id join Country
	on cd.toCountry_code=Country.code
	where c.phoneNumber=@customerId and YEAR(callDate)=YEAR(@reportDate) and MONTH(callDate)=MONTH(@reportDate)
    
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
	IF( @calltime < @peakTime and @calltime > @offPeakTime  ) 
		set @call_rate= @peakRate	
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

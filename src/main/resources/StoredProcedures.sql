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

CREATE PROCEDURE getMonthlyReport 
    @reportDate date,
    @customerId numeric(19, 0) 
AS 
	select distinct cd.id, cd.callDate as date,cd.callTime as time,cd.duration,Country.name as country,cd.toTel as phoneno, cd.duration* 2 as cost
	from CallDetail cd join Customer 
	on Customer.phoneNumber =cd.fromCustomer_phoneNumber join CallRate
	on Customer.countryService_id=CallRate.country_service_id join Country
	on cd.toCountry_code=Country.code
	where Customer.phoneNumber=@customerId and YEAR(callDate)=YEAR(@reportDate) and MONTH(callDate)=MONTH(@reportDate)
    
GO

--execute getMonthlyReport '2014-12-05',7139375437

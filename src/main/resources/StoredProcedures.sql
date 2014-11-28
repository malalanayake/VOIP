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
  
    SELECT *
    FROM CallRate
    WHERE country_service_id=@countryServiceId
GO
--execute findLatestRate 1

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


---Generate some test data---

   insert into Service values('Spectra');
   insert into Service values('Deluxe');
   insert into Service values('VOIP');
   
   insert into CountryService
	select Country.code, Service.id
	from Country, Service
	Where Country.name='USA' 
    


---test Data---

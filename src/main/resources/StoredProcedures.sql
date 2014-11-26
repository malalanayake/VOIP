CREATE PROCEDURE getCountryService 
    @code int, 
    @serviceName nvarchar(20) 
AS 
  
    SELECT CountryService.*
    FROM CountryService Join Service on CountryService.service_id=Service.id
    WHERE Service.name= @serviceName and CountryService.country_code=@code
GO

--execute getCountryService 1, 'Spectra';
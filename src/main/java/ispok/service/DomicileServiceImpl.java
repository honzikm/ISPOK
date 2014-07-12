/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.service;

import ispok.bo.City;
import ispok.bo.Country;
import ispok.bo.Domicile;
import ispok.bo.PostalCode;
import ispok.bo.Region;
import ispok.dto.DomicileDto;
import java.util.List;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DomicileServiceImpl extends AbstractDataAccessService implements DomicileService {
    
    Logger logger = LogManager.getLogger();

    @Autowired
    private CityService cityService;

    @Override
    public void saveDomicile(DomicileDto domicileDto) {
        
        logger.entry();

        String address1 = domicileDto.getAddress1();

        List<Domicile> domicileList = genericDao.getByProperty("address1", address1, Domicile.class);
        
        

        logger.debug("Domicile list size: {}", domicileList.size());

        boolean createNew = true;
        Domicile domicile;

        Long cityId = domicileDto.getCityId();
        Long postalCodeId = domicileDto.getPostalCodeId();
        Long regionId = domicileDto.getRegionId();
        Long countryId = domicileDto.getCountryId();

        for (Domicile d : domicileList) {
            City city = d.getCity();
            PostalCode postalCode = d.getPostalCode();
            Region region = d.getRegion();
            Country country = d.getCountry();

            if ((!Objects.equals(city.getId(), cityId))
                    || (!Objects.equals(postalCode.getId(), postalCodeId))
                    || (!Objects.equals(region.getId(), regionId))
                    || (!Objects.equals(country.getId(), countryId))) {
                System.out.println("city: " + city.getId() + " , " + cityId);
                System.out.println("postal code: " + postalCode.getId() + " , " + postalCodeId);
                System.out.println("region: " + region.getId() + " , " + regionId);
                System.out.println("country: " + country.getId() + " , " + countryId);
            } else {
                logger.debug("Domicile exists");
                domicileDto.setId(d.getId());
                logger.exit();
                return;
//                createNew = false;
//                domicile = d;
//                break;
            }
        }

        domicile = new Domicile();
        domicile.setAddress1(address1);
        domicile.setCity(genericDao.getById(cityId, City.class));
        domicile.setPostalCode(genericDao.getById(postalCodeId, PostalCode.class));
        domicile.setRegion(genericDao.getById(regionId, Region.class));
        domicile.setCountry(genericDao.getById(countryId, Country.class));

        domicileDto.setId(genericDao.saveOrUpdate(domicile).getId());

        logger.exit();
    }

    @Override
    public DomicileDto getDomicileById(Long id) {
        Domicile domicile = genericDao.getById(id, Domicile.class);
        return getDomicileDto(domicile);
    }
    
    public DomicileDto getDomicileDto(Domicile domicile){
        DomicileDto domicileDto = new DomicileDto();
        domicileDto.setId(domicile.getId());
        domicileDto.setAddress1(domicile.getAddress1());
        domicileDto.setAddress2(domicile.getAddress2());
        domicileDto.setCityId(domicile.getCity().getId());
        domicileDto.setPostalCodeId(domicile.getPostalCode().getId());
        domicileDto.setRegionId(domicile.getRegion().getId());
        domicileDto.setCountryId(domicile.getCountry().getId());
        return domicileDto;
    }
}

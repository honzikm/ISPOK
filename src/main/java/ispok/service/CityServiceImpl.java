/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.service;

import ispok.bo.City;
import ispok.dto.CityDto;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CityServiceImpl extends AbstractDataAccessService implements CityService {

    @Override
    public void saveCity(CityDto cityDto) {

        List<City> cityList = genericDao.getByProperty("name", cityDto.getName(), City.class);

        if (cityList.isEmpty()) {
            City city = new City();
            city.setName(cityDto.getName());
            cityDto.setId(genericDao.saveOrUpdate(city).getId());
        } else {
            cityDto.setId(cityList.get(0).getId());
        }
    }

    @Override
    public CityDto getCityById(Long id) {
        City city = genericDao.getById(id, City.class);
        return getCityDto(city);
    }

    private CityDto getCityDto(City city) {
        CityDto cityDto = new CityDto();
        cityDto.setId(city.getId());
        cityDto.setName(city.getName());
        return cityDto;
    }

}

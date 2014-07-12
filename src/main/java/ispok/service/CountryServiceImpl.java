/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.service;

import ispok.bo.Country;
import ispok.dao.CountryDao;
import ispok.dto.CountryDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Component
public class CountryServiceImpl extends AbstractDataAccessService implements CountryService {

    @Autowired
    protected CountryDao countryDao;

    @Override
    public List<CountryDto> getAllCountries() {
        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

        List<Country> countries = countryDao.getAll(locale.getISO3Language());

        return getCountryDtos(countries);
    }

    private List<CountryDto> getCountryDtos(List<Country> countries) {
        List<CountryDto> countryDtos = new ArrayList<>(countries.size());
        for (Country country : countries) {
            countryDtos.add(getCountryDto(country));
        }
        return countryDtos;
    }

    private CountryDto getCountryDto(Country country) {
        CountryDto countryDto = new CountryDto();
        countryDto.setId(country.getId());
        countryDto.setAlpha2(country.getAlpha2());
        countryDto.setAlpha3(country.getAlpha3());
        countryDto.setName(country.getEng());
        return countryDto;
    }

    @Override
    public CountryDto getCountryById(Long id) {
        Country country = genericDao.getById(id, Country.class);
        return getCountryDto(country);
    }

//    private CountryDto getCountryDto(Country country) {
//        CountryDto countryDto = new CountryDto();
//        countryDto.setId(country.getId());
//        countryDto.setAlpha2(country.getAlpha2());
//        countryDto.setAlpha3(country.getAlpha3());
//        return countryDto;
//    }

}

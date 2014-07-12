/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.service;

import ispok.dto.CountryDto;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
public interface CountryService {

//        @Transactional(readOnly = true)
//    public OfficeDto getOfficeByName(String username);
    @Transactional(readOnly = true)
    public List<CountryDto> getAllCountries();

    @Transactional(readOnly = true)
    public CountryDto getCountryById(Long id);

}

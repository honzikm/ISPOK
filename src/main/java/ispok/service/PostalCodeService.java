/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.service;

import ispok.dto.PostalCodeDto;
import ispok.dto.VisitorDto;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
public interface PostalCodeService {

    @Transactional(readOnly = true)
    public boolean contains(String code);
//    public VisitorDto getPostalCode(String name);

    @Transactional
    public void savePostalCode(PostalCodeDto postalCodeDto);
    
    @Transactional
    public PostalCodeDto getPostalCodeById(Long id);

}

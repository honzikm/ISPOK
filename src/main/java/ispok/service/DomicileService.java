/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.service;

import ispok.dto.DomicileDto;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
public interface DomicileService {

    @Transactional
    public void saveDomicile(DomicileDto domicileDto);

    @Transactional
    public DomicileDto getDomicileById(Long id);
}

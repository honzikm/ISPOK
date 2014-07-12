/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.service;

import ispok.dto.RegionDto;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
public interface RegionService {

    @Transactional
    public void saveRegion(RegionDto regionDto);
    
    @Transactional
    public RegionDto getRegionById(Long id);

}

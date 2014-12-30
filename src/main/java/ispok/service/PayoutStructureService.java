/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.service;

import ispok.dto.PayoutPlaceDto;
import ispok.dto.PayoutStructureDto;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
public interface PayoutStructureService {

    @Transactional
    public List<PayoutStructureDto> getAll();

    @Transactional
    public PayoutStructureDto getStructureById(Long id);

    @Transactional
    public PayoutPlaceDto getPlaceById(Long l);

    @Transactional
    public void save(PayoutStructureDto payoutStructure, List<PayoutPlaceDto> payoutPlaces);

    @Transactional
    public void savePlace(PayoutPlaceDto payoutPlaceDto);

    @Transactional
    public void saveStructure(PayoutStructureDto payoutStructureDto);
    
    @Transactional
    public boolean deleteStructure(Long id);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.service;

import ispok.dto.OfficeDto;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jan
 */
@Transactional
public interface OfficeService {

    @Transactional(readOnly = true)
    public OfficeDto getOfficeByName(String username);

    @Transactional(readOnly = true)
    public List<OfficeDto> getAllOffices();

    @Transactional
    public Long addOffice(OfficeDto officeDto);

    @Transactional
    public void updateOffice(OfficeDto officeDto);

    @Transactional
    public void deleteOffice(Long id);
}

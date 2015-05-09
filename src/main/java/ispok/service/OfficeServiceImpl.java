/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.service;

import ispok.bo.Office;
import ispok.dto.OfficeDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jan
 */
@Component
public class OfficeServiceImpl extends AbstractDataAccessService implements OfficeService {

    @Override
    public OfficeDto getByName(String name) {
        Office office = genericDao.getByPropertyUnique("name", name, Office.class);
        OfficeDto officeDto = new OfficeDto(office);
        return officeDto;
    }

    @Override
    public List<OfficeDto> getAll() {
        List<Office> offices = genericDao.getAll(Office.class);
        List<OfficeDto> officeDtos = new ArrayList<>(offices.size());

        for (Office office : offices) {
            officeDtos.add(new OfficeDto(office));
        }
        return officeDtos;
    }

    @Override
    public Long addOffice(OfficeDto officeDto) {
        Office office = new Office(officeDto);
        Long id = genericDao.saveOrUpdate(office).getId();
        officeDto.setId(id);
        return id;
    }

    @Override
    public void deleteOffice(Long id) {
        genericDao.removeById(id, Office.class);
    }

    @Override
    public void updateOffice(OfficeDto officeDto) {
        Office office = genericDao.getById(officeDto.getId(), Office.class);
        office.setName(officeDto.getName());
        office.setInfo(officeDto.getInfo());
        genericDao.saveOrUpdate(office);
    }

    @Override
    public boolean officeExist(String name) {
        Office o = null;
        o = genericDao.getByPropertyUnique("name", name, Office.class);
        if (o == null) {
            return false;
        }
        return true;
    }
}

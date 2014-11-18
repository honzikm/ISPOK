/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.service;

import ispok.bo.Country;
import ispok.bo.Domicile;
import ispok.bo.Visitor;
import ispok.dao.VisitorDao;
import ispok.dto.VisitorDto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jan
 */
@Component
public class VisitorServiceImpl extends AbstractDataAccessService implements VisitorService {

    Logger logger = LogManager.getLogger();

    @Autowired
    private CountryService countryService;
    @Autowired
    private VisitorDao visitorDao;


    @Override
    public List<VisitorDto> getAllVisitors() {
        List<Visitor> visitors = genericDao.getAll(Visitor.class);
        List<VisitorDto> visitorDtos = new ArrayList<>(visitors.size());

        for (Visitor visitor : visitors) {
            visitorDtos.add(getVisitorDto(visitor));
        }
        return visitorDtos;
    }

    @Override
    public List<VisitorDto> getPage(int first, int pageSize, String sortField, boolean ascending, Map<String, Object> filters) {
        logger.entry();
        List<Visitor> visitors = visitorDao.getPage(first, pageSize, sortField, ascending, filters, Visitor.class);
        List<VisitorDto> visitorDtos = getVisitorDtos(visitors);
        logger.exit();
        return visitorDtos;

    }

    @Override
    public List<VisitorDto> getPage(int first, int rows, String sortBy, boolean ascending) {
//        List<Visitor> visitors = genericDao.getPage(first, rows, sortBy, ascending, Visitor.class);

        List<Visitor> visitors = visitorDao.getPage(first, rows, sortBy, ascending, null, Visitor.class);

        return getVisitorDtos(visitors);
    }

    @Override
    public VisitorDto getVisitorById(Long id) {
        Visitor visitor = genericDao.getById(id, Visitor.class);
        return getVisitorDto(visitor);
    }

    private List<VisitorDto> getVisitorDtos(List<Visitor> visitors) {
        logger.entry();

        List<VisitorDto> visitorDtos = new ArrayList<>(visitors.size());
        for (Visitor visitor : visitors) {
            visitorDtos.add(getVisitorDto(visitor));
        }

        logger.exit();
        return visitorDtos;
    }

    private VisitorDto getVisitorDto(Visitor visitor) {
        VisitorDto visitorDto = new VisitorDto();
        visitorDto.setId(visitor.getId());
        visitorDto.setFirstName(visitor.getFirstName());
        visitorDto.setLastName(visitor.getLastName());

        visitorDto.setBirthDate((Date) visitor.getBirthDate().clone());

        visitorDto.setNin(visitor.getNin());
        visitorDto.setNickname(visitor.getNickname());
        visitorDto.setTelephone(visitor.getTelephone());
        visitorDto.setEmail(visitor.getEmail());
        visitorDto.setSex(visitor.getSex());
        visitorDto.setPasswordHash(visitor.getPasswordHash());
        visitorDto.setSaltHash(visitor.getSaltHash());
        visitorDto.setIdNumber(visitor.getIdNumber());
        visitorDto.setBonusPoints(visitor.getBonusPoints());

        byte[] photoData = visitor.getPhoto();
        if (photoData != null) {
            visitorDto.setPhoto(visitor.getPhoto().clone());
        }

        visitorDto.setCitizenshipId(visitor.getCitizenship().getId());
        visitorDto.setDomicileId(visitor.getDomicile().getId());
        return visitorDto;
    }

    private Visitor getVisitor(VisitorDto visitorDto) {
        Visitor visitor = new Visitor();
        visitor.setFirstName(visitorDto.getFirstName());
        visitor.setLastName(visitorDto.getLastName());
        visitor.setBirthDate(visitorDto.getBirthDate());
        visitor.setNin(visitorDto.getNin());
        visitor.setNickname(visitorDto.getNickname());
        visitor.setTelephone(visitorDto.getTelephone());
        visitor.setEmail(visitorDto.getEmail());
        visitor.setSex(visitorDto.getSex());
        visitor.setBonusPoints(visitorDto.getBonusPoints());
        visitor.setPassword(visitorDto.getPassword());
        visitor.setPhoto(visitorDto.getPhoto().clone());

        Country country = genericDao.getById(visitorDto.getCitizenshipId(), Country.class);
        visitor.setCitizenship(country);
        Domicile domicile = genericDao.getById(visitorDto.getDomicileId(), Domicile.class);

        visitor.setDomicile(domicile);

        return visitor;
    }

    @Override
    public void addVisitor(VisitorDto visitorDto) {

        logger.entry();

        Visitor visitor = getVisitor(visitorDto);

        visitorDto.setId(genericDao.saveOrUpdate(visitor).getId());

        logger.exit();

    }

    @Override
    public void updateVisitor(VisitorDto visitorDto) {

        logger.entry();

        Visitor visitor = genericDao.getById(visitorDto.getId(), Visitor.class);

        Domicile domicile = genericDao.getById(visitorDto.getDomicileId(), Domicile.class);
        Country country = genericDao.getById(visitorDto.getCitizenshipId(), Country.class);

        visitor.setFirstName(visitorDto.getFirstName());
        visitor.setLastName(visitorDto.getLastName());
        visitor.setBirthDate(visitorDto.getBirthDate());
        visitor.setNin(visitorDto.getNin());
        visitor.setNickname(visitorDto.getNickname());
        visitor.setTelephone(visitorDto.getTelephone());
        visitor.setEmail(visitorDto.getEmail());
        visitor.setSex(visitorDto.getSex());
        visitor.setBonusPoints(visitorDto.getBonusPoints());
        visitor.setPassword(visitorDto.getPassword());
        visitor.setPhoto(visitorDto.getPhoto().clone());

        visitor.setDomicile(domicile);
        visitor.setCitizenship(country);

        genericDao.saveOrUpdate(visitor);

        logger.exit();
    }

    @Override
    public void deleteVisitor(Long id) {
        genericDao.removeById(id, Visitor.class);
    }

    @Override
    public Long getVisitorCount() {
        return visitorDao.getCount(Visitor.class);
    }

    @Override
    public Long getVisitorCount(Map<String, Object> filters) {
        return visitorDao.getCount(filters, Visitor.class);
    }

    @Override
    public VisitorDto getVisitorByName(String name) {

        Visitor v = genericDao.getByPropertyUnique("username", name, Visitor.class);
        if (v == null) {
            return null;
        }
        return getVisitorDto(v);
    }

    @Override
    public boolean emailAvailable(String email) {
        Visitor v = genericDao.getByPropertyUnique("email", email, Visitor.class);
        if(v == null){
            return true;
        }
        return false;
    }
}

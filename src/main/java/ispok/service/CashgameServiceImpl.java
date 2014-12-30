/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.service;

import ispok.bo.Cashgame;
import ispok.bo.Office;
import ispok.dao.CashgameDao;
import ispok.dto.CashgameDto;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Component
public class CashgameServiceImpl extends AbstractDataAccessService implements CashgameService {

    @Autowired
    private CashgameDao cashgameDao;

    @Autowired
    private OfficeService officeService;

    @Override
    public List<CashgameDto> getPage(int first, int pageSize, String sortField, boolean ascending, Map<String, Object> filters) {
        List<Cashgame> cashgames = cashgameDao.getPage(first, pageSize, sortField, ascending, filters);
        List<CashgameDto> cashgameDtos = getCashgameDtos(cashgames);
        return cashgameDtos;
    }

    @Override
    public Long getCount(Map<String, Object> filters) {
        return cashgameDao.getCount(filters);
    }

    private List<CashgameDto> getCashgameDtos(List<Cashgame> cashgames) {
        List<CashgameDto> cashgameDtos = new ArrayList<>(cashgames.size());
        for (Cashgame c : cashgames) {
            cashgameDtos.add(getCashgameDto(c));

        }
        return cashgameDtos;
    }

    private CashgameDto getCashgameDto(Cashgame cashgame) {
        CashgameDto cashgameDto = new CashgameDto();
        cashgameDto.setId(cashgame.getId());
        cashgameDto.setName(cashgame.getName());
        cashgameDto.setInfo(cashgame.getInfo());
        cashgameDto.setBigBlind(cashgame.getBigBlind());
        cashgameDto.setSmallBlind(cashgame.getBigBlind());
        cashgameDto.setSmallBlind(cashgame.getSmallBlind());
        cashgameDto.setAnte(cashgame.getAnte());
        cashgameDto.setMinBuyin(cashgame.getMinBuyin());
        cashgameDto.setMaxBuyin(cashgame.getMaxBuyin());
        cashgameDto.setRake(cashgame.getRake());
        cashgameDto.setMaxRake(cashgame.getMaxRake());
        cashgameDto.setPoints(cashgame.getPoints());
        cashgameDto.setOfficeId(cashgame.getOffice().getId());
        cashgameDto.setChillTime(cashgame.getChillTime());
        cashgameDto.setEnabled(cashgame.isEnabled());
        return cashgameDto;
    }

    @Override
    public void save(CashgameDto cashgameDto) {
        Office office = genericDao.getById(cashgameDto.getOfficeId(), Office.class);

        Cashgame cashgame = new Cashgame();
        cashgame.setId(cashgameDto.getId());
        cashgame.setName(cashgameDto.getName());
        cashgame.setInfo(cashgameDto.getInfo());
        cashgame.setOffice(office);
        cashgame.setBigBlind(cashgameDto.getBigBlind());
        cashgame.setSmallBlind(cashgameDto.getSmallBlind());
        cashgame.setAnte(cashgameDto.getAnte());
        cashgame.setMinBuyin(cashgameDto.getMinBuyin());
        cashgame.setMaxBuyin(cashgameDto.getMaxBuyin());
        cashgame.setRake(cashgameDto.getRake());
        cashgame.setMaxRake(cashgameDto.getMaxRake());
        cashgame.setPoints(cashgameDto.getPoints());
        cashgame.setChillTime(cashgameDto.getChillTime());
        cashgame.setEnabled(cashgameDto.isEnabled());
        genericDao.saveOrUpdate(cashgame);

        cashgameDto.setId(cashgame.getId());

    }

    @Override
    public void delete(Long id) {
        genericDao.removeById(id, Cashgame.class);
    }

    @Override
    public List<CashgameDto> getActiveByOfficeId(Long id) {
        List<Cashgame> cashgames = genericDao.getAll(Cashgame.class);
        List<CashgameDto> cashgameDtos = new LinkedList<>();
        for (Cashgame c : cashgames) {
            if (c.getOffice().getId().equals(id) && c.isEnabled()) {
                cashgameDtos.add(getCashgameDto(c));
            }
        }
        return cashgameDtos;
    }
}

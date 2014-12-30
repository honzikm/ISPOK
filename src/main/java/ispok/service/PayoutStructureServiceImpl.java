/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.service;

import ispok.bo.PayoutPlace;
import ispok.bo.PayoutStructure;
import ispok.bo.Tournament;
import ispok.dto.PayoutPlaceDto;
import ispok.dto.PayoutStructureDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Component
public class PayoutStructureServiceImpl extends AbstractDataAccessService implements PayoutStructureService {

    @Autowired
    TournamentService tournamentService;

    @Override
    public List<PayoutStructureDto> getAll() {
        List<PayoutStructure> payoutStructures = genericDao.getAll(PayoutStructure.class);
        List<PayoutStructureDto> payoutStructureDtos = new ArrayList<>(payoutStructures.size());
        for (PayoutStructure ps : payoutStructures) {
            PayoutStructureDto psd = new PayoutStructureDto();
            psd.setId(ps.getId());
            psd.setName(ps.getName());
            List<PayoutPlace> pps = ps.getPayoutPlaces();
            List<Long> ppIds = new ArrayList<>(pps.size());
            for (PayoutPlace pp : pps) {
                ppIds.add(pp.getId());
            }
            psd.setPayoutPlaceIds(ppIds);
            payoutStructureDtos.add(psd);
        }
        return payoutStructureDtos;
    }

    @Override
    public void save(PayoutStructureDto payoutStructureDto, List<PayoutPlaceDto> payoutPlaceDtos) {
        PayoutStructure ps = new PayoutStructure();
        ps.setId(payoutStructureDto.getId());
        ps.setName(payoutStructureDto.getName());
        ps.setPayoutPlaces(new ArrayList<>(20));

        List<PayoutPlace> pps = genericDao.getAll(PayoutPlace.class);

        PayoutPlace payoutPlace = null;
        for (PayoutPlaceDto placeDto : payoutPlaceDtos) {
            payoutPlace = null;
            for (PayoutPlace pp : pps) {
                if (pp.getPlace() == placeDto.getPlace() && pp.getPercent() == placeDto.getPercent()) {
                    payoutPlace = pp;
                    break;
                }
            }
            if (payoutPlace == null) {
                payoutPlace = new PayoutPlace();
                payoutPlace.setPercent(placeDto.getPercent());
                payoutPlace.setPlace(placeDto.getPlace());
                genericDao.saveOrUpdate(payoutPlace);
            }
            ps.getPayoutPlaces().add(payoutPlace);
        }

        payoutStructureDto.setId(genericDao.saveOrUpdate(ps).getId());
    }

    @Override
    public PayoutPlaceDto getPlaceById(Long l) {
        PayoutPlace pp = genericDao.getById(l, PayoutPlace.class);
        PayoutPlaceDto ppd = new PayoutPlaceDto();
        ppd.setId(pp.getId());
        ppd.setPlace(pp.getPlace());
        ppd.setPercent(pp.getPercent());
        return ppd;
    }

    @Override
    public void savePlace(PayoutPlaceDto payoutPlaceDto) {
        List<PayoutPlace> pps = genericDao.getByProperty("place", payoutPlaceDto.getPlace(), PayoutPlace.class);

        PayoutPlace payoutPlace = null;

        for (PayoutPlace pp : pps) {
            if (pp.getPlace() == payoutPlaceDto.getPlace() && pp.getPercent() == payoutPlaceDto.getPercent()) {
                payoutPlace = pp;
                break;
            }
        }
        if (payoutPlace == null) {
            payoutPlace = new PayoutPlace();
            payoutPlace.setPercent(payoutPlaceDto.getPercent());
            payoutPlace.setPlace(payoutPlaceDto.getPlace());
            genericDao.saveOrUpdate(payoutPlace);
        }
        payoutPlaceDto.setId(payoutPlace.getId());
    }

    @Override
    public void saveStructure(PayoutStructureDto payoutStructureDto) {
        PayoutStructure ps = new PayoutStructure();
        ps.setId(payoutStructureDto.getId());
        ps.setName(payoutStructureDto.getName());
        ps.setPayoutPlaces(new ArrayList<>(20));

        for (Long l : payoutStructureDto.getPayoutPlaceIds()) {
            PayoutPlace pp = genericDao.getById(l, PayoutPlace.class);
            ps.getPayoutPlaces().add(pp);
        }

        genericDao.saveOrUpdate(ps);
    }

    @Override
    public PayoutStructureDto getStructureById(Long id) {
        PayoutStructure ps = genericDao.getById(id, PayoutStructure.class);
        PayoutStructureDto psDto = new PayoutStructureDto();
        psDto.setId(ps.getId());
        psDto.setName(ps.getName());

        List<Long> idList = new ArrayList<>(ps.getPayoutPlaces().size());
        for (PayoutPlace pp : ps.getPayoutPlaces()) {
            idList.add(pp.getId());
        }
        psDto.setPayoutPlaceIds(idList);
        return psDto;
    }

    @Override
    public boolean deleteStructure(Long id) {
        PayoutStructure ps = genericDao.getById(id, PayoutStructure.class);
        if (ps.getTournaments().isEmpty()) {
            genericDao.remove(ps);
            return true;
        }
        return false;
    }
}

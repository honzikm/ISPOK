/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.pres.bb;

import ispok.dto.PayoutPlaceDto;
import ispok.dto.PayoutStructureDto;
import ispok.helper.FacesUtil;
import ispok.service.PayoutStructureService;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.SessionScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Component
@SessionScoped
public class PayoutStructureEdit {

    @Autowired
    private PayoutStructureService payoutStructureService;

    private List<PayoutStructureDto> payoutStructures;
    private Long payoutStructureId;
    private PayoutStructureDto payoutStructure = new PayoutStructureDto();
    private List<PayoutPlaceDto> payoutPlaces = new ArrayList<>();

    /**
     * Get the value of payoutPlaces
     *
     * @return the value of payoutPlaces
     */
    public List<PayoutPlaceDto> getPayoutPlaces() {
        return payoutPlaces;
    }

    /**
     * Set the value of payoutPlaces
     *
     * @param payoutPlaces new value of payoutPlaces
     */
    public void setPayoutPlaces(List<PayoutPlaceDto> payoutPlaces) {
        this.payoutPlaces = payoutPlaces;
    }

    /**
     * Get the value of payoutStructure
     *
     * @return the value of payoutStructure
     */
    public PayoutStructureDto getPayoutStructure() {
        return payoutStructure;
    }

    /**
     * Set the value of payoutStructure
     *
     * @param payoutStructure new value of payoutStructure
     */
    public void setPayoutStructure(PayoutStructureDto payoutStructure) {
        this.payoutStructure = payoutStructure;
    }

    /**
     * Get the value of payoutStructureId
     *
     * @return the value of payoutStructureId
     */
    public Long getPayoutStructureId() {
        return payoutStructureId;
    }

    /**
     * Set the value of payoutStructureId
     *
     * @param payoutStructureId new value of payoutStructureId
     */
    public void setPayoutStructureId(Long payoutStructureId) {
        this.payoutStructureId = payoutStructureId;
        payoutStructure = payoutStructureService.getStructureById(payoutStructureId);
        payoutPlaces.clear();
        for (Long id : payoutStructure.getPayoutPlaceIds()) {
            payoutPlaces.add(payoutStructureService.getPlaceById(id));
        }
    }

    public List<PayoutStructureDto> getStructures() {
        payoutStructures = payoutStructureService.getAll();
        if (payoutStructures != null && !payoutStructures.isEmpty()) {
            setPayoutStructureId(payoutStructures.get(0).getId());
        } else {
            payoutPlaces.clear();
        }
        return payoutStructures;
    }

    public List<PayoutPlaceDto> getPlaces() {
//        payoutPlaces.clear();
//        PayoutStructureDto ps = null;
//        for (PayoutStructureDto psd : payoutStructures) {
//            if (ps.getId().equals(payoutStructureId)) {
//                ps = psd;
//            }
//        }
//
//        if (ps != null) {
//            payoutPlaces = new ArrayList<>(ps.getPayoutPlaceIds().size());
//            for (Long l : ps.getPayoutPlaceIds()) {
//                payoutPlaces.add(payoutStructureService.getPlaceById(l));
//            }
//        }
        return payoutPlaces;
    }

    public String newStructure() {
        payoutStructure = new PayoutStructureDto();
        PayoutPlaceDto ppd = new PayoutPlaceDto();
        ppd.setPlace(1);
        ppd.setPercent(0);
        payoutPlaces.clear();
        payoutPlaces.add(ppd);
        return "/admin/management/tournaments/newPayoutStructure.xhtml";
    }

    public void addPlace(int placeNumber) {
        PayoutPlaceDto ppd = new PayoutPlaceDto();
        ppd.setPlace(placeNumber + 1);
        ppd.setPercent(payoutPlaces.get(placeNumber - 1).getPercent());
        payoutPlaces.add(placeNumber, ppd);
        for (int i = placeNumber + 1; i < payoutPlaces.size(); i++) {
            payoutPlaces.get(i).setPlace(i + 1);
        }
    }

    public void removePlace(int placeNumber) {
        payoutPlaces.remove(placeNumber - 1);
        if (payoutPlaces.isEmpty()) {
            PayoutPlaceDto payoutPlaceDto = new PayoutPlaceDto();
            payoutPlaceDto.setPlace(1);
            payoutPlaces.add(payoutPlaceDto);
        } else {
            for (int i = placeNumber - 1; i < payoutPlaces.size(); i++) {
                payoutPlaces.get(i).setPlace(i + 1);
            }
        }
    }

    public String add() {
        float sum = 0;
        for (PayoutPlaceDto ppd : payoutPlaces) {
            float percent = ppd.getPercent();
            if (percent >= 0) {
                sum += ppd.getPercent();
            } else {
                FacesUtil.addErrorMessage("failed", "payout_structure_negative_value");
                return null;
            }
        }
        if (sum > 100) {
            FacesUtil.addErrorMessage("failed", "payout_structure_100");
            return null;
        }

        PayoutStructureDto psd = new PayoutStructureDto();
        psd.setName(payoutStructure.getName());
        psd.setPayoutPlaceIds(new ArrayList<>(payoutPlaces.size()));
        for (PayoutPlaceDto ppd : payoutPlaces) {
            payoutStructureService.savePlace(ppd);
            psd.getPayoutPlaceIds().add(ppd.getId());
        }

        payoutStructureService.saveStructure(psd);

        return "/admin/management/tournaments/payoutStructures.xhtml";
    }

    public void delete() {
        if (payoutStructureService.deleteStructure(payoutStructureId)) {
            FacesUtil.addInfoMessage("success", "success");
        } else {
            FacesUtil.addErrorMessage("failed", "failed");
        }
    }

    public String cancel() {
        return "/admin/management/tournaments/payoutStructures.xhtml";
    }

    public void clear() {
        PayoutPlaceDto ppd = new PayoutPlaceDto();
        ppd.setPlace(1);
        ppd.setPercent(0);
        payoutPlaces.clear();
        payoutPlaces.add(ppd);
        payoutStructure.setName("");
    }
}

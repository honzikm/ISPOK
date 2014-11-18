/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.pres.bb;

import ispok.dto.OfficeDto;
import ispok.helper.FacesUtil;
import ispok.service.OfficeService;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Component
@SessionScoped
public class OfficeEdit implements Serializable {

    private OfficeDto office = new OfficeDto();

    private List<OfficeDto> offices;
    private List<OfficeDto> filteredOffices;
    private OfficeDto[] selected;

    @Autowired
    private OfficeService officeService;

    public OfficeDto getOffice() {
        return office;
    }

    public List<OfficeDto> getOffices() {
        if (offices == null) {
            offices = officeService.getAllOffices();
        }
        return offices;
    }

    public void setOffices(List<OfficeDto> offices) {
        this.offices = offices;
    }

    public List<OfficeDto> getFilteredOffices() {
        return filteredOffices;
    }

    public void setFilteredOffices(List<OfficeDto> filteredOffices) {
        this.filteredOffices = filteredOffices;
    }

    public OfficeDto[] getSelected() {
        return selected;
    }

    public void setSelected(OfficeDto[] selected) {
        this.selected = selected;
    }

    public void addOffice() {
        try {
            OfficeDto newOffice = new OfficeDto(office);
            newOffice.setId(officeService.addOffice(newOffice));
            offices.add(newOffice);
            if (filteredOffices != null) {
                filteredOffices.add(newOffice);
            }
        } catch (DataIntegrityViolationException dive) {
            FacesUtil.addMessage(new FacesMessage(FacesMessage.SEVERITY_FATAL, FacesUtil.getString("fail"), dive.toString()));
        }
        FacesUtil.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, FacesUtil.getString("success"), FacesUtil.getString("success")));
        clearEdit();
    }

    public void updateOffice() {
        OfficeDto officeDto = selected[0];
        officeDto.setName(office.getName());
        officeService.updateOffice(officeDto);
        FacesUtil.addInfoMessage("success", "office_update_success");
    }

    public void delete() {
        for (OfficeDto officeDto : selected) {
            officeService.deleteOffice(officeDto.getId());
            offices.remove(officeDto);
        }
        FacesUtil.addInfoMessage("success", "office_delete_success");
    }

    public void clearEdit() {
        office = new OfficeDto();
    }

    public void loadOffice() {
        if (selected.length == 0) {
            FacesUtil.addWarnMessage("warn", "no_item_selected");
            RequestContext.getCurrentInstance().addCallbackParam("showDialog", false);
            return;
        }
        office = selected[0];
        RequestContext.getCurrentInstance().addCallbackParam("showDialog", true);
    }
}

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

    private String name;

    private List<OfficeDto> offices;
    private List<OfficeDto> filteredOffices;

    private OfficeDto[] selected;

    @Autowired
    private OfficeService officeService;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        OfficeDto officeDto = new OfficeDto(name);

        FacesContext context = FacesContext.getCurrentInstance();

        try {
            officeService.addOffice(officeDto);
            offices.add(officeDto);
            if (filteredOffices != null) {
                filteredOffices.add(officeDto);
            }
        } catch (DataIntegrityViolationException dive) {
            FacesUtil.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, FacesUtil.getMessage("ispok/pres/inter/ispok", "fail"), dive.toString()));
//            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, bundle.getString("fail"), dive.toString()));
        }
        FacesUtil.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, FacesUtil.getMessage("ispok/pres/inter/ispok", "success"), FacesUtil.getMessage("ispok/pres/inter/ispok", "office_add_success")));
//        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("success"), bundle.getString("office_add_success")));
        clearEdit();
    }

    public void updateOffice() {

        OfficeDto officeDto = selected[0];

        officeDto.setName(name);

        officeService.updateOffice(officeDto);

        FacesUtil.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, FacesUtil.getMessage("ispok/pres/inter/ispok", "success"), FacesUtil.getMessage("ispok/pres/inter/ispok", "office_update_success")));

    }

    public void delete() {

        for (OfficeDto officeDto : selected) {
            officeService.deleteOffice(officeDto.getId());
            offices.remove(officeDto);
            if (filteredOffices != null && filteredOffices.contains(officeDto)) {
                filteredOffices.remove(officeDto);
            }
        }
        FacesUtil.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, FacesUtil.getMessage("ispok/pres/inter/ispok", "success"), FacesUtil.getMessage("ispok/pres/inter/ispok", "office_delete_success")));
//        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("success"), bundle.getString("office_delete_success")));
//        selected = new OfficeDto[0];
    }

    public void clearEdit() {
        name = null;
    }

    public void loadOffice() {
        name = selected[0].getName();
    }
}

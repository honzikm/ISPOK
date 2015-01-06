/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.pres.bb;

import ispok.dto.CashgameDto;
import ispok.helper.FacesUtil;
import ispok.service.CashgameService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Component
@SessionScoped
public class CashgameEdit {

    @Autowired
    private CashgameService cashgameService;

    @Autowired
    private LazyDataModel<CashgameDto> cashgameLazyDataModel;

    private CashgameDto cashgame;
    private CashgameDto selected;
    private List<CashgameDto> filtered;

    private boolean editing = false;

    @PostConstruct
    private void Init() {
        cashgame = new CashgameDto();
    }

    public boolean isEditing() {
        return editing;
    }

    public CashgameDto getCashgame() {
        return cashgame;
    }

    public void setCashgame(CashgameDto cashgame) {
        this.cashgame = cashgame;
    }

    public CashgameDto getSelected() {
        return selected;
    }

    public void setSelected(CashgameDto selected) {
        this.selected = selected;
    }

    public LazyDataModel<CashgameDto> getCashgameLazyDataModel() {
        return cashgameLazyDataModel;
    }

    public List<CashgameDto> getFiltered() {
        return filtered;
    }

    public void newCg() {
        cashgame = new CashgameDto();
        cashgame.setName("");
        editing = false;
    }

    public void editCg() {
        if (selected != null) {
            cashgame = new CashgameDto(selected);
            editing = true;
            RequestContext.getCurrentInstance().addCallbackParam("showDialog", true);
        } else {
            FacesUtil.addWarnMessage("warn", "no_item_selected");
            RequestContext.getCurrentInstance().addCallbackParam("showDialog", false);
        }
    }

    public void save() {
        CashgameDto newCashgameDto = new CashgameDto(cashgame);
        cashgameService.save(newCashgameDto);
        if (editing) {
            FacesUtil.addInfoMessage("success", "cashgame_update_success");
        } else {
            FacesUtil.addInfoMessage("success", "cashgame_add_success");
        }
    }

    public void delete() {
        try {
            cashgameService.delete(selected.getId());
        } catch (DataIntegrityViolationException e) {
            FacesUtil.addWarnMessage("warn", "cashgame_delete_invalid");
            return;
        }
        FacesUtil.addInfoMessage("success", "cashgame_delete_success");
    }

    public void clear() {
        cashgame = new CashgameDto();
    }

    public String getDialogHeader() {
        if (editing) {
            return FacesUtil.getString("cashgame_edit");
        }
        return FacesUtil.getString("cashgame_new");
    }

    public String getFirstButtonText() {
        if (editing) {
            return FacesUtil.getString("save");
        }
        return FacesUtil.getString("add");
    }
}

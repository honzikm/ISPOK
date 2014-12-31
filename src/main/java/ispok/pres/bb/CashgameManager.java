/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.pres.bb;

import ispok.dto.CashgameDto;
import ispok.dto.CashgameSessionDto;
import ispok.dto.OfficeDto;
import ispok.dto.VisitorDto;
import ispok.helper.FacesUtil;
import ispok.service.CashgameService;
import ispok.service.CashgameSessionService;
import ispok.service.OfficeService;
import ispok.service.VisitorService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Component
@SessionScoped
public class CashgameManager {

    @Autowired
    private OfficeService officeService;
    @Autowired
    private CashgameService cashgameService;
    @Autowired
    private CashgameSessionService cashgameSessionService;
    @Autowired
    private VisitorService visitorService;

    private List<CashgameDto> cashgames;

//    private List<VisitorDto> activeVisitors;
//    private OfficeDto office;
    private CashgameDto cashgame;
    private Long officeId;
    private Long cashgameId;
    private List<CashgameSessionDto> cashgameSessions;
    private List<VisitorDto> players;

    private String autoCompleteText;

    @PostConstruct
    void init() {
        List<OfficeDto> officeDtos = getAllOffices();
        setOfficeId(officeDtos.get(0).getId());
        cashgames = getCashgames();
    }

    public String getAutoCompleteText() {
        return autoCompleteText;
    }

    public void setAutoCompleteText(String autoCompleteText) {
        this.autoCompleteText = autoCompleteText;
    }

    public Long getCashgameId() {
        return cashgameId;
    }

    public void setCashgameId(Long cashgameId) {
        for (CashgameDto c : cashgames) {
            if (cashgameId.equals(c.getId())) {
                cashgame = c;
                cashgameSessions = cashgameSessionService.getActiveSessionsByCashgameId(cashgame.getId());
                players = new ArrayList<>(cashgameSessions.size());
                for (CashgameSessionDto csd : cashgameSessions) {
                    players.add(visitorService.getVisitorById(csd.getVisitorId()));
                }
                break;
            }
        }
        this.cashgameId = cashgameId;
    }

    /**
     * Get the value of officeId
     *
     * @return the value of officeId
     */
    public Long getOfficeId() {
        return officeId;
    }

    public CashgameDto getCashgame() {
        return cashgame;
    }

    /**
     * Set the value of officeId
     *
     * @param officeId new value of officeId
     */
    public void setOfficeId(Long officeId) {
        cashgames = cashgameService.getActiveByOfficeId(officeId);
        if (cashgames != null && cashgames.size() > 0) {
            setCashgameId(cashgames.get(0).getId());
        }
        this.officeId = officeId;
    }

    public List<OfficeDto> getAllOffices() {
        return officeService.getAll();
    }

    public List<CashgameDto> getCashgames() {

        return cashgames;
//        if (officeId == null) {
//            return null;
//        }
//        return cashgameService.getActiveByOfficeId(officeId);
    }

    public List<VisitorDto> getPlayers() {
        if (cashgameId != null) {
            return players;
        }
        return null;
    }

    public void addVisitor(Long id) {
        for (CashgameSessionDto csd : cashgameSessions) {
            if (csd.getVisitorId().equals(id)) {
                FacesUtil.addWarnMessage("failed", "cashgame_player_already_in");
                RequestContext.getCurrentInstance().update("growl");
                return;
            }
        }
        CashgameSessionDto csd = new CashgameSessionDto();
        csd.setCashgameId(cashgame.getId());
        csd.setVisitorId(id);
        csd.setStart(new Date());
        cashgameSessionService.save(csd);
        cashgameSessions.add(csd);
        players.add(visitorService.getVisitorById(id));
        FacesUtil.addInfoMessage("success", "player_added");
        RequestContext.getCurrentInstance().update("activeVisitorsTableId");
        RequestContext.getCurrentInstance().update("growl");
    }

    public void removeVisitor(Long id) {
        CashgameSessionDto csdto = null;
        for (CashgameSessionDto csd : cashgameSessions) {
            if (csd.getVisitorId().equals(id)) {
                csdto = csd;
                break;
            }
        }
        if (csdto != null) {
            csdto.setStop(new Date());
            cashgameSessionService.save(csdto);
            cashgameSessions.remove(csdto);
            for (VisitorDto v : players) {
                if (v.getId().equals(csdto.getVisitorId())) {
                    players.remove(v);
                    break;
                }
            }
        }
        FacesUtil.addInfoMessage("success", "cashgame_player_removed");
        RequestContext.getCurrentInstance().update("activeVisitorsTableId");
        RequestContext.getCurrentInstance().update("growl");
    }
}

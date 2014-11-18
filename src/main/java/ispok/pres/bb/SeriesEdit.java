/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.pres.bb;

import ispok.dto.SeriesDto;
import ispok.helper.FacesUtil;
import ispok.service.SeriesService;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Component
@SessionScoped
public class SeriesEdit {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private SeriesService seriesService;

    private SeriesDto selectedSeries;
    private List<SeriesDto> filteredSeries;

    @Autowired
    private LazyDataModel<SeriesDto> seriesLazyDataModel;

    private String name;
    private ResourceBundle rb;

    @PostConstruct
    private void init() {
        rb = ResourceBundle.getBundle("ispok/pres/inter/ispok", FacesContext.getCurrentInstance().getViewRoot().getLocale());
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the value of filteredSeries
     *
     * @return the value of filteredSeries
     */
    public List<SeriesDto> getFilteredSeries() {
        return filteredSeries;
    }

    /**
     * Set the value of filteredSeries
     *
     * @param filteredSeries new value of filteredSeries
     */
    public void setFilteredSeries(List<SeriesDto> filteredSeries) {
        this.filteredSeries = filteredSeries;
    }

    /**
     * Get the value of selectedSeries
     *
     * @return the value of selectedSeries
     */
    public SeriesDto getSelectedSeries() {
        return selectedSeries;
    }

    /**
     * Set the value of selectedSeries
     *
     * @param selectedSeries new value of selectedSeries
     */
    public void setSelectedSeries(SeriesDto selectedSeries) {
        this.selectedSeries = selectedSeries;
    }

    /**
     *
     * @return
     */
    public LazyDataModel<SeriesDto> getSeriesLazyDataModel() {
        return seriesLazyDataModel;
    }

    public void clear() {
        name = null;
    }

    public void add() {
//        ResourceBundle rb = ResourceBundle.getBundle("ispok/pres/inter/ispok", facesContext.getViewRoot().getLocale());
        SeriesDto seriesDto = new SeriesDto(name);
        seriesService.add(seriesDto);
        FacesUtil.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, rb.getString("success"), rb.getString("series_added")));
    }

    /**
     *
     */
    public void delete() {
        if (selectedSeries == null) {
            FacesUtil.addMessage(new FacesMessage(FacesMessage.SEVERITY_WARN, rb.getString("warn"), rb.getString("no_item_selected")));
            return;
        }
        seriesService.remove(selectedSeries.getId());
        FacesUtil.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, rb.getString("success"), rb.getString("series") + " \"" + selectedSeries.getName() + "\" " + rb.getString("series_delete_success")));
    }
}

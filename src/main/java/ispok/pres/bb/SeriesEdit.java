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
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
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

    private SeriesDto series = new SeriesDto();

    public SeriesDto getSeries() {
        return series;
    }

    public void setSeries(SeriesDto series) {
        this.series = series;
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
        series = new SeriesDto();
    }

    public void add() {
        SeriesDto seriesDto = new SeriesDto(series);
        seriesService.add(seriesDto);
        FacesUtil.addInfoMessage("success", "series_added");
    }

    public void loadSelected() {
        if (selectedSeries == null) {
            FacesUtil.addWarnMessage("warn", "no_item_selected");
            return;
        }
        series = new SeriesDto(selectedSeries);
    }

    public void update() {
        seriesService.save(series);
    }

    /**
     *
     */
    public void delete() {
        if (selectedSeries == null) {
            FacesUtil.addWarnMessage("warn", "no_item_selected");
            return;
        }
        seriesService.remove(selectedSeries.getId());
        FacesUtil.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, FacesUtil.getString("success"), FacesUtil.getString("series") + " \"" + selectedSeries.getName() + "\" " + FacesUtil.getString("series_delete_success")));
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.helper;

import ispok.dto.SeriesDto;
import ispok.service.SeriesService;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */

@Component(value = "seriesLazyDataModel")
public class SeriesLazyDataModel extends LazyDataModel<SeriesDto> {

    private static final Logger logger = LogManager.getLogger();


    @Autowired
    private SeriesService seriesService;

    @Override
    public SeriesDto getRowData(String rowKey) {
        return seriesService.getSeriesById(Long.parseLong(rowKey));
    }

    @Override
    public Object getRowKey(SeriesDto seriesDto) {
        return seriesDto.getId();
    }

    @Override
    public List<SeriesDto> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

        boolean ascending;

        if (sortOrder.ordinal() == 0) {
            ascending = true;
        } else {
            ascending = false;
        }

        if (sortField == null) {
            sortField = "id";
        }

        List<SeriesDto> seriesDtos = seriesService.getPage(first, pageSize, sortField, ascending, filters);

        int matchesCount = seriesService.getCount(filters).intValue();

        this.setRowCount(matchesCount);
        return seriesDtos;
    }

}

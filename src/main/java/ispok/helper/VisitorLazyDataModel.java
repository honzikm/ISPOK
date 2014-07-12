/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.helper;

import ispok.dto.VisitorDto;
import ispok.service.VisitorService;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import static org.primefaces.model.SortOrder.ASCENDING;

public class VisitorLazyDataModel extends LazyDataModel<VisitorDto> {

    private static Logger logger = LogManager.getLogger();

    private VisitorService visitorService;

    public VisitorLazyDataModel(VisitorService visitorService) {
        logger.debug("Visitor service: {}", visitorService);
        this.visitorService = visitorService;
    }

    @Override
    public VisitorDto getRowData(String rowKey) {
        return visitorService.getVisitorById(Long.parseLong(rowKey));
    }

    @Override
    public Object getRowKey(VisitorDto visitorDto) {
        return visitorDto.getId();
    }

    @Override
    public List<VisitorDto> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

        boolean ascending;
        switch (sortOrder) {
            case ASCENDING:
                ascending = true;
                break;
            case DESCENDING:
                ascending = false;
                break;
            default:
                ascending = false;
                break;
        }

        logger.debug("Sort order: {}, {}", sortOrder, sortOrder.ordinal());
        logger.debug("Sort field: {}", sortField);
        logger.debug("Filters: " + Arrays.toString(filters.entrySet().toArray()));

        if (sortField == null) {
            sortField = "id";
        }

//        List<VisitorDto> visitorDtos = visitorService.getPage(first, pageSize, sortField, ascending);
        List<VisitorDto> visitorDtos1 = visitorService.getPage(first, pageSize, sortField, ascending, filters);
        int matchesCount = (visitorService.getVisitorCount(filters)).intValue();

        logger.debug("Matches found: {}", matchesCount);

        this.setRowCount(matchesCount);
        return visitorDtos1;

    }

}

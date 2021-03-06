/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.helper;

import ispok.dto.TournamentDto;
import ispok.service.TournamentService;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jan
 */

@Component(value = "tournamentLazyDataModel")
public class TournamentLazyDataModel extends LazyDataModel<TournamentDto> {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private TournamentService tournamentService;

    @Override
    public Object getRowKey(TournamentDto tournamentDto) {
        return tournamentDto.getId();
    }

    @Override
    public List<TournamentDto> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

        boolean ascending;

        if (sortOrder.ordinal() == 0) {
            ascending = true;
        } else {
            ascending = false;
        }

        if (sortField == null) {
            sortField = "id";
        }

        logger.debug("Sort order: {}, {}", sortOrder, sortOrder.ordinal());
        logger.debug("Sort field: {}", sortField);
        logger.debug("Filters: " + Arrays.toString(filters.entrySet().toArray()));

        List<TournamentDto> tournamentDtos = tournamentService.getPage(first, pageSize, sortField, ascending, filters);

        int matchesCount = tournamentService.getCount(filters).intValue();

        logger.debug("Matches found: {}", matchesCount);

        this.setRowCount(matchesCount);
        return tournamentDtos;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.helper;

import ispok.dto.CashgameDto;
import ispok.service.CashgameService;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Component(value = "cashgameLazyDataModel")
public class CashgameLazyDataModel extends LazyDataModel<CashgameDto> {

    @Autowired
    private CashgameService cashgameService;

    @Override
    public Object getRowKey(CashgameDto cashgameDto) {
        return cashgameDto.getId();
    }

    @Override
    public List<CashgameDto> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        boolean ascending = false;

        if (sortOrder == sortOrder.ASCENDING) {
            ascending = true;
        }

        if (sortField == null) {
            sortField = "id";
        }

        List<CashgameDto> cashgameDtos = cashgameService.getPage(first, pageSize, sortField, ascending, filters);

        int matchesCount = cashgameService.getCount(filters).intValue();

        this.setRowCount(matchesCount);
        return cashgameDtos;
    }

}

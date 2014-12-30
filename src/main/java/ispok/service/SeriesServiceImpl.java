/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.service;

import ispok.bo.Series;
import ispok.dao.GenericDao;
import ispok.dto.SeriesDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Component
public class SeriesServiceImpl implements SeriesService {

    @Autowired
    private GenericDao genericDao;

    @Override
    public List<SeriesDto> getPage(int first, int pageSize, String sortField, boolean ascending, Map<String, Object> filters) {
        List<Series> series = genericDao.getPage(first, pageSize, sortField, ascending, filters, Series.class);
        List<SeriesDto> seriesDtos = new ArrayList<>(series.size());
        for (Series s : series) {
            seriesDtos.add(getSeriesDto(s));
        }
        return seriesDtos;
    }

    @Override
    public Long getCount(Map<String, Object> filters) {
        return genericDao.getCount(filters, Series.class);
    }

    private SeriesDto getSeriesDto(Series s) {
        SeriesDto seriesDto = new SeriesDto(s.getName());
        seriesDto.setId(s.getId());
        seriesDto.setInfo(s.getInfo());
        return seriesDto;
    }

    @Override
    public SeriesDto getSeriesById(Long id) {
        Series s = genericDao.getById(id, Series.class);
        return getSeriesDto(s);
    }

    @Override
    public boolean nameExists(String name) {
        List<Series> series = genericDao.getByProperty("name", name, Series.class);
        if (series.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public void add(SeriesDto seriesDto) {
        Series series = new Series();
        series.setName(seriesDto.getName());
        series.setInfo(seriesDto.getInfo());
        Long id = genericDao.saveOrUpdate(series).getId();
        seriesDto.setId(id);
    }

    @Override
    public void remove(Long id) {
        genericDao.removeById(id, Series.class);
    }

    @Override
    public SeriesDto getByName(String name) {
        Series series = genericDao.getByPropertyUnique("name", name, Series.class);
        if (series == null) {
            return null;
        }
        return getSeriesDto(series);
    }

    @Override
    public void save(SeriesDto seriesDto) {
        Series series = genericDao.getById(seriesDto.getId(), Series.class);
        if (series == null) {
            series = new Series();
        }
        series.setName(seriesDto.getName());
        series.setInfo(seriesDto.getInfo());
        seriesDto.setId(genericDao.saveOrUpdate(series).getId());
    }

    @Override
    public List<SeriesDto> getAll() {
        List<Series> serieses = genericDao.getAll(Series.class);
        List<SeriesDto> seriesDtos = new ArrayList<>(serieses.size());
        for (Series s : serieses) {
            seriesDtos.add(getSeriesDto(s));
        }
        return seriesDtos;
    }
}

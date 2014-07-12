/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.service;

import ispok.bo.Region;
import ispok.dto.RegionDto;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Component
public class RegionServiceImpl extends AbstractDataAccessService implements RegionService {

    @Override
    public void saveRegion(RegionDto regionDto) {

        String regionName = regionDto.getName();

        List<Region> regionList = genericDao.getByProperty("name", regionName, Region.class);

        if (regionList.isEmpty()) {
            Region region = new Region();
            region.setName(regionName);
            regionDto.setId(genericDao.saveOrUpdate(region).getId());
        } else {
            regionDto.setId(regionList.get(0).getId());
        }
    }

    @Override
    public RegionDto getRegionById(Long id) {
        Region region = genericDao.getById(id, Region.class);
        return getRegionDto(region);
    }

    private RegionDto getRegionDto(Region region) {
        RegionDto regionDto = new RegionDto();
        regionDto.setId(region.getId());
        regionDto.setName(region.getName());
        return regionDto;
    }
}

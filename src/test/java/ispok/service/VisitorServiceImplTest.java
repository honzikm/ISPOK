/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.service;

import ispok.dto.CityDto;
import ispok.dto.DomicileDto;
import ispok.dto.PostalCodeDto;
import ispok.dto.RegionDto;
import ispok.dto.VisitorDto;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
public class VisitorServiceImplTest extends AbstractServiceTest {

    private static final Logger logger = LogManager.getLogger();

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Autowired
    private VisitorService visitorService;
    @Autowired
    private CityService cityService;
    @Autowired
    private PostalCodeService postalCodeService;
    @Autowired
    private RegionService regionService;
    @Autowired
    private DomicileService domicileService;

    public VisitorServiceImplTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of visit method, of class VisitorServiceImpl.
     */
    @Test
    public void testVisitorVisit() {

        CityDto cityDto = new CityDto();
        cityDto.setName("city");

        cityService.saveCity(cityDto);

        assertNotEquals((long) 0, (long) cityDto.getId());

        PostalCodeDto postalCodeDto = new PostalCodeDto("postalCode");

        postalCodeService.savePostalCode(postalCodeDto);

        assertNotEquals((long) 0, (long) postalCodeDto.getId());

        RegionDto regionDto = new RegionDto("region");

        regionService.saveRegion(regionDto);

        assertNotEquals((long) 0, (long) regionDto.getId());

        DomicileDto domicileDto = new DomicileDto();
        domicileDto.setAddress1("Addr1");
        domicileDto.setAddress2("Addr2");
        domicileDto.setCityId(cityDto.getId());
        domicileDto.setCountryId((long) 4);
        domicileDto.setPostalCodeId(postalCodeDto.getId());
        domicileDto.setRegionId(regionDto.getId());

        domicileService.saveDomicile(domicileDto);

        assertNotEquals((long) 0, (long) domicileDto.getId());

        VisitorDto visitorDto = new VisitorDto();
        visitorDto.setFirstName("Jan");
        visitorDto.setLastName("Mucha");
        visitorDto.setEmail("j.mucha@seznam.cz");
        visitorDto.setBirthDate(new Date());
        visitorDto.setCitizenshipId((long) 4);
        visitorDto.setTelephone("724941933");
        visitorDto.setSex("M");
        visitorDto.setDomicileId(domicileDto.getPostalCodeId());
        visitorDto.setBonusPoints(10);
        visitorDto.setNin("nin");
        visitorDto.setPassword("pass");
        visitorDto.setPasswordHash("passhash");
        visitorDto.setSaltHash("salthash");

        visitorService.addVisitor(visitorDto);

        assertNotEquals((long) 0, (long) visitorDto.getId());

        if (!visitorService.visit(visitorDto.getId(), new Date())) {
            fail("visit not added");
        }

    }

}

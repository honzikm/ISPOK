/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.pres.bb;

import ispok.dto.DomicileDto;
import ispok.dto.VisitorDto;
import ispok.service.CityService;
import ispok.service.CountryService;
import ispok.service.DomicileService;
import ispok.service.PostalCodeService;
import ispok.service.RegionService;
import ispok.service.VisitorService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PreDestroy;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Component
@SessionScoped

public class VisitorProfile {

    @Autowired
    private VisitorService visitorService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private DomicileService domicileService;
    @Autowired
    private CityService cityService;
    @Autowired
    private PostalCodeService postalCodeService;
    @Autowired
    private RegionService regionService;

    private VisitorDto visitor;
    private String citizenship;
    private DomicileDto domicile;

    private String city;
    private String postalCode;
    private String region;

    private String photoPath;
    private String photoName;

    public void init() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String visitorName = authentication.getName();
            visitor = visitorService.getVisitorByEmail(visitorName);
            citizenship = countryService.getCountryById(visitor.getCitizenshipId()).getName();
            domicile = domicileService.getDomicileById(visitor.getDomicileId());

            city = cityService.getCityById(domicile.getCityId()).getName();
            postalCode = postalCodeService.getPostalCodeById(domicile.getPostalCodeId()).getCode();
            region = regionService.getRegionById(domicile.getRegionId()).getName();

            byte[] photo = visitor.getPhoto();

            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/visitor/");
            String photoName = visitorName + Math.random() + ".jpg";
            photoPath = path + photoName;

            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(photoPath);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(VisitorProfile.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                fos.write(photo);
            } catch (IOException ex) {
                Logger.getLogger(VisitorProfile.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fos.close();
                } catch (IOException ex) {
                    Logger.getLogger(VisitorProfile.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @PreDestroy
    public void preDestroy() {
//        try {
        File f = new File(photoPath);
        f.delete();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
    }

    public VisitorDto getVisitor() {
        return visitor;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public DomicileDto getDomicile() {
        return domicile;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getRegion() {
        return region;
    }

    public String getPhoto() {
        return "/visitor/" + photoPath;
    }

}

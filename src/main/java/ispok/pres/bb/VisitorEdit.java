/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.pres.bb;

import ispok.dto.CashgameSessionDto;
import ispok.dto.CityDto;
import ispok.dto.CountryDto;
import ispok.dto.DomicileDto;
import ispok.dto.PostalCodeDto;
import ispok.dto.RegionDto;
import ispok.dto.TournamentSessionDto;
import ispok.dto.VisitDto;
import ispok.dto.VisitorDto;
import ispok.helper.FacesUtil;
import ispok.helper.ImageUtil;
import ispok.service.CashgameSessionService;
import ispok.service.CityService;
import ispok.service.CountryService;
import ispok.service.DomicileService;
import ispok.service.PostalCodeService;
import ispok.service.RegionService;
import ispok.service.TournamentSessionService;
import ispok.service.VisitorService;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.NativeUploadedFile;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@SessionScoped
public class VisitorEdit {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private VisitorService visitorService;
    @Autowired
    private DomicileService domicileService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private RegionService regionService;
    @Autowired
    private PostalCodeService postalCodeService;
    @Autowired
    private CityService cityService;
    @Autowired
    private LazyDataModel<VisitorDto> visitorLazyModel;
    @Autowired
    private CashgameSessionService cashgameSessionService;
    @Autowired
    private TournamentSessionService tournamentSessionService;

    private List<VisitorDto> selected;
    private VisitorDto selectedVisitor;
    private VisitorDto visitorDto;
    private DomicileDto domicileDto;
    private CityDto cityDto;
    private PostalCodeDto postalCodeDto;
    private RegionDto regionDto;
    private CountryDto countryDto;
    private CountryDto citizenshipDto;
    private List<VisitorDto> filteredVisitors;

    private boolean foreigner;
    private boolean foreignerNewVal;
    private NativeUploadedFile photoFile;

    private List<VisitDto> visitDtos;
    private List<CashgameSessionDto> cashgameSessionDtos;
    private List<TournamentSessionDto> tournamentSessionDtos;

    @PostConstruct
    public void init() {
//        visitorLazyModel = new VisitorLazyDataModel(visitorService);
    }

    private boolean loadVisitorDetails() {
        if (selectedVisitor == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            ResourceBundle bundle = ResourceBundle.getBundle("ispok/pres/inter/ispok", context.getViewRoot().getLocale());
            FacesUtil.addMessage(new FacesMessage(FacesMessage.SEVERITY_WARN, bundle.getString("warn"), bundle.getString("no_item_selected")));
            RequestContext.getCurrentInstance().addCallbackParam("showDialog", false);
            return false;
        }

        visitorDto = visitorService.getVisitorById(selectedVisitor.getId());
        domicileDto = domicileService.getDomicileById(visitorDto.getDomicileId());
        cityDto = cityService.getCityById(domicileDto.getCityId());
        postalCodeDto = postalCodeService.getPostalCodeById(domicileDto.getPostalCodeId());
        regionDto = regionService.getRegionById(domicileDto.getRegionId());
        countryDto = countryService.getCountryById(domicileDto.getCountryId());
        citizenshipDto = countryService.getCountryById(visitorDto.getCitizenshipId());
        if (Objects.equals(countryDto.getId(), citizenshipDto.getId())) {
            foreigner = false;
        } else {
            foreigner = true;
        }
        foreignerNewVal = foreigner;

        visitDtos = null;
        cashgameSessionDtos = null;
        tournamentSessionDtos = null;

        RequestContext.getCurrentInstance().addCallbackParam("showDialog", true);
        return true;
    }

    public String cancel() {
        logger.entry();
        logger.exit();
        return "/admin/management/visitors/visitors.xhtml";
    }

    public String back() {
        return "/admin/management/visitors/edit.xhtml";
    }

    public void reset() {
        foreignerNewVal = foreigner;
    }

    public String detail() {
        if (!loadVisitorDetails()) {
            return null;
        }
        return "/admin/management/visitors/visitor.xhtml";
    }

    public String edit() {
        if (loadVisitorDetails()) {
            return "/admin/management/visitors/edit.xhtml";
        } else {

        }
        return null;
    }

    public void delete() {
        visitorService.deleteVisitor(selectedVisitor.getId());

        FacesContext fc = FacesContext.getCurrentInstance();
        Locale l = fc.getViewRoot().getLocale();
        ResourceBundle rb = ResourceBundle.getBundle("ispok/pres/inter/ispok", l);
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, rb.getString("visitor_delete_success"), selectedVisitor.getFirstName() + " " + selectedVisitor.getLastName()));

    }

    public String updateSummary() {

        countryDto = countryService.getCountryById(countryDto.getId());
        citizenshipDto = countryService.getCountryById(citizenshipDto.getId());

        BufferedImage originalPhotoImage = null;
        if (photoFile.getSize() > 0) {
            try {
                originalPhotoImage = ImageIO.read(photoFile.getInputstream());

            } catch (IOException ex) {
                logger.catching(ex);
            }

            visitorDto.setPhoto(ImageUtil.getResizedImage(originalPhotoImage, 500, 500));

        }
        return "/admin/management/visitors/confirmEdit.xhtml";
    }

    public String update() {

        logger.entry();

        cityService.saveCity(cityDto);
        postalCodeService.savePostalCode(postalCodeDto);
        regionService.saveRegion(regionDto);

        domicileDto.setCityId(cityDto.getId());
        domicileDto.setPostalCodeId(postalCodeDto.getId());
        domicileDto.setCountryId(countryDto.getId());
        domicileDto.setRegionId(regionDto.getId());
        domicileService.saveDomicile(domicileDto);

        visitorDto.setDomicileId(domicileDto.getId());
        if (foreignerNewVal == false) {
            visitorDto.setCitizenshipId(countryDto.getId());
        } else {
            visitorDto.setCitizenshipId(citizenshipDto.getId());
        }

        logger.trace("Update visitor");
        visitorService.updateVisitor(visitorDto);
        logger.trace("Update visitor finish");

        FacesContext fc = FacesContext.getCurrentInstance();
        Locale l = fc.getViewRoot().getLocale();
        ResourceBundle rb = ResourceBundle.getBundle("ispok/pres/inter/ispok", l);
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, rb.getString("visitor_edit_success"), selectedVisitor.getFirstName() + " " + selectedVisitor.getLastName()));

        logger.exit();

        return "/admin/management/visitors/visitors.xhtml";
    }

    public List<VisitorDto> getFilteredVisitors() {
        return filteredVisitors;
    }

    public void setFilteredVisitors(List<VisitorDto> filteredVisitors) {
        this.filteredVisitors = filteredVisitors;
    }

    public LazyDataModel<VisitorDto> getVisitorLazyModel() {
        return visitorLazyModel;
    }

    public List<VisitorDto> getSelected() {
        return selected;
    }

    public void setSelected(List<VisitorDto> selected) {
        this.selected = selected;
    }

    public VisitorDto getSelectedVisitor() {
        return selectedVisitor;
    }

    public void setSelectedVisitor(VisitorDto selectedVisitor) {
        this.selectedVisitor = selectedVisitor;
    }

    public DomicileDto getDomicileDto() {
        return domicileDto;
    }

    public void setDomicileDto(DomicileDto domicileDto) {
        this.domicileDto = domicileDto;
    }

    public CityDto getCityDto() {
        return cityDto;
    }

    public void setCityDto(CityDto cityDto) {
        this.cityDto = cityDto;
    }

    public PostalCodeDto getPostalCodeDto() {
        return postalCodeDto;
    }

    public void setPostalCodeDto(PostalCodeDto postalCodeDto) {
        this.postalCodeDto = postalCodeDto;
    }

    public RegionDto getRegionDto() {
        return regionDto;
    }

    public void setRegionDto(RegionDto regionDto) {
        this.regionDto = regionDto;
    }

    public CountryDto getCountyDto() {
        return countryDto;
    }

    public void setCountyDto(CountryDto countyDto) {
        this.countryDto = countyDto;
    }

    public CountryDto getCitizenshipDto() {
        return citizenshipDto;
    }

    public void setCitizenshipDto(CountryDto citizenshipDto) {
        this.citizenshipDto = citizenshipDto;
    }

    public StreamedContent getPhoto() {
        if (visitorDto == null) {
            return null;
        }
        logger.trace("getPhoto()");
        if (visitorDto.getPhoto() != null) {
            return new DefaultStreamedContent(new ByteArrayInputStream(visitorDto.getPhoto()), "image/png");
        } else {
            return null;
        }
    }

    public StreamedContent getPhotoThumb() {

        if (visitorDto == null) {
            return null;
        }

        BufferedImage bi = null;
        try {
            byte[] photoData = visitorDto.getPhoto();
            if (photoData == null) {
                return null;
            }
            bi = ImageIO.read(new ByteArrayInputStream(visitorDto.getPhoto()));
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(VisitorEdit.class.getName()).log(Level.SEVERE, null, ex);
        }

        int width = bi.getWidth();
        int height = bi.getHeight();
        float scaleFactorThumb;
        if (height > width) {
            scaleFactorThumb = (float) 200 / height;
        } else {
            scaleFactorThumb = (float) 200 / width;
        }

        int thumbWidth = (int) (width * scaleFactorThumb);
        int thumbHeight = (int) (height * scaleFactorThumb);

        BufferedImage thumbPhotoImage = ImageUtil.resizeImage(bi, thumbWidth, thumbHeight);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(thumbPhotoImage, "png", baos);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(VisitorEdit.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            baos.flush();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(VisitorEdit.class.getName()).log(Level.SEVERE, null, ex);
        }

        InputStream is = new ByteArrayInputStream(baos.toByteArray());

        return new DefaultStreamedContent(is, "image/png");

    }

    public boolean getForeigner() {
        return foreigner;
    }

    public void setForeigner(boolean foreigner) {
        logger.entry();
        logger.debug(foreigner);
        this.foreigner = foreigner;
        logger.exit();
    }

    public VisitorDto getVisitorDto() {
        return visitorDto;
    }

    public void setVisitorDto(VisitorDto visitorDto) {
        this.visitorDto = visitorDto;
    }

    public boolean isForeignerNewVal() {
        return foreignerNewVal;
    }

    public void setForeignerNewVal(boolean foreignerNewVal) {
        this.foreignerNewVal = foreignerNewVal;
    }

    public NativeUploadedFile getPhotoFile() {
        return photoFile;
    }

    public void setPhotoFile(NativeUploadedFile photoFile) {
        this.photoFile = photoFile;
    }

    public void visit(Long id) {
        if (visitorService.visit(id, new Date())) {
            FacesUtil.addInfoMessage("success", "visitor_visit_added");
        } else {
            FacesUtil.addErrorMessage("fail", "fail");
        }
    }

    public List<VisitDto> getVisits() {
        if (selectedVisitor != null) {
            if (visitDtos == null) {
                visitDtos = visitorService.getVisitsByVisitorId(visitorDto.getId());
            }
            return visitDtos;
        }
        return new ArrayList<>(0);

    }

    public List<CashgameSessionDto> getCashgames() {
        if (selectedVisitor != null) {
            if (cashgameSessionDtos == null) {
                cashgameSessionDtos = cashgameSessionService.getByVisitorId(visitorDto.getId());
            }
            return cashgameSessionDtos;
        }
        return new ArrayList<>(0);
    }

    public List<TournamentSessionDto> getTournaments() {
        if (selectedVisitor != null) {
            if (tournamentSessionDtos == null) {
                tournamentSessionDtos = tournamentSessionService.getByVisitorId(visitorDto.getId());
            }
            return tournamentSessionDtos;
        }
        return new ArrayList<>(0);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.pres.bb;

import ispok.dto.CityDto;
import ispok.dto.CountryDto;
import ispok.dto.DomicileDto;
import ispok.dto.PostalCodeDto;
import ispok.dto.RegionDto;
import ispok.dto.VisitorDto;
import ispok.helper.ImageUtil;
import ispok.helper.RandomString;
import ispok.service.CityService;
import ispok.service.CountryService;
import ispok.service.DomicileService;
import ispok.service.PostalCodeService;
import ispok.service.RegionService;
import ispok.service.VisitorService;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.NativeUploadedFile;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class NewVisitor {

    private static final Logger logger = LogManager.getLogger();

    private String firstName;
    private String lastName;
    private Date birthDate;
    private String nin;
    private String nickname;
    private String telephone;
    private String email;
    private String sex;
    private String passportId;
    private Long citizenshipId;
    private String address;
    private String city;
    private String postalCode;
    private String region;
    private String password;
    private Long countryId;
    private boolean foreigner;
    private NativeUploadedFile photoFile;
    private byte[] normalizedPhotoData;
    private byte[] thumbPhotoData;

//    private StreamedContent photo;
//    private StreamedContent photoThumbnail;
    private Long id;

//    private Image photoImage;
    @Autowired
    private PostalCodeService postalCodeService;
    @Autowired
    private CityService cityService;
    @Autowired
    private RegionService regionService;
    @Autowired
    private DomicileService domicileService;
    @Autowired
    private VisitorService visitorService;
    @Autowired
    private CountryService countryService;

    /**
     *
     * @return
     */
    public String addVisitor() {

        PostalCodeDto postalCodeDto = new PostalCodeDto(postalCode);
        postalCodeService.savePostalCode(postalCodeDto);

        CityDto cityDto = new CityDto(city);
        cityService.saveCity(cityDto);

        RegionDto regionDto = new RegionDto(region);
        regionService.saveRegion(regionDto);

        DomicileDto domicileDto = new DomicileDto();
        domicileDto.setAddress1(address);
        domicileDto.setCityId(cityDto.getId());
        domicileDto.setPostalCodeId(postalCodeDto.getId());
        logger.debug("Country: {}", countryId);
        domicileDto.setCountryId(countryId);
        domicileDto.setRegionId(regionDto.getId());

        domicileService.saveDomicile(domicileDto);

        VisitorDto visitorDto = new VisitorDto();
        visitorDto.setFirstName(firstName);
        visitorDto.setLastName(lastName);
        visitorDto.setBirthDate(birthDate);
        visitorDto.setNin(nin);
        visitorDto.setNickname(nickname);
        visitorDto.setTelephone(telephone);
        visitorDto.setEmail(email);
        visitorDto.setSex(sex);
        visitorDto.setPassword(password);
        visitorDto.setBonusPoints(0);
        logger.debug("Citizenship: {}", citizenshipId);
        if (citizenshipId == null) {
            citizenshipId = countryId;
        }
        visitorDto.setCitizenshipId(citizenshipId);
        visitorDto.setDomicileId(domicileDto.getId());

        try {
            logger.trace("Read photo file");
            logger.debug("Photo name: {}", photoFile.getFileName());

            BufferedImage originalPhotoImage = ImageIO.read(photoFile.getInputstream());

            int width = originalPhotoImage.getWidth();
            int height = originalPhotoImage.getHeight();
            float scaleFactorNormalize;
            float scaleFactorThumb;
            if (height > width) {
                scaleFactorThumb = (float) 200 / height;
                scaleFactorNormalize = (float) 500 / height;
            } else {
                scaleFactorThumb = (float) 200 / width;
                scaleFactorNormalize = (float) 500 / width;
            }

            logger.debug("Scale factor for normalized photo: {}", scaleFactorNormalize);
            logger.debug("Scale factor for photo thumbnail: {}", scaleFactorThumb);

//            Image scaledImage = bi.getScaledInstance((int) (width * scaleFactor), (int) (height * scaleFactor), Image.SCALE_SMOOTH);
//            BufferedImage resizedImage = new BufferedImage((int) (width * scaleFactor), (int) (height * scaleFactor), bi.getType());
//            Graphics2D g = resizedImage.createGraphics();
//            g.drawImage(scaledImage, 0, 0, new Color(0, 0, 0), null);
//            g.dispose();
//
//            BufferedImage resizedImage = bi.getScaledInstance(width, height, width)
//            AffineTransform at = AffineTransform.getScaleInstance(scaleFactor, scaleFactor);
//            AffineTransformOp ato = new AffineTransformOp(at, null);
//            Graphics2D g = bi.createGraphics();
//            g.drawImage(bi, ato, 0, 0);
//            g.dispose();
//
            int normalizedWidth = (int) (width * scaleFactorNormalize);
            int normalizeHeight = (int) (height * scaleFactorNormalize);

            logger.debug("Normalized Width: {}", normalizedWidth);
            logger.debug("Normalized Height: {}", normalizeHeight);

            int thumbWidth = (int) (width * scaleFactorThumb);
            int thumbHeight = (int) (height * scaleFactorThumb);

            logger.debug("Thumb width: {}", thumbWidth);
            logger.debug("Thumb height: {}", thumbHeight);

            BufferedImage normalizedPhotoImage = ImageUtil.resizeImage(originalPhotoImage, normalizedWidth, normalizeHeight);

            logger.debug("Width of normalized photo: {}", normalizedPhotoImage.getWidth());
            logger.debug("Height of normalized photo: {}", normalizedPhotoImage.getHeight());

            BufferedImage thumbPhotoImage = ImageUtil.resizeImage(originalPhotoImage, thumbWidth, thumbHeight);

            logger.debug("Width of thumb photo: {}", thumbPhotoImage.getWidth());
            logger.debug("Height of thumb photo: {}", thumbPhotoImage.getHeight());

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(normalizedPhotoImage, "png", baos);
            baos.flush();

            normalizedPhotoData = baos.toByteArray();

            baos = new ByteArrayOutputStream();
            ImageIO.write(thumbPhotoImage, "png", baos);
            baos.flush();

            thumbPhotoData = baos.toByteArray();

        } catch (IOException ex) {
            logger.catching(ex);
        }

        if (photoFile != null) {
            visitorDto.setPhoto(normalizedPhotoData);
        } else {
            visitorDto.setPhoto(new byte[0]);
        }

        if ("".equals(password)) {
            password = RandomString.getRandomString(6);
        }

        visitorService.addVisitor(visitorDto);
        id = visitorDto.getId();

        return "/admin/management/visitors/confirmNew.xhtml";
    }

    public String complete() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Locale locale = fc.getViewRoot().getLocale();
        ResourceBundle rb = ResourceBundle.getBundle("ispok/pres/inter/ispok", locale);
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, rb.getString("visitor_added"), firstName + " " + lastName));
        clear();
        return "/admin/management/visitors/visitors.xhtml";
    }

    /**
     *
     * @return
     */
    public String delete() {
        visitorService.deleteVisitor(id);
        return "/admin/management/visitors/visitors.xhtml";
    }

//    public String back() {
//        visitorService.deleteVisitor(id);
//        return "/admin/management/visitors/newvisitor.xhtml";
//    }
    /**
     *
     */
    public void clear() {
        logger.trace("Entering Clear()");
        id = null;
        firstName = null;
        lastName = null;
        birthDate = null;
        nin = null;
        nickname = null;
        telephone = null;
        email = null;
        sex = null;
        password = null;
        address = null;
        city = null;
        postalCode = null;
        region = null;
        countryId = null;
        foreigner = false;
        passportId = null;
        citizenshipId = null;

        normalizedPhotoData = null;
        thumbPhotoData = null;
        photoFile = null;

        logger.trace("Exitting Clear()");
    }

    public StreamedContent getPhoto() {
        logger.trace("getPhoto()");
        return new DefaultStreamedContent(new ByteArrayInputStream(normalizedPhotoData), "image/png");
    }

    public StreamedContent getPhotoThumbnail() {
        logger.trace("getPhotoThumbnail()");
        return new DefaultStreamedContent(new ByteArrayInputStream(thumbPhotoData), "image/png");
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(Long id) {
        this.id = id;
    }

    public boolean isForeigner() {
        return foreigner;
    }

    public void setForeigner(boolean foreigner) {
        this.foreigner = foreigner;
    }

    /**
     * Get the value of countryId
     *
     * @return the value of countryId
     */
    public Long getCountryId() {
        return countryId;
    }

    /**
     * Set the value of countryId
     *
     * @param countryId new value of countryId
     */
    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the value of region
     *
     * @return the value of region
     */
    public String getRegion() {
        return region;
    }

    /**
     * Set the value of region
     *
     * @param region new value of region
     */
    public void setRegion(String region) {
        this.region = region;
    }

    public String getZip() {
        return postalCode;
    }

    public void setZip(String zip) {
        this.postalCode = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Get the value of citizenshipId
     *
     * @return the value of citizenshipId
     */
    public Long getCitizenshipId() {
        return citizenshipId;
    }

    /**
     * Set the value of citizenshipId
     *
     * @param citizenshipId new value of citizenshipId
     */
    public void setCitizenshipId(Long citizenshipId) {
        this.citizenshipId = citizenshipId;
    }

    /**
     * Get the value of idNumber
     *
     * @return the value of idNumber
     */
    public String getPassportId() {
        return passportId;
    }

    /**
     * Set the value of idNumber
     *
     * @param idNumber new value of idNumber
     */
    public void setPassportId(String idNumber) {
        this.passportId = idNumber;
    }

    public NativeUploadedFile getPhotoFile() {
        logger.trace("getPhotoFile");
        logger.debug("Photo file: {}", photoFile);;
        return photoFile;
    }

    public void setPhotoFile(NativeUploadedFile photoFile) {
        logger.trace("setPhotoFile");

        this.photoFile = photoFile;
    }

    /**
     * Get the value of email
     *
     * @return the value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the value of email
     *
     * @param email new value of email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * Get the value of telephone
     *
     * @return the value of telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Set the value of telephone
     *
     * @param telephone new value of telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * Get the value of nickname
     *
     * @return the value of nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Set the value of nickname
     *
     * @param nickname new value of nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Get the value of nin
     *
     * @return the value of nin
     */
    public String getNin() {
        return nin;
    }

    /**
     * Set the value of nin
     *
     * @param nin new value of nin
     */
    public void setNin(String nin) {
        this.nin = nin;
    }

    /**
     * Get the value of birthDate
     *
     * @return the value of birthDate
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Set the value of birthDate
     *
     * @param birthDate new value of birthDate
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Get the value of lastName
     *
     * @return the value of lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the value of lastName
     *
     * @param lastName new value of lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the value of firstName
     *
     * @return the value of firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the value of firstName
     *
     * @param firstName new value of firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCountry() {
        CountryDto countryDto = countryService.getCountryById(countryId);
        return countryDto.getAlpha3();
    }
}

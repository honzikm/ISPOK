/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.service;

import ispok.bo.PostalCode;
import ispok.dto.PostalCodeDto;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PostalCodeServiceImpl extends AbstractDataAccessService implements PostalCodeService {

    @Override
    public boolean contains(String code) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void savePostalCode(PostalCodeDto postalCodeDto) {

        List<PostalCode> postalCodeList = genericDao.getByProperty("code", postalCodeDto.getCode(), PostalCode.class);

        if (postalCodeList.isEmpty()) {
            PostalCode postalCode = new PostalCode();
            postalCode.setCode(postalCodeDto.getCode());
            postalCodeDto.setId(genericDao.saveOrUpdate(postalCode).getId());
        } else {
            postalCodeDto.setId(postalCodeList.get(0).getId());
        }
    }

    @Override
    public PostalCodeDto getPostalCodeById(Long id) {
        PostalCode postalCode = genericDao.getById(id, PostalCode.class);
        return getPostalCodeDto(postalCode);
    }
 
    private PostalCodeDto getPostalCodeDto(PostalCode postalCode){
        PostalCodeDto postalCodeDto = new PostalCodeDto();
        postalCodeDto.setId(postalCode.getId());
        postalCodeDto.setCode(postalCode.getCode());
        return postalCodeDto;
    }
    
}

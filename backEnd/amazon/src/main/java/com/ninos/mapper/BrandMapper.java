package com.ninos.mapper;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.ninos.entity.Brand;
import com.ninos.model.BrandResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BrandMapper {

    public BrandResponse brandEntityToDTO(Brand brand){
       BrandResponse brandResponse = new BrandResponse();
       BeanUtils.copyProperties(brand,brandResponse);
       return brandResponse;
    }

    public Brand brandDtoToEntity(BrandResponse brandResponse){
        Brand brand = new Brand();
        BeanUtils.copyProperties(brandResponse,brand);
        return brand;
    }


}

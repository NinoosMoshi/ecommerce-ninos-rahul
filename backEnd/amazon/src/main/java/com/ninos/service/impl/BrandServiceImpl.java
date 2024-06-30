package com.ninos.service.impl;

import com.ninos.entity.Brand;
import com.ninos.mapper.BrandMapper;
import com.ninos.model.BrandResponse;
import com.ninos.repository.BrandRepository;
import com.ninos.service.BrandService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@AllArgsConstructor
@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;


    @Override
    public List<BrandResponse> getAllBrands() {
        log.info("fetching all brands....");
        List<Brand> brands = brandRepository.findAll();
        log.info("All brands are fetched");
        return brands.stream().map(brand -> brandMapper.brandEntityToDTO(brand))
                .collect(Collectors.toList());
    }
}

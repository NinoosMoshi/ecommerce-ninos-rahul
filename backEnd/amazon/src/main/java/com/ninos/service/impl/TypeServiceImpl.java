package com.ninos.service.impl;

import com.ninos.entity.Type;
import com.ninos.mapper.TypeMapper;
import com.ninos.model.TypeResponse;
import com.ninos.repository.TypeRepository;
import com.ninos.service.TypeService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@AllArgsConstructor
@Service
public class TypeServiceImpl implements TypeService {

    private final TypeRepository typeRepository;
    private final TypeMapper typeMapper;

    @Override
    public List<TypeResponse> getAllTypes() {
        log.info("fetching all types....");
        List<Type> types = typeRepository.findAll();
        log.info("all types are fetched");
        return types.stream().map(type -> typeMapper.typeEntityToDto(type))
                .collect(Collectors.toList());
    }


}

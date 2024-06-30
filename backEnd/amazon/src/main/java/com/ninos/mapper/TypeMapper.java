package com.ninos.mapper;

import com.ninos.entity.Type;
import com.ninos.model.TypeResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TypeMapper {

  public TypeResponse typeEntityToDto(Type type){
      TypeResponse typeResponse = new TypeResponse();
      BeanUtils.copyProperties(type,typeResponse);
      return typeResponse;
  }


    public Type typeDtoToEntity(TypeResponse typeResponse){
        Type type = new Type();
        BeanUtils.copyProperties(typeResponse,type);
        return type;
    }



}

package com.zirius.product.mapper;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(componentModel = "spring",
unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapperConfig {

}

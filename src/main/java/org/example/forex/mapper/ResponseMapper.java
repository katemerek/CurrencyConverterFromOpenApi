package org.example.forex.mapper;

import org.example.forex.models.ConverterResponse;
import org.example.forex.dto.ConverterResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ResponseMapper {
    ConverterResponse toConverterResponse (ConverterResponseDto converterResponseDto);

}

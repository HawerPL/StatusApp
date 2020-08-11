package com.hawer.app.mappers;

import java.util.List;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import com.hawer.app.dto.ProductionDto;
import com.hawer.app.entity.Production;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductionMapper {
	
	ProductionDto productionToProductionDto(Production production);
	Production productionDtoToProduction(ProductionDto productionDto);
	
	List<Production> toProductionList(List<ProductionDto> productionDtoList);
	List<ProductionDto> toProductionDtoList(List<Production> productionList);
}

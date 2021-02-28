package com.hawer.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hawer.app.dto.ProductionDto;
import com.hawer.app.exception.ServiceException;
import com.hawer.app.mappers.ProductionMapper;
import com.hawer.app.services.ProductionService;

@CrossOrigin
@RestController
public class ProductionController {

	@Autowired
	ProductionMapper productionMapper;

	@Autowired
	ProductionService productionService;

	@GetMapping(value = "/productions")
	public List<ProductionDto> getProductions() throws ServiceException{
		
			List<ProductionDto> list = productionMapper.toProductionDtoList(productionService.getProductions());
			
			return list;
		
	}

	@GetMapping(value = "/production/{guid}")
	public ProductionDto getProduction(@PathVariable("guid") String guid) throws ServiceException {
		try {
			if (productionService.existsProduction(guid)) {
				return productionMapper.productionToProductionDto(productionService.getProduction(guid));
			} else {
				throw new ServiceException("Production not found");
			}
		} catch (ServiceException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	@PostMapping(value = "/production")
	public void addProduction(@RequestBody ProductionDto productionDto) throws ServiceException {
		try {
			if (!productionDto.getName().equals("")) {
				productionService.addProduction(productionMapper.productionDtoToProduction(productionDto));
			} else {
				throw new ServiceException("Produkcja musi mieć nazwę");
			}
		} catch (ServiceException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@DeleteMapping(value = "/production/{guid}")
	public void deleteProduction(@PathVariable("guid") String guid) throws ServiceException {
		try {
			if (productionService.existsProduction(guid)) {
				productionService.deleteProduction(guid);
			} else {
				throw new ServiceException("Production not found");
			}
		} catch (ServiceException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	@PutMapping(value = "/production")
	public void updateProduction(@RequestBody ProductionDto productionDto) throws ServiceException {
		productionService.updateProduction(productionMapper.productionDtoToProduction(productionDto));

	}
}

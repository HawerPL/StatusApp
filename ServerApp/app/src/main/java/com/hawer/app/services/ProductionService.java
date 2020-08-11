package com.hawer.app.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawer.app.accessingdata.ProductionRepository;
import com.hawer.app.entity.Production;

@Service
public class ProductionService {

	@Autowired
	private ProductionRepository productionRepository;

	public List<Production> getProductions() {
		return productionRepository.findAll();
	}

	public Production getProduction(String guid) {
		return productionRepository.findProductionByGuid(guid);
	}

	public void deleteProduction(String guid) {
		productionRepository.deleteById(guid);
	}

	public void addProduction(Production production) {
		production.setGuid(UUID.randomUUID().toString());
		productionRepository.save(production);
	}

	public void updateProduction(Production production) {
		String guid = production.getGuid();
		String name = production.getName();
		String description = production.getDescription();
		if (name.isBlank()) {
			name = productionRepository.findProductionByGuid(guid).getName();
			production.setName(name);
		}
		if (description.isBlank()) {
			description = productionRepository.findProductionByGuid(guid).getDescription();
			production.setDescription(description);
		}
		productionRepository.save(production);
	}

	public boolean existsProduction(String guid) {
		if (productionRepository.existsById(guid)) {
			return true;
		} else {
			return false;
		}
	}
}

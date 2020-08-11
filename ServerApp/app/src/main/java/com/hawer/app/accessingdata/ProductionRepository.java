package com.hawer.app.accessingdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hawer.app.entity.Production;

@Repository
public interface ProductionRepository extends JpaRepository<Production, String>{

	@Query("SELECT p FROM Production p WHERE p.guid=:guid ORDER BY p.guid ASC")
	Production findProductionByGuid(@Param("guid") String guid);
	
}

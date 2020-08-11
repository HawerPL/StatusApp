package com.hawer.app.accessingdata;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hawer.app.entity.Application;


@Repository
public interface ApplicationRepository extends JpaRepository<Application, String> {

	@Query("SELECT a FROM Application a WHERE a.guid_production=:guid_production ORDER BY a.guid_app ASC")
	List<Application> findApplicationsByGuidProduction(@Param("guid_production") String guid_production);

	@Query("SELECT a FROM Application a WHERE a.guid_app=:guid_app")
	Application findApplicationByGuid(@Param("guid_app") String guid_app);

	@Query("DELETE FROM Application a WHERE a.guid_production=:guid_production")
	void deleteAllProductionByGuidProduction(@Param("guid_production") String guid);
	
	@Transactional
	@Modifying
	@Query("UPDATE Application a SET a.status=:status WHERE a.guid_app=:guid_app")
	void updateApplicationStatus(@Param("status") boolean status, @Param("guid_app") String guid_app);
}

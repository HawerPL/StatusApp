package com.hawer.app.accessingdata;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.hawer.app.entity.Error;

@Repository
public interface ErrorRepository extends JpaRepository<Error, Long>{

	@Query("SELECT e FROM Error e WHERE e.guid_app=:guid_app ORDER BY e.date")
	List<Error> findAllErrorsByGuidApp(@Param("guid_app") String guid_app);
}

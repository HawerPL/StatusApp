package com.hawer.app.accessingdata;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hawer.app.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE u.username=:username")
	User findByUsername(@Param("username")String username);

	@Transactional
	@Modifying
	@Query("UPDATE User u SET u.password = :password  WHERE u.username = :username")
	void updatePassword(@Param("password") String password, @Param("username") String username);
	
	@Query("SELECT u FROM User u WHERE u.id=:id")
	User findUserById(@Param("id") Long id);
	
	@Query("SELECT u FROM User u ORDER BY u.id ASC")
	List<User> findAndSortAllUsers();
	
	@Query("SELECT u FROM User u WHERE u.username=:username")
	User loadUserByUsername(@Param("username") String username);
}

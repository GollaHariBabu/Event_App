package org.jsp.event_app.repository;

import java.util.Optional;

import org.jsp.event_app.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<Admin, Integer>{

	@Query("select a from Admin a where a.phone=?1and a.password=?2")
	Optional<Admin> findAdminByPhoneAndPassword(long phone, String password);
}

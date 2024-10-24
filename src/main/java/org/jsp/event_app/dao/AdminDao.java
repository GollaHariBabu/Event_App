package org.jsp.event_app.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.event_app.entity.Admin;

public interface AdminDao {

	Optional<Admin> findAdminById(int id);

	List<Admin> findAllAdmins();

	Admin updateAdmin(Admin admin);

	Admin saveAdmin(Admin admin);

	Optional<Admin> findAdminByPhoneAndPassword(long phone, String password);



	






	

}

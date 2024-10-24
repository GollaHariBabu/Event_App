package org.jsp.event_app.service;

import org.jsp.event_app.entity.Admin;
import org.jsp.event_app.util.AuthAdmin;
import org.springframework.http.ResponseEntity;

public interface AdminService {

	ResponseEntity<?> saveAdmin(Admin admin);
	
	ResponseEntity<?> updateAdmin(Admin admin);
	
	ResponseEntity<?> findAllAdmins();
	
	ResponseEntity<?> findAdminById(int id);
	
	ResponseEntity<?> deleteAdminById(int id);

	ResponseEntity<?> login(AuthAdmin authAdmin);

	


	
	
}

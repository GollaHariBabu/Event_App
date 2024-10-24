package org.jsp.event_app.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.jsp.cms.exceptionClasses.InvalidCredentialsException;
import org.jsp.event_app.dao.AdminDao;
import org.jsp.event_app.entity.Admin;
import org.jsp.event_app.exceptionclasses.InvalidAdminIdException;
import org.jsp.event_app.exceptionclasses.NoAdminFoundException;
import org.jsp.event_app.responseStructure.ResponseStructure;
import org.jsp.event_app.service.AdminService;
import org.jsp.event_app.util.AuthAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao;

	@Override
	public ResponseEntity<?> saveAdmin( Admin admin) {
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Admin saved successfully...").body(adminDao.saveAdmin(admin)).build());
	}

	@Override
	public ResponseEntity<?> updateAdmin( Admin admin) {
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Admin Updated Successfully...").body(adminDao.updateAdmin(admin)).build());
	}

	@Override
	public ResponseEntity<?> findAllAdmins() {
		List<Admin> admin = adminDao.findAllAdmins();
		if (admin.isEmpty()) {
			throw NoAdminFoundException.builder().message("No Admin present in the database table...").build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("All Admins found successfully...").body(admin).build());
	}

	@Override
	public ResponseEntity<?> findAdminById( int id) {
		Optional<Admin> admin = adminDao.findAdminById(id);
		if (admin.isEmpty())
			throw InvalidAdminIdException.builder().message("Invalid Admin Id... Unable to Fetch Admin").build();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Admin Fetched successfully...").body(admin).build());
	}

	@Override
	public ResponseEntity<?> deleteAdminById( int id) {
		Optional<Admin> admin = adminDao.findAdminById(id);
		if (admin.isEmpty())
			throw InvalidAdminIdException.builder().message("Invalid Admin Id... Unable to Delete Admin").build();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Admin Deleted successfully...").body(admin.get()).build());
	}

	@Override
	public ResponseEntity<?> login(AuthAdmin authAdmin) {
		Optional<Admin> optional = adminDao.findAdminByPhoneAndPassword(authAdmin.getPhone(),authAdmin.getPassword());
		if(optional.isEmpty())
			throw InvalidCredentialsException.builder().message("Invalid Credentials...").build();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Admin verified successfully...").body(optional.get()).build());
	}
}

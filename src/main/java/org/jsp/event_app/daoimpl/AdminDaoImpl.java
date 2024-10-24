package org.jsp.event_app.daoimpl;

import java.util.List;
import java.util.Optional;

import org.jsp.event_app.dao.AdminDao;
import org.jsp.event_app.entity.Admin;
import org.jsp.event_app.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDaoImpl implements AdminDao{

	@Autowired
	private AdminRepository adminRepository;

	@Override
	public Admin saveAdmin(Admin admin) {
		return adminRepository.save(admin);
	}
	
	@Override
	public Admin updateAdmin(Admin admin) {
		return adminRepository.save(admin);
	}

	@Override
	public Optional<Admin> findAdminById(int id) {
		return adminRepository.findById(id);
	}

	@Override
	public List<Admin> findAllAdmins() {
		return adminRepository.findAll();
	}


	@Override
	public Optional<Admin> findAdminByPhoneAndPassword(long phone, String password) {
		return adminRepository.findAdminByPhoneAndPassword(phone, password);
	}
}

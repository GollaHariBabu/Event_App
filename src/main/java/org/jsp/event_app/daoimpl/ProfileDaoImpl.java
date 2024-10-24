package org.jsp.event_app.daoimpl;

import org.jsp.event_app.dao.ProfileDao;
import org.jsp.event_app.entity.Profile;
import org.jsp.event_app.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProfileDaoImpl implements ProfileDao{

	@Autowired
	private ProfileRepository profileRepository;

	@Override
	public Profile saveProfile(Profile profile) {
		return profileRepository.save(profile);
	}

	@Override
	public Profile updateProfile(Profile profile) {
		return profileRepository.save(profile);
	}
}

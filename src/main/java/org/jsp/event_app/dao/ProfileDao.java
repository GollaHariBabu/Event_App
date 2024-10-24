package org.jsp.event_app.dao;

import org.jsp.event_app.entity.Profile;

public interface ProfileDao {

	Profile saveProfile(Profile profile);
	
	Profile updateProfile(Profile profile);
	
}

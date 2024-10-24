package org.jsp.event_app.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.jsp.event_app.dao.EventDao;
import org.jsp.event_app.dao.ProfileDao;
import org.jsp.event_app.entity.Event;
import org.jsp.event_app.entity.Profile;
import org.jsp.event_app.exceptionclasses.NoEventFoundException;
import org.jsp.event_app.responseStructure.ResponseStructure;
import org.jsp.event_app.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProfileServiceImpl implements ProfileService {

	private static final String FOLDER_PATH = "C:\\Users\\harib\\OneDrive\\Desktop\\event profiles\\";
	
	@Autowired
	private ProfileDao profileDao; 
	
	@Autowired
	private EventDao eventDao;
	
	@Override
	public ResponseEntity<?> saveProfile(int eid, MultipartFile file) {
		Optional<Event> optinal1 = eventDao.findEventById(eid);
		if (optinal1.isEmpty())
			throw NoEventFoundException.builder().message("No Event Found For The Passed ID : " + eid).build();
		Event event = optinal1.get();
		String fileName = UUID.randomUUID() + file.getOriginalFilename();
		String path = FOLDER_PATH + fileName;
		try {
			file.transferTo(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Profile p = Profile.builder().name(fileName).type(file.getContentType()).path(path).build();
		p = profileDao.saveProfile(p);
		event.setProfile(p);
		eventDao.updateEvent(event);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Image Saved Successfully...").body(event).build());
	}
}

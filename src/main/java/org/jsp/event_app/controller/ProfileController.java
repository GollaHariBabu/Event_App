package org.jsp.event_app.controller;

import org.jsp.event_app.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

	@Autowired
	private ProfileService profileService;

	@Operation(summary = "To Save All the Profiles...", description = "This API will fetch the event and accept an Profile JSON Object and saves it to the Database Table and Returns the Saved Profile Entity Object with the Id which is generated automatically...")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "profile saved successfully..."),
	@ApiResponse(responseCode = "400", description = "Bad request... Unable to save Profile") })
	@PostMapping
	public ResponseEntity<?> saveProfile(@RequestParam int eid, @RequestParam MultipartFile file) {
		return profileService.saveProfile(eid, file);
	}
}

package org.jsp.event_app.controller;

import org.jsp.event_app.entity.Admin;
import org.jsp.event_app.service.AdminService;
import org.jsp.event_app.util.AuthAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/admins")
public class AdminController {

	@Autowired
	private AdminService adminService;

	
	@Operation(summary = "To Save All the Admins...", description = "This API will accept an Admin JSON Object and saves it to the Database Table and Returns the Saved Admin Entity Object with the Id which is generated automatically...")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Admin saved successfully..."),
	@ApiResponse(responseCode = "400", description = "Bad request... Unable to save Admin") })
	@PostMapping
	public ResponseEntity<?> saveAdmin(@RequestBody Admin admin) {
		return adminService.saveAdmin(admin);
	}

	@Operation(summary = "To update Admin using Id...", description = "This API will accept an Admin JSON Object and update according to User and saves it to the Database Table and Returns the Saved Admin Entity Object by using Id ...")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Admin updated successfully..."),
	@ApiResponse(responseCode = "400", description = "Invalid Id... Unable to update Admin") })
	@PutMapping
	public ResponseEntity<?> updateAdmin(@RequestBody Admin admin) {
		return adminService.updateAdmin(admin);
	}

	@Operation(summary = "To Fetch All Admins", description = "This API will Fetch All the Admins  Available in the Database Table...")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "All the Admins Found Successfully..."),
	@ApiResponse(responseCode = "404", description = "No Admins Present in the Database Table...") })
	@GetMapping
	public ResponseEntity<?> findAllAdmins() {
		return adminService.findAllAdmins();
	}

	@Operation(summary = "To Fetch Admin By Id", description = "This API will Fetch the Admin By Id  Available in the Database Table...")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Admin Found Successfully..."),
	@ApiResponse(responseCode = "404", description = "No Admin Present in the Database Table...") })
	@GetMapping("/{id}")
	public ResponseEntity<?> findAdminById(@PathVariable int id) {
		return adminService.findAdminById(id);
	}

	@Operation(summary = "To Delete Admin By Id", description = "This API will Fetch and delete the Admin By Id which is Available in the Database Table...")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Admin Found Successfully..."),
	@ApiResponse(responseCode = "404", description = "No Admin Present in the Database Table...") })
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAdminById(@PathVariable int id) {
		return adminService.deleteAdminById(id);
	}
	@Operation(summary = "To login based on phone and password", description = "This API will do login the Admin with the help of AuthAdmin class (which is used for utility purpose to send the phone and password in secure) based on phone and password which is Available in the Database Table...")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Phone logged in Successfully..."),
	@ApiResponse(responseCode = "404", description = "Invalid Phone or password, please check and try again") })
	@PostMapping(value = "/login")
	public ResponseEntity<?> login(@RequestBody AuthAdmin authAdmin){
		return adminService.login(authAdmin);
	}
}

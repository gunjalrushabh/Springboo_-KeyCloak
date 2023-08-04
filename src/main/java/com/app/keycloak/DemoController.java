package com.app.keycloak;

import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {

	@GetMapping
	@PreAuthorize("hasRole('client_user')")  //role based // this role is not valid it need to be starts with ROLE_  403 forbidden  => JwtAuthConverters
	public String hello() {
		return "Hello Springboot and KeyCloak";
	}
	
	@GetMapping("/hello-2")
	@PreAuthorize("hasRole('client_admin')")  //role based  // this role is not valid it need to be starts with ROLE_  403 forbidden => JwtAuthConverters
	public String hello2() {
		return "Hello Springboot and KeyCloak - Admin	";
	}
}

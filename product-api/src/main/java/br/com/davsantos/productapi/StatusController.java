package br.com.davsantos.productapi;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class StatusController {

	@GetMapping("/status")
	public ResponseEntity<?> getApiStatus() {
		var response = new HashMap<>();

		response.put("service", "PRODUCT-API");
		response.put("status", "UP");
		response.put("httpStatus", HttpStatus.OK.value());

		return ResponseEntity.ok(response);
	}

}

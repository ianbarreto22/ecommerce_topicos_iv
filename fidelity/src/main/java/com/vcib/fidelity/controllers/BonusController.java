package com.vcib.fidelity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vcib.fidelity.services.FailureService;

@Controller
public class BonusController {
	
	@Autowired
	private FailureService failureService;
	
	@PostMapping("/bonus")
	public ResponseEntity<String> addBonus(@RequestParam Long user, @RequestParam Integer bonus){
		
		failureService.simulateFailure();
		
		return ResponseEntity.ok("Bônus " + bonus + " adiconado ao usuário " +  user);
	}
}

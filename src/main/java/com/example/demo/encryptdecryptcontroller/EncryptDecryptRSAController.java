package com.example.demo.encryptdecryptcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.encryptiondecryptionservice.EncryptDecryptMessageService;

@RestController
public class EncryptDecryptRSAController {
	
	@Autowired
	EncryptDecryptMessageService encryptDecryptmessage;
	
	@GetMapping("/createkeys")
	public void createPrivatePublickey() {
		encryptDecryptmessage.createKeys();
	}
	
	@PostMapping("/encrypt")
	public String encryptMessage(@RequestBody String plainString) {
		return encryptDecryptmessage.encryptMessage(plainString);
	}
	
	@PostMapping("/decrypt")
	public String decryptMessage(@RequestBody String encryptstring) {
		return encryptDecryptmessage.decryptMessage(encryptstring);
	}

}

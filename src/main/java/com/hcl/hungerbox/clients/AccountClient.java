package com.hcl.hungerbox.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hcl.hungerbox.dto.AccountDto;


//@FeignClient(name="http://bank-service:7777/testbank/users/")
@FeignClient(value="bank-service", url="http://localhost:7777/testbank/users/")
public interface AccountClient {

	@GetMapping("/accounts")
	public AccountDto getAccounts(@RequestParam("phoneNumber") int phoneNumber);
	
	@PostMapping("/transfer")
	public ResponseEntity<String> fundTransfer(@RequestParam("fromAccount") int fromAccount,
			@RequestParam("toAccount") int toAccount,
			@RequestParam("amount") int amount);
}
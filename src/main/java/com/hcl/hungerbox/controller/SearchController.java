package com.hcl.hungerbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.hungerbox.dto.ItemListResponseDto;
import com.hcl.hungerbox.service.SearchService;

@RestController
@RequestMapping("/searches")
public class SearchController {
	
	@Autowired
	SearchService searchService;
	
	@GetMapping("")
	public ResponseEntity<ItemListResponseDto> searchMenu(@RequestParam String itemName, @RequestParam String vendorName) {
		
		ItemListResponseDto itemListResponseDto = new ItemListResponseDto();
		itemListResponseDto = searchService.searchMenu(itemName, vendorName);
		return new ResponseEntity<>(itemListResponseDto,HttpStatus.OK);
		
		
	}

}
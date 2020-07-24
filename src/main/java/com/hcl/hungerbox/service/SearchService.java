package com.hcl.hungerbox.service;

import com.hcl.hungerbox.dto.ItemListResponseDto;

public interface SearchService {
	
	public ItemListResponseDto searchMenu(String itemName, String vendorName);
	
	
}

package com.hcl.hungerbox.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.hungerbox.dto.ItemDto;
import com.hcl.hungerbox.dto.ItemListResponseDto;
import com.hcl.hungerbox.entity.Item;
import com.hcl.hungerbox.entity.Vendor;
import com.hcl.hungerbox.repository.ItemRepository;
import com.hcl.hungerbox.repository.VendorRepository;

@Service
public class SearchServiceImpl implements SearchService {
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	VendorRepository vendorRepository;

	@Override
	public ItemListResponseDto searchMenu(String itemName, String vendorName) {
		
		ItemListResponseDto itemListResponseDto = new ItemListResponseDto();
		List<ItemDto> itemDtos = new ArrayList<>();
		if(!Objects.isNull(itemName)) {
			if(!Objects.isNull(vendorName)) {
				Vendor vendor = vendorRepository.findByVendorNameContains(vendorName);
				System.out.println("vendor: ");
				List<Item> items = itemRepository.findByItemNameContainsAndVendor(itemName,vendor);
				System.out.println("Both are not null:  ");
				for(Item item: items) {
					ItemDto itemDto = new ItemDto();
					BeanUtils.copyProperties(item, itemDto);
					itemDtos.add(itemDto);
				}
				itemListResponseDto.setItemList(itemDtos);
				itemListResponseDto.setStatusMessage("Menu List is: ");
				itemListResponseDto.setStatusCode(HttpStatus.OK.value());
				return itemListResponseDto;
			} else {
				List<Item> items = itemRepository.findByItemNameContains(itemName);
				for(Item item: items) {
					ItemDto itemDto = new ItemDto();
					BeanUtils.copyProperties(item, itemDto);
					itemDtos.add(itemDto);
				}
				itemListResponseDto.setItemList(itemDtos);
				itemListResponseDto.setStatusMessage("Menu List is: ");
				itemListResponseDto.setStatusCode(HttpStatus.OK.value());
				return itemListResponseDto;
			}
		} else if(!Objects.isNull(vendorName)) {
			Vendor vendor = vendorRepository.findByVendorNameContains(vendorName);
			List<Item> items = itemRepository.findByVendor(vendor);
			for(Item item: items) {
				ItemDto itemDto = new ItemDto();
				BeanUtils.copyProperties(item, itemDto);
				itemDtos.add(itemDto);
			}
			itemListResponseDto.setItemList(itemDtos);
			itemListResponseDto.setStatusMessage("Menu List is: ");
			itemListResponseDto.setStatusCode(HttpStatus.OK.value());
			return itemListResponseDto;
			
		} else {
			
			itemListResponseDto.setItemList(itemDtos);
			itemListResponseDto.setStatusMessage("Menu List is: ");
			itemListResponseDto.setStatusCode(HttpStatus.OK.value());
			return itemListResponseDto;
		}
	}
	
	

}

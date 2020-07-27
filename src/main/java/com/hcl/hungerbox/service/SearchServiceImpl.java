package com.hcl.hungerbox.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	
	
	private static Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class);
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	VendorRepository vendorRepository;

	@Override
	public ItemListResponseDto searchMenu(String itemName, String vendorName) {
		
		ItemListResponseDto itemListResponseDto = new ItemListResponseDto();
		List<ItemDto> itemDtos = new ArrayList<>();
		logger.info("itemName: "+itemName);
		if(!Objects.isNull(itemName)) {
			if(!Objects.isNull(vendorName)) {
				logger.info("both are present");
				logger.info("item name: "+itemName);
				logger.info("vendor name: "+vendorName);
				Vendor vendor = vendorRepository.findByVendorNameContains(vendorName);
				System.out.println("vendor: "+vendor);
				List<Item> items = itemRepository.findByItemNameContainsAndVendor(itemName,vendor);
				System.out.println("Both are not null:  ");
				for(Item item: items) {
					ItemDto itemDto = new ItemDto();
					BeanUtils.copyProperties(item, itemDto);
					itemDtos.add(itemDto);
					item.getItemId();
				}
				itemListResponseDto.setItemList(itemDtos);
				itemListResponseDto.setStatusMessage("Menu List is: ");
				itemListResponseDto.setStatusCode(HttpStatus.OK.value());
				return itemListResponseDto;
			} else {
				logger.info("vendor is not present");
				logger.info("item name: "+itemName);
				logger.info("vendor name: "+vendorName);
				logger.info("no vendor name, inside the else part: ");
				List<Item> items = itemRepository.findByItemNameContains(itemName);
				logger.info("items: "+items);
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
		}
			
		if(itemName==null) {
			if(vendorName!=null){
			
				logger.info("item is null ");
				Vendor vendor = vendorRepository.findByVendorNameContains(vendorName);
				System.out.println("vendor: "+vendor);
				List<Item> items = itemRepository.findByVendor(vendor);
				logger.info("items: "+items);
				for(Item item: items) {
					ItemDto itemDto = new ItemDto();
					BeanUtils.copyProperties(item, itemDto);
					itemDtos.add(itemDto);
				}
				itemListResponseDto.setItemList(itemDtos);
				itemListResponseDto.setStatusMessage("Menu List is: ");
				itemListResponseDto.setStatusCode(HttpStatus.OK.value());
				return itemListResponseDto;
			}  else {
			itemListResponseDto.setItemList(itemDtos);
			itemListResponseDto.setStatusMessage("Either the item name or vendor name you entered is not there");
			itemListResponseDto.setStatusCode(HttpStatus.NOT_FOUND.value());
			return itemListResponseDto;
		}
	}
		return itemListResponseDto;
		
		
		
	
	}
}

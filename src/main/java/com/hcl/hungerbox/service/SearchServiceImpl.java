package com.hcl.hungerbox.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
		logger.info("itemName: " + itemName);
		if (!Objects.isNull(itemName)) {
			if (!Objects.isNull(vendorName)) {
				logger.info("both are present");
				logger.info("item name: " + itemName);
				logger.info("vendor name: " + vendorName);
				Optional<Vendor> vendors = vendorRepository.findByVendorNameContains(vendorName);
				if (vendors.isPresent()) {
					Vendor vendor = vendors.get();
					System.out.println("vendor: " + vendor);
					Optional<List<Item>> items = itemRepository.findByItemNameContainsAndVendor(itemName, vendor);
					if (items.isPresent()) {
						List<Item> itemList = items.get();
						for (Item item : itemList) {
							ItemDto itemDto = new ItemDto();
							BeanUtils.copyProperties(item, itemDto);
							itemDtos.add(itemDto);
							item.getItemId();
						}
					} else {
						itemListResponseDto.setStatusMessage("The item is not found");
						itemListResponseDto.setStatusCode(HttpStatus.OK.value());
						return itemListResponseDto;
					}
				} else {
					itemListResponseDto.setStatusMessage("There is no vendor with that name ");
					itemListResponseDto.setStatusCode(HttpStatus.OK.value());
					return itemListResponseDto;
				}

				itemListResponseDto.setItemList(itemDtos);
				itemListResponseDto.setStatusMessage("Menu List is: ");
				itemListResponseDto.setStatusCode(HttpStatus.OK.value());
				return itemListResponseDto;
			} else {
				logger.info("vendor is not present");
				logger.info("item name: " + itemName);
				logger.info("vendor name: " + vendorName);
				logger.info("no vendor name, inside the else part: ");
				Optional<List<Item>> items = itemRepository.findByItemNameContains(itemName);
				logger.info("items: " + items);
				if (items.isPresent()) {

					List<Item> itemList = items.get();
					for (Item item : itemList) {
						ItemDto itemDto = new ItemDto();
						BeanUtils.copyProperties(item, itemDto);
						itemDtos.add(itemDto);
					}
					itemListResponseDto.setItemList(itemDtos);
					itemListResponseDto.setStatusMessage("Menu List is: ");
					itemListResponseDto.setStatusCode(HttpStatus.OK.value());
					return itemListResponseDto;
				} else {
					itemListResponseDto.setStatusMessage("There are no items with the given item name");
					itemListResponseDto.setStatusCode(HttpStatus.NOT_FOUND.value());
					return itemListResponseDto;
				}
			}
		}

		else if (Objects.isNull(itemName)) {
			logger.info("itemName is null: " + itemName);
			logger.info("vendor name is null: " + vendorName);
			if (!Objects.isNull(vendorName)) {
				Optional<Vendor> vendors = vendorRepository.findByVendorNameContains(vendorName);
				logger.info(vendorName);
				if (vendors.isPresent()) {
					Vendor vendor = vendors.get();
					Optional<List<Item>> items = itemRepository.findByVendor(vendor);
					if (items.isPresent()) {
						List<Item> itemList = items.get();
						logger.info("items: " + items);
						for (Item item : itemList) {
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
						itemListResponseDto.setStatusMessage("No items present with that name");
						itemListResponseDto.setStatusCode(HttpStatus.OK.value());
						return itemListResponseDto;
					}

				} else {
					logger.info("else part: no vendor");
					itemListResponseDto.setItemList(itemDtos);
					itemListResponseDto.setStatusMessage("No vendor with that name");
					itemListResponseDto.setStatusCode(HttpStatus.OK.value());
					return itemListResponseDto;
				}
			} else {
				logger.info("else part, no details: ");
				itemListResponseDto.setItemList(itemDtos);
				itemListResponseDto.setStatusMessage("Please enter either of the details");
				itemListResponseDto.setStatusCode(HttpStatus.NOT_FOUND.value());
				return itemListResponseDto;
			}
		}
		return itemListResponseDto;
	}
}

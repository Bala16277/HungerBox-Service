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

import com.hcl.hungerbox.config.ApplicationConstants;
import com.hcl.hungerbox.dto.ItemDto;
import com.hcl.hungerbox.dto.ItemListResponseDto;
import com.hcl.hungerbox.entity.Item;
import com.hcl.hungerbox.entity.Vendor;
import com.hcl.hungerbox.exception.ItemNotFoundException;
import com.hcl.hungerbox.exception.NoDataException;
import com.hcl.hungerbox.exception.VendorNotFoundException;
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
	public ItemListResponseDto searchMenu(String itemName, String vendorName)
			throws ItemNotFoundException, VendorNotFoundException, NoDataException {

		ItemListResponseDto itemListResponseDto = new ItemListResponseDto();
		List<ItemDto> itemDtos = new ArrayList<>();
		logger.info("itemName: " + itemName);
		if (!Objects.isNull(itemName)) {
			if (!Objects.isNull(vendorName)) {
				logger.info("both are present");
				logger.info("item name: " + itemName);
				logger.info("vendor name: " + vendorName);
				Optional<List<Vendor>> vendors = vendorRepository.findByVendorNameContains(vendorName);

				if (vendors.isPresent()) {
					List<Vendor> vendorsList = vendors.get();
					for (Vendor vendor : vendorsList) {
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
							throw new ItemNotFoundException(ApplicationConstants.ITEM_NOT_FOUND);
						}
					}
				} else {
					throw new VendorNotFoundException(ApplicationConstants.VENDOR_NOT_FOUND);
				}

				itemListResponseDto.setItemList(itemDtos);
				itemListResponseDto.setStatusMessage(ApplicationConstants.MENU_LIST);
				itemListResponseDto.setStatusCode(HttpStatus.OK.value());
				return itemListResponseDto;
			} else {
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
					itemListResponseDto.setStatusMessage(ApplicationConstants.MENU_LIST);
					itemListResponseDto.setStatusCode(HttpStatus.OK.value());
					return itemListResponseDto;
				} else {
					throw new ItemNotFoundException(ApplicationConstants.ITEM_NOT_FOUND);
				}
			}
		}

		else if (Objects.isNull(itemName)) {
			logger.info("itemName is null: " + itemName);
			logger.info("vendor name is null: " + vendorName);
			if (!Objects.isNull(vendorName)) {
				Optional<List<Vendor>> vendors = vendorRepository.findByVendorNameContains(vendorName);
				logger.info(vendorName);
				if (vendors.isPresent()) {
					List<Vendor> vendorList = vendors.get();
					logger.info("vendors: " + vendorList);
					for (Vendor vendor : vendorList) {
						Optional<List<Item>> items = itemRepository.findByVendor(vendor);
						logger.info("vendor name: " + vendor.getVendorName());
						if (items.isPresent()) {
							List<Item> itemList = items.get();
							logger.info("items: " + items);
							for (Item item : itemList) {
								ItemDto itemDto = new ItemDto();
								BeanUtils.copyProperties(item, itemDto);
								itemDtos.add(itemDto);
							}
							itemListResponseDto.setItemList(itemDtos);

						} else {
							throw new ItemNotFoundException(ApplicationConstants.ITEM_NOT_FOUND);
						}
					}
					itemListResponseDto.setStatusMessage(ApplicationConstants.MENU_LIST);
					itemListResponseDto.setStatusCode(HttpStatus.OK.value());
					return itemListResponseDto;
				} else {
					logger.info("else part: no vendor");
					throw new VendorNotFoundException(ApplicationConstants.VENDOR_NOT_FOUND);
				}
			} else {
				logger.info("else part, no details: ");
				throw new NoDataException(ApplicationConstants.NO_DETAILS_PRESENT);
			}
		}
		return itemListResponseDto;
	}
}

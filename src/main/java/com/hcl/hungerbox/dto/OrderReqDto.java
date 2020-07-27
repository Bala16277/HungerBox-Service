package com.hcl.hungerbox.dto;

import java.util.List;

public class OrderReqDto {
	
	private String userName;

	private List<OrderDto> itemList;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<OrderDto> getItemList() {
		return itemList;
	}

	public void setItemList(List<OrderDto> itemList) {
		this.itemList = itemList;
	}
}

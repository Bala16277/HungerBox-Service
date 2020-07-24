package com.hcl.hungerbox.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class ItemOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer itemOrderId;

	@OneToOne
	@JoinColumn(name = "itemId")
	private Item item;

	@OneToOne
	@JoinColumn(name = "orderId")
	private Order order;

	private Integer quantity;

	public Integer getItemOrderId() {
		return itemOrderId;
	}

	public void setItemOrderId(Integer itemOrderId) {
		this.itemOrderId = itemOrderId;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}

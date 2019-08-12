package com.shop.entity;


public class CartItem {
    
	private Product product;
	private Integer number;
	private double subtotal;



	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public double getSubtotal() {
		if(product.getPid()==1&&number>=12){
			return number*product.getPrice()*0.5+product.getPostage()+number*product.getTaxation();
		}
		return number*product.getPrice()+product.getPostage()+number*product.getTaxation();
	}

	@Override
	public String toString() {
		return "CartItem{" +
				"product=" + product +
				", number=" + number +
				", subtotal=" + subtotal +
				'}';
	}
}

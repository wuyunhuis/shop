package com.shop.entity;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;






public class Cart {
    
	// 购物总计:
	private double total;
	// 购物车属性
	private Map<Integer, CartItem> map=new LinkedHashMap<Integer, CartItem>();

	public Map<Integer, CartItem> getMap() {
		return map;
	}

	public void setMap(Map<Integer, CartItem> map) {
		this.map = map;
	}

	public double getTotal() {
		return total;
	}
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	
	public void addCart(CartItem cartItem) {
		Integer pid=cartItem.getProduct().getPid();
		if(map.containsKey(pid)){
			CartItem ci=map.get(pid);
			ci.setNumber(cartItem.getNumber()+ci.getNumber());
			}else{
				map.put(pid, cartItem);
			}
		total += cartItem.getSubtotal();
	}
	// 2.从购物车移除购物项
		public void removeCart(Integer pid) {
			// 将购物项移除购物车:
			CartItem cartItem = map.remove(pid);
			// 总计 = 总计 -移除的购物项小计:
			total -= cartItem.getSubtotal();
		}

		// 3.清空购物车
		public void clearCart() {
			// 将所有购物项清空
			map.clear();
			// 将总计设置为0
			total = 0;
		}
}

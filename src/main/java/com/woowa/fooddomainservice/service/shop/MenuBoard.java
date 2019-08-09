/**
 * 
 */
package com.woowa.fooddomainservice.service.shop;


import java.util.List;

import com.woowa.fooddomainservice.domain.generic.money.Money;
import com.woowa.fooddomainservice.domain.shop.Menu;
import com.woowa.fooddomainservice.domain.shop.Shop;

import lombok.Data;

/**
 * packageName	: com.woowa.fooddomainservice.service.shop
 * fileName	: MenuBoard.java 
 * author		: twayair
 * date		: 2019. 8. 8.
 * 내용			: 
 *
 * ===========================================================
 *
 * DATE				AUTHOR			NOTE
 * -----------------------------------------------------------
 * 2019. 8. 8.			twayair			최초 생성
 */
@Data
public class MenuBoard {
	private Long shopId;
	private String shopName;
	private boolean open;
	private Money minOrderAmount;
	private List<MenuItem> menuItems;
	/**
	 * @param shopId
	 * @param shopName
	 * @param open
	 * @param minOrderAmount
	 * @param menuItems
	 */
	public MenuBoard(Shop shop, List<Menu> menus) {
		this.shopId = shop.getId();
		this.shopName = shop.getName();
		this.open = shop.isOpen();
		this.minOrderAmount = shop.getMinOrderAmount();
	/*	this.menuItems = ;*/ 
	}
	
	
	@Data
	public static class MenuItem{
		private Long menuId;
		private String menuName;
		private Money menuBasePrice;
		private String menuDescription;
		
		public MenuItem(Menu menu) {
			this.menuId = menu.getId();
			this.menuName = menu.getName();
		}
	}
}

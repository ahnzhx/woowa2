/**
 * 
 */
package com.woowa.fooddomainservice.service.shop;


import java.util.List;
import java.util.stream.Collectors;

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

	public MenuBoard(Shop shop, List<Menu> menus) {
		this.shopId = shop.getId();
		this.shopName = shop.getName();
		this.open = shop.isOpen();
		this.minOrderAmount = shop.getMinOrderAmount();
		this.menuItems = toMenuItems(menus);
	}

	private List<MenuItem> toMenuItems(List<Menu> menus){
		return menus.stream().map(MenuItem::new).collect(Collectors.toList());
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
			this.menuBasePrice = menu.getBasePrice();
			this.menuDescription = menu.getDescription();
		}
	}
}

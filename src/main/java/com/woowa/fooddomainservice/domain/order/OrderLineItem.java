/**
 * 
 */
package com.woowa.fooddomainservice.domain.order;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.woowa.fooddomainservice.domain.shop.OptionGroup;

import lombok.Builder;
import lombok.Getter;

/**
 * packageName	: com.woowa.fooddomainservice.domain.order
 * fileName	: OrderLineItem.java 
 * author		: twayair
 * date		: 2019. 8. 5.
 * 내용			: 
 *
 * ===========================================================
 *
 * DATE				AUTHOR			NOTE
 * -----------------------------------------------------------
 * 2019. 8. 5.			twayair			최초 생성
 */
@Entity
@Table(name="ORDER_LINE_ITEMS")
@Getter
public class OrderLineItem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ORDER_LINE_ITEM_ID")
	private Long id;
	
	@Column()
	private Long menuId;
	
	private String name;
	private int count;
	private List<OrderOptionGroup> groups = new ArrayList<>();
	
	public OrderLineItem(Long menuId, String name, int count, List<OrderOptionGroup> groups) {
		this(null, menuId, name, count, groups);
	}
	
	@Builder
	public OrderLineItem(Long id, Long menuId, String name, int count, List<OrderOptionGroup> groups) {
		this.id = id;
		this.menuId = menuId;
		this.name = name;
		this.count = count;
		this.groups.addAll(groups);
	}
	
	OrderLineItem(){
	}
	
	public Money calculatePrice() {
		return Money.sum(groups, OrderOptionGroup::calculatePrice).times(count);
	}
	
	private List<OptionGroup> convertToOptionGroups() {
		return groups.stream().map(OrderOptionGroup::convertToOptionGroup).collect(toList());
	}
}

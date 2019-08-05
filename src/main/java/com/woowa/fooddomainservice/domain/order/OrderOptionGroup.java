/**
 * 
 */
package com.woowa.fooddomainservice.domain.order;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.woowa.fooddomainservice.domain.generic.money.Money;
import com.woowa.fooddomainservice.domain.shop.OptionGroup;
import lombok.Builder;
import lombok.Getter;

import static java.util.stream.Collectors.toList;

/**
 * packageName	: com.woowa.fooddomainservice.domain.order
 * fileName	: OrderOptionGroup.java 
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
@Table(name="ORDER_OPTION_GROUPS")
@Getter
public class OrderOptionGroup {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ORDER_OPTION_GROUP_ID")
	private Long id;
	
	@Column(name="NAME")
	private String name;
	
	@ElementCollection
	@CollectionTable(name="ORDER_OPTIONS", joinColumns=@JoinColumn(name="ORDER_OPTION_GROUP_ID"))
	private List<OrderOption> orderOptions;
	
	public OrderOptionGroup(String name, List<OrderOption> options) {
		this(null, name, options);
	}

	/**
	 * @param id
	 * @param name
	 * @param orderOptions
	 */
	@Builder
	public OrderOptionGroup(Long id, String name, List<OrderOption> orderOptions) {
		super();
		this.id = id;
		this.name = name;
		this.orderOptions = orderOptions;
	}
	
	OrderOptionGroup(){
		
	}
	public Money calculatePrice() {
		return Money.sum(orderOptions, OrderOption::getPrice);
	}
	public OptionGroup convertToOptionGroup() {
		return new OptionGroup(name, orderOptions.stream().map(OrderOption::convertToOption).collect(toList()));
	}
	
	
}

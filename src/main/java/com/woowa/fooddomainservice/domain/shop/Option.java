/**
 * 
 */
package com.woowa.fooddomainservice.domain.shop;

import com.woowa.fooddomainservice.domain.generic.money.Money;
import lombok.Builder;
import lombok.Data;

/**
 * packageName	: com.woowa.fooddomainservice.domain.shop
 * fileName	: Option.java 
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
@Data
public class Option {
	private String name;
	private Money price;
	
	@Builder
	public Option(String name, Money price) {
		this.name = name;
		this.price = price;
	}

}

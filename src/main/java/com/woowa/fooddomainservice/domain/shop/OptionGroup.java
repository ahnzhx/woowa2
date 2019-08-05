package com.woowa.fooddomainservice.domain.shop;

import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * packageName	: com.woowa.fooddomainservice.domain.order
 * fileName	: OptionGroup.java 
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
public class OptionGroup {
	private String name;
	private List<Option> options;

	@Builder
	public OptionGroup(String name, List<Option> options) {
		this.name = name;
		this.options = options;
	}
}

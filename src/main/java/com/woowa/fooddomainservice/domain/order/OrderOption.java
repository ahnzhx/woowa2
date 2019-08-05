/**
 * 
 */
package com.woowa.fooddomainservice.domain.order;

import com.woowa.fooddomainservice.domain.generic.money.Money;
import com.woowa.fooddomainservice.domain.shop.Option;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * packageName	: com.woowa.fooddomainservice.domain.order
 * fileName	: OrderOption.java 
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
@Embeddable
@Getter
public class OrderOption {
    @Column(name="NAME")
    private String name;

    @Column(name="PRICE")
    private Money price;

    @Builder
    public OrderOption(String name, Money price){
        this.name = name;
        this.price = price;
    }
    OrderOption(){}

    public Option convertToOption(){
        return new Option(name, price);
    }

}

/**
 * 
 */
package com.woowa.fooddomainservice.billing.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.woowa.fooddomainservice.domain.generic.money.Money;

import lombok.Builder;
import lombok.Getter;

/**
 * packageName	: com.woowa.fooddomainservice.billing.domain
 * fileName	: Billing.java 
 * author		: twayair
 * date		: 2019. 9. 4.
 * 내용			: 
 *
 * ===========================================================
 *
 * DATE				AUTHOR			NOTE
 * -----------------------------------------------------------
 * 2019. 9. 4.			twayair			최초 생성
 */
@Entity
@Table(name="BILLINGS")
@Getter
public class Billing {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="BILLING_ID")
	private Long id;
	
	@Column(name="SHOP_ID")
	private Long shopId;
	
	@Column(name="COMMISSION")
	private Money commission = Money.ZERO;

	public Billing(Long shopId) {
		this(null, shopId, Money.ZERO);
	}
	@Builder
	public Billing(Long id, Long shopId, Money commission) {
		this.id = id;
		this.shopId = shopId;
		this.commission = commission;
	}
	
	Billing(){
		
	}
	
	public void billCommissionFee(Money commission) {
		this.commission = this.commission.plus(commission);
	}
	
}

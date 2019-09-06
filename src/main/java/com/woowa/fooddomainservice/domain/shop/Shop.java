package com.woowa.fooddomainservice.domain.shop;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.woowa.fooddomainservice.domain.generic.money.Money;
import com.woowa.fooddomainservice.domain.generic.money.Ratio;

import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name="SHOPS")
@Getter
public class Shop {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="SHOP_ID")
    private Long id;

    @Column(name="NAME")
    private String name;

    @Column(name="OPEN")
    private boolean open;

    @Column(name="MIN_ORDER_AMOUNT")
    private Money minOrderAmount;

    @Column(name="COMMISSION_RATE")
    private Ratio commissionRate;

    @Column(name="COMMISSION")
    private Money commission = Money.ZERO;

    public Shop(String name, boolean open, Money minOrderAmount){
        this(name, open, minOrderAmount, Ratio.valueOf(0), Money.ZERO);
    }

    public Shop(String name, boolean open, Money minOrderAmount, Ratio commissionRate, Money commission){
        this(null, name, open, minOrderAmount, commissionRate, commission);
    }

    @Builder
    public Shop(Long id, String name, boolean open, Money minOrderAmount, Ratio commissionRate, Money commission) {
        this.id = id;
        this.name = name;
        this.open = open;
        this.minOrderAmount = minOrderAmount;
        this.commissionRate = commissionRate;
        this.commission = commission;
    }

    Shop(){

    }
    public boolean isValidOrderAmount(Money amount){
        return amount.isGreaterThanOrEqual(minOrderAmount);
    }

    public void open(){
        this.open = true;
    }

    public void close(){
        this.open = false;
    }

    public void modifyCommissionRate(Ratio commissionRate){
        this.commissionRate = commissionRate;
    }

    public void billCommissionFee(Money price){
        commission = commission.plus(commissionRate.of(price));
    }
    
    public Money calculateCommissionFee(Money price) {
    	return commissionRate.of(price);
    }
}

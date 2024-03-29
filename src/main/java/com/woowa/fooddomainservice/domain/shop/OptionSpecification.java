package com.woowa.fooddomainservice.domain.shop;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.woowa.fooddomainservice.domain.generic.money.Money;

import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name="OPTION_SPECS")
@Getter
public class OptionSpecification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="OPTION_SPEC_ID")
	private Long id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="PRICE")
	private Money price;
	
	public OptionSpecification(String name, Money price) {
		this(null, name, price);
	}

	@Builder
	public OptionSpecification(Long id, String name, Money price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	OptionSpecification(){
		
	}

	@Override
	public boolean equals(Object object) {
		if(object == null) {
			return false;
		}
		if(!(object instanceof OptionSpecification)) {
			return false;
		}
		OptionSpecification other = (OptionSpecification) object;
		return Objects.equals(name, other.getName()) && Objects.equals(price, other.getPrice());
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, price);
	}
	
	public boolean isSatisfiedBy(Option option) {
		return Objects.equals(name, option.getName()) && Objects.equals(price, option.getPrice());
	}
}

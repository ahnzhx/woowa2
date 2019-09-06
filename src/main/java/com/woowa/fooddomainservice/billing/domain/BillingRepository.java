/**
 * 
 */
package com.woowa.fooddomainservice.billing.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName	: com.woowa.fooddomainservice.billing.domain
 * fileName	: BillingRepository.java 
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
public interface BillingRepository extends JpaRepository<Billing, Long> {
	Optional<Billing> findByShopId(Long shopId);
}

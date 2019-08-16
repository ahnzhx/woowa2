/**
 * 
 */
package com.woowa.fooddomainservice.service.shop;

import com.woowa.fooddomainservice.domain.shop.Menu;
import com.woowa.fooddomainservice.domain.shop.MenuRepository;
import com.woowa.fooddomainservice.domain.shop.Shop;
import com.woowa.fooddomainservice.domain.shop.ShopRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ShopService {
    private ShopRepository shopRepository;
    private MenuRepository menuRepository;

    public ShopService(ShopRepository shopRepository,
                       MenuRepository menuRepository) {
        this.shopRepository = shopRepository;
        this.menuRepository = menuRepository;
    }

    @Transactional(readOnly = true)
    public MenuBoard getMenuBoard(Long shopId){
        Shop shop = shopRepository.findById(shopId).orElseThrow(IllegalArgumentException::new);
        List<Menu> menus = menuRepository.findByShopId(shopId);
        return new MenuBoard(shop, menus);
    }
}

package com.woowa.fooddomainservice.generic.money;

import com.woowa.fooddomainservice.domain.generic.money.Money;
import com.woowa.fooddomainservice.domain.generic.money.Ratio;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RatioTest {

    @Test
    public void 퍼센트(){
        Ratio tenPercent = Ratio.valueOf(0.1);
        Money thousandWon = Money.wons(1000);
        assertThat(tenPercent.of(thousandWon), is(Money.wons(100)));

    }
}

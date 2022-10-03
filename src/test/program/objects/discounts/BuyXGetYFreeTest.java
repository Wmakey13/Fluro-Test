package test.program.objects.discounts;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import main.program.objects.Sku;
import main.program.objects.discounts.BuyXGetYFree;

public class BuyXGetYFreeTest
{
    private final Sku item = new Sku("A", 100);
    private final Sku item2 = new Sku("B", 100);
    private final BuyXGetYFree defaultbuyXGetYFree = new BuyXGetYFree(item, 3, 1);
    private BuyXGetYFree buyXGetYFree;
    private Map<String, Integer> basket = new HashMap<String, Integer>();

    @Test
    public void testBuyXGetYFreeCreatedCorrectly()
    {
        buyXGetYFree = new BuyXGetYFree(item, 4, 1);
        assertEquals(item, buyXGetYFree.getDiscountedItem());
        assertEquals(5, buyXGetYFree.getRequirement());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuyXGetYFreeCreatedWithoutItemThrowsIllegalArgumentException()
    {
        buyXGetYFree = new BuyXGetYFree(null, 4, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuyXGetYFreeCreatedWithTheRequirementOFZeroThrowsIllegalArgumentException()
    {
        buyXGetYFree = new BuyXGetYFree(item, 0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuyXGetYFreeCreatedWithoutAmountFreeThrowsIllegalArgumentException()
    {
        buyXGetYFree = new BuyXGetYFree(item, 4, 0);
    }

    @Test
    public void testBuyXGetYFreeCalculatedCorrectly()
    {
        basket.put(item.getDesignation(), 4);
        assertEquals(100, defaultbuyXGetYFree.calculateDiscount(basket));
    }

    @Test
    public void testBuyXGetYFreeCalculatedCorrectlyWithMultipleOccurences()
    {
        basket.put(item.getDesignation(), 9);
        assertEquals(200, defaultbuyXGetYFree.calculateDiscount(basket));
    }

    @Test
    public void testBuyXGetYFreeCalculatedCorrectlyInAnAssortedList()
    {
        basket.put(item.getDesignation(), 4);
        basket.put(item2.getDesignation(), 3);
        assertEquals(100, defaultbuyXGetYFree.calculateDiscount(basket));
    }
}

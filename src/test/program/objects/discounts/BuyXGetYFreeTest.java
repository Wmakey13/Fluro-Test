package test.program.objects.discounts;

import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import main.program.objects.Item;
import main.program.objects.discounts.BuyXGetYFree;
import main.program.objects.discounts.MultiPrice;

public class BuyXGetYFreeTest
{
    public BuyXGetYFree buyXGetYFree;
    Item item = new Item("A", 100);
    Item item2 = new Item("B", 100);
    Map<Item, Integer> basket = new HashMap<Item, Integer>();

    @Test
    public void testBuyXGetYFreeCreatedCorrectly()
    {
        buyXGetYFree = new BuyXGetYFree(item, 4);
        assertTrue(buyXGetYFree.getDiscountedItem().equals(item));
        assertTrue(buyXGetYFree.getRequirement() == 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuyXGetYFreeCreatedWithoutItemThrowsIllegalArgumentException()
    {
        buyXGetYFree = new BuyXGetYFree(null, 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuyXGetYFreeCreatedWithTheRequirementOFZeroThrowsIllegalArgumentException()
    {
        buyXGetYFree = new BuyXGetYFree(item, 0);
    }

    @Test
    public void testBuyXGetYFreeCalculatedCorrectly()
    {
        buyXGetYFree = new BuyXGetYFree(item, 3);
        basket.put(item, 3);
        assertTrue(buyXGetYFree.checkDiscount(basket) == 100);
    }

    @Test
    public void testBuyXGetYFreeCalculatedCorrectlyWithMultipleOccurences()
    {
        buyXGetYFree = new BuyXGetYFree(item, 3);
        basket.put(item, 9);
        assertTrue(buyXGetYFree.checkDiscount(basket) == 300);
    }

    @Test
    public void testBuyXGetYFreeCalculatedCorrectlyInAnAssortedList()
    {
        buyXGetYFree = new BuyXGetYFree(item, 3);
        basket.put(item, 3);
        basket.put(item2, 3);
        assertTrue(buyXGetYFree.checkDiscount(basket) == 100);
    }
}

package test.program.objects.discounts;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.program.objects.Item;
import main.program.objects.discounts.BuyXGetYFree;
import main.program.objects.discounts.MultiPrice;

public class BuyXGetYFreeTest
{
    public BuyXGetYFree buyXGetYFree;

    @Test
    public void testBuyXGetYFreeCreatedCorrectly()
    {
        Item item = new Item("A", 100);
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
        buyXGetYFree = new BuyXGetYFree(new Item("A", 100), 0);
    }

    @Test
    public void testBuyXGetYFreeCalculatedCorrectly()
    {
        Item item = new Item("B", 100);
        buyXGetYFree = new BuyXGetYFree(item, 3);
        Item[] items = { item, item, item };
        assertTrue(buyXGetYFree.checkDiscount(items) == 100);
    }

    @Test
    public void testBuyXGetYFreeCalculatedCorrectlyWithMultipleOccurences()
    {
        Item item = new Item("A", 100);
        buyXGetYFree = new BuyXGetYFree(item, 3);
        Item[] items = { item, item, item, item, item, item, item, item, item };
        assertTrue(buyXGetYFree.checkDiscount(items) == 300);
    }

    @Test
    public void testBuyXGetYFreeCalculatedCorrectlyInAnAssortedList()
    {
        Item item = new Item("A", 100);
        Item item2 = new Item("B", 150);
        buyXGetYFree = new BuyXGetYFree(item, 3);
        Item[] items = { item, item2, item, item2, item, item2 };
        assertTrue(buyXGetYFree.checkDiscount(items) == 100);
    }
}

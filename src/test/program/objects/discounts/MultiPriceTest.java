package test.program.objects.discounts;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.program.objects.Item;
import main.program.objects.discounts.MultiPrice;

public class MultiPriceTest
{
    public MultiPrice multiPrice;

    @Test
    public void testMultiPriceCreatedCorrectly()
    {
        Item item = new Item("A", 100);
        multiPrice = new MultiPrice(item, 3, 50);
        assertTrue(multiPrice.getDiscountedItem().equals(item));
        assertTrue(multiPrice.getDiscountPrice() == 50);
        assertTrue(multiPrice.getOccurencesPrice() == 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMultiPriceCreatedWithoutItemThrowsIllegalArgumentException()
    {
        multiPrice = new MultiPrice(null, 50, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMultiPriceCreatedWithoutDiscountedPriceThrowsIllegalArgumentException()
    {
        Item item = new Item("A", 100);
        multiPrice = new MultiPrice(item, 0, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMultiPriceCreatedWithoutOccurencesThrowsIllegalArgumentException()
    {
        Item item = new Item("A", 100);
        multiPrice = new MultiPrice(item, 0, 0);
    }

    @Test
    public void testMultiPriceCalculatedCorrectly()
    {
        Item item = new Item("A", 100);
        multiPrice = new MultiPrice(item, 3, 250);
        Item[] items = { item, item, item };
        assertTrue(multiPrice.checkDiscount(items) == 50);
    }

    @Test
    public void testMultiPriceCalculatedCorrectlyWithMultipleOccurences()
    {
        Item item = new Item("A", 100);
        multiPrice = new MultiPrice(item, 3, 250);
        Item[] items = { item, item, item, item, item, item, item, item, item };
        assertTrue(multiPrice.checkDiscount(items) == 150);
    }

    @Test
    public void testMultiPriceCalculatedCorrectlyInAnAssortedList()
    {
        Item item = new Item("A", 100);
        Item item2 = new Item("B", 200);
        multiPrice = new MultiPrice(item, 3, 250);
        Item[] items = { item, item2, item, item2, item, item2 };
        assertTrue(multiPrice.checkDiscount(items) == 50);
    }
}

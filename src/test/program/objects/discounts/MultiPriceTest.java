package test.program.objects.discounts;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import main.program.objects.Item;
import main.program.objects.discounts.MultiPrice;

public class MultiPriceTest
{
    public MultiPrice multiPrice;
    Item item = new Item("A", 100);
    Map<Item, Integer> basket = new HashMap<Item, Integer>();

    @Test
    public void testMultiPriceCreatedCorrectly()
    {
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
        multiPrice = new MultiPrice(item, 0, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMultiPriceCreatedWithoutOccurencesThrowsIllegalArgumentException()
    {
        multiPrice = new MultiPrice(item, 0, 0);
    }

    @Test
    public void testMultiPriceCalculatedCorrectly()
    {
        multiPrice = new MultiPrice(item, 3, 250);
        basket.put(item, 3);
        assertTrue(multiPrice.checkDiscount(basket) == 50);
    }

    @Test
    public void testMultiPriceCalculatedCorrectlyWithMultipleOccurences()
    {
        multiPrice = new MultiPrice(item, 3, 250);
        basket.put(item, 9);
        assertTrue(multiPrice.checkDiscount(basket) == 150);
    }

    @Test
    public void testMultiPriceCalculatedCorrectlyInAnAssortedList()
    {
        Item item2 = new Item("B", 200);
        multiPrice = new MultiPrice(item, 3, 250);
        basket.put(item, 3);
        basket.put(item2, 3);
        assertTrue(multiPrice.checkDiscount(basket) == 50);
    }
}

package test.program.objects.discounts;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.program.objects.Item;
import main.program.objects.discounts.MultiPrice;

public class MultiPriceTest
{
    public MultiPrice multiPrice;

    @Test
    public void testDiscountCreatedCorrectly()
    {
        Item item = new Item("A", 100);
        multiPrice = new MultiPrice(item, 50, 3);
        assertTrue(multiPrice.getDiscountedItem().equals(item));
        assertTrue(multiPrice.getDiscountPrice() == 50);
        assertTrue(multiPrice.getOccurencesPrice() == 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDiscountCreatedWithoutItemThrowsIllegalArgumentException()
    {
        multiPrice = new MultiPrice(null, 50, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDiscountCreatedWithoutDiscountedPriceThrowsIllegalArgumentException()
    {
        Item item = new Item("A", 100);
        multiPrice = new MultiPrice(item, 0, 3);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testDiscountCreatedWithoutOccurencesThrowsIllegalArgumentException()
    {
        Item item = new Item("A", 100);
        multiPrice = new MultiPrice(item, 0, 0);
    }

    @Test
    public void testCorrectDiscount()
    {

    }

    @Test
    public void testCorrectDiscountMultiple()
    {

    }
}

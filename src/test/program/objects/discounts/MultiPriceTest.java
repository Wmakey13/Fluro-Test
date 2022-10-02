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

    @Test
    public void testDiscountCreatedWithoutItemsThrowsIllegalArgumentException()
    {

    }

    @Test
    public void testDiscountCreatedWithoutRequirementThrowsIllegalArgumentException()
    {

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

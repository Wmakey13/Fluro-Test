package test.program.objects.discounts;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.program.objects.Item;
import main.program.objects.discounts.BuyXGetYFree;

public class BuyXGetYFreeTest
{
    public BuyXGetYFree buyXGetYFree;

    @Test
    public void testDiscountCreatedCorrectly()
    {
        Item item = new Item("A", 100);
        buyXGetYFree = new BuyXGetYFree(item, 4);
        assertTrue(buyXGetYFree.getDiscountedItem().equals(item));
        assertTrue(buyXGetYFree.getRequirement() == 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDiscountCreatedWithoutItemThrowsIllegalArgumentException()
    {
        buyXGetYFree = new BuyXGetYFree(null, 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDiscountCreatedWithoutRequirementThrowsIllegalArgumentException()
    {
        buyXGetYFree = new BuyXGetYFree(new Item("A", 100), 0);
    }

    @Test
    public void testCorrectDiscount()
    {
    }

    @Test
    public void testCorrectDiscountMultipleOccurences()
    {
    }
}

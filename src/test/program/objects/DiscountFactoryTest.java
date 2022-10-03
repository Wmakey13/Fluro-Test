package test.program.objects;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.program.objects.Discount;
import main.program.objects.DiscountFactory;
import main.program.objects.Item;
import main.program.objects.discounts.BuyXGetYFree;
import main.program.objects.discounts.MealDeal;
import main.program.objects.discounts.MultiPrice;

public class DiscountFactoryTest
{
    Item item = new Item("A", 100);
    DiscountFactory discountFactory = new DiscountFactory();

    @Test
    public void testBuyXGetYFreeConstructsCorrectly()
    {
        Item[] items = { item };
        Integer[] details = { 100 };
        Discount discount = discountFactory.createDiscount("BuyXGetYFree", items, details);
        assertTrue(discount instanceof BuyXGetYFree);
    }

    @Test
    public void testMealDealConstructsCorrectly()
    {
        Item item2 = new Item("B", 200);
        Item[] items = { item, item2 };
        Integer[] details = { 100 };
        Discount discount = discountFactory.createDiscount("MealDeal", items, details);
        assertTrue(discount instanceof MealDeal);
    }

    @Test
    public void testMultiPriceConstructsCorrectly()
    {
        Item item2 = new Item("B", 200);
        Item[] items = { item };
        Integer[] details = { 3, 100 };
        Discount discount = discountFactory.createDiscount("MultiPrice", items, details);
        assertTrue(discount instanceof MultiPrice);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuyXGetYFreeThrowsIllegalArgumentExceptionWithWrongArguments()
    {
        Item[] items = { null };
        Integer[] details = { 100 };
        Discount discount = discountFactory.createDiscount("BuyXGetYFree", items, details);
        assertTrue(discount instanceof BuyXGetYFree);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testtMealDealThrowsIllegalArgumentExceptionWithWrongArguments()
    {
        Item[] items = { item };
        Integer[] details = { 100 };
        Discount discount = discountFactory.createDiscount("MealDeal", items, details);
        assertTrue(discount instanceof BuyXGetYFree);
    }

    @Test
    public void testtMultiPriceThrowsIllegalArgumentExceptionWithWrongArguments()
    {
        Item[] items = { item };
        Integer[] details = { 100 };
        Discount discount = discountFactory.createDiscount("MultiPrice", items, details);
        assertNull(discount);
    }
}

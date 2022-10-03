package test.program.objects;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.program.objects.Discount;
import main.program.objects.DiscountFactory;
import main.program.objects.Sku;
import main.program.objects.discounts.BuyXGetYFree;
import main.program.objects.discounts.MealDeal;
import main.program.objects.discounts.MultiPrice;

public class DiscountFactoryTest
{
    private final Sku item = new Sku("A", 100);
    private final DiscountFactory discountFactory = new DiscountFactory();
    private final Sku[] items = { item };
    private final Integer[] details = { 100 };

    @Test
    public void testBuyXGetYFreeConstructsCorrectly()
    {
        Integer[] twoDetails = { 3, 1 };
        Discount discount = discountFactory.createDiscount("BuyXGetYFree", items, twoDetails);
        assertTrue(discount instanceof BuyXGetYFree);
    }

    @Test
    public void testMealDealConstructsCorrectly()
    {
        Sku item2 = new Sku("B", 200);
        Sku[] items2 = { item, item2 };
        Discount discount = discountFactory.createDiscount("MealDeal", items2, details);
        assertTrue(discount instanceof MealDeal);
    }

    @Test
    public void testMultiPriceConstructsCorrectly()
    {
        Integer[] twoDetails = { 3, 100 };
        Discount discount = discountFactory.createDiscount("MultiPrice", items, twoDetails);
        assertTrue(discount instanceof MultiPrice);
    }

    @Test
    public void testBuyXGetYFreeWithInsufficientArgumentsReturnsNull()
    {
        assertNull(discountFactory.createDiscount("BuyXGetYFree", items, details));
    }

    @Test
    public void testMultiPriceWithInsufficientArgumentsReturnsNull()
    {
        assertNull(discountFactory.createDiscount("MultiPrice", items, details));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuyXGetYFreeThrowsIllegalArgumentExceptionWithWrongArguments()
    {
        Sku[] itemsNull = { null };
        Integer[] details = { 3, 1 };
        Discount discount = discountFactory.createDiscount("BuyXGetYFree", itemsNull, details);
        assertTrue(discount instanceof BuyXGetYFree);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testtMealDealThrowsIllegalArgumentExceptionWithWrongArguments()
    {
        Discount discount = discountFactory.createDiscount("MealDeal", items, details);
        assertTrue(discount instanceof BuyXGetYFree);
    }

    @Test
    public void testtMultiPriceThrowsIllegalArgumentExceptionWithWrongArguments()
    {
        Discount discount = discountFactory.createDiscount("MultiPrice", items, details);
        assertNull(discount);
    }
}

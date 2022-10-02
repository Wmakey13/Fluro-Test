package test.program.objects.discounts;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.program.objects.Item;
import main.program.objects.discounts.MealDeal;

public class MealDealTest
{
    public MealDeal mealDeal;

    @Test
    public void testDiscountCreatedCorrectly()
    {
        Item item = new Item("A", 100);
        Item item2 = new Item("B", 200);
        mealDeal = new MealDeal(new Item[] { item, item2 }, 250);
        Item[] mealDealItems = mealDeal.getMealDealItems();
        assertTrue(mealDealItems[0].equals(item));
        assertTrue(mealDealItems[1].equals(item2));
        assertTrue(mealDeal.getMealDealPrice() == 250);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDiscountCreatedWithoutItemsThrowsIllegalArgumentException()
    {
        mealDeal = new MealDeal(null, 250);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDiscountCreatedWithNullItemsThrowsIllegalArgumentException()
    {
        Item item = new Item("A", 100);
        mealDeal = new MealDeal(new Item[] { item, null }, 250);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDiscountCreatedWithoutRequirementThrowsIllegalArgumentException()
    {
        Item item = new Item("A", 100);
        Item item2 = new Item("B", 200);
        mealDeal = new MealDeal(new Item[] { item, item2 }, 0);
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

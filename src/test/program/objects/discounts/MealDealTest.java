package test.program.objects.discounts;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.program.objects.Item;
import main.program.objects.discounts.MealDeal;
import main.program.objects.discounts.MultiPrice;

public class MealDealTest
{
    public MealDeal mealDeal;

    @Test
    public void testMealDealCreatedCorrectly()
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
    public void testMealDealCreatedWithoutItemsThrowsIllegalArgumentException()
    {
        mealDeal = new MealDeal(null, 250);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMealDealCreatedWithNullItemsThrowsIllegalArgumentException()
    {
        Item item = new Item("A", 100);
        mealDeal = new MealDeal(new Item[] { item, null }, 250);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMealDealCreatedWithoutRequirementThrowsIllegalArgumentException()
    {
        Item item = new Item("A", 100);
        Item item2 = new Item("B", 200);
        mealDeal = new MealDeal(new Item[] { item, item2 }, 0);
    }

    @Test
    public void testMealDealCalculatedCorrectly()
    {
        Item item = new Item("A", 100);
        Item item2 = new Item("B", 200);
        mealDeal = new MealDeal(new Item[] { item, item2 }, 250);
        Item[] items = { item, item2 };
        assertTrue(mealDeal.checkDiscount(items) == 50);
    }

    @Test
    public void testMealDealCalculatedCorrectlyWithMultipleOccurences()
    {
        Item item = new Item("A", 100);
        Item item2 = new Item("B", 200);
        mealDeal = new MealDeal(new Item[] { item, item2 }, 250);
        Item[] items = { item, item2, item, item2, item, item2, item, item2 };
        assertTrue(mealDeal.checkDiscount(items) == 200);
    }
    
    @Test
    public void testMealDealCalculatedCorrectlyInAnAssortedList()
    {
        Item item = new Item("A", 100);
        Item item2 = new Item("B", 200);
        Item item3 = new Item("C", 300);
        mealDeal = new MealDeal(new Item[] { item, item2 }, 250);
        Item[] items = {item3, item, item3, item2 };
        assertTrue(mealDeal.checkDiscount(items) == 50);
    }
}

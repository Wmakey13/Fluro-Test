package test.program.objects.discounts;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import main.program.objects.Item;
import main.program.objects.discounts.MealDeal;
import main.program.objects.discounts.MultiPrice;

public class MealDealTest
{
    public MealDeal mealDeal;
    Item item = new Item("A", 100);
    Item item2 = new Item("B", 200);
    Map<Item, Integer> basket = new HashMap<Item, Integer>();

    @Test
    public void testMealDealCreatedCorrectly()
    {
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
        mealDeal = new MealDeal(new Item[] { item, null }, 250);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMealDealCreatedWithoutRequirementThrowsIllegalArgumentException()
    {
        mealDeal = new MealDeal(new Item[] { item, item2 }, 0);
    }

    @Test
    public void testMealDealCalculatedCorrectly()
    {
        mealDeal = new MealDeal(new Item[] { item, item2 }, 250);
        basket.put(item, 1);
        basket.put(item2, 1);
        assertTrue(mealDeal.checkDiscount(basket) == 50);
    }

    @Test
    public void testMealDealCalculatedCorrectlyWithMultipleOccurences()
    {
        mealDeal = new MealDeal(new Item[] { item, item2 }, 250);
        basket.put(item, 3);
        basket.put(item2, 3);
        assertTrue(mealDeal.checkDiscount(basket) == 150);
    }

    @Test
    public void testMealDealCalculatedCorrectlyInAnAssortedList()
    {
        Item item3 = new Item("C", 300);
        mealDeal = new MealDeal(new Item[] { item, item2 }, 250);
        basket.put(item, 1);
        basket.put(item2, 1);
        basket.put(item3, 4);
        assertTrue(mealDeal.checkDiscount(basket) == 50);
    }
}

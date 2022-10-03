package test.program.objects.discounts;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import main.program.objects.Sku;
import main.program.objects.discounts.MealDeal;

public class MealDealTest
{
    private final Sku item = new Sku("A", 100);
    private final Sku item2 = new Sku("B", 200);
    private MealDeal mealDeal;
    private Map<String, Integer> basket = new HashMap<String, Integer>();

    @Test
    public void testMealDealCreatedCorrectly()
    {
        mealDeal = new MealDeal(new Sku[] { item, item2 }, 250);
        Sku[] mealDealItems = mealDeal.getMealDealItems();
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
        mealDeal = new MealDeal(new Sku[] { item, null }, 250);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMealDealCreatedWithoutRequirementThrowsIllegalArgumentException()
    {
        mealDeal = new MealDeal(new Sku[] { item, item2 }, 0);
    }

    @Test
    public void testMealDealCalculatedCorrectly()
    {
        mealDeal = new MealDeal(new Sku[] { item, item2 }, 250);
        basket.put(item.getDesignation(), 1);
        basket.put(item2.getDesignation(), 1);
        assertEquals(mealDeal.calculateDiscount(basket), 50);
    }

    @Test
    public void testMealDealCalculatedCorrectlyWithMultipleOccurences()
    {
        mealDeal = new MealDeal(new Sku[] { item, item2 }, 250);
        basket.put(item.getDesignation(), 3);
        basket.put(item2.getDesignation(), 3);
        assertEquals(mealDeal.calculateDiscount(basket), 150);
    }

    @Test
    public void testMealDealCalculatedCorrectlyInAnAssortedList()
    {
        Sku item3 = new Sku("C", 300);
        mealDeal = new MealDeal(new Sku[] { item, item2 }, 250);
        basket.put(item.getDesignation(), 1);
        basket.put(item2.getDesignation(), 1);
        basket.put(item3.getDesignation(), 4);
        assertEquals(mealDeal.calculateDiscount(basket), 50);
    }
}

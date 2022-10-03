package main.program.objects.discounts;

import java.util.Map;

import main.program.objects.Discount;
import main.program.objects.Sku;

public class MealDeal implements Discount
{
    private final Sku[] mealDealItems;
    private final int mealDealPrice;

    public MealDeal(Sku[] mealDealItems, int mealDealPrice)
    {
        this.mealDealItems = mealDealItems;
        this.mealDealPrice = mealDealPrice;
        validate();
    }

    @Override
    public int calculateDiscount(Map<String, Integer> items)
    {
        int numberOfTimes = Math.min(items.get(mealDealItems[0].getDesignation()),
                items.get(mealDealItems[1].getDesignation()));

        return ((numberOfTimes * mealDealItems[0].getPrice()) + (numberOfTimes * mealDealItems[1].getPrice()))
                - (mealDealPrice * numberOfTimes);
    }

    @Override
    public void validate()
    {
        if (mealDealItems == null || mealDealItems.length < 2 || mealDealItems[0] == null || mealDealItems[1] == null
                || mealDealPrice <= 0)
        {
            throw new IllegalArgumentException("Arguments aren't correctly filled");
        }
    }
    
    // testing purposes
    public Sku[] getMealDealItems()
    {
        return mealDealItems;
    }

    public int getMealDealPrice()
    {
        return mealDealPrice;
    }
}

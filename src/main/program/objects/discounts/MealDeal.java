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
        String designationA = mealDealItems[0].getDesignation();
        String designationB = mealDealItems[1].getDesignation();
        
        int mealDealItemOneAppearances = items.containsKey(designationA) ? items.get(designationA) : 0;
        int mealDealItemTwoAppearances = items.containsKey(designationB) ? items.get(designationB) : 0;

        int numberOfTimes = Math.min(mealDealItemOneAppearances, mealDealItemTwoAppearances);

        return numberOfTimes > 0
                ? ((numberOfTimes * mealDealItems[0].getPrice()) + (numberOfTimes * mealDealItems[1].getPrice()))
                        - (mealDealPrice * numberOfTimes)
                : 0;
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

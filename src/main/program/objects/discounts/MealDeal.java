package main.program.objects.discounts;

import main.program.objects.Discount;
import main.program.objects.Item;

public class MealDeal implements Discount
{
    Item[] mealDealItems;
    int mealDealPrice;

    public MealDeal(Item[] mealDealItems, int mealDealPrice)
    {
        this.mealDealItems = mealDealItems;
        this.mealDealPrice = mealDealPrice;
        validate();
    }

    @Override
    public int checkDiscount(Item[] items)
    {
        int countItemA = 0;
        int countItemB = 0;

        for (Item selectedItem : items)
        {
            if (mealDealItems[0].equals(selectedItem))
            {
                countItemA++;
            }
            if (mealDealItems[1].equals(selectedItem))
            {
                countItemB++;
            }
        }

        int numberOfTimes = Math.min(countItemA, countItemB);

        return ((numberOfTimes * mealDealItems[0].getPrice()) + (numberOfTimes * mealDealItems[1].getPrice()))
                - (mealDealPrice * numberOfTimes);
    }

    // testing purposes
    public Item[] getMealDealItems()
    {
        return mealDealItems;
    }

    public int getMealDealPrice()
    {
        return mealDealPrice;
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

}

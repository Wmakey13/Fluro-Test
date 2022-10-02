package main.program.objects.discounts;

import main.program.objects.Discount;
import main.program.objects.Item;

public class MealDeal implements Discount
{
    Item[] mealDealItems;
    int mealDealPrice;

    public MealDeal(Item[] mealDealItems, int price)
    {
        this.mealDealItems = mealDealItems;
        this.mealDealPrice = price;
    }

    @Override
    public int checkDiscount(Item[] items)
    {
    }

    //testing purposes
    public Item[] getMealDealItems() {
        return mealDealItems;
    }
    
    public int getMealDealPrice() {
        return mealDealPrice;
    }
    
}

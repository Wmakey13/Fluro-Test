package main.program.objects;

import main.program.objects.discounts.BuyXGetYFree;
import main.program.objects.discounts.MealDeal;
import main.program.objects.discounts.MultiPrice;

public class DiscountFactory
{
    public DiscountFactory()
    {

    }

    public BuyXGetYFree getNewBuyXGetYFree(Item item, int requirement)
    {
        return new BuyXGetYFree(item, requirement);
    }

    public MealDeal getMealDeal(Item[] mealDealItems, int mealDealPrice)
    {
        return new MealDeal(mealDealItems, mealDealPrice);
    }

    public MultiPrice getMultiPrice(Item item, int occurences, int discount)
    {
        return new MultiPrice(item, occurences, discount);
    }
}

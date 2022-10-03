package main.program.objects;

import main.program.objects.discounts.BuyXGetYFree;
import main.program.objects.discounts.MealDeal;
import main.program.objects.discounts.MultiPrice;

public class DiscountFactory
{
    public Discount createDiscount(String discountType, Item[] items, Integer[] details) throws IllegalArgumentException
    {
        if (discountType.equals("BuyXGetYFree"))
        {
            return new BuyXGetYFree(items[0], details[0]);
        }
        else if (discountType.equals("MealDeal"))
        {
            return new MealDeal(items, details[0]);
        }
        else if (discountType.equals("MultiPrice") && details.length == 2)
        {
            return new MultiPrice(items[0], details[0], details[1]);
        }
        else
        {
            return null;
        }
    }
}

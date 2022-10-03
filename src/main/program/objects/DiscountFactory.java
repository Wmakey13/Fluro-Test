package main.program.objects;

import main.program.objects.discounts.BuyXGetYFree;
import main.program.objects.discounts.MealDeal;
import main.program.objects.discounts.MultiPrice;

public class DiscountFactory
{
    public Discount createDiscount(String discountType, Sku[] items, Integer[] details) throws IllegalArgumentException
    {
        if (validate(discountType, items, details))
        {
            if (discountType.equals("BuyXGetYFree") && details.length == 2)
            {
                return new BuyXGetYFree(items[0], details[0], details[1]);
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
        return null;
    }

    public boolean validate(String discountType, Sku[] items, Integer[] details)
    {
        return discountType != null && !discountType.isEmpty() && items != null & details != null;
    }

}

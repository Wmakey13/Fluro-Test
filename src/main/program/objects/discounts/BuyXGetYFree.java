package main.program.objects.discounts;

import main.program.objects.Discount;
import main.program.objects.Item;

public class BuyXGetYFree implements Discount
{
    Item discountItem;
    int requirement = 0;

    public BuyXGetYFree(Item item, int requirement)
    {
        this.discountItem = item;
        this.requirement = requirement;
    }

    @Override
    public int checkDiscount(Item[] items)
    {

    }

    // for testing purposes
    public Item getDiscountedItem()
    {
        return discountItem;
    }

    public int getRequirement()
    {
        return requirement;
    }
}

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
        validate();
    }

    @Override
    public int checkDiscount(Item[] items)
    {
        int count = 0;
        for (Item item : items)
        {
            if (item.equals(discountItem))
            {
                count++;
            }
        }

        int timesApplied = count/ requirement;

        return discountItem.getPrice() * timesApplied;
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

    @Override
    public void validate()
    {
        if (discountItem == null || requirement <= 0)
        {
            throw new IllegalArgumentException("Arguments aren't correctly filled");
        }
    }
}

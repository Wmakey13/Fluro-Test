package main.program.objects.discounts;

import java.util.Map;

import main.program.objects.Discount;
import main.program.objects.Sku;

public class BuyXGetYFree implements Discount
{
    private final Sku discountItemSKU;
    private final int requirement;
    private final int free;

    public BuyXGetYFree(Sku item, int requirement, int free)
    {
        this.discountItemSKU = item;
        this.requirement = requirement + 1;
        // Plus 1 because if you add three to basket without the fourth the discount shouldn't be applied
        this.free = free;
        validate();
    }

    @Override
    public int calculateDiscount(Map<String, Integer> items)
    {

        int appearances = items.containsKey(discountItemSKU.getDesignation())
                ? items.get(discountItemSKU.getDesignation())
                : 0;
        
        if (appearances > 0)
        {
            int timesApplied = appearances / requirement;
            return discountItemSKU.getPrice() * free * timesApplied;
        }

        return 0;
    }

    @Override
    public void validate()
    {
        if (discountItemSKU == null || requirement <= 1 || free <= 0)
        {
            throw new IllegalArgumentException("Arguments aren't correctly filled");
        }
    }

    // for testing purposes
    public Sku getDiscountedItem()
    {
        return discountItemSKU;
    }

    public int getRequirement()
    {
        return requirement;
    }
}

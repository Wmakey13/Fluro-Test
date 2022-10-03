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
        this.requirement = requirement;
        this.free = free;
        validate();
    }

    @Override
    public int calculateDiscount(Map<String, Integer> items)
    {

        int timesApplied = items.get(discountItemSKU.getDesignation()) / requirement;

        return discountItemSKU.getPrice() * timesApplied;
    }

    @Override
    public void validate()
    {
        if (discountItemSKU == null || requirement <= 0 || free <= 0)
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

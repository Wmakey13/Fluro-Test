package main.program.objects.discounts;

import java.util.Map;

import main.program.objects.Discount;
import main.program.objects.Sku;

public class MultiPrice implements Discount
{
    private final Sku discountedItem;
    private final int discount;
    private final int occurences;

    public MultiPrice(Sku item, int occurences, int discount)
    {
        this.discountedItem = item;
        this.discount = discount;
        this.occurences = occurences;
        validate();
    }

    @Override
    public int calculateDiscount(Map<String, Integer> items)
    {
        Integer counter = items.get(discountedItem.getDesignation());
        return (discountedItem.getPrice() * counter) - (discount * (counter / occurences));
    }

    @Override
    public void validate()
    {
        if (discountedItem == null || occurences <= 0 || discount <= 0)
        {
            throw new IllegalArgumentException("Arguments aren't correctly filled");
        }
    }

    // Testing Purposes
    public Sku getDiscountedItem()
    {
        return discountedItem;
    }

    public int getDiscountPrice()
    {
        return discount;
    }

    public int getOccurencesPrice()
    {
        return occurences;
    }
}

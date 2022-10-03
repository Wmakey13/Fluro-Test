package main.program.objects.discounts;

import java.util.List;
import java.util.Map;

import main.program.objects.Discount;
import main.program.objects.Item;

public class MultiPrice implements Discount
{
    Item discountedItem;
    int discount;
    int occurences;

    public MultiPrice(Item item, int occurences, int discount)
    {
        this.discountedItem = item;
        this.discount = discount;
        this.occurences = occurences;
        validate();
    }

    @Override
    public int checkDiscount(Map<String, Integer> items)
    {
        Integer counter = items.get(discountedItem.getDesignation());
        return (discountedItem.getPrice() * counter) - (discount * (counter / occurences));
    }

    // Testing Purposes
    // testing purposes
    public Item getDiscountedItem()
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

    @Override
    public void validate()
    {
        if (discountedItem == null || occurences <= 0 || discount <= 0)
        {
            throw new IllegalArgumentException("Arguments aren't correctly filled");
        }
    }
}

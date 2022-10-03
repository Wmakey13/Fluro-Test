package main.program.objects.discounts;

import java.util.List;
import java.util.Map;

import main.program.objects.Discount;
import main.program.objects.Item;

public class MultiPrice implements Discount
{
    Item item;
    int discount;
    int occurences;

    public MultiPrice(Item item, int occurences, int discount)
    {
        this.item = item;
        this.discount = discount;
        this.occurences = occurences;
        validate();
    }

    @Override
    public int checkDiscount(Map<Item, Integer> items)
    {
        Integer counter = items.get(item);
        return (item.getPrice() * counter) - (discount * (counter / occurences));
    }

    // Testing Purposes
    // testing purposes
    public Item getDiscountedItem()
    {
        return item;
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
        if (item == null || occurences <= 0 || discount <= 0)
        {
            throw new IllegalArgumentException("Arguments aren't correctly filled");
        }
    }
}

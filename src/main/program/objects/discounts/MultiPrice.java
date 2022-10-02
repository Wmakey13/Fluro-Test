package main.program.objects.discounts;

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
    public int checkDiscount(Item[] items)
    {
        int timesOccuring = 0;
        int totalNo = 0;
        int counter = 0;

        for (Item selectedItem : items)
        {
            if (selectedItem.equals(item))
            {
                counter++;
                totalNo++;
            }
            if (counter == occurences)
            {
                timesOccuring++;
                counter = 0;
            }
        }

        return (item.getPrice() * totalNo) - (discount * timesOccuring);
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

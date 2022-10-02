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
    }

    @Override
    public int checkDiscount(Item[] items)
    {
    }
    
    
    //Testing Purposes
    //testing purposes
    public Item getDiscountedItem() {
        return item;
    }
    
    public int getDiscountPrice() {
        return discount;
    }
    
    public int getOccurencesPrice() {
        return occurences;
    }
}

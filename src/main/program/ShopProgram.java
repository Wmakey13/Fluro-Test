package main.program;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import main.program.objects.Discount;
import main.program.objects.DiscountFactory;
import main.program.objects.Item;

public class ShopProgram
{
    Map<String, Item> items = new HashMap<String, Item>();
    DiscountFactory discountFactory = new DiscountFactory();
    ArrayList<Discount> discounts;
    String[] basket;

    public static void main(String[] args)
    {

    }

    public void addItemToItems(String designation, int priceInPence)
    {
        try
        {
            Item item = new Item(designation, priceInPence);
            items.put(designation, item);
        }
        catch (IllegalArgumentException illegalArgumentException)
        {
            System.out.println(illegalArgumentException.getMessage());
        }
    }

    public void addDiscountToDiscounts(String discountType, Item[] item, Integer[] details)
    {
        discounts.add(discountFactory.createDiscount(discountType, item, details));
    }

    public int calculatePrices(String[] selectedItems)
    {
        Map<Item, Integer> itemsByOccurence = new HashMap<Item, Integer>();

        int price = 0;
        int moneyToRemove = 0;

        for (String itemString : selectedItems)
        {
            Item item = items.get(itemString);
            price = price + item.getPrice();
            if (itemsByOccurence.containsKey(item))
            {
                itemsByOccurence.put(item, itemsByOccurence.get(item) + 1);
            }
            else
            {
                itemsByOccurence.put(item, 1);
            }
        }

        for (Discount discount : discounts)
        {
            moneyToRemove = discount.checkDiscount(itemsByOccurence);
        }

        return price - moneyToRemove;
    }

    // For Testing Purposes
    public Map<String, Item> returnItems()
    {
        return items;
    }

    public ArrayList<Discount> returnDiscounts()
    {
        return discounts;
    }

}

package main.program;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import main.program.objects.Discount;
import main.program.objects.Item;

public class ShopProgram
{
    Map<String, Item> items = new HashMap<String, Item>();
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

    public void addDiscountToDiscounts()
    {

    }

    public void addItemToBasket(String designation)
    {

    }

    public void applyDiscounts(String[] selectedItems)
    {

    }

    public int calculatePrices(String[] selectedItems)
    {
        int price = 0;
        for (String item : selectedItems)
        {
            price = price + items.get(item).getPrice();
        }
        // for item in list items, add price
        // applyDiscounts(String[]) items
        return price;
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

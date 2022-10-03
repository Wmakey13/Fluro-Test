package main.program;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import main.program.objects.Discount;
import main.program.objects.DiscountFactory;
import main.program.objects.Sku;

public class ShopProgram
{
    private Map<String, Sku> items;
    private DiscountFactory discountFactory;
    private ArrayList<Discount> discounts;

    public ShopProgram()
    {
        items = new HashMap<String, Sku>();
        discountFactory = new DiscountFactory();
        discounts = new ArrayList<Discount>();
    }

    public void addItemsToShopMenu(String designation, int priceInPence)
    {
        try
        {
            Sku item = new Sku(designation, priceInPence);
            items.put(designation, item);
        }
        catch (IllegalArgumentException illegalArgumentException)
        {
            System.out.println(illegalArgumentException.getMessage());
        }
    }

    public void addDiscountToDiscountsList(String discountType, Sku[] item, Integer[] details)
    {
        Discount discount = discountFactory.createDiscount(discountType, item, details);
        if (discount != null)
        {
            discounts.add(discount);
        }
    }

    public int calculatePrices(String[] selectedItems)
    {
        Map<String, Integer> itemsByOccurence = new HashMap<String, Integer>();

        int price = 0;
        int accumulatedDiscount = 0;

        for (String itemString : selectedItems)
        {
            Sku item = items.get(itemString);
            price = price + item.getPrice();
            if (itemsByOccurence.containsKey(itemString))
            {
                itemsByOccurence.put(itemString, itemsByOccurence.get(itemString) + 1);
            }
            else
            {
                itemsByOccurence.put(itemString, 1);
            }
        }

        if (!discounts.isEmpty())
        {
            for (Discount discount : discounts)
            {
                accumulatedDiscount = discount.calculateDiscount(itemsByOccurence);
            }
        }
        return price - accumulatedDiscount;
    }
    
    public int updateRulesAndDiscountsAndCalculatePrices(ArrayList<String> newPricesAndDiscounts) {
        return 0;
        
    }

    // For Testing Purposes
    public Map<String, Sku> getSKUs()
    {
        return items;
    }

    public ArrayList<Discount> getDiscounts()
    {
        return discounts;
    }
}

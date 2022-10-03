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

    public void removeItemsFromShopMenu(String designation)
    {
        if (items.containsKey(designation))
        {
            items.remove(designation);
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
                accumulatedDiscount = accumulatedDiscount + discount.calculateDiscount(itemsByOccurence);
            }
        }
        return price - accumulatedDiscount;
    }

    public int updateRulesAndDiscountsAndCalculatePrices(ArrayList<String> newPricesAndDiscounts)
    {
        return updateRulesAndDiscountsAndCalculatePrices(newPricesAndDiscounts, true, false);
    }

    public int updateRulesAndDiscountsAndCalculatePrices(ArrayList<String> newPricesAndDiscounts,
            boolean clearDiscounts, boolean clearItems)
    {
        if (clearItems)
        {
            items = new HashMap<String, Sku>();
        }
        if (clearDiscounts)
        {
            discounts = new ArrayList<Discount>();
        }

        return calculatePrices(parseString(newPricesAndDiscounts));
    }

    private String[] parseString(ArrayList<String> newPricesAndDiscounts)
    {
        String[] basket = null;
        for (String information : newPricesAndDiscounts)
        {
            String[] info = information.split(":");
            if (info[0].equals("Item"))
            {
                addItemsToShopMenu(info[1], Integer.parseInt(info[2]));
            }
            else if (info[0].equals("Basket"))
            {
                basket = info[1].split("\\|");
            }
            else
            {
                Sku[] newItems;
                Integer[] details;
                if (info[1].contains("|"))
                {
                    String[] itemSplit = info[1].split("\\|");
                    newItems = new Sku[] { items.get(itemSplit[0]), items.get(itemSplit[1]) };
                }
                else
                {
                    newItems = new Sku[] { items.get(info[1]) };
                }
                if (info[2].contains("|"))
                {
                    String[] intSplit = info[2].split("\\|");
                    details = new Integer[] { Integer.parseInt(intSplit[0]), Integer.parseInt(intSplit[1]) };
                }
                else
                {
                    details = new Integer[] { Integer.parseInt(info[2]) };
                }

                addDiscountToDiscountsList(info[0], newItems, details);
            }
        }
        return basket;
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

package test.program;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import main.program.ShopProgram;
import main.program.objects.Sku;

public class ShopProgramTest
{
    private static final Sku item = new Sku("A", 100);
    private static final Sku item2 = new Sku("B", 200);
    private ShopProgram shopProgram;
    private ArrayList<String> pricesAndRules;

    @Before
    public void setUp()
    {
        shopProgram = new ShopProgram();

        shopProgram.addItemsToShopMenu("A", 100);
        shopProgram.addItemsToShopMenu("B", 200);
        shopProgram.addItemsToShopMenu("C", 300);
        shopProgram.addItemsToShopMenu("D", 400);
        shopProgram.addItemsToShopMenu("E", 500);
    }

    @Test
    public void testItemsCanBeAddedToItemList()
    {
        assertEquals(shopProgram.getSKUs().size(), 5);
    }

    @Test
    public void testItemIsNotAddedIfItemIsInvalid()
    {
        shopProgram = new ShopProgram();

        shopProgram.addItemsToShopMenu("A", 0);
        shopProgram.addItemsToShopMenu(null, 200);
        shopProgram.addItemsToShopMenu("C", 300);

        Map<String, Sku> itemsList = shopProgram.getSKUs();

        assertEquals(itemsList.size(), 1);
        assertTrue(itemsList.containsKey("C"));
        assertFalse(itemsList.containsKey("A"));
    }

    @Test
    public void testItemIsOverwrittenWhenSameDesignationIsUsed()
    {
        shopProgram = new ShopProgram();

        shopProgram.addItemsToShopMenu("A", 100);
        shopProgram.addItemsToShopMenu("A", 140);

        Map<String, Sku> itemsList = shopProgram.getSKUs();

        assertEquals(itemsList.size(), 1);
        assertEquals(itemsList.get("A").getPrice(), 140);
    }

    @Test
    public void testDiscountNotAddedIfNull()
    {
        shopProgram = new ShopProgram();
        shopProgram.addDiscountToDiscountsList("BuyXGetYFree", new Sku[] { item }, new Integer[] { 5, 2 });
        shopProgram.addDiscountToDiscountsList(null, null, null);
        assertEquals(shopProgram.getDiscounts().size(), 1);
    }

    @Test
    public void testCalculatePriceReturnsTheRightPrice()
    {
        String[] items = { "A", "B", "B" };
        assertEquals(shopProgram.calculatePrices(items), 500);
    }

    @Test
    public void testBuyXGetYFreeDiscountIsAppliedCorrectly()
    {
        shopProgram.addDiscountToDiscountsList("BuyXGetYFree", new Sku[] { item }, new Integer[] { 3, 1 });

        String[] items = { "A", "A", "A" };
        assertEquals(shopProgram.calculatePrices(items), 200);
    }

    @Test
    public void testBuyXGetYFreeDiscountIsAppliedCorrectlyWhenItemsAreApart()
    {
        shopProgram.addDiscountToDiscountsList("BuyXGetYFree", new Sku[] { item }, new Integer[] { 3, 1 });

        String[] items = { "A", "B", "A", "B", "A" };
        assertEquals(shopProgram.calculatePrices(items), 600);
    }

    @Test
    public void testMealDealDiscountIsAppliedCorrectly()
    {
        shopProgram.addDiscountToDiscountsList("MealDeal", new Sku[] { item, item2 }, new Integer[] { 250 });

        String[] items = { "A", "B" };
        assertEquals(shopProgram.calculatePrices(items), 250);
    }

    @Test
    public void testMultiPriceDiscountIsAppliedCorrectly()
    {
        shopProgram.addDiscountToDiscountsList("MultiPrice", new Sku[] { item }, new Integer[] { 3, 275 });

        String[] items = { "A", "A", "A" };
        assertEquals(shopProgram.calculatePrices(items), 275);
    }

    @Test
    public void testMultiPriceDiscountIsAppliedCorrectlyWhenItemsAreApart()
    {
        shopProgram.addDiscountToDiscountsList("MultiPrice", new Sku[] { item }, new Integer[] { 3, 275 });

        String[] items = { "A", "B", "A", "B", "A" };
        assertEquals(shopProgram.calculatePrices(items), 675);
    }

    @Test
    public void testUpdateRulesAndDiscountsAndCalculatePricesCalculatesCorrectly()
    {
        setUpUpdateArrayList();
        int expectedPrice = 1800 + 650 + 350;
        pricesAndRules.add("Basket: D|A|B|D|A|D|B|A|C|D");

        assertEquals(shopProgram.updateRulesAndDiscountsAndCalculatePrices(pricesAndRules), expectedPrice);
    }

    public void setUpUpdateArrayList()
    {
        pricesAndRules = new ArrayList<String>();
        pricesAndRules.add("A:250");
        pricesAndRules.add("B:350");
        pricesAndRules.add("C:450");
        pricesAndRules.add("D:550");
        pricesAndRules.add("E:650");
        pricesAndRules.add("BuyXGetYFree: A|3");
        pricesAndRules.add("MealDeal: B|C|650");
        pricesAndRules.add("MultiPrice: D|4|1800");
    }
}

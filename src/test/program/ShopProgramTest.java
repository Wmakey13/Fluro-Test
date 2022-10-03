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
        assertEquals(5, shopProgram.getSKUs().size());
    }

    @Test
    public void testItemIsNotAddedIfItemIsInvalid()
    {
        shopProgram = new ShopProgram();

        shopProgram.addItemsToShopMenu("A", 0);
        shopProgram.addItemsToShopMenu(null, 200);
        shopProgram.addItemsToShopMenu("C", 300);

        Map<String, Sku> itemsList = shopProgram.getSKUs();

        assertEquals(1, itemsList.size());
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

        assertEquals(1, itemsList.size());
        assertEquals(140, itemsList.get("A").getPrice());
    }

    @Test
    public void testDiscountNotAddedIfNull()
    {
        shopProgram = new ShopProgram();
        shopProgram.addDiscountToDiscountsList("BuyXGetYFree", new Sku[] { item }, new Integer[] { 5, 2 });
        shopProgram.addDiscountToDiscountsList(null, null, null);
        assertEquals(1, shopProgram.getDiscounts().size());
    }

    @Test
    public void testCalculatePriceReturnsTheRightPrice()
    {
        String[] items = { "A", "B", "B" };
        assertEquals(500, shopProgram.calculatePrices(items)); // 100+200+200
    }

    @Test
    public void testBuyXGetYFreeDiscountIsAppliedCorrectly()
    {
        shopProgram.addDiscountToDiscountsList("BuyXGetYFree", new Sku[] { item }, new Integer[] { 3, 1 });

        String[] items = { "A", "A", "A", "A" };
        assertEquals(300, shopProgram.calculatePrices(items)); // 400-100
    }

    @Test
    public void testBuyXGetYFreeDiscountIsAppliedCorrectlyWhenItemsAreApart()
    {
        shopProgram.addDiscountToDiscountsList("BuyXGetYFree", new Sku[] { item }, new Integer[] { 3, 1 });

        String[] items = { "A", "B", "A", "B", "A", "B", "A" };
        assertEquals(900, shopProgram.calculatePrices(items)); // 400-100 + 600
    }

    @Test
    public void testMealDealDiscountIsAppliedCorrectly()
    {
        shopProgram.addDiscountToDiscountsList("MealDeal", new Sku[] { item, item2 }, new Integer[] { 250 });

        String[] items = { "A", "B" };
        assertEquals(250, shopProgram.calculatePrices(items));
    }

    @Test
    public void testMealDealDiscountIsAppliedCorrectlyWhenItemsAreApart()
    {
        shopProgram.addDiscountToDiscountsList("MealDeal", new Sku[] { item, item2 }, new Integer[] { 250 });

        String[] items = { "A", "E", "B" };
        assertEquals(750, shopProgram.calculatePrices(items)); // 250+500
    }

    @Test
    public void testMultiPriceDiscountIsAppliedCorrectly()
    {
        shopProgram.addDiscountToDiscountsList("MultiPrice", new Sku[] { item }, new Integer[] { 3, 275 });

        String[] items = { "A", "A", "A" };
        assertEquals(275, shopProgram.calculatePrices(items));
    }

    @Test
    public void testMultiPriceDiscountIsAppliedCorrectlyWhenItemsAreApart()
    {
        shopProgram.addDiscountToDiscountsList("MultiPrice", new Sku[] { item }, new Integer[] { 3, 275 });

        String[] items = { "A", "B", "A", "B", "A" };
        assertEquals(675, shopProgram.calculatePrices(items)); // 275+400
    }

    @Test
    public void testUpdateRulesAndDiscountsAndCalculatePricesCalculatesCorrectly()
    {
        setUpUpdateArrayList();
        int expectedPrice = 1800 + 1000 + 650 + 350 + 550 + 650;
        pricesAndRules.add("Basket:D|A|B|D|A|C|D|A|D|A|B|D|A|E");

        assertEquals(expectedPrice, shopProgram.updateRulesAndDiscountsAndCalculatePrices(pricesAndRules));
    }

    public void setUpUpdateArrayList()
    {
        pricesAndRules = new ArrayList<String>();
        pricesAndRules.add("Item:A:250");
        pricesAndRules.add("Item:B:350");
        pricesAndRules.add("Item:C:450");
        pricesAndRules.add("Item:D:550");
        pricesAndRules.add("Item:E:650");
        pricesAndRules.add("BuyXGetYFree:A:3|1");
        pricesAndRules.add("MealDeal:B|C:650");
        pricesAndRules.add("MultiPrice:D:4|1800");
    }
}

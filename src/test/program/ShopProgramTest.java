package test.program;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;

import main.program.ShopProgram;
import main.program.objects.Item;

public class ShopProgramTest
{
    public ShopProgram shopProgram = new ShopProgram();

    @Test
    public void testItemsCanBeAddedToItemList()
    {
        shopProgram = new ShopProgram();
        
        shopProgram.addItemToItems("A", 100);
        shopProgram.addItemToItems("B", 200);
        shopProgram.addItemToItems("C", 300);
        shopProgram.addItemToItems("D", 400);
        shopProgram.addItemToItems("E", 500);

        assertEquals(shopProgram.returnItems().size(), 5);
    }

    @Test
    public void testItemIsNotAddedIfItemIsInvalid()
    {
        shopProgram = new ShopProgram();
        
        shopProgram.addItemToItems("A", 0);
        shopProgram.addItemToItems(null, 200);
        shopProgram.addItemToItems("C", 300);

        Map<String, Item> itemsList = shopProgram.returnItems();
        
        assertEquals(itemsList.size(), 1);
        assertTrue(itemsList.containsKey("C"));
        assertFalse(itemsList.containsKey("A"));
    }
    
    @Test
    public void testCalculatePriceReturnsTheRightPrice()
    {
        shopProgram = new ShopProgram();
        
        shopProgram.addItemToItems("A", 100);
        shopProgram.addItemToItems("B", 200);
        
        String[] items = {"A", "B", "B"};
        assertEquals(shopProgram.calculatePrices(items), 500);
    }
    
    /**
     * Tests to implement
     * 
     * That items without a discount can be scanned and a price given 
     * That items with discount buy x get x free is applied correctly 
     * That items with discount x for x price is applied correctly 
     * That items with discount x and y for z is applied correctly 
     * That discounts still happen when items aren't next to each other That item prices can be changed
     * 
     **/
}

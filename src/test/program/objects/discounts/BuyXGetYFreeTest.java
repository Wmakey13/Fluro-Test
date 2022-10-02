package test.program.objects.discounts;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.program.objects.Item;
import main.program.objects.discounts.BuyXGetYFree;

public class BuyXGetYFreeTest
{
    public BuyXGetYFree buyXGetYFree;
    
    
    @Test
    public void testDiscountCreatedCorrectly() {
      Item item = new Item("A", 100);
      buyXGetYFree = new BuyXGetYFree(item, 4);
      assertTrue(buyXGetYFree.getDiscountedItem().equals(item));
      assertTrue(buyXGetYFree.getRequirement() == 4);
    }
    
    @Test
    public void testDiscountCreatedWithoutItemsThrowsIllegalArgumentException() {
        
    }
    
    @Test
    public void testDiscountCreatedWithoutRequirementThrowsIllegalArgumentException() {
        
    }
    
    @Test
    public void testCorrectDiscount() {
        
    }
    
    @Test
    public void testCorrectDiscountMultiple() {
        
    }
}

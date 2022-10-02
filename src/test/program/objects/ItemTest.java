package test.program.objects;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.program.objects.Item;

public class ItemTest
{
    //make sure items cannot be set without a price or designation
    @Test
    public void itemSetWithCorrectDesignationAndPrice() {
        Item item = new Item("A", 100);
        assertEquals(item.getDesignation(), "A");
        assertEquals(item.getPrice(), 100);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void itemSetWithEmptyDesignationThrowsIllegalArgumentException() {
        Item item = new Item("", 100);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void itemSetWithoutDesignationThrowsIllegalArgumentException() {
        Item item = new Item(null, 100);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void itemSetWithLessThanZeroThrowsIllegalArgumentException() {
        Item item = new Item(null, -1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void itemSetWithZeroThrowsIllegalArgumentException() {
        Item item = new Item(null, 0);
    }
}

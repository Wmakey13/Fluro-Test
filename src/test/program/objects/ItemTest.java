package test.program.objects;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.program.objects.Sku;

public class ItemTest
{
    @Test
    public void itemSetWithCorrectDesignationAndPrice()
    {
        Sku item = new Sku("A", 100);
        assertEquals("A", item.getDesignation());
        assertEquals(100, item.getPrice());
    }

    @Test(expected = IllegalArgumentException.class)
    public void itemSetWithEmptyDesignationThrowsIllegalArgumentException()
    {
        Sku item = new Sku("", 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void itemSetWithoutDesignationThrowsIllegalArgumentException()
    {
        Sku item = new Sku(null, 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void itemSetWithLessThanZeroThrowsIllegalArgumentException()
    {
        Sku item = new Sku(null, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void itemSetWithZeroThrowsIllegalArgumentException()
    {
        Sku item = new Sku(null, 0);
    }
}

package main.program.objects;

import java.util.Map;

public interface Discount
{
    public int checkDiscount(Map<String, Integer> items);

    public void validate();
}
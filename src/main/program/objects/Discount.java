package main.program.objects;

import java.util.Map;

public interface Discount
{
    public int calculateDiscount(Map<String, Integer> items);

    void validate();
}
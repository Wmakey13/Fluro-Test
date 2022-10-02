package main.program.objects;

public class Item
{
    private String designation;
    private int price;

    public Item(String designation, int price)
    {
        validateFilled(designation, price);
        this.designation = designation;
        this.price = price;
    }

    public String getDesignation()
    {
        return designation;
    }

    public void setDesignation(String designation)
    {
        this.designation = designation;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public void validateFilled(String designation, int price) {
        if((designation == null || designation.isEmpty())|| price <= 0) {
            throw new IllegalArgumentException("Arguments aren't correctly filled");
        }
    }
}

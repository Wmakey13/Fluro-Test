package main.program.objects;

public class Sku
{
    private String designation;
    private int price;

    public Sku(String designation, int price)
    {
        validate(designation, price);
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

    private void validate(String designation, int price) {
        if((designation == null || designation.isEmpty())|| price <= 0) {
            throw new IllegalArgumentException("Arguments aren't correctly filled");
        }
    }
}

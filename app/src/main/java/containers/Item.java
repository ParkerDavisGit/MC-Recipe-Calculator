package containers;

public class Item
{
    private String id;
    private String[] ingredients;
    
    // False if the item has a recipe. True if raw resource.
    private boolean base;

    public Item()
    {
        id = "INVALID ITEM";
        ingredients = null;
        base = true;
    }

    public Item(String _id)
    {
        id = _id;
        ingredients = null;
        base = true;
    }

    public Item(String _id, String[] _ingredients)
    {
        id = _id;
        ingredients = _ingredients.clone();
        base = false;
    }


    public String toString()
    {
        return ingredients.toString();
    }

    public boolean isBase()
        { return base; }
    
    public String getId()
        { return id; }
    
    public String[] getIngredients()
        { return ingredients; }
    
}

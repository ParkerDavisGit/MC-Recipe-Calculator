package containers;

import java.util.HashMap;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

import io.RecipeReader;

public class RecipeBook
{
    private HashMap<Item, ArrayList<Item>> recipes;

    public RecipeBook()
    {
        recipes = new RecipeReader().getBook();
    }

    public void addRecipe(Item key, ArrayList<Item> ingredients)
    {
        if (recipes.containsKey(key))
        {
            System.out.println("ERROR ERROR, RECIPE ALREADY EXISTS!!!");
            return;
        }
        try
        {
            FileWriter writer = new FileWriter("recipe_data.txt", true);
            
            writer.write(String.format("%n%s %d%n", key.id, key.amount));

            for (Item item : ingredients)
            {
                writer.write(String.format("%s %d ", item.id, item.amount));
            }

            writer.close();
        }
        catch(IOException exception)
        {
            System.out.println("File broke -Recipebook.addRecipe()-");
        }

        recipes.put(key, ingredients);
    }

    public ArrayList<Item> getRecipe(Item _key)
    {
        return recipes.get(_key);
    }

    public ArrayList<Item> getRecipe(String _id)
    {
        for (Item key : recipes.keySet())
        {
            if (key.id.equals(_id))
                {return getRecipe(key);}
        }

        return null;
    }
    
    public String toString()
    {
        for (Item item : recipes.keySet())
        {
            System.err.println(item);
        }

        return "";
        //return recipes.toString();
    }

    public boolean contains(Item _key)
    {
        return recipes.containsKey(_key);
    }

    public boolean contains(String _id)
    {
        if (getItemFromString(_id) == null)
            {return true;}
        return false;
    }
    
    public Item getItemFromString(String _id)
    {
        for (Item key : recipes.keySet())
        {
            if (key.id.equals(_id))
                {return key;}
        }

        System.err.println("Item not found ;-;, getItemFromString(): " + _id);
        return null;
    }

    public ArrayList<Item> calculateIngredients(Item _item, int _amount)
    {
        ArrayList<Item> ingredients = new ArrayList<Item>();

        // If the item is not contained, just add the item
        if (!this.contains(_item))
        {
            ingredients.add(_item.clone());
            return ingredients;
        }

        boolean exists;
        for (Item item : getRecipe(_item))
        {
            for (Item ingredient : calculateIngredients(item, item.amount))
            {
                System.err.println(
                    item + " " + ingredient + " " + _amount
                );
                exists = false;
                for (Item alreadyAddedItem : ingredients)
                {
                    if (alreadyAddedItem.equals(ingredient))
                    {
                        alreadyAddedItem.add(ingredient, _amount);
                        exists = true;
                    }
                }
                if (!exists)
                {
                    ingredients.add(ingredient.clone());
                }
            }
        }
        System.err.println(ingredients);
        return ingredients;
    }

    public ArrayList<Item> calculateIngredients(String _item, int _amount)
    {
        return calculateIngredients(getItemFromString(_item), _amount);
    }
}

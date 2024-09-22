package containers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

public class RecipeBook
{
    private HashMap<String, ArrayList<String>> recipes;

    public RecipeBook()
    {
        recipes = new HashMap<String, ArrayList<String>>();
    }

    public void addRecipe(String key, ArrayList<String> ingredients)
    {
        recipes.put(key, ingredients);
    }

    public String getRecipe(String key)
    {
        String output = "";

        output = output + String.format("%s - [", key);
        ArrayList<String> ingredients = recipes.get(key);
        Iterator<String> iterator = ingredients.iterator();
        
        while (iterator.hasNext())
        {
            output = output + String.format("%s", iterator.next());

            if (iterator.hasNext())
            {
                output = output + ", ";
            }
        }

        output = output + "]";

        return output;
    }

    public String toString()
    {
        return recipes.toString();
    }
}

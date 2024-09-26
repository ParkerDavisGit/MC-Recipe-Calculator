package io;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.List;
import java.util.Iterator;

import containers.Item;

public class RecipeReader
{
    private HashMap<Item, ArrayList<Item>> recipe_book;
    //private PrintWriter test;
    private BufferedReader recipe_data;

    public RecipeReader()
    {
        recipe_book = new HashMap<Item, ArrayList<Item>>();

        try
        {
            File file = new File("recipe_data.txt");
            //test = new PrintWriter(new FileOutputStream(file, true));
            recipe_data = new BufferedReader(new FileReader(file));
        }
        catch(IOException exception)
        {
            System.out.println("Oops");
            return;
        }
        
        this.readFile();
    }

    public void readFile()
    {  
        String[] split_line;

        List<String> recipe_data_by_line = recipe_data.lines().toList();
        Iterator<String> recipe_data_iterator = recipe_data_by_line.iterator();
        while (recipe_data_iterator.hasNext())
        {
            split_line = recipe_data_iterator.next().split(" ");
            Item keyItem =
                new Item(split_line[0].trim(), Integer.parseInt(split_line[1]));
            split_line = recipe_data_iterator.next().split(" ");
            ArrayList<Item> ingredients = new ArrayList<Item>();

            for (int i = 0; i < split_line.length; i = i + 2) {
                ingredients.add(
                    new Item(split_line[i].trim(),
                             Integer.parseInt(split_line[i+1]))
                );
            }
            
            recipe_book.put(keyItem, ingredients);
        }
    }

    public HashMap<Item, ArrayList<Item>> getBook()
    {
        return recipe_book;
    }
}

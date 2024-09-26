package containers;

public class Item
{
    public String id;
    public int amount;

    public Item(String _id, int _amount)
    {
        id = _id;
        amount = _amount;
    }
    
    @Override
    public String toString()
    {
        return id + " x" + amount;
    }

    @Override
    public boolean equals(Object _otherObject)
    {   
        if (!(_otherObject instanceof Item))
            {return false;}

        Item _otherItem = (Item) _otherObject;

        if (id.equals(_otherItem.id))
            {return true;}
        else
            {return false;}
    }

    @Override
    public int hashCode()
    {
        return id.hashCode();
    }

    public Item clone()
    {
        Item newItem = new Item (this.id, this.amount);
        return newItem;
    }

    public void add(Item _item, int _amount)
    {
        this.amount = this.amount + _item.amount * _amount;
    }
}

package hibernate.dao.dao;

import hibernate.entities.Item;

import java.util.List;

public interface ItemDao {
    public List<Item> getAllItems();

    public Item getItemById(long id);

    public Item getByName(String name);

    public List<String> getAllItemNames();

    public int saveItem(Item item);

    public int updateItem(Item item);

}

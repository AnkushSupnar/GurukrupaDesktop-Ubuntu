package hibernate.service.serviceimpl;

import hibernate.dao.dao.ItemDao;
import hibernate.dao.daoimpl.ItemDaoImpl;
import hibernate.entities.Item;
import hibernate.service.service.ItemService;

import java.util.List;

public class ItemServiceImpl implements ItemService {
    private ItemDao dao;
    public ItemServiceImpl()
    {
        this.dao=new ItemDaoImpl();
    }

    @Override
    public List<Item> getAllItems() {
        return dao.getAllItems();
    }

    @Override
    public Item getItemById(long id) {
        return dao.getItemById(id);
    }

    @Override
    public Item getByName(String name) {
        return dao.getByName(name);
    }

    @Override
    public List<String> getAllItemNames() {
        return dao.getAllItemNames();
    }

    @Override
    public int saveItem(Item item) {
        return dao.saveItem(item);
    }

    @Override
    public int updateItem(Item item) {
        return dao.updateItem(item);
    }
}

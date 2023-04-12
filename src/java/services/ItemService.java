/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.ItemDB;
import java.util.List;
import models.Item;
import models.User;
/**
 *
 * @author Xavier
 */
public class ItemService {
    public Item get(Integer id) throws Exception {
        ItemDB itemDB = new ItemDB();
        Item item = itemDB.get(id);
        return item;
    }
    
    public List<Item> getAll(User user) throws Exception {
        ItemDB itemDB = new ItemDB();
        List<Item> items = itemDB.getAll(user);
        return items;
    }
    
    public void insert(Item item) throws Exception {
        ItemDB itemDB = new ItemDB();
        itemDB.insert(item);
    }
    
    public void update(Item item) throws Exception {
        ItemDB itemDB = new ItemDB();
        itemDB.update(item);
    }
    
    public void delete(Item item) throws Exception {
        ItemDB itemDB = new ItemDB();
        itemDB.delete(item);
    }
}

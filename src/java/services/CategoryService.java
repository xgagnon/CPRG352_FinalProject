/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.CategoryDB;
import java.util.List;
import models.Category;
/**
 *
 * @author Xavier
 */
public class CategoryService {
    public Category get(Integer id) throws Exception {
        CategoryDB categoryDB = new CategoryDB();
        Category category = categoryDB.get(id);
        return category;
    }
    
    public List<Category> getAll() throws Exception {
        CategoryDB categoryDB = new CategoryDB();
        List<Category> category = categoryDB.getAll();
        return category;
    }
    
    public void insert(Category category) throws Exception {
        CategoryDB categoryDB = new CategoryDB();
        categoryDB.insert(category);
    }
    
    public void update(Category category) throws Exception {
        CategoryDB categoryDB = new CategoryDB();
        categoryDB.update(category);
    }
    
    public void delete(Category category) throws Exception {
        CategoryDB categoryDB = new CategoryDB();
        categoryDB.delete(category);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Category;
import models.Item;
import models.User;
import services.CategoryService;
import services.ItemService;
import services.UserService;

/**
 *
 * @author Xavier
 */
public class InventoryServlet extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        User user = (User) session.getAttribute("user");
        ItemService itemService = new ItemService();
        CategoryService categoryService = new CategoryService();
        UserService userService = new UserService();
        
        try {
            List<Category> categories = categoryService.getAll();
            request.setAttribute("categories",categories);
        } catch (Exception ex) {
        }
        
        String action = request.getParameter("action");

        try{
            if (action != null && action.equals("edit")){
                String itemIdString = request.getParameter("itemId");
                int itemId = Integer.parseInt(itemIdString);
                Item item = itemService.get(itemId);
                
                request.setAttribute("selectedItem",item);
            } 
            else if (action != null && action.equals("delete")){
                String itemIdString = request.getParameter("itemId");
                int itemId = Integer.parseInt(itemIdString);
                Item item = itemService.get(itemId);
                itemService.delete(item);
                user.getItemList().remove(item);
                userService.update(user);
            }    
        } catch (Exception ex){
        }
        
        List<Item> items = user.getItemList();
        request.setAttribute("items", items);

        getServletContext().getRequestDispatcher("/WEB-INF/userinventory.jsp")
                    .forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        String action = request.getParameter("action");
        ItemService itemService = new ItemService();
        CategoryService categoryService = new CategoryService();
        UserService userService = new UserService();
        
        String itemIdString = request.getParameter("itemId");
        Item item= new Item(); 
         
        if(itemIdString != null) {
            int itemId = (Integer) Integer.parseInt(itemIdString);
            try {
                item = itemService.get(itemId);
            } catch (Exception ex) {
            }
        }
        
        User user = (User) session.getAttribute("user");
        
        String itemName = (String) request.getParameter("itemName");
        
        String priceString = (String) request.getParameter("price");
        Double price = (Double) Double.parseDouble(priceString);
        
        if(price < 0) {
            request.setAttribute("message","Item was not added to the inventory");
            
            List<Item> items = user.getItemList();
            request.setAttribute("items", items);

            try {
                List<Category> categories = categoryService.getAll();
                request.setAttribute("categories",categories);
            } catch (Exception ex) {
            }
            
            getServletContext().getRequestDispatcher("/WEB-INF/userinventory.jsp")
                    .forward(request, response);
            
            return;
        }
        
        String categoryIdString = (String) request.getParameter("category");
        int categoryId = (Integer) Integer.parseInt(categoryIdString);
        
        Category category = null;
        
        try {
            category = categoryService.get(categoryId);
        } catch (Exception ex) {
        }
        
        item.setItemName(itemName);
        item.setPrice(price);
        item.setCategory(category);
        item.setOwner(user);
        
        
        try{
            switch (action){
                case "add":
                    itemService.insert(item);
                    user.getItemList().add(item);
                    userService.update(user);
                    break;
                case "edit":
                    user.getItemList().remove(item);
                    itemService.update(item);
                    user.getItemList().add(item);
                    break;
            }
        } catch (Exception ex){
        }
        
        List<Item> items = user.getItemList();
        request.setAttribute("items", items);
        
        try {
            List<Category> categories = categoryService.getAll();
            request.setAttribute("categories",categories);
        } catch (Exception ex) {
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/userinventory.jsp")
                    .forward(request, response);
    }

}

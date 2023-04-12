/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Category;
import services.CategoryService;

/**
 *
 * @author Xavier
 */
public class EditCategoriesServlet extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        CategoryService categoryService = new CategoryService();
        String action = request.getParameter("action");

        try{
            if (action != null && action.equals("edit")){
                int catId = (Integer) Integer.parseInt(request.getParameter("catId"));
                Category selCat = categoryService.get(catId);
                request.setAttribute("selCat", selCat);
                request.setAttribute("catName", selCat.getCategoryName());
            }
            
            List<Category> categories = categoryService.getAll();
            request.setAttribute("categories", categories);
            
        } catch (Exception ex){
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/editcategories.jsp")
                .forward(request,response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoryService categoryService = new CategoryService();   
        String action = request.getParameter("action");
        String catName = request.getParameter("catName");
        String catIdString = request.getParameter("catId");
        
        Category category = new Category();
        
        
         if(catIdString != null) {
            int catId = (Integer) Integer.parseInt(catIdString);
            try {
                category = categoryService.get(catId);
            } catch (Exception ex) {
            }
        }
        
        category.setCategoryName(catName);
        
        try{
            switch (action){
                case "add":
                    categoryService.insert(category);
                    break;
                case "update":
                    categoryService.update(category);
                    break;
            }
            
            List<Category> categories = categoryService.getAll();
            request.setAttribute("categories", categories);
 
        } catch (Exception ex){
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/editcategories.jsp")
                .forward(request, response);
    }

}

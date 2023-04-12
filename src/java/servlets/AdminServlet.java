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
import models.Item;
import models.Role;
import models.User;
import services.RoleService;
import services.UserService;

/**
 *
 * @author Xavier
 */
public class AdminServlet extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        UserService userService = new UserService();
        String action = request.getParameter("action");
        String deactivate = request.getParameter("submit");

        try{
            if (action != null && action.equals("edit")) {
                String email = request.getParameter("email");
                User user = userService.get(email);
                request.setAttribute("selUser", user);
                request.setAttribute("email", user.getEmail());
            } 
            else if (action != null && action.equals("delete")) {
                String email = request.getParameter("email");
                User user = userService.get(email);
                userService.delete(user);
                List<Item> items = user.getItemList();
            } 
                        
            List<User> users = userService.getAll();
            request.setAttribute("users", users);
            
        } catch (Exception ex){
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp")
                .forward(request,response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService userService = new UserService();   
        String action = request.getParameter("submit");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        String roleName = request.getParameter("role");
        String isActiveString = request.getParameter("isActive");
        Integer roleId = null;
        boolean isActive = true;
        
        if(roleName.equals("system admin")) {
            roleId = 1;
        }
        else if(roleName.equals("regular user")){
            roleId = 2;
        }
        
        if(isActiveString != null && isActiveString.equals("true")) {
            isActive = true;
        }
        else if(isActiveString != null && isActiveString.equals("false")) {
            isActive = false;
        }
        
        Role role = new Role(roleId,roleName);
        User user = new User(email,isActive,firstName,lastName,password);
        user.setRole(role);
        try {
            user.setItemList(userService.get(email).getItemList());
        } catch (Exception ex) {
        }

        try{
            switch (action){
                case "Add User":
                    userService.insert(user);
                    break;
                case "Update":
                    userService.update(user);
                    break;
            }
            
            List<User> users = userService.getAll();
            request.setAttribute("users", users);
 
        } catch (Exception ex){
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp")
                .forward(request, response);
    }

}

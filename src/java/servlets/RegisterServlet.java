/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import dataaccess.RoleDB;
import dataaccess.UserDB;
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
import models.User;
import services.AccountService;
import services.RoleService;
import services.UserService;

/**
 *
 * @author Xavier
 */
public class RegisterServlet extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                .forward(request,response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        UserService userService = new UserService();
        RoleService roleService = new RoleService();
        
        if(email.equals("") || email.isEmpty()) {
            request.setAttribute("message","Please enter an email");
            
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                .forward(request,response);
            
            return;
        }
        
        if(firstName.equals("") || firstName.isEmpty()) {
            request.setAttribute("message","Please enter a first name");
            
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                .forward(request,response);
            
            return;
        }
        
        if(lastName.equals("") || lastName.isEmpty()) {
            request.setAttribute("message","Please enter a last name");
            
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                .forward(request,response);
            
            return;
        }
        
        if(password.equals("") || password.isEmpty()) {
            request.setAttribute("message","Please enter a password");
            
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                .forward(request,response);
            
            return;
        }
        
        User newUser = new User(email,
                                true,
                                firstName,
                                lastName,
                                password);
        
        try {
            newUser.setRole(roleService.get(2));
        } catch (Exception ex) {
        }
        List<Item> items = new ArrayList<>();
        newUser.setItemList(items);
        
        try {
            userService.insert(newUser);
        } catch (Exception ex) {  
        }
        
        request.setAttribute("message","New user has been registered");
        
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request,response);
    }

}

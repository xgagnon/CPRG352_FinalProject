/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.UserService;

/**
 *
 * @author Xavier
 */
public class AccountInfoServlet extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();  
        User user = (User) session.getAttribute("user");
        
        getServletContext().getRequestDispatcher("/WEB-INF/accountinfo.jsp")
                    .forward(request, response);
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();  
        User user = (User) session.getAttribute("user");
        UserService userService = new UserService();
        
        String action = request.getParameter("submit");
        
        if(action.equals("Deactivate Account")) {
            user.setActive(false);
            try {
                userService.update(user);
            } catch (Exception ex) {
            }
            
            request.setAttribute("message", "Your Account has been deactivated");
            
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                    .forward(request, response);
            
            return;
        }
        
        if(action.equals("Save Info")) {
            user.setEmail(request.getParameter("email"));
            user.setFirstName(request.getParameter("firstName"));
            user.setLastName(request.getParameter("lastName"));
            
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirm");
            if(confirmPassword.equals(password)) {
                user.setPassword(password);
            }
            
            try {
                userService.update(user);
            } catch (Exception ex) {
            }
            
            response.sendRedirect("inventory");
            return;
        }  
    }
}

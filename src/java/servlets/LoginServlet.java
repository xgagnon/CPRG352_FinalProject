/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;

/**
 *
 * @author Xavier
 */
public class LoginServlet extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request,response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        AccountService accountService = new AccountService();
        User user = accountService.login(email, password);
        
        if (user == null) {
            request.setAttribute("message","Username or password is wrong");
            
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                    .forward(request, response);
            return;
        }
        
        if (user.getActive() == false) {
            request.setAttribute("message","User is not active");
            
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                    .forward(request, response);
            return;
        }
        
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        
        if (user.getRole().getRoleId() == 1) {
            response.sendRedirect("admin");
        } else {
            response.sendRedirect("inventory");
        }
    }

}

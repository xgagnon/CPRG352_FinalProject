<%-- 
    Document   : accountinfo
    Created on : 4-Apr-2023, 12:48:03 PM
    Author     : Xavier
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Information</title>
    </head>
    <body>
        <h1>Account Information</h1>
        
        <form action="accountinfo" method="post">
            Email: <input type="text" name="email" value="${user.email}" required> <br>
            First Name: <input type="text" name="firstName" value="${user.firstName}" required> <br>
            Last Name: <input type="text" name="lastName" value="${user.lastName}" required> <br>
            Password: <input type="password" name="password" value="${user.password}" required> <br>
            Confirm Password: <input type="password" name="confirm" required> <br>
            
            <input type="submit" name="submit" value="Save Info">
            <input type="submit" name="submit" value="Deactivate Account">
        </form>
        <br>
        <a href="login">Logout</a>
        <a href="inventory">Back to inventory</a>
    </body>
</html>

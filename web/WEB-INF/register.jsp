<%-- 
    Document   : register
    Created on : 4-Apr-2023, 10:49:33 AM
    Author     : Xavier
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <h1>Register as a new User</h1>
        ${message}
        <form action="register" method="post">
            Email: <input type="text" name="email"><br>
            First Name: <input type="text" name="firstName"><br>
            Last Name: <input type="text" name="lastName"><br>
            Password: <input type="password" name="password"><br>
            <input type="submit" value="Register">
        </form>
        <a href="login">Already a User?</a>
    </body>
</html>

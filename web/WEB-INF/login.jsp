<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inventory Login</title>
    </head>
    <body>
        <h1>HOME nVentory</h1>
        <h2>Login</h2>
        <h4>${message}</h4>
        <form action="login" method="post">
            Email: <input type="text" name="email"><br>
            Password: <input type="password" name="password"><br>
            <input type="submit" value="Sign in">
        </form>
        <a href="register">Register</a>
    </body>
</html>

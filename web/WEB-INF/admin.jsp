<%-- 
    Document   : user
    Created on : 28-Feb-2023, 9:54:51 AM
    Author     : Xavier
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Users</title>
    </head>
    <body>
        <h1>Manage Users</h1>
        <c:choose>
            <c:when test="${users.size() > 0}">
                <table border="1">
                    <tr>
                        <th>Email</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Role</th>
                        <th>Active</th>
                        <th></th>
                        <th></th>
                    </tr>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td>${user.email}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                            <td>${user.role.roleName}</td>
                            <td>${user.active}</td>
                            <td>
                                <c:url value="/admin" var="editUser">
                                    <c:param name="email" value="${user.email}" />
                                    <c:param name="action" value="edit" />
                                </c:url>
                                <a href=${editUser}>Edit</a>
                            </td>
                            <td>
                                <c:url value="/admin" var="deleteUser">
                                    <c:param name="email" value="${user.email}" />
                                    <c:param name="action" value="delete" />
                                </c:url>
                                <a href=${deleteUser}>Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <h3>
                    There are no users to display
                </h3>
            </c:otherwise>
        </c:choose>

        <c:if test="${selUser eq null}">
            <h2>Add User</h2>
            <form action="admin" method="post">
                Email: <input type="text" name="email" required> <br>
                First Name: <input type="text" name="firstName" required> <br>
                Last Name: <input type="text" name="lastName" required> <br>
                Password: <input type="password" name="password" required> <br>
                Role: <select name="role">
                        <option value="system admin">system admin</option>
                        <option value="regular user">regular user</option>
                      </select> <br>
                      
                <input type="submit" name="submit" value="Add User">
            </form>
        </c:if> 

        <c:if test="${selUser ne null}">           
            <h2>Edit Users</h2>
            <form action="admin" method="post">
                <input type="hidden" name="email" value="${selUser.email}"> 
                Email: ${selUser.email} <br>
                First Name: <input type="text" name="firstName" required value="${selUser.firstName}"> <br>
                Last Name: <input type="text" name="lastName" required value="${selUser.lastName}"> <br>
                Password: <input type="password" name="password" required value="${selUser.password}"> <br>
                Role: <select name="role">
                        <option value="system admin">system admin</option>
                        <option value="regular user">regular user</option>
                      </select> <br>
                Active: <select name="isActive">
                        <option value="true">True</option>
                        <option value="false">False</option>
                      </select> <br>
                    
                    <input type="submit" name="submit" value="Update">
                    <a href="admin" class="button">Cancel</a>
            </form>
        </c:if>
        
        <a href="login">Logout</a>
        <a href="editcategories">Edit Categories</a>
    </body>
</html>

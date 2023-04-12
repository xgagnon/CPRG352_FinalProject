<%-- 
    Document   : editcategories
    Created on : 11-Apr-2023, 4:49:33 PM
    Author     : Xavier
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Categories</title>
    </head>
    <body>
        <h1>Categories</h1>
        
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th></th>
            </tr>
            <c:forEach items="${categories}" var="category">
                <tr>
                    <td>${category.categoryId}</td>
                    <td>${category.categoryName}</td>
                    <td>
                        <c:url value="/editcategories" var="editCat">
                            <c:param name="catId" value="${category.categoryId}" />
                            <c:param name="action" value="edit" />
                        </c:url>
                        <a href=${editCat}>Edit</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <c:if test="${selCat eq null}">
            <h2>Add Category</h2>
            <form action="editcategories" method="post">
                Category Name: <input type="text" name="catName" required> <br>                      
                <input type="hidden" name="action" value="add">
                <input type="submit" value="Add Category">
            </form>
        </c:if> 

        <c:if test="${selCat ne null}">           
            <h2>Edit Category</h2>
            <form action="editcategories" method="post">
                Category Name: <input type="text" name="catName" value="${selCat.categoryName}" required> <br>                      
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="catId" value="${selCat.categoryId}">
                <input type="submit" value="Save Category">
                    <a href="admin" class="button">Cancel</a>
            </form>
        </c:if>
                    
        <a href="login">Logout</a>
        <a href="admin">Edit Users</a>
    </body>
</html>

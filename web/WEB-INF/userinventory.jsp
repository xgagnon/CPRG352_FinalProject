<%-- 
    Document   : userinventory
    Created on : 4-Apr-2023, 11:11:18 AM
    Author     : Xavier
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inventory</title>
    </head>
    <body>
        <h1>Welcome ${user.firstName}</h1>
        <h4>${message}</h4>
        <c:choose>
            <c:when test="${items.size() > 0}">
                <h2>Here is your inventory</h2>
        
                <table border="1">
                    <tr>
                        <th>Item ID</th>
                        <th>Category</th>
                        <th>Item Name</th>
                        <th>Price</th>
                        <th></th>
                        <th></th>
                    </tr>
                    <c:forEach items="${items}" var="item">
                        <tr>
                            <td>${item.itemId}</td>
                            <td>${item.category.categoryName}</td>
                            <td>${item.itemName}</td>
                            <td>${item.price}</td>
                            <td>
                                <c:url value="/inventory" var="editItem">
                                    <c:param name="itemId" value="${item.itemId}" />
                                    <c:param name="action" value="edit" />
                                </c:url>
                                <a href=${editItem}>Edit</a>
                            </td>
                            <td>
                                <c:url value="/inventory" var="deleteItem">
                                    <c:param name="itemId" value="${item.itemId}" />
                                    <c:param name="action" value="delete" />
                                </c:url>
                                <a href=${deleteItem}>Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <h3>
                    There are no items in your inventory
                </h3>
            </c:otherwise>
        </c:choose>
        
        <c:if test="${selectedItem eq null}">
            <h2>Add Item</h2>
            <form action="inventory" method="post">
                Item Name: <input type="text" name="itemName" required> <br>
                Price: <input type="number" name="price" required> <br>
                Category: <select name="category">
                        <c:forEach items="${categories}" var="category">
                            <option value="${category.categoryId}">${category.categoryName}</option>
                        </c:forEach>
                      </select> <br>
                      
                <input type="hidden" name="action" value="add">
                <input type="submit"value="Add Item">
            </form>
        </c:if>
            
        <c:if test="${selectedItem ne null}">
            <h2>Edit Item</h2>
            <form action="inventory" method="post">
                Item Name: <input type="text" name="itemName" value="${selectedItem.itemName}" required> <br>
                Price: <input type="text" name="price" value="${selectedItem.price}" required> <br>
                Category: <select name="category">
                        <c:forEach items="${categories}" var="category">
                            <option value="${category.categoryId}">${category.categoryName}</option>
                        </c:forEach>
                      </select> <br>
                      
                <input type="hidden" name="action" value="edit">
                <input type="hidden" name="itemId" value="${selectedItem.itemId}">
                <input type="submit"value="Edit Item">
                <a href="inventory" class="button">Cancel</a>
            </form>
        </c:if>
        
        <a href="login">Logout</a>
        <a href="accountinfo">Edit Account</a>
    </body>
</html>

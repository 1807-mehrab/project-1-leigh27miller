<%@ page language="java" contentType="text/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link type = "text/css" rel = "stylesheet" href="updateProfile.css">
</head>
<body>
<h1> Update Profile</h1>
<div class = "box">

    <form action="/messageboard/UpdateProfileServlet" method = "POST">
        <div id = "update">
            <input type="text" name="password" value="${user.password}" placeholder="Update Password " class="password"/>
            <br>
            <input type="text" name= "email" value="${user.email}" placeholder="Update Email" class="email"/>
            <input type="text" name= "firstName" value="${user.firstName}" placeholder="Update first name" class="email"/>
            <input type="text" name= "lastName" value="${user.lastName}" placeholder="Update last name" class="email"/>
            <input type="submit" value="Update" class = "btn"></input>

        </div>
</div>




</form>

</body>
</html>


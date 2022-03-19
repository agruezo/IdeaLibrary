<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<title>Login and Registration</title>
</head>
<body>

	<div class="container-fluid my-5">
        <div class="row justify-content-around my-2 gy-3">
            <div class="col-12 col-lg-6">
                <form:form action="/register" method="post" modelAttribute="newUser" class="p-4 bg-dark text-light">
                    <h2 class="text-primary">Register</h2>
                    <form:label path="name" class="py-2">Name:</form:label>
                    <form:errors path="name" class="text-danger"/>
                    <form:input path="name" type="text" class="form-control"/>
                    <form:label path="email" class="py-2">Email:</form:label>
                    <form:errors path="email" class="text-danger"/>
                    <form:input path="email" type="email" class="form-control"/>
                    <form:label path="password" class="py-2">Password:</form:label>
                    <form:errors path="password" class="text-danger"/>
                    <form:password path="password" class="form-control"/>
                    <form:label path="confirm" class="py-2">Confirm Password:</form:label>
                    <form:errors path="confirm" class="text-danger"/>
                    <form:password path="confirm" class="form-control"/>
                    <p class="pt-3">
                        <input type="submit" value="Register" class="btn btn-primary">
                    </p>
                </form:form>
            </div>
            <div class="col-12 col-lg-6">
                <form:form action="/login" method="post" modelAttribute="newLogin" class="p-4 bg-dark text-light">
                    <h2 class="text-success">Login</h2>
                    <form:label path="userEmail" class="py-2">Email:</form:label>
                    <form:errors path="userEmail" class="text-danger"/>
                    <form:input path="userEmail" type="email" class="form-control"/>
                    <form:label path="userPassword" class="py-2">Password:</form:label>
                    <form:errors path="userPassword" class="text-danger"/>
                    <form:password path="userPassword" class="form-control"/>
                    <p class="pt-3">
                        <input type="submit" value="Login" class="btn btn-success">
                    </p>
                </form:form>
            </div>
        </div>
    </div>

</body>
</html>
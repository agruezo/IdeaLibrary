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
<title>Show Idea</title>
</head>
<body>

	<div class="container-fluid m-3">
	   	<div class="col-12 col-md-6">
	   		<h3>${idea.content}</h3>
	   		<p>Created By: <c:out value="${idea.creator.name}"/></p>
				
			
	   	</div>
	   	<div class="col-6">
	   	<c:if test="${idea.creator.id == loggedInUser.id}">
	   	
	   		<h3>Users who liked your idea:</h3>
	   		<p>
			<a href="/ideas/${idea.id}/edit" class="btn btn-primary">Edit</a>
			</p>
	   	<table class="table table-striped table-primary my-3">
			<thead>
				<tr>
					<th>Name</th>
				</tr>
			<tbody>
				<c:forEach items="${likers}" var="liker">
			
				<tr class="table-light">
				
					<td>
						${liker.name}
					</td>
				</tr>
			
				</c:forEach>
			</tbody>
		</table>
	   	
	   	
	   	</c:if>
		<a href="/ideas" class="btn btn-warning">Dashboard</a> <a href="/logout" class="btn btn-danger">Logout</a>
	   	</div>
		
		
	</div>
	
</body>
</html>
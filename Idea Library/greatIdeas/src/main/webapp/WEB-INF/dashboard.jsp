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
<title>Dashboard</title>
</head>
<body>

	<div class="container-fluid">
		<h1 class="my-3">Welcome, <c:out value="${userLoggedIn.name}"/></h1>
		<div class="d-flex justify-content-between">
			<div class="my-2">
				<h4>Ideas</h4>
			</div>
			<div class="my-2">
				<p class="text-center">
					<a href="/logout" class="btn btn-danger">Logout</a>
				</p>
			</div>
		</div>
		<table class="table table-striped table-primary my-3">
		<thead>
			<tr>
				<th>Idea</th>
				<th>Created By: </th>
				<th>Likes</th>
				<th>Action</th>
			</tr>
		<tbody>
			<c:forEach items="${ideas}" var="idea">
			<tr class="table-light">
				<td><a href="/ideas/${idea.id}"><c:out value="${idea.content}"/></a></td>
				<td>${idea.creator.name}</td>
				<td>
					${idea.ideaLikers.size()}
				</td>
				<td>
				<c:if test="${idea.creator.id != loggedInUser.id}">
					<c:choose>
						<c:when test="${idea.ideaLikers.contains(userLoggedIn)}">
							<a href="/ideas/${idea.id}/unlike" class="btn btn-danger">Unlike</a>
						</c:when>
						<c:otherwise>
							<a href="/ideas/${idea.id}/like" class="btn btn-success">Like</a>
						</c:otherwise>
					</c:choose>
				</c:if>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<p><a href="/ideas/new" class="btn btn-primary">Create an idea</a></p>
	</div>
</body>
</html>
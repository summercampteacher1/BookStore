<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<%@ page import="com.bookstore.model.User" %>
<%@ page import="com.bookstore.model.Book" %>
<%! User user = null;%>
<% user = (User)session.getAttribute("user");%> 


<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add a Book <%= user.getFirstName() + " " + user.getLastName() %></title>
</head>
<body>

<jsp:include page="menu.jsp" />

<form action="/bookstore/controller?action=addABook" method="post" >
	<table border="0">
		<tr>
			<td>
				Book Title
			</td>
			<td>
				<input type="text" name = "title" placeholder="Book Title" />
			</td>
		</tr>
		<tr>
			<td>
				Author
			</td>
			<td>
				<input type="text" name = "author" placeholder="Author Name" />
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value = "Add Book" />
			</td>
		</tr>
	</table>
</form>




</body>
</html>
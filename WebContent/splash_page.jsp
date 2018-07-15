<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<%@ page import="com.bookstore.model.Book" %>
<%@ page import="com.bookstore.model.User" %>
<%@ page import="java.util.List" %>
<%! User user = null;%>
<% user = (User)session.getAttribute("user");%> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome <%= user.getFirstName() + " " + user.getLastName() %></title>
</head>
<body>

<jsp:include page="menu.jsp" />

<%! List<Book> books = null;%>
<% books = (List<Book>)session.getAttribute("books"); 
if (books != null && books.size() > 0) { %>
<table border="1">
	<tr>
		<th>
			Book Id
		</th>
		<th>
			Title
		</th>
		<th>
			Author
		</th>
		<th>
			Action
		</th>
	</tr>
<% } 
for (int i = 0; i < books.size();i++) {
	Book book = books.get(i);
	%>	
	<tr>
		<td>
			<%=book.getId()%>
		</td>
		<td>
			<%=book.getTitle()%>
		</td>
		<td>
			<%=book.getAuthor()%>
		</td>
		<td>
			<a href="/updateABook.jsp?id=<%=book.getId() %>">Update</a>
			&nbsp;
			<a href="/controller?action=deleteBooks&id=<%=book.getId() %>">Delete</a>
		</td>
	</tr>
	
<%} %>
</table>




</body>
</html>
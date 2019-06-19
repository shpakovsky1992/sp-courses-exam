<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<body>
  <a href="/sp-cources/logout">Logout</a>
	<h2>All books</h2>

	<p><b>Just list:</b><p>
<pre>
	${booksVariable}
</pre>

	<p><b>Iterated List:</b><p>

	<ol>
		<c:forEach var="book" items="${booksVariable}">
			<li>${book.id}. <a href="/sp-cources/book/edit/${book.id}">${book.name} (${book.publishedIn})</a></li>
		</c:forEach>
	</ol>
</body>
</html>
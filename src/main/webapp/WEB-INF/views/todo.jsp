<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<body>

<h1>Todo List</h1>

<div id="todoForm">
	<form:form action="${pageContext.request.contextPath}/todo/update" modelAttribute="todo" method="post">
		<form:hidden path="id"/>
		<form:hidden path="completed"/>
		이름: <form:input path="name"/><br/>
		코멘트: <form:input path="comment1"/><br/>
		<input type="submit" value="갱신" />
	</form:form>
	${result} <br/>
</div>

<ul>
	<style scoped="scoped">
		.strike{
			text-decoration: line-through;
		}
	</style>

	<c:forEach var="todo" items="${list}">
	<li>
			<a href="<c:url value="/todo/select?id=${todo.id}"/>">${todo.name}</a>
			<c:choose>
				<c:when test="${todo.completed}">
					<span class="strike">${todo.comment1}</span>
				</c:when>
				<c:otherwise>
					${todo.comment1}
					<form:form action="${pageContext.request.contextPath}/todo/complete"  modelAttribute="todo" 
							method="post" cssStyle="display: inline-block">
						<form:hidden path="id" value="${todo.id}" />
						<form:button>완료</form:button>
					</form:form>
				</c:otherwise>
			</c:choose>
			
			<fmt:formatDate value="${todo.updatedAt}"
					pattern="yyyy-MM-dd" />
					
			<form:form action="${pageContext.request.contextPath}/todo/delete"  modelAttribute="todo" 
					method="post" cssStyle="display: inline-block">
				<form:hidden path="id" value="${todo.id}" />
				<form:button>삭제</form:button>
			</form:form>
		
	</li>
	</c:forEach>
</ul>

</body>
</html>

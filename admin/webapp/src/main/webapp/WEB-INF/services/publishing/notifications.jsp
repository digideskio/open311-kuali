<%--
  Copyright 2011 The Kuali Foundation Licensed under the Educational Community
  License, Version 2.0 (the "License"); you may not use this file except in
  compliance with the License. You may obtain a copy of the License at
  http://www.osedu.org/licenses/ECL-2.0 Unless required by applicable law or
  agreed to in writing, software distributed under the License is distributed
  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
  express or implied. See the License for the specific language governing
  permissions and limitations under the License.
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
	<title>Publishing :: Notifications</title>
</head>
<body>
<h2>Notifications</h2>
<p>
<a href="notificationForm">New</a> 
</p>
<p>
<c:forEach items="${notifications}" var="notification" varStatus="status">
<a href="editNotification?id=${notification.notificationId}">Edit</a> | <a href="deleteNotification?id=${notification.notificationId}">Delete</a> -- 
<c:out value="${notification.title}"/> - <c:out value="${notification.message}"/> : <c:out value="${notification.startDate}"/> - <c:out value="${notification.endDate}"/> <br/> 
</c:forEach>
</p>
</body>
</html>

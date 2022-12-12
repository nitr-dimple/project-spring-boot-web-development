<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />

  <title>HouseRental</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

</head>

<body>
  <nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
      <div class="navbar-header">
        <a class="navbar-brand" href="#">HouseRental</a>
      </div>
      <ul class="nav navbar-nav">
        <li class="active"><a href="${pageContext.request.contextPath}/">Home</a></li>
        <li class="nav-item"><a  href="${pageContext.request.contextPath}/user/explore.htm">Explore</a></li>
        <c:if test="${sessionScope['username'] != null}" >
          <li class="nav-item"><a href="${pageContext.request.contextPath}/user/viewUser.htm"> <c:out value="${sessionScope['username'].getFullname()}"></c:out> </a></li>
          <c:if test="${sessionScope['username'].getUsertype().equals('owner')}">
            <li class="nav-item"><a href="${pageContext.request.contextPath}/user/addPost.htm">Add Post</a></li>
          </c:if>
          <li class="nav-item"><a href="${pageContext.request.contextPath}/user/logout.htm">Logout</a></li>
        </c:if>
        <c:if test="${sessionScope['username'] == null}" >
          <li class="nav-item"><a href="${pageContext.request.contextPath}/user/login.htm">Login</a></li>
          <li class="nav-item"><a href="${pageContext.request.contextPath}/user/add.htm">Register</a></li>
        </c:if>
      </ul>
    </div>
  </nav>


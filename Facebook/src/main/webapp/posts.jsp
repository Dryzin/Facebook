<jsp:directive.page contentType="text/html; charset=UTF-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
  
<!DOCTYPE html>
<html lang="pt-BR">
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-icons.css">
	<title>Facebook</title>
<body>
	<div class="container">
		<div class="row pt-5">
			<div class="col-md-1"></div>
			<div class="col-md-10">
				
				<a href="index.jsp" class="btn btn-secondary">
			    	<i class="bi bi-house"></i>
			  	</a>
				
				<div class="d-flex justify-content-between align-items-center mb-3">
			 
				  <h1 class="m-0">Posts</h1>
				
				  <a href="form_post.jsp" class="btn btn-primary">
				    New Post
				  </a>
			
				</div>
				
				<table class="table table-hover">
					<thead>
						<tr>
							<th scope="col">Id</th>
							<th scope="col">Content</th>
							<th scope="col">post_date</th>
							<th scope="col">user_id</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="posts" items="${posts}">
							<tr>
								<th scope="row">${posts.getId()}</th>
								<td scope="row">${posts.getContent()}</td>
								<td scope="row">${posts.getPostDate()}</td>
								<td scope="row">${posts.getUser().getName()}</td>
								<td>
									<a class="bi bi-pencil-square" href="${pageContext.request.contextPath}/post/update?id=${posts.id}"></a>
								</td>
								<td>
									<a class="bi bi-trash" href="${pageContext.request.contextPath}/post/delete?id=${posts.id}"></a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
				
				
			</div>
			<div class="col-md-1"></div>
		</div>
		<div class="row">

		</div>
	</div>
	<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
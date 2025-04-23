<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:directive.page contentType="text/html; charset=UTF-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<title>Criar Postagem</title>
</head>
<body>

	<div class="container mt-5">
		<h1 class="mb-4">${post.id != null ? 'Editar Postagem' : 'Nova Postagem'}</h1>

		<form action="/facebook/post/save" method="post">
    		
    		<input type="hidden" name="post_id" value="${post.id}" />

			

			<div class="mb-3">
				<label for="content" class="form-label">Conteúdo da Postagem</label>
				<textarea class="form-control" id="content" name="content" rows="1" required>
				    ${post.content}
				</textarea>

			</div>

			<button type="submit" class="btn btn-primary">
			  ${post.id != null ? 'Atualizar' : 'Publicar'}
			</button>
		</form>
	</div>

	<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
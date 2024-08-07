<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- jakarta.tags.core -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><!-- jakarta.tags.functions -->
<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>StackOverBus - Página de Login</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/login.css">
</head>
<body>
	<div class="container">
		<div class="col-lg-4 offset-lg-4 col-sm-12">
			<c:choose>
				<c:when test="${result == 'registered'}">
					<div class="alert alert-success alert-dismissible fade show"
						role="alert">
						Usuário cadastrado com sucesso.
						<button type="button" class="btn-close" data-bs-dismiss="alert"
							aria-label="Close"></button>
					</div>
				</c:when>
				<c:when test="${result == 'loginError'}">
					<div class="alert alert-danger alert-dismissible fade show"
						role="alert">
						E-mail ou senha inválidos.
						<button type="button" class="btn-close" data-bs-dismiss="alert"
							aria-label="Close"></button>
					</div>
				</c:when>
			</c:choose>
			<form action="ControllerServlet" method="post">

				<div class="col-12">
					<h1 class="text-center">Login</h1>
				</div>


				<div class="input-group mb-3">
					<span class="input-group-text"> <img src="img/envelope.svg"
						alt="E-mail" width="32" height="32">
					</span> <input type="email" name="email" placeholder="E-mail" id="email"
						class="form-control" required="required">
				</div>


				<div class="input-group mb-3">
					<span class="input-group-text"> <img src="img/file-lock.svg"
						alt="Senha" width="32" height="32">
					</span> <input type="password" name="password" placeholder="Senha"
						class="form-control" id="password" required="required">
				</div>


				<div class="col-12 mb-3">
					<button type="submit" class="btn btn-primary" name="action"
						value="login">Login</button>
				</div>

				<div class="col-12 mb-3">
					<a class="btn btn-secondary" href="user-register.jsp">Cadastrar</a>
				</div>

			</form>

		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
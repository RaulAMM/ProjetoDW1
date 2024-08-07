<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    import = "java.util.List,Bus.model.entities.Card"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/home.css">
<title>StackOverBus - Página principal</title>
</head>
<body>
	<jsp:include page="navbar.jsp" />
	<div class="container">
		<div class="center col-lg-10 col-sm-12">
			<div class="col-12">
				<h1 class="text-center">Cartões</h1>
			</div>
			<form action="ControllerServlet" method="post">
				<div class="row">
					<div class="col-12 col-lg-3">
					  	<div class="mb-2">
							<label for="type">Tipo</label> 
							<select class="form-select"
								name="type" id="type">
								<option value="" selected>Selecione</option>
								<option value="Escolar">Escolar</option>
								<option value="Padrao">Padrão</option>
								<option value="Empresarial">Empresarial</option>
							</select>
						</div>
					</div>
					<div class="col-12 col-lg-3 mt-4">
						<button type="submit" class="btn btn-primary" name="action" value="searchActivities">Filtrar</button>
					</div>  
				</div>
			</form>
			<c:choose>
				<c:when test="${fn:length(userActivities) > 0}">
					<table class="table table-responsive table-striped table-hover" >
						<tr>
							<th>#</th>
							<th>Tipo</th>
							<th>Id</th>
							<th>Status</th>
							<th>Saldo</th>
						</tr>
						<c:forEach var="card" items="${userCards}" varStatus="index">
							<tr>
								<td>${index.count}</td>
								<td>
									<c:choose>
										<c:when test="${card.type == 'Escolar'}">
											<img src="img/running_icon.png" alt="Escolar">
										</c:when>
										<c:when test="${card.type == 'Padrao'}">
											<img src="img/walking_icon.png" alt="Corrida">
										</c:when>
										<c:when test="${card.type == 'Empresarial'}">
											<img src="img/cycling_icon.png" alt="Corrida">
										</c:when>
									</c:choose>
								</td>
							
									<span data-bs-toggle="tooltip" data-bs-placement="top" title="Editar">
										<a class="btn" href="ControllerServlet?action=updateCard&Card-id=${card.id}">
	                						<img src="img/pencil-square.svg" alt="Editar">
	                					</a>
									</span>
									
                					<span data-bs-toggle="tooltip" data-bs-placement="top" title="Excluir">
                						<a type="button" class="btn" data-bs-toggle="modal" data-bs-target="#myModal" data-bs-id="${activity.id}">
	                						<img src="img/trash.svg" alt="Excluir">
	                					</a>
                					</span>
                					
								</td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<c:out value="Sem cartões registrados."></c:out>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	
	<!-- Modal -->
	<div class="modal" tabindex="-1" id="myModal">
		<div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title">Exclusão</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		        <p>Tem certeza que deseja excluir o cartão?</p>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
		        <button type="button" id="delete" class="btn btn-danger">Excluir</button>
		      </div>
		    </div>
	  	</div>
	</div>
	
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<script type="text/javascript" src="js/home.js"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="col-9">
<jsp:include page="/common/inform.jsp"></jsp:include>
	<div class="row p-2">

		<c:forEach var="item" items="${videos }">
			<div class="col-4 mt-2">



				<div class="card" style="width: 15rem;">
					<img src="${item.poster }" class="card-img-top" alt="...">
					<div class="card-body">
						<div style="text-align: center">
							<h5 class="card-title">${item.title}</h5>

						</div>
						<div style="text-align: justify">
							<p class="card-text">${item.desciption }</p>
						</div>

						
						<c:if test="${!isLogin }">
						
							<a
							href="Login"
							class="btn btn-success">Like</a>
						
						</c:if>
							<c:if test="${isLogin }">
						
							<a
							href="Favorite/Like?username=${username }&videoId=${item.videoId}"
							class="btn btn-success">Like</a>
						
						</c:if>
						
						

						<c:if test="${!isLogin }">
							<a
							href="Login"
							class="btn btn-primary">Share</a>

						
						</c:if>	
							
							<c:if test="${isLogin }">
							<a
							href="ShareVideo?username=${username }&videoId=${item.videoId}"
							class="btn btn-primary">Share</a>
							
							</c:if>
					
						





					</div>
				</div>
			</div>
		</c:forEach>



	</div>
	<div class="row">

		<div class="col">
			<nav aria-label="Page navigation">
				<ul class="pagination justify-content-center">
					<li class="page-item disabled"><a class="page-link" href="#"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
							<span class="sr-only">Previous</span>
					</a></li>
					<li class="page-item active"><a class="page-link" href="#">1</a></li>
					<li class="page-item"><a class="page-link" href="#">2</a></li>
					<li class="page-item"><a class="page-link" href="#"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span> <span
							class="sr-only">Next</span>
					</a></li>
				</ul>
			</nav>
		</div>
	</div>
</div>


<div class="col-3">
	<div class="row mt-3 mb-3">
		<div class="col">
			<div class="card">

				<div class="card-header">
					<i class="fa fa-thumbs-up" aria-hidden="true"></i> Favorite
				</div>
				<ul class="list-group list-group-flush">
					<c:forEach var="item" items="${favList }">
					
					<li class="list-group-item">${item.video.title }</li>
					</c:forEach>
					
					
				</ul>
			</div>
		</div>
	</div>
</div>







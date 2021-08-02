<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="col-9">
<jsp:include page="/common/inform.jsp"></jsp:include>
	<div class="row p-2">
		<c:forEach var="item" items="${favList }">
			<div class="col-4 mt-2">



				<div class="card" style="width: 15rem;">
					<img src="${item.video.poster }" class="card-img-top" alt="...">
					<div class="card-body">
						<div style="text-align: center">
							<h5 class="card-title">${item.video.title}</h5>

						</div>
						<div style="text-align: justify">
							<p class="card-text">${item.video.desciption }</p>
						</div>
						<a
							href="Favorite/Unlike?username=${username }&videoId=${item.video.videoId}"
							class="btn btn-danger"> Unlike </a> 
							<a
							href="ShareVideo?username=${username }&videoId=${item.video.videoId}"
							class="btn btn-primary">Share</a>
							
							




					</div>
				</div>
			</div>
		</c:forEach>



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
<script type="text/javascript">
	var exampleModal = document.getElementById('exampleModal')
	exampleModal.addEventListener('show.bs.modal', function(event) {
		// Button that triggered the modal
		var button = event.relatedTarget
		// Extract info from data-bs-* attributes
		var recipient = button.getAttribute('data-bs-whatever')
		// If necessary, you could initiate an AJAX request here
		// and then do the updating in a callback.
		//
		// Update the modal's content.
		var modalTitle = exampleModal.querySelector('.modal-title')
		var modalBodyInput = exampleModal.querySelector('.modal-body input')

		modalTitle.textContent = 'New message to '
		modalBodyInput.value = recipient
	})
</script>



<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!doctype html>
<html lang="en">

<head>
<base href="/Poly.Asg/" />
<title>${page.title }</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="starter-template.css" rel="stylesheet">
<link href="../assets/dist/css/bootstrap.min.css" rel="stylesheet">


</head>

<body>
	<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">Shop</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarsExampleDefault"
				aria-controls="navbarsExampleDefault" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarsExampleDefault">
				<ul class="navbar-nav me-auto mb-2 mb-md-0">
					<li class="nav-item active"><a class="nav-link"
						href="Homepage"><i class="fa fa-home" aria-hidden="true"></i>Home
							<span class="sr-only"></span></a></li>
					<li class="nav-item"><a class="nav-link" href="#"><i
							class="fa fa-info" aria-hidden="true"></i> About Us</a></li>
					<li class="nav-item"><a class="nav-link" href="#"><i
							class="fa fa-id-card" aria-hidden="true"></i> Contact Us</a></li>
					<li class="nav-item"><a class="nav-link" href="#"> <i
							class="fa fa-comments" aria-hidden="true"></i> My Favorite
					</a></li>
					



					<li class="nav-item dropdown">
					<c:if test="${!isLogin }">
					<a
						class="nav-link dropdown-toggle" href="#" id="dropdownId"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i
							class="fa fa-user" aria-hidden="true"></i> My Account</a>
							
							</c:if>
							<c:if test="${isLogin }">
					<a
						class="nav-link dropdown-toggle" href="#" id="dropdownId"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i
							class="fa fa-user" aria-hidden="true"></i>Hello ${username }</a>
							
							</c:if>
						<div class="dropdown-menu" aria-labelledby="dropdownId">
						
							<c:if test="${!isLogin }">
								<a class="dropdown-item" href="Login">Login</a>
								<a class="dropdown-item" href="Registration">Registration</a>
								<a class="dropdown-item" href="Forgotpassword">Forgot Password</a>
							</c:if>

							<c:if test="${isLogin }">
								<c:if test="${role }">
								<a class="dropdown-item" href="Admin/VideosManagement">Admin</a>
								</c:if>
								<a class="dropdown-item" href="Logoff">Logout</a>
								<a class="dropdown-item" href="Changepassword">Change Password</a>
								<a class="dropdown-item" href="EditProfile">Edit Profile</a>
							

							</c:if>
						</div></li>
				</ul>
				<form class="d-flex">
					<input class="form-control me-2" type="search" placeholder="Search"
						aria-label="Search">
					<button class="btn btn-outline-success" type="submit">Search</button>
				</form>
			</div>
		</div>
	</nav>


	<main class="container">
		<div class="border border-secondary bg-dark img-fluid">
			<img alt="" src="images/Logo-FE.png" width="100%" height="450px">
		</div>

		<section class="row">
			<jsp:include page="${page.contentUrl }"></jsp:include>
		</section>
		<footer class="row">

			<div class="col text-center border-top">
				<strong>FPT Polytechnic &copy; 2020. ALL right reserved</strong>
			</div>
		</footer>
	</main>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
	<script src="../assets/dist/js/bootstrap.bundle.min.js"></script>

	<c:if test="${not empty page.scriptUrl }">
		<jsp:include page="${page.scriptUrl }"></jsp:include>

	</c:if>
</body>
<script>
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

		modalTitle.textContent = 'New message to ' + recipient
		modalBodyInput.value = recipient
	})
</script>

</html>
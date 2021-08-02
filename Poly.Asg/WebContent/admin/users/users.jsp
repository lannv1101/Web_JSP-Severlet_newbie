<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="col mt-4">
<jsp:include page="/common/inform.jsp"></jsp:include>
	<ul class="nav nav-tabs" id="myTab" role="tablist">
		<li class="nav-item" role="presentation"><a
			class="nav-link active" id="userEditing-tab" data-toggle="tab"
			href="#userEditing" role="tab" aria-controls="userEditing"
			aria-selected="true">User Editing</a></li>
		<li class="nav-item" role="presentation"><a class="nav-link"
			id="userList-tab" data-toggle="tab" href="#userList" role="tab"
			aria-controls="userList" aria-selected="false">User List</a></li>

	</ul>
	<div class="tab-content" id="myTabContent">
		<div class="tab-pane fade show active" id="userEditing"
			role="tabpanel" aria-labelledby="userEditing-tab">

			<form action="" method="post" enctype="multipart/form-data">
				<div class="card">
					<div class="card-body">
						<div class="row">
							<div class="col">
								<div class="form-group">
									<label for="username">Username</label> <input value="${user.username }" type="text"
										class="form-control" name="username" id="username"
										aria-describedby="username" placeholder="username"> <small
										id="username" class="form-text text-muted">Username is
										required</small>
								</div>
								<div class="form-group">
									<label for="fullname">Fullname</label> <input value="${user.fullname }" type="text"
										class="form-control" name="fullname" id="fullname"
										aria-describedby="fullname" placeholder="Fullname"> <small
										id="fullname" class="form-text text-muted">Fullname is
										required</small>
								</div>
							</div>
							<div class="col">
								<div class="form-group">
									<label for="password">Password</label> <input type="password"
										class="form-control" name="password" id="password"
										aria-describedby="password" placeholder="Password"> <small
										id="password" class="form-text text-muted">Password is
										required</small>
								</div>
								<div class="form-group">
									<label for="email">Email</label> <input value="${user.email }" type="text"
										class="form-control" name="email" id="email"
										aria-describedby="email" placeholder="Email"> <small
										id="email" class="form-text text-muted">Email is
										required</small>
								</div>
							</div>
						</div>
					</div>
					<div class="card-footer text-muted">
						<button class="btn btn-primary" formaction="Admin/UsersManagement/create">Create</button>
						<button class="btn btn-warning" formaction="Admin/UsersManagement/update" >Update</button>
						<button class="btn btn-danger" formaction="Admin/UsersManagement/delete" >Delete</button>
						<button class="btn btn-info" formaction="Admin/UsersManagement/reset" >Reset</button>
					</div>
				</div>
			</form>
		</div>
		<div class="tab-pane fade" id="userList" role="tabpanel"
			aria-labelledby="userList-tab">

			<table class="table table-strip">
				<tr>
					<td>UserName</td>
					<td>Fullname</td>
					<td>Email</td>
					<td>Role</td>
					<td>&nbsp;</td>
				</tr>
				<c:forEach var="item" items="${users }">
				<tr>
					<td>${item.username }</td>
					<td>${item.fullname }</td>
					<td>${item.email }</td>
					<td>${item.admin? 'Admin':'User' }</td>
					<td><a href="Admin/UsersManagement/edit?username=${item.username }"><i class="fa fa-edit" aria-hidden="true">Edit</i></a>
					
							<a href="Admin/UsersManagement/delete?username=${item.username }"><i class="fa fa-edit" aria-hidden="true">Delete</i></a>
					</td>
				</tr>
				</c:forEach>
			</table>
		</div>

	</div>
</div>
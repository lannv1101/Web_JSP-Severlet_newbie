<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="col-9">
 
	<div class="row p-2">
		
			<div class="col-4 mt-2">



				<div class="card" style="width: 15rem;">
					<img src="${video.poster }" class="card-img-top" alt="...">
					<div class="card-body">
						<div style="text-align: center">
						<h5 class="card-title">${video.title}</h5>
						
						</div>
						<div style="text-align: justify">
						<p class="card-text">${video.desciption }</p>
						</div>
						<a href="DetailVideo?videoid=${video.videoId}" class="btn btn-primary">UnLike</a> 
						<a href="#"
							class="btn btn-success">Share</a>
						


				
					</div>
				</div>
			</div>
	



	</div>
	
</div>



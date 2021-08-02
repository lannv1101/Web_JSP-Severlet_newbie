<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <div class="col mt-4">
 			  <jsp:include page="/common/inform.jsp"></jsp:include>
            <ul class="nav nav-tabs" id="myTab" role="tablist">
                <li class="nav-item" role="presentation">
                  <a class="nav-link active" id="videoEditing-tab" data-toggle="tab" href="#videoEditing" role="tab" aria-controls="videoEditing" aria-selected="true">Video Editing</a>
                </li>
                <li class="nav-item" role="presentation">
                  <a class="nav-link" id="videoList-tab" data-toggle="tab" href="#videoList" role="tab" aria-controls="videoList" aria-selected="false">Video List</a>
                </li>
                
              </ul>
              <div class="tab-content" id="myTabContent">
            
                <div class="tab-pane fade show active" id="videoEditing" role="tabpanel" aria-labelledby="videoEditing-tab">
					
                    <form action="" method="post" enctype="multipart/form-data">
                        <div class="card">
                            <div class="card-body">
                               <div class="row">
                                   <div class="col-3">
                                    <div class="border p-3">
                                        <img src="${video.poster !=null?video.poster:'images/desktop.jsp'  }" alt="" class="img-fluid">
                                        <div class="input-group mb-3 mt-3">
                                      	  	<div class="input-group-prepend">
                                        		<span class="input-group-text">Upload</span>
                                        
                                       		 </div>
                                       		 <div class="custom-file">
                                       		 
                                       		 <input type="file" class="custom-file-input" id="cover" name="cover"/>
                                       		 	<label for="cover">Choose File</label>
                                       		 </div>
                                       		 <div class="mb-3">
											  <label for="formFile" class="form-label"></label>
											  <input class="form-control" type="file" id="cover" name="cover">
											</div>
										
                                        
                                        
                                        </div>
                                    </div>
                                   </div>
                                   <div class="col-9">
                                       <div class="form-group">
                                         <label for="youtubeId">Youtube ID</label>
                                         <input value="${video.videoId }" type="text" class="form-control" name="videoId" id="youtubeId" aria-describedby="youtubeId" placeholder="Youtube ID">
                                         <small id="youtubeId" class="form-text text-muted">Youtube id is required</small>
                                       </div>
                                       <div class="form-group">
                                        <label for="videoTitle">Video Title</label>
                                        <input value="${video.title }" type="text" class="form-control" name="title" id="videoTitle" aria-describedby="videoTitle" placeholder="Video Title">
                                        <small id="videoTitle" class="form-text text-muted">Video Title is required</small>
                                      </div>
                                      <div class="form-group">
                                        <label for="viewCount">View Count</label>
                                        <input value="${video.views }" type="text" class="form-control" name="views" id="viewCount" aria-describedby="viewCount" placeholder="View Count">
                                        <small id="viewCount" class="form-text text-muted">View Count is required</small>
                                      </div>
                                      <div class="form-check form-check-inline">
                                        <label >
                                        <input ${video.active? 'checked':'' } type="radio" class="form-check-input" name="active" id="status" value="true">Active
                                    </label>
                                    <label >
                                        <input ${!video.active? 'checked':'' } type="radio" class="form-check-input" name="active" id="status" value="false">In Active
                                    </label>
                                    </div>
                                   </div>
                               </div>
                               <div class="row">
                                   <div class="col">
                                       <label for="description">Description</label>
                                       <textarea   name="desciption" id="desciption" cols="30" rows="4" class="form-control">${video.desciption }</textarea>
                                   </div>
                               </div>
                            </div>
                            <div class="card-footer text-muted">
                              
                                <button class="btn btn-primary" formaction="Admin/VideosManagement/create">Create</button>
                                <button class="btn btn-warning" formaction="Admin/VideosManagement/update">Update</button>
                                <button class="btn btn-danger" formaction="Admin/VideosManagement/delete">Delete</button>
                                <button class="btn btn-info" formaction="Admin/VideosManagement/reset">Reset</button>
                           
                        </div>
                        </div>
                    </form>
                </div>
                <div class="tab-pane fade" id="videoList" role="tabpanel" aria-labelledby="videoList-tab">
					 
                    <table class="table table-strip">
                        <tr>
                            <td>Youtube ID</td>
                            <td>Video Title</td>
                            <td>View Count</td>
                            <td>Status</td>
                            <td>&nbsp;</td>
                        </tr>
                        <c:forEach var="item" items="${videos}">
                        <tr>
                            <td>${item.videoId }</td>
                            <td>${item.title }</td>
                            <td>${item.views }</td>
                            <td>${item.active? 'Active':'Inactive' }</td>
                            <td><a href="Admin/VideosManagement/edit?videoId=${item.videoId }"><i class="fa fa-edit" aria-hidden="true">Edit</i></a>
                                <a href="Admin/VideosManagement/delete?videoId=${item.videoId }"><i class="fa fa-trash" aria-hidden="true"></i>Delete</a>
                            
                            </td>
                        </tr>
                        </c:forEach>
                    </table>
                </div>
   
              </div>
            </div>
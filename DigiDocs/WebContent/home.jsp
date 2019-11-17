<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- <!DOCTYPE html> -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/main.css">
<title>Welcome to DigiDocs</title>

</head>
<body>
	<jsp:include page="/GetFolderFile"></jsp:include>
	<div class="div_0">
		<div class="div0_1">
			<div class="profile">
				<c:if test="${sessionScope.profilepic != null}">
					<img src="data:image/jpeg;base64,${sessionScope.profilepic}"
						class="pic" id="profile_photo">
				</c:if>
				<c:if test="${sessionScope.profilepic == null}">
					<img src="images/pp.png" class="pic" id="profile_photo">
				</c:if>
			</div>
			<div class="dbx">
				<p class="details">${sessionScope.user.name }</p>
			</div>
		</div>
		<div class="div0_2">
			<div class="option">
				<c:if test="${sessionScope.user.name == folder.user.name }">
					<button class="btn1" id="UploadFile">Upload file</button>
					<button class="btn1" id="CreateFolder">Create folder</button>
				</c:if>
				<c:if test="${sessionScope.user.name != folder.user.name }">
					<button class="btn1" id="UploadFile" disabled="disabled"
						onclick="alert('You can upload files in your folder only!')">Upload
						file</button>
					<button class="btn1" id="CreateFolder" disabled="disabled">Create
						folder</button>
				</c:if>
				<a href="Logout" class="btn1" style="float: right;">Logout</a>
			</div>
			<div>
				<table class="tbt">
					<c:forEach items="${folders}" var="folder">
						<c:url var="openFolder" value="GetSubFoldersFiles">
							<c:param name="id" value="${folder.folderId }"></c:param>
						</c:url>
						<c:url var="deleteFolder" value="Delete">
							<c:param name="id" value="${folder.folderId }"></c:param>
							<c:param name="type" value="folder"></c:param>
						</c:url>
						<tr>
							<td class="tbtd"><a href="${openFolder}"><c:out
										value="${folder.folderName}"></c:out></a></td>
							<c:if test="${sessionScope.user.name == folder.user.name }">
								<td class="tbtd"><a class="btn1" href="${deleteFolder}">Delete</a></td>
							</c:if>
						</tr>
					</c:forEach>
				</table>
				<table class="tbt">
					<c:forEach items="${files}" var="file">
						<c:url var="delete" value="Delete">
							<c:param name="id" value="${file.fileId }"></c:param>
							<c:param name="type" value="file"></c:param>
						</c:url>
						<c:set var="contains" value="false" />
						<c:forEach var="item" items="${sessionScope.user.files2}">
							<c:if test="${item.fileName eq file.fileName}">
								<c:set var="contains" value="true" />
							</c:if>
						</c:forEach>
						<tr>
							<form id="accessForm">
								<td><c:if
										test="${sessionScope.user.name == file.user.name  || contains == true}">
										<a href="${file.url }"><c:out value="${file.fileName}"></c:out></a>
									</c:if> <c:if
										test="${sessionScope.user.name != file.user.name  && contains == false}">
										<c:out value="${file.fileName}"></c:out>
									</c:if></td>
								<c:if test="${sessionScope.user.name == file.user.name }">
									<td><a class="btn1" href="${delete}">Delete</a></td>
								</c:if>
								<input type="hidden" name="fileId"
									value="${file.fileId }">
								<c:if test="${sessionScope.user.name == file.user.name }">
									<td><input type="submit" class="btn1" id="grantAccess"
										value="Grant Access"></td>
								</c:if>
							</form>
							<form id="revokeForm">
							<input type="hidden" name="fileId"
									value="${file.fileId }">
							
							<c:if test="${sessionScope.user.name == file.user.name }">
									<td><input type="submit" class="btn1" id="revokeAccess"
										value="Revoke Access"></td>
								</c:if>
							</form>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	<div class="modal" id="addFolderModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Enter the
						folder name below</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<input type="text" id="newFolderName" class="txtbx"
						placeholder="Enter Folder Name Here">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="create">Create
						Folder</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal" id="addFileModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Select the file
						below</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form action="<%=request.getContextPath()%>/UploadFile"
					method="post" enctype="multipart/form-data">
					<div class="modal-body">
						<input type="file" name="file" id="newFile"
							placeholder="Your file"> <br> <input type="radio"
							name="access" id="access" value="open" checked>Public <input
							type="radio" name="access" id="access" value="close">Private
					</div>
					<div class="modal-footer">
						<input type="submit" class="btn btn-primary">
						<!-- 					<button type="button" class="btn btn-primary" id="upload">Upload File</button> -->
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="modal" id="grantAccessModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Enter the User
						Email Id below</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<input type="email" id="userId" class="txtbx"
						placeholder="Enter Email Id Here">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="grant">Grant
						Access</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal" id="revokeAccessModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Enter the User
						Email Id below</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<input type="email" id="revokeUserId" class="txtbx"
						placeholder="Enter Email Id Here">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="revoke">Revoke
						Access</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="js/jquery.js"></script>
</body>
<script type="text/javascript">
		var modal = document.getElementById("addFolderModal");
		var btn = document.getElementById("CreateFolder");
		var foldername= document.getElementById("newFolderName");
		var span = document.getElementsByClassName("close")[0];
		var create = document.getElementById("create");
		btn.onclick = function() {
			modal.style.display = "block";
		}
		span.onclick = function() {
			modal.style.display = "none";
			$('#newFolderName').val(' ');
		}
		window.onclick = function(event) {
			if (event.target == modal) {
				modal.style.display = "none";
				$('#newFolderName').val(' ');
			}
		}
		create.onclick = function(){
			modal.style.display = "none";
		}
		
		$('#create').on('click',function(e){
			var fname=$('#newFolderName').val();
			console.log(fname);
			$.get("<%=request.getContextPath()%>/CreateFolder", {name : "" + fname + ""}).done(function() {
		});
	});

	var modal2 = document.getElementById("addFileModal");
	var btn2 = document.getElementById("UploadFile");
	var file = document.getElementById("newFile");
	var access = document.getElementById("access");
	var span2 = document.getElementsByClassName("close")[1];
	btn2.onclick = function() {
		modal2.style.display = "block";
	}
	span2.onclick = function() {
		modal2.style.display = "none";
		$('#newFile').val(' ');
	}
	window.onclick = function(event) {
		if (event.target == modal2) {
			modal2.style.display = "none";
			$('#newFile').val(' ');
		}
	}
	
	var data;
	var fileId;
	$('#accessForm').submit(function(e){
		e.preventDefault();
		data=$(this).serialize();
		console.log(data);
		var fakeURL = "http://www.example.com/t.html?" +  data;
		var createURL = new URL(fakeURL);
		fileId=createURL.searchParams.get('fileId');
		return false;
	});
	
	var modal3 = document.getElementById("grantAccessModal");
	var btn3 = document.getElementById("grantAccess");
	var userId= document.getElementById("userId");
	var span3 = document.getElementsByClassName("close")[2];
	var grant = document.getElementById("grant");
	btn3.onclick = function() {
		modal3.style.display = "block";
	}
	span3.onclick = function() {
		modal3.style.display = "none";
		$('#userId').val(' ');
	}
	window.onclick = function(event) {
		if (event.target == modal3) {
			modal3.style.display = "none";
			$('#userId').val(' ');
		}
	}
	grant.onclick = function(){
		modal3.style.display = "none";
	}
	
	
	$('#grant').on('click',function(e){
		var uid=$('#userId').val();
		$.get("<%=request.getContextPath()%>/GrantAccess", {userId : "" + uid + "",fileId : "" + fileId + ""}).done(function() {
		});
	});
	
	$('#revokeForm').submit(function(e){
		e.preventDefault();
		data=$(this).serialize();
		console.log(data);
		var fakeURL = "http://www.example.com/t.html?" +  data;
		var createURL = new URL(fakeURL);
		fileId=createURL.searchParams.get('fileId');
		return false;
	});
	
	var modal4 = document.getElementById("revokeAccessModal");
	var btn4 = document.getElementById("revokeAccess");
	var revokeUserId= document.getElementById("revokeUserId");
	var span4 = document.getElementsByClassName("close")[3];
	var revoke = document.getElementById("revoke");
	btn4.onclick = function() {
		modal4.style.display = "block";
	}
	span4.onclick = function() {
		modal4.style.display = "none";
		$('#revokeUserId').val(' ');
	}
	window.onclick = function(event) {
		if (event.target == modal4) {
			modal4.style.display = "none";
			$('#revokeUserId').val(' ');
		}
	}
	revoke.onclick = function(){
		modal4.style.display = "none";
	}
	
	$('#revoke').on('click',function(e){
		var uid=$('#revokeUserId').val();
		$.get("<%=request.getContextPath()%>/RevokeAccess", {userId : "" + uid + "",fileId : "" + fileId + ""}).done(function() {
		});
	});
</script>
</html>
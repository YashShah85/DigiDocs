<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- <!DOCTYPE html> -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				<button class="btn1" id="UploadFile">Upload file</button>
				<button class="btn1" id="CreateFolder">Create folder</button>
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
							<td class="tbtd"><a href="${openFolder}"><c:out value="${folder.folderName}"></c:out></a></td>
							<td class="tbtd"><a class="btn1" href="${deleteFolder}">Delete</a></td>
						</tr>
					</c:forEach>
				</table>
				<table class="tbt">
					<c:forEach items="${files}" var="file">
						<c:url var="delete" value="Delete">
							<c:param name="id" value="${file.fileId }"></c:param>
							<c:param name="type" value="file"></c:param>
						</c:url>
						<tr>
							<td><a href="${file.url }"><c:out
										value="${file.fileName}"></c:out></a></td>
							<td><a class="btn1" href="${delete}">Delete</a></td>
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
				<form action="<%=request.getContextPath()%>/UploadFile" method="post" enctype="multipart/form-data">
				<div class="modal-body">
				<input type="file" name="file" id="newFile" placeholder="Your file">
				<br>
				<input type="radio" name="access" id="access" value="open" checked>Public
				<input type="radio" name="access" id="access" value="close">Private
				</div>
				<div class="modal-footer">
				<input type="submit" class="btn btn-primary">
<!-- 					<button type="button" class="btn btn-primary" id="upload">Upload File</button> -->
				</div>
				</form>
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
		btn.onclick = function() {
			modal.style.display = "block";
		}
		span.onclick = function() {
			modal.style.display = "none";
			document.getElementById('newFolderName').value == '';
		}
		window.onclick = function(event) {
			if (event.target == modal) {
				modal.style.display = "none";
			}
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
		document.getElementById('newFile').value == '';
	}
	window.onclick = function(event) {
		if (event.target == modal2) {
			modal2.style.display = "none";
		}
	}
</script>
</html>
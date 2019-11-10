<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- <!DOCTYPE html> -->
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/masterCSS.css">
<title>Register</title>
</head>
<body>
	<div class="div0">
		<div class="div1"></div>
		<div class="div2">
			<img src="images/logo.PNG"></img>
		</div>
		<div>
			<form action="<%=request.getContextPath()%>/Register" method="post" enctype="multipart/form-data">
				<div class="div3_1">
					<div>
						<table class="tbt" align="center">
							<tr>
								<th class="tbth">
									<p>Full Name</p>
								</th>
								<td class="tbtd"><input type="text" class="txtbx"
									name="name" required></td>
							</tr>
							<!-- 							<tr> -->
							<!-- 								<th class="tbth"> -->
							<!-- 									<p>Last Name</p> -->
							<!-- 								</th> -->
							<!-- 								<td class="tbtd"><input type="password" class="txtbx"> -->
							<!-- 								</td> -->
							<!-- 							</tr> -->
							<tr>
								<th class="tbth">
									<p>Email</p>
								</th>
								<td class="tbtd"><input type="email" class="txtbx"
									name="email" required></td>
							</tr>
							<tr>
								<th class="tbth">
									<p>Password</p>
								</th>
								<td class="tbtd"><input type="password" class="txtbx"
									name="password" required></td>
							</tr>
							<!-- 							<tr> -->
							<!-- 								<th class="tbth"> -->
							<!-- 									<p>Confirm Password</p> -->
							<!-- 								</th> -->
							<!-- 								<td class="tbtd"><input type="password" class="txtbx" name="confpassword" required> -->
							<!-- 								</td> -->
							<!-- 							</tr> -->
							<tr>
								<th class="tbth">
									<p>Profile Picture</p>
								</th>
								<td class="tbtd"><input type="file" class="txtbx"
									name="pic"></td>
							</tr>
						</table>
						<input type="submit" class="btn" value="Register">
						<!-- 						<a href="main.html" class="btn">Register</a> -->
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
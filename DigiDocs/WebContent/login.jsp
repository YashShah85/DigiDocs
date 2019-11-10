<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- <!DOCTYPE html> -->
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/masterCSS.css">
<title>Login</title>
</head>
<body>
	<div class="div0">
		<div class="div1"></div>
		<div class="div2">
			<img src="images/logo.PNG"></img>
		</div>
		<div>
			<form action="<%= request.getContextPath()%>/Login" method="post">
				<div class="div3_1">
					<div>
						<table class="tbt" align="center">
							<tr>
								<th class="tbth">
									<p>Username</p>
								</th>
								<td class="tbtd"><input type="email" class="txtbx"
									name="username" required></td>
							</tr>
							<tr>
								<th class="tbth">
									<p>Password</p>
								</th>
								<td class="tbtd"><input type="password" class="txtbx"
									name="password" required></td>
							</tr>
						</table>
						<input type="submit" class="btn" value="Login">
<!-- 						<a href="main.html" class="btn">Login</a> -->
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
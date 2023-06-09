<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<title>Register</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="/assets/fonts/font-awesome-4.7.0/css/font-awesome.min.cs" >
	<link rel="stylesheet" type="text/css" href="<c:url value='/assets/fonts/iconic/css/material-design-iconic-font.min.css'/> ">

	<link rel="stylesheet" type="text/css" href="<c:url value='/assets/css/util.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/assets/css/main.css'/>">
</head>
<body>
	
	<div class="limiter">
		<div class="container-login100" style="background-image: url('images/bg-01.jpg');">
			<div class="wrap-login100">
				<form:form class="login100-form validate-form" action="/api/v/auth/register/sign-up" method="post"
				 modelAttribute="AccountRegister">
					<span class="login100-form-title p-b-34 p-t-27">
						Sign Up 
                    </span>
                    <div class="wrap-input100 validate-input" data-validate = "firstname">
                            < form:input path="firstname" class="input100" type="text" name="firstname" placeholder="firstname" />
                            <span class="focus-input100" data-placeholder="&#xf207;"></span>
                        </div>
					<div class="wrap-input100 validate-input" data-validate = "lastname">
                            <form:input path="lastname" class="input100" type="text" name="lastname" placeholder="lastname" />
                            <span class="focus-input100" data-placeholder="&#xf207;"></span>
                        </div>

                        <div class="wrap-input100 validate-input" data-validate = "Email">
                                <form:input path="email"  class="input100" type="email" name="Email" placeholder="Email" />
                                <span class="focus-input100" data-placeholder="&#xf207;"></span>
                            </div>

					<div class="wrap-input100 validate-input" data-validate = "Enter username">
						<form:input path="phone"  class="input100" type="text" name="phone" placeholder="phone" />
						<span class="focus-input100" data-placeholder="&#xf207;"></span>
					</div>

					<div class="wrap-input100 validate-input" data-validate="Enter password">
						<form:input path="password"  class="input100" type="password" name="pass" placeholder="Password" />
						<span class="focus-input100" data-placeholder="&#xf191;"></span>
                    </div>
                    <div class="wrap-input100 validate-input" data-validate="Repeat password">
                            <input class="input100" type="password" name="pass" placeholder="Repeat Password">
                            <span class="focus-input100" data-placeholder="&#xf191;"></span>
                        </div>

					<div class="contact100-form-checkbox">
						<input class="input-checkbox100" id="ckb1" type="checkbox" name=" I Agree to terms of user ">
						<label class="label-checkbox100" for="ckb1">
                                I agree to <b>the terms of user</b> 
						</label>
					</div>

					<div class="container-login100-form-btn">
						<button class="login100-form-btn">
							Sign Up
						</button>
					</div>

					<div class="text-center p-t-90">
						<a class="txt1" href="index.html">
							-> sign in 
						</a>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<div id="dropDownSelect1"></div>
	
<!--===============================================================================================-->
<script src="<c:url value='/assets/vendor/jquery/jquery-3.2.1.min.js'/>" ></script>
<!--===============================================================================================-->
	<script src="<c:url value='/assets/vendor/animsition/js/animsition.min.js'/> "></script>
<!--===============================================================================================-->
	<script src="<c:url value='/assets/vendor/bootstrap/js/popper.js'/> "></script>
	<script src="<c:url value='/assets/vendor/bootstrap/js/bootstrap.min.js'/> "></script>
<!--===============================================================================================-->
	<script src="<c:url value='/assets/vendor/select2/select2.min.js'/> "></script>
<!--===============================================================================================-->
	<script src="<c:url value='/assets/vendor/daterangepicker/moment.min.js'/>" ></script>
	<script src="<c:url value='/assets/vendor/daterangepicker/daterangepicker.js'/> "></script>
<!--===============================================================================================-->
	<script src="<c:url value='/assets/vendor/countdowntime/countdowntime.js'/> "></script>
<!--===============================================================================================-->
	<script src="<c:url value='/assets/js/main.js'/> "></script>

</body>
</html>
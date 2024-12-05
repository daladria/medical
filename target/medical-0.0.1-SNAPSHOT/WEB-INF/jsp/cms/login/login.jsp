<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ page pageEncoding="UTF-8"%>

<html>

<!doctype html>
<!--[if lte IE 9]> <html class="lte-ie9" lang="en"> <![endif]-->
<!--[if gt IE 9]><!-->
<html lang="en">
<!--<![endif]-->

<head>
<!-- Start : Meta -->
<meta charset="UTF-8">
<meta name="viewport" content="initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Remove Tap Highlight on Windows Phone IE -->
<meta name="msapplication-tap-highlight" content="no" />
<!-- End : Meta -->


<!-- Start : Title -->
<title>Login</title>

<!-- Favicon-->
<link rel="shortcut icon" href="jsLib/images/favicon.png" />
<link rel="icon" href="jsLib/images/favicon.png" />

<!-- Font-->
<!-- 
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;700&display=swap"
	rel="stylesheet">
 -->
 
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/jsLib/googleapis/roboto.css" />
	
<!-- Style -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/jsLib/plugins/bootstrap-4.5.2-dist/css/bootstrap.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/jsLib/plugins/font-awesome-4.7.0/css/font-awesome.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/jsLib/jquery-ui-1.12.1/jquery-ui.min.css" />
<link rel="stylesheet"href="${pageContext.request.contextPath}/jsLib/plugins/bootstrap-sweetalert/sweet-alert.css" />


<link rel="stylesheet" href="${pageContext.request.contextPath}/jsLib/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/jsLib/css/login.css">

<!-- Script -->
<script src="${pageContext.request.contextPath}/jsLib/jquery/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath}/jsLib/jquery-ui-1.12.1/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/jsLib/plugins/bootstrap-4.5.2-dist/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/jsLib/plugins/bootstrap-4.5.2-dist/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/jsLib/plugins/bootstrap-sweetalert/sweet-alert.min.js"></script>
<script src="${pageContext.request.contextPath}/jsLib/js/script.js"></script>

</head>


<body class="widescreen">
<div class="wrapper-page">
        <div class=" card-box">
            <div class="panel-heading">
            <div class="text-center login-logo">
                    <img src="jsLib/images/ibb-logo-giris.png">
                </div>
                <h3 class="text-center pt-3 pb-3"> Giriş <strong class="text-custom">Yap</strong></h3>
            </div>


            <div class="panel-body">
                <form class="form-horizontal m-t-20" name="f" action="login" method="POST">

                    <div class="form-group ">
                        <div class="col-xs-12">
                            <input class="form-control" type="text" name="username" required placeholder="<spring:message code="login.userName" />">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-12">
                            <input class="form-control" type="password" name="password" required placeholder="<spring:message code="login.password" />">
                        </div>
                    </div>

                    <div class="form-group text-center m-t-40">
                        <div class="col-xs-12">
                        
                        	<button type="submit" class="btn btn-red btn-block text-uppercase waves-effect waves-light"><spring:message code="login.loginButton"/></button>
                           
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>



	<script type="text/javascript">
		var messages = new Array();
		messages['webapplicationloginwrongPasswordOrUsername'] = "${webapplicationloginwrongPasswordOrUsername}";
		messages['webapplicationAllFieldsMustBeSet'] = "${webapplicationAllFieldsMustBeSet}";

		document.onreadystatechange = function() {//window.addEventListener('readystatechange',function(){...}); (for Netscape) and window.attachEvent('onreadystatechange',function(){...}); (for IE and Opera) also work
			if (document.readyState == 'loaded' || document.readyState == 'complete') {
				if ("${login.wrongPassword}" =="true") {
					swal("Hata",messages['webapplicationloginwrongPasswordOrUsername'], "error");
				}

			}
		}

		function checkSubmit() {

			if ((document.getElementById('login-username').value.length == 0)
					|| (document.getElementById('login-password').value.length == 0)) {
				swal("Hata",messages['webapplicationAllFieldsMustBeSet'], "error");
				return false;
			};


			return true; // don't submit the form
		}
	</script>


</body>
</html>
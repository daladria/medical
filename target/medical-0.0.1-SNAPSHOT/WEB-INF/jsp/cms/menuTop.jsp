<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ page pageEncoding="UTF-8" %>
		<!--start : #header-->
		<header id="header">
			<div class="navbar-custom">
				<!--start : profil-->
				<div class="btn-group float-right pt-3 pr-3">

					<button type="button" class="btn btn-dark dropdown-toggle"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<sec:authentication property="name" />
					</button>
					<div class="dropdown-menu">
						<a class="dropdown-item"
							href="${pageContext.request.contextPath}/logout">Çıkış Yap</a>
					</div>
				</div>
				<!--end : profil-->
				<!-- start : logo -->
				<div class="logo-box">
					<a href="${pageContext.request.contextPath}/main"
						class="logo logo-light text-center"> <span class="logo-sm">
							<img src="jsLib/images/ibb-logo-main.jpg" alt=""
							height="50" width="150">
					</span> <span class="logo-lg"> <img
							src="jsLib/images/ibb-logo-main.jpg" alt="" height="50" width="150">
					</span>
					</a>
				</div>
				<!--end : logo-->

			</div>
		</header>
		<!--end : #header-->
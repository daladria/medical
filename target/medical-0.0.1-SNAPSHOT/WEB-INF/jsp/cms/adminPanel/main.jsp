<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ page pageEncoding="UTF-8" %>

<!doctype html>
<html>

<jsp:include page="..\header.jsp"/>

<body >
	
    <!--start : #wrapper-->
    <main id="wrapper">
  		<jsp:include page="..\menuTop.jsp"/>
		<jsp:include page="..\menuLeft.jsp"/>

        <!--start : #content-->
        <div id="content-page">
			<jsp:include page="..\dashBoard.jsp"/>
			<jsp:include page="..\trends.jsp"/>

            		<!--start : #content-->
			<div id="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-xs-12">
							<!--start : page-title-->
							<!--start : page-title-->
							<div class="row">
								<div class="col-xl-6 col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<div class="page-title-box">
										<h4 class="page-title">Servis Kullanıcılar(Top ${showUserCount})</h4>
									</div>
								</div>
							</div>
							
							<!--end : page-title-->
							<div class="card-box">
								<div class="row">
									<div class="col-lg-12">
										<div class="p-10">
											<div class="table-responsive">
												<table class="table m-0 users-table grid" id="users_table">
													<thead>
														<tr>
															<th>Kullanıcı</th>
															<th>İstek Sayısı</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="entry" items="${requestUsers}">
															<tr id="row_${entry}">
																<td><c:out value="${entry.key}" /></td>
																<td><c:out value="${entry.value}" /></td>

															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>

							</div>
						</div>
						
						<div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-xs-12">

							<!--start : page-title-->
							<div class="row">
								<div class="col-xl-6 col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<div class="page-title-box">
										<h4 class="page-title">Http Kullanıcılar(Top ${showUserCount})</h4>
									</div>
								</div>
							</div>
							<!--end : page-title-->

							<div class="card-box">
								<div class="row">
									<div class="col-lg-12">
										<div class="p-10">
											<div class="table-responsive">
												<table class="table m-0 users-table grid" id="users_table">
													<thead>
														<tr>
															<th>Kullanıcı</th>
															<th>İstek Sayısı</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="entry" items="${httpUsers}">
															<tr id="row_${entry}">
																<td><c:out value="${entry.key}" /></td>
																<td><c:out value="${entry.value}" /></td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
							
						</div>

					</div>

				</div>
			</div>
        
        <jsp:include page="..\footer.jsp"/>

    </main>
    <!--end : #wrapper-->
	
	
</body>
</html>




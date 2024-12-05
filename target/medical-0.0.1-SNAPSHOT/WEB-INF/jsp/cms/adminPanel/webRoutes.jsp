<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ page pageEncoding="UTF-8"%>

<!doctype html>
<html>
<jsp:include page="..\header.jsp"/>
<body align="center">

	<!--start : #wrapper-->
	<main id="wrapper">
		<jsp:include page="..\menuTop.jsp"/>
		<jsp:include page="..\menuLeft.jsp"/>


		<!--start : #content-->
		<div id="content-page">
			<div id="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-xs-12">
							<!--start : page-title-->
							<div class="row">
								<div class="col-xl-6 col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<div class="page-title-box">
										<h4 class="page-title">Servis Ekle</h4>
									</div>
								</div>
							</div>
							<!--end : page-title-->
							<div class="card-box">
								<form>
									<div class="row">
										<div class="col-xl-10 col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group">
												<label for="methodName" class="bold">Method Adı</label>
												<input type="text" class="form-control" id="methodName">
											</div>
										</div>
										</row>

										<div class="col-xl-10 col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group">
												<label for="request" class="bold">Request</label> <input
													type="text" class="form-control" id="request">
											</div>
										</div>

										<div class="col-xl-10 col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group">
												<label for="response" class="bold">Response</label> <input
													type="text" class="form-control" id="response">
											</div>
										</div>

										<div class="col-xl-10 col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group">
												<label for="channelCode" class="bold">Kanal Kodu</label> <input
													type="text" class="form-control" id="channelCode">
											</div>
										</div>

										<div class="col-xl-10 col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group">
												<label for="userCode" class="bold">Kullanıcı Kodu</label> <input
													type="text" class="form-control" id="userCode">
											</div>
										</div>
										<div class="col-xl-10 col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group">
												<label for="ipWhiteList" class="bold">IP White List</label> <input
													type="text" class="form-control" id="ipWhiteList">
											</div>
										</div>

										<div class="col-xl-10 col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group">
												<label for="ipBlackList" class="bold">IP Black List</label> <input
													type="text" class="form-control" id="ipBlackList">
											</div>
										</div>
										
										<div class="col-xl-10 col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group">
												<label for="userBlackList" class="bold">User Black List</label> <input
													type="text" class="form-control" id="userBlackList">
											</div>
										</div>

										<div class="col-xl-10 col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group">
												<label for="userWhiteList" class="bold">User White List</label> <input
													type="text" class="form-control" id="userWhiteList">
											</div>
										</div>
										
										<div class="col-xl-4 col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group">

												<label for="withAuthentication" class="bold">Güvenli Erişim</label> <select
													class="form-control" id="withAuthentication">
													<option value="">None</option>
													<option value="true">Evet</option>
													<option value="false">Hayır</option>
												</select>

											</div>
										</div>

										<div class="col-xl-10 col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group">
												<label for="httpHeaders" class="bold">HTTP Headers</label> 
												<textarea class="form-control" id="httpHeaders" rows="3"></textarea>
											</div>
										</div>

										<div class="col-xl-10 col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group">
												<label for="route" class="bold">Servis Url</label> 
												<textarea  class="form-control" id="route" rows="3"></textarea>
											</div>
										</div>
										
										<div class="col-xl-10 col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group">
												<label for="bandWidth" class="bold">Band Width</label> <input
													type="text" class="form-control" id="bandWidth">
											</div>
										</div>

										<div class="col-12">
											<div class="float-left">
												<button type="button"
													class="btn btn-success waves-effect waves-light mt-2 mb-2"
													onclick="insertUser()">
													<span class="btn-label"><i class="fa fa-check"></i></span>Servis Ekle</button>
												<button type="button"
													class="btn btn-warning waves-effect waves-light mt-2 mb-2"
													onclick="updateUser()">
													<span class="btn-label"><i class="fa fa-pencil"></i></span>Servis Güncelle</button>
												<button type="button"
													class="btn btn-danger waves-effect waves-light mt-2 mb-2"
													onclick="deleteUser()">
													<span class="btn-label"><i class="fa fa-times"></i></span>Servis Sil</button>
											</div>
										</div>
									</div>
								</form>


							</div>
						</div>
						<div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-xs-12">

							<!--start : page-title-->
							<div class="row">
								<div class="col-xl-6 col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<div class="page-title-box">
										<h4 class="page-title">Api Servis Listesi</h4>
									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-12">
									<div class="form-group">
										<input type="text" class="form-control" id="searchText">
											<button type="button"
												class="btn btn-danger waves-effect waves-light mt-2 mb-2"
												onclick="searchRoutes()">
										<span class="btn-label"><i class="fa fa-search"></i></span>Method Arama</button>
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
															<th>Komut Adı</th>
															<th>Oluşturma Zamanı</th>
															<th>İşlem</th>
														</tr>
													</thead>
													<tbody id ="methodsList">
														<c:forEach var="entry" items="${routes}">
															<tr id="row_${entry.methodName}">
																<td><c:out value="${entry.methodName}" /></td>
																<td><c:out value="${entry.dateTime}" /></td>
																<td>
																	<button class="btn btn-warning btn-sm" title="Güncelle"
																		onclick="getMethod('${entry.methodName}')">
																		<i class="fa fa-pencil" aria-hidden="true"></i>
																	</button>
																</td>
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
		</div>
     <jsp:include page="..\footer.jsp"/>

	</main>
	<!--end : #wrapper-->





</body>
</html>

<script>
	$(document).ready(function() {
		$('#methodName').val('');
		$('#request').val('');
		$('#response').val('');
		$('#channelCode').val('');
		$('#userCode').val('');
		$('#ipWhiteList').val('');
		$('#ipBlackList').val('');
		$('#userWhiteList').val('');
		$('#userBlackList').val('');
		$('#withAuthentication').val('');
		$('#httpHeaders').val('');
		$('#route').val('');
		$('#bandWidth').val('');
		
	})

	function updateUser() {
		var command = "webRoutes.updateRoute";
		var methodName = $('#methodName').val();
		if (methodName.length == 0) {
			alert('Eksik bilgi var, Kontrol edip tekrar deneyin...');
			return;
		}
		$
				.ajax({
					url : "getRequestWithHttp",
					type : 'POST',
					contentType : "application/json;charset=UTF-8",
					data : JSON.stringify({
						"command" : command,
						"methodName": $('#methodName').val(),
						"request": $('#request').val(),
						"response": $('#response').val(),
						"channelCode": $('#channelCode').val(),
						"userCode": $('#userCode').val(),
						"ipWhiteList": $('#ipWhiteList').val(),
						"ipBlackList": $('#ipBlackList').val(),
						"userWhiteList": $('#userWhiteList').val(),
						"userBlackList": $('#userBlackList').val(),
						"withAuthentication": $('#withAuthentication').val(),
						"httpHeaders": $('#httpHeaders').val(),
						"route": $('#route').val(),
						"bandWidth": $('#bandWidth').val()
					}),
					timeout : 30000,
					async : false,
					success : function(result) {
						if (result != null) {
							// alert( "Status: " +result.status +  "\r\nStatus Detail:" + result.statusDetail  + "\r\nStatus Detail Message:" + result.statusDetailMessage );

							if (result.status == "success") {
								alert("İşlem başarıyla gerçekleştirildi.");
							} else {
								alert("İşlem sırasında bir hata oluştu. Lütfen daha sonra tekrar deneyiniz."
								+ "\r\nStatus Detail: " + result.statusDetailMessage);
							}

							if (result.status == 'success') {
								$('#methodName').val('');
								$('#request').val('');
								$('#response').val('');
								$('#channelCode').val('');
								$('#userCode').val('');
								$('#ipWhiteList').val('');
								$('#ipBlackList').val('');
								$('#userWhiteList').val('');
								$('#userBlackList').val('');
								$('#withAuthentication').val('');
								$('#httpHeaders').val('');
								$('#route').val('');
								$('#bandWidth').val('');

							}
						} else
							alert("Status: Failed"
									+ "\r\nStatus Detail: Communication Error");
					},
					error : function(xhr, textStatus, errorThrown) {
						alert("Status: Failed"
								+ "\r\nStatus Detail: Communication Error");
					}
				});
	}

	function deleteUser() {
		var command = "webRoutes.deleteRoute";
		var methodName = $('#methodName').val();
		if (methodName.length == 0) {
			alert('Eksik bilgi var, Kontrol edip tekrar deneyin...');
			return;
		}
		$.ajax({
			url : "getRequestWithHttp",
			type : 'POST',
			contentType : "application/json;charset=UTF-8",
			data : JSON.stringify({
				"command" : command,
				"methodName" : $('#methodName').val()
			}),
			timeout : 30000,
			async : false,
			success : function(result) {
				if (result != null) {
					// alert( "Status: " +result.status +  "\r\nStatus Detail:" + result.statusDetail  + "\r\nStatus Detail Message:" + result.statusDetailMessage );
					//alert( "Status: " +result.status );
					
					
					if (result.status == 'success') {
						alert("İşlem başarıyla gerçekleştirildi.");
					}else{
						alert("İşlem sırasında bir hata oluştu. Lütfen daha sonra tekrar deneyiniz."
								+ "\r\nStatus Detail: " + result.statusDetailMessage);
					
						}
					if (result.status == 'success') {
						$('#row_' + methodName).remove();
						$('#methodName').val('');
						$('#request').val('');
						$('#response').val('');
						$('#channelCode').val('');
						$('#userCode').val('');
						$('#ipWhiteList').val('');
						$('#ipBlackList').val('');
						$('#userWhiteList').val('');
						$('#userBlackList').val('');
						$('#withAuthentication').val('');
						$('#httpHeaders').val('');
						$('#route').val('');
						$('#bandWidth').val('');

					}
				} else
					alert("Status: Failed"
							+ "\r\nStatus Detail: Communication Error");
			},
			error : function(xhr, textStatus, errorThrown) {
				alert("Status: Failed"
						+ "\r\nStatus Detail: Communication Error");
			}
		});
	}

	function insertUser() {
		var command = "webRoutes.insertRoute";

		var methodName = $('#methodName').val();
		if (methodName.length == 0 ) {
			alert('Eksik bilgi var, Kontrol edip tekrar deneyin...');
			return;
		}
		$
		.ajax({
					url : "getRequestWithHttp",
					type : 'POST',
					contentType : "application/json;charset=UTF-8",
					data : JSON.stringify({
						"command" : command,
						"methodName": $('#methodName').val(),
						"request": $('#request').val(),
						"response": $('#response').val(),
						"channelCode": $('#channelCode').val(),
						"userCode": $('#userCode').val(),
						"ipWhiteList": $('#ipWhiteList').val(),
						"ipBlackList": $('#ipBlackList').val(),
						"userWhiteList": $('#userWhiteList').val(),
						"userBlackList": $('#userBlackList').val(),
						"withAuthentication": $('#withAuthentication').val(),
						"httpHeaders": $('#httpHeaders').val(),
						"route": $('#route').val(),
						"bandWidth": $('#bandWidth').val()

					}),
					timeout : 30000,
					async : false,
					success : function(result) {
						if (result != null) {
							// alert( "Status: " +result.status +  "\r\nStatus Detail:" + result.statusDetail  + "\r\nStatus Detail Message:" + result.statusDetailMessage );
							//alert( "Status: " +result.status );
							if (result.status == 'success') {

								if (result.status == 'success') {
									alert("İşlem başarıyla gerçekleştirildi.");
								}else{
									alert("İşlem sırasında bir hata oluştu. Lütfen daha sonra tekrar deneyiniz.")
								}
/*
								var userRow = '';
								userRow += '<tr id="row_'+username+'">';
								userRow += '<td>' + username + '</td>';
								userRow += '<td>';
								userRow += '<button class="btn btn-warning btn-sm" title="Düzenle" onclick="getUser(\''
										+ username + '\')">';
								userRow += '<i class="fa fa-pencil" aria-hidden="true"></i>';
								userRow += '</button>';
								userRow += '</td>';
								userRow += '</tr>';
								$("#users_table").append(userRow);
								*/
								
								$('#methodName').val('');
								$('#request').val('');
								$('#response').val('');
								$('#channelCode').val('');
								$('#userCode').val('');
								$('#ipWhiteList').val('');
								$('#ipBlackList').val('');
								$('#userWhiteList').val('');
								$('#userBlackList').val('');
								$('#withAuthentication').val('');
								$('#httpHeaders').val('');
								$('#route').val('');
								$('#bandWidth').val('');


							}else
								alert("Status: Failed"
										+ "\r\nStatus Detail: " + result.statusDetailMessage);
						} else
							alert("Status: Failed"
									+ "\r\nStatus Detail: Communication Error");
					},
					error : function(xhr, textStatus, errorThrown) {
						alert("Status: Failed"
								+ "\r\nStatus Detail: Communication Error");
					}
				});
	}

	function getMethod(key) {
		$('#methodName').val('');
		$('#request').val('');
		$('#response').val('');
		$('#channelCode').val('');
		$('#userCode').val('');
		$('#ipWhiteList').val('');
		$('#ipBlackList').val('');
		$('#userWhiteList').val('');
		$('#userBlackList').val('');
		$('#withAuthentication').val('');
		$('#httpHeaders').val('');
		$('#route').val('');
		$('#bandWidth').val('');

		var command = "webRoutes.getRoute";
		$.ajax({
			url : "getRequestWithHttp",
			type : 'POST',
			contentType : "application/json;charset=UTF-8",
			data : JSON.stringify({
				"command" : command,
				"methodName" : key
			}),
			timeout : 30000,
			async : false,
			success : function(result) {
				if (result != null) {
					$('#methodName').val(result.methodName);
					$('#request').val(result.request);
					$('#response').val(result.response);
					$('#channelCode').val(result.channelCode);
					$('#userCode').val(result.userCode);
					$('#ipWhiteList').val(result.ipWhiteList);
					$('#ipBlackList').val(result.ipBlackList);
					$('#userWhiteList').val(result.userWhiteList);
					$('#userBlackList').val(result.userBlackList);
					$('#withAuthentication').val(result.withAuthentication);
					$('#httpHeaders').val(result.httpHeaders);
					$('#route').val(result.route);
					$('#bandWidth').val(result.bandWidth);

				} else
					alert("Status: Failed"
							+ "\r\nStatus Detail: Communication Error");
			},
			error : function(xhr, textStatus, errorThrown) {
				alert("Status: Failed"
						+ "\r\nStatus Detail: Communication Error");
			}
		});
	}
	
	function searchRoutes() {
		var command = "webRoutes.searchUsers";
		$('#methodsList').html('');
		$.ajax({
			url : "getRequestWithHttp",
			type : 'POST',
			contentType : "application/json;charset=UTF-8",
			data : JSON.stringify({
				"command" : command,
				"methodName" :  $('#searchText').val()
			}),
			timeout : 30000,
			async : false,
			success : function(result) {
				if (result != null) {
					// alert( "Status: " +result.status +  "\r\nStatus Detail:" + result.statusDetail  + "\r\nStatus Detail Message:" + result.statusDetailMessage );
					//alert( "Status: " +result.status );
					//$('#username').val(result.username);
					//$('#password').val(result.password);
					//$('#role').val(result.roles);
					users = result.methods;
					ht = "";
					for(i=0;i<users.length;i++) {
						ht = ht + "<tr id=\"" + users[i].methodName + "\">" + 
							"<td>" + users[i].methodName + "</td>"  + 
							"<td>" + users[i].dateTime  + "</td>" +
							"<td>" + 
								"<button class=\"btn btn-warning btn-sm\" title=\"Güncelle\"" +
									"onclick=\"getMethod('" + users[i].methodName  + "')\">" + 
									"<i class=\"fa fa-pencil\" aria-hidden=\"true\"></i>" + 
								"</button>" + 
							"</td>" + 
						"</tr>";
//						alert(users[i].id);
//						console.log(users[i].id);
					}
					$('#methodsList').html(ht);
				} else
					alert("Status: Failed"
							+ "\r\nStatus Detail: Communication Error");
			},
			error : function(xhr, textStatus, errorThrown) {
				alert("Status: Failed"
						+ "\r\nStatus Detail: Communication Error");
			}
		});
	}

</script>
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
										<h4 class="page-title">Kullanıcı Ekle</h4>
									</div>
								</div>
							</div>
							<!--end : page-title-->
							<div class="card-box">
								<form>
									<div class="row">
										<div class="col-xl-4 col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group">
												<label for="kullaniciAdi" class="bold">Kullanıcı Adı</label>
												<input type="text" class="form-control" id="username">
											</div>
										</div>
										<div class="col-xl-4 col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group">
												<label for="sifre" class="bold">Şifre</label> <input
													type="password" class="form-control" id="password">
											</div>
										</div>
										<div class="col-xl-4 col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group">
<!-- 											
												<label for="allowedIps" class="bold">Kullanıcı Rolu</label>
												<input type="text" class="form-control" id="allowedips">
  -->

												<label for="roller" class="bold">Roller</label> <select
													class="form-control" id="role">
													<option value="ROLE_NONE"></option>
													<option value="ROLE_OPERATOR">OPERATOR</option>
													<option value="ROLE_MANAGER">YONETIM</option>
													<option value="ROLE_ADMIN">ADMIN</option>
												</select>

											</div>
										</div>
										<div class="col-12">
											<div class="float-left">
												<button type="button"
													class="btn btn-success waves-effect waves-light mt-2 mb-2"
													onclick="insertUser()">
													<span class="btn-label"><i class="fa fa-check"></i></span>Kullanıcı Ekle
												</button>
												<button type="button"
													class="btn btn-warning waves-effect waves-light mt-2 mb-2"
													onclick="updateUser()">
													<span class="btn-label"><i class="fa fa-pencil"></i></span>Kullanıcı Güncelle
												</button>
												<button type="button"
													class="btn btn-danger waves-effect waves-light mt-2 mb-2"
													onclick="deleteUser()">
													<span class="btn-label"><i class="fa fa-times"></i></span>Kullanıcı Sil
												</button>
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
										<h4 class="page-title">Kullanıcı Listesi</h4>
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
															<th>İşlem</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="entry" items="${users}">
															<tr id="row_${entry}">
																<td><c:out value="${entry}" /></td>
																<td>
																	<button class="btn btn-warning btn-sm" title="Güncelle"
																		onclick="getUser('${entry}')">
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
		$('#username').val('');
		$('#password').val('');
		$('#role').val('');
	})

	function updateUser() {
		var command = "userOperations.updateUser";
		var username = $('#username').val();
		if (username.length == 0) {
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
						"username" : $('#username').val(),
						"password" : $('#password').val(),
						"roles" : $('#role').val()
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
								$('#username').val('');
								$('#password').val('');
								$('#role').val('');
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
		var command = "userOperations.deleteUser";
		var username = $('#username').val();
		if (username.length == 0) {
			alert('Eksik bilgi var, Kontrol edip tekrar deneyin...');
			return;
		}
		$.ajax({
			url : "getRequestWithHttp",
			type : 'POST',
			contentType : "application/json;charset=UTF-8",
			data : JSON.stringify({
				"command" : command,
				"username" : $('#username').val(),
				"password" : $('#password').val(),
				"roles" : $('#role').val()
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
						$('#row_' + username).remove();
						$('#username').val('');
						$('#password').val('');
						$('#role').val('');
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
		var command = "userOperations.insertUser";
		var username = $('#username').val();
		if (username.length == 0) {
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
						"username" : $('#username').val(),
						"password" : $('#password').val(),
						"roles" : $('#role').val()
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

								$('#username').val('');
								$('#password').val('');
								$('#role').val('');

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

	function getUser(key) {

		var command = "userOperations.getUser";
		$.ajax({
			url : "getRequestWithHttp",
			type : 'POST',
			contentType : "application/json;charset=UTF-8",
			data : JSON.stringify({
				"command" : command,
				"username" : key
			}),
			timeout : 30000,
			async : false,
			success : function(result) {
				if (result != null) {
					// alert( "Status: " +result.status +  "\r\nStatus Detail:" + result.statusDetail  + "\r\nStatus Detail Message:" + result.statusDetailMessage );
					//alert( "Status: " +result.status );
					$('#username').val(result.username);
					$('#password').val(result.password);
					$('#role').val(result.roles);

				} else
					alert("Status: Failed"
							+ "\r\nStatus Detail: Communication Error");
			},
			error : function(xhr, textStatus, errorThrown) {
				alert("Status: Failed"
						+ "\r\nStatus Detail: Communication Error");
			}
		});

		//alert(key);
		//$("#usersTableBody").empty();
		//$("#usersTableBody").append('<tr><td><button style ="background:none;border:none;color:green;" onclick="getUser(\'ali\')" >deneme</button></td></tr>');
		//$('#row_' + key).remove();
		//$('#username').val(key);
		//$('#role').val("ADMIN");
	}
</script>
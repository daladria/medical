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
										<h4 class="page-title">Api Kullanıcısı Ekle</h4>
									</div>
								</div>
							</div>
							<!--end : page-title-->
							<div class="card-box">
								<form>
									<div class="row">
										<div class="col-xl-10 col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group">
												<label for="kullaniciAdi" class="bold">Api Kullanıcı Adı</label>
												<input type="text" class="form-control" id="username">
											</div>
										</div>
										</row>

										<div class="col-xl-10 col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group">
												<label for="sifre" class="bold">Şifre</label> <input
													type="text" class="form-control" id="password">
											</div>
										</div>

										<div class="col-xl-10 col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group">
												<label for="sifre" class="bold">Açıklama</label> <input
													type="text" class="form-control" id="description">
											</div>
										</div>
										<div class="col-xl-4 col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group">

												<label for="roller" class="bold">Durum</label> <select
													class="form-control" id="isDeleted">
													<option value="">None</option>
													<option value="false">Aktif</option>
													<option value="true">Pasif</option>
												</select>

											</div>
										</div>
										
										<div class="col-xl-10 col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group">
												<label for="services" class="bold">Servisler</label> <input
													type="text" class="form-control" id="services">
											</div>
										</div>
										
										<div class="col-12">
											<div class="float-left">
												<button type="button"
													class="btn btn-success waves-effect waves-light mt-2 mb-2"
													onclick="insertUser()">
													<span class="btn-label"><i class="fa fa-check"></i></span>Api Kullanıcı Ekle</button>
												<button type="button"
													class="btn btn-warning waves-effect waves-light mt-2 mb-2"
													onclick="updateUser()">
													<span class="btn-label"><i class="fa fa-pencil"></i></span>Api Kullanıcı Güncelle</button>
												<button type="button"
													class="btn btn-danger waves-effect waves-light mt-2 mb-2"
													onclick="deleteUser()">
													<span class="btn-label"><i class="fa fa-times"></i></span>Api Kullanıcı Sil</button>
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
										<h4 class="page-title">Api Kullanıcı Listesi</h4>
									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-12">
									<div class="form-group">
										<input type="text" class="form-control" id="searchText">
											<button type="button"
												class="btn btn-danger waves-effect waves-light mt-2 mb-2"
												onclick="searchUser()">
										<span class="btn-label"><i class="fa fa-search"></i></span>Api Kullanıcı Arama</button>
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
															<th>Api Kullanıcı</th>
															<th>Oluşturma Zamanı</th>
															<th>İşlem</th>
														</tr>
													</thead>
													<tbody id ="usersList">
														<c:forEach var="entry" items="${users}">
															<tr id="row_${entry.id}">
																<td><c:out value="${entry.id}" /></td>
																<td><c:out value="${entry.createDate}" /></td>
																<td>
																	<button class="btn btn-warning btn-sm" title="Güncelle"
																		onclick="getUser('${entry.id}')">
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



				<div class="row">
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
										<input type="text" class="form-control" id="searchTextMethod">
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
									<div class="col-lg-16">
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
																		onclick="addMethod'${entry.methodName}')">
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
		$('#services').val('');
	})
	
	function addMethod(key) {
		var tmp = $('#services').val();
		if (tmp.indexOf(key)===-1)
			if ($('#services').val().trim().length>0) $('#services').val($('#services').val() + ',' + key);
			else $('#services').val(key);
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
				"methodName" :  $('#searchTextMethod').val()
			}),
			timeout : 30000,
			async : false,
			success : function(result) {
				if (result != null) {
					users = result.methods;
					ht = "";
					for(i=0;i<users.length;i++) {
						ht = ht + "<tr id=\"" + users[i].methodName + "\">" + 
							"<td>" + users[i].methodName + "</td>"  + 
							"<td>" + users[i].dateTime  + "</td>" +
							"<td>" + 
								"<button class=\"btn btn-warning btn-sm\" title=\"Güncelle\"" +
									"onclick=\"addMethod('" + users[i].methodName  + "')\">" + 
									"<i class=\"fa fa-plus-square\" aria-hidden=\"true\"></i>" + 
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


	
	function isNum(tmp) {
		var res = true;
		tmp = tmp.replace('-','');
		var numbers = '0123456789';
		for (let i =0;i<tmp.length;i++) {
			if (numbers.indexOf(tmp.substring(i,i+1)) ==-1 )
				res = false;
		}
		return res;
	}
	
	function updateUser() {
		var command = "apiUserOperations.updateUser";
		var username = $('#username').val();

		if (!isNum(username)) {
			alert('Girdiğiniz Kullanıcı Adı PAX Kullanıcı Adı Kriterlerine Uymamaktadır...');
			return;
		}

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
						"id" : $('#username').val(),
						"password" : $('#password').val(),
						"description" : $('#description').val(),
						"isDeleted" : $('#isDeleted').val(),
						"services" : $('#services').val()
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
								var username = $('#username').val('');
								var password = $('#password').val('');
								var description = $('#description').val('');
								var description = $('#isDeleted').val('');
								var services = $('#services').val('');
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
		var command = "apiUserOperations.deleteUser";
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
				"id" : $('#username').val()
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
						$('#description').val('');
						$('#isDeleted').val('');
						$('#services').val('');

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
		var command = "apiUserOperations.insertUser";

		var username = $('#username').val();
		var tmpUserName = 'XXX';
		try {
			tmpUserName = username.replace('-','');
		}catch(err) {
			tmpUserName = 'XXX';
		}
		if (!isNum(username)) {
			alert('Girdiğiniz Kullanıcı Adı PAX Kullanıcı Adı Kriterlerine Uymamaktadır...');
			return;
		}

		if (username.length == 0 || password.length==0 || description.length==0 ) {
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
						"id" : $('#username').val(),
						"password" : $('#password').val(),
						"description" : $('#description').val(),
						"isDeleted" : $('#isDeleted').val(),
						"services" : $('#services').val()
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
								var username = $('#username').val('');
								var password = $('#password').val('');
								var description = $('#description').val('');
								var isDeleted = $('#isDeleted').val('');
								var services = $('#services').val('');


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
		$('#username').val('');
		$('#password').val('');
		$('#description').val('');
		$('#isDeleted').val('');
		$('#services').val('');

		var command = "apiUserOperations.getUser";
		$.ajax({
			url : "getRequestWithHttp",
			type : 'POST',
			contentType : "application/json;charset=UTF-8",
			data : JSON.stringify({
				"command" : command,
				"id" : key
			}),
			timeout : 30000,
			async : false,
			success : function(result) {
				if (result != null) {
					// alert( "Status: " +result.status +  "\r\nStatus Detail:" + result.statusDetail  + "\r\nStatus Detail Message:" + result.statusDetailMessage );
					//alert( "Status: " +result.status );
					$('#username').val(result.id);
					$('#password').val(result.password);
					$('#description').val(result.description);
					$('#isDeleted').val(result.isDeleted);
					$('#services').val(result.services);

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
	
	function searchUser() {
		var command = "apiUserOperations.searchUsers";
		$('#usersList').html('');
		$.ajax({
			url : "getRequestWithHttp",
			type : 'POST',
			contentType : "application/json;charset=UTF-8",
			data : JSON.stringify({
				"command" : command,
				"username" :  $('#searchText').val()
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
					users = result.users;
					ht = "";
					for(i=0;i<users.length;i++) {
						ht = ht + "<tr id=\"" + users[i].id + "\">" + 
							"<td>" + users[i].id + "</td>"  + 
							"<td>" + users[i].createDate  + "</td>" +
							"<td>" + 
								"<button class=\"btn btn-warning btn-sm\" title=\"Güncelle\"" +
									"onclick=\"getUser('" + users[i].id  + "')\">" + 
									"<i class=\"fa fa-pencil\" aria-hidden=\"true\"></i>" + 
								"</button>" + 
							"</td>" + 
						"</tr>";
//						alert(users[i].id);
//						console.log(users[i].id);
					}
					$('#usersList').html(ht);
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
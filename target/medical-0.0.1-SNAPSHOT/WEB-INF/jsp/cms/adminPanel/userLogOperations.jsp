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
										<h4 class="page-title">Rapor Oluştur</h4>
									</div>
								</div>
							</div>
							<!--end : page-title-->
							<div class="card-box">
								<form>
									<div class="row">
										<div class="col-xl-4 col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<label for="roller" class="bold">İşlem tipi</label> <select
													class="form-control" id="reportType">
													<option value=""></option>
													<option value="apiUserOperations.">Api User İşlemleri</option>
													<option value="params.">Parametre İşlemleri</option>
													<option value="webRoutes">Servis İşlemleri</option>
													<option value="userOperations.">Web Kullanıcı işlemleri</option>
													<option value="logOperations.">Log İşlemleri</option>
													<option value="userLogOperations">Kullanıcı Log İşlemleri</option>
												</select>
										</div>
									</div>	
									<div class="row">
										<div class="col-xl-4 col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group">
												<label for="kullaniciAdi" class="bold">Kullanıcı Adı</label>
												<input type="text" class="form-control" id="username">
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-xl-4 col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group">
												<label for="startDate" class="bold">Baslangic Tarihi</label> 
												<input class="form-control" type="date" placeholder="yyyy-mm-dd" id="startDate">
											</div>
										</div>
										<div class="col-xl-4 col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group">
												<label for="startTime" class="bold">Baslangic Zamanı</label> 
												<input class="form-control" type="time" id="startTime" >
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-xl-4 col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group">
												<label for="endDate" class="bold">Bitiş Tarihi</label> 
												<input class="form-control" type="date" placeholder="yyyy-mm-dd" id="endDate">
											</div>
										</div>
										<div class="col-xl-4 col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group">
												<label for="endTime" class="bold">Baslangic Zamanı</label> 
												<input class="form-control" type="time" id="endTime">
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-12">
											<div class="float-left">
												<button type="button"
													class="btn btn-success waves-effect waves-light mt-2 mb-2"
													onclick="getReport()">
													<span class="btn-label"><i class="fa fa-check"></i></span>Rapor Oluştur
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
										<h4 class="page-title">Oluşturulan Rapor Listesi</h4>
									</div>
								</div>
							</div>
							<!--end : page-title-->

							<div class="card-box">
								<div class="row">
									<div class="col-lg-12">
										<div class="p-10">
											<div class="table-responsive">
												<table class="table m-0 users-table grid" id="reports_table">
													<thead>
														<tr>
															<th>Tarih</th>
															<th>Rapor Id</th>
															<th>Download</th>
															<th>Sil</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="entry" items="${reports}">
															<tr id="row_${entry.guid}">
																<td><c:out value="${entry.createDate}" /></td>
																<td><c:out value="${entry.id}" /></td>
																<td>
																	<button class="btn btn-warning btn-sm" title="İndir"
																		onclick="downloadFile ('${entry.id}')">
																		<i class="fa fa-download" aria-hidden="true"></i>
																	</button>
																</td>
																<td>
																	<button class="btn btn-warning btn-sm" title="Sil"
																		onclick="deleteReport ('${entry.id}','${entry.guid}')">
																		<i class="fa fa-times" aria-hidden="true"></i>
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
	})

	function downloadFile(id) {

    	window.open("/belbim/getUserReport?id=" + id, '_blank');
	
	}
	

	function deleteReport(id, guid) {
		var command = "userLogOperations.deleteReport";
		var username = $('#username').val();

		$.ajax({
			url : "getRequestWithHttp",
			type : 'POST',
			contentType : "application/json;charset=UTF-8",
			data : JSON.stringify({
				"command" : command,
				"id" : id
			}),
			timeout : 30000,
			async : false,
			success : function(result) {
				if (result != null) {
					// alert( "Status: " +result.status +  "\r\nStatus Detail:" + result.statusDetail  + "\r\nStatus Detail Message:" + result.statusDetailMessage );
					//alert( "Status: " +result.status );
					
					if (result.status == 'success') {
						alert("İşlem başarıyla gerçekleştirildi.");
						$('#row_' + guid).remove();
					}else{
						alert("İşlem sırasında bir hata oluştu. Lütfen daha sonra tekrar deneyiniz."
								+ "\r\nStatus Detail: " + result.statusDetailMessage);
					
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

	function getReport() {
		var command = "userLogOperations.requestReport";

		if ($('#startDate').val().length == 0) {
			alert('Başlangıç Tarihini Seçmelisiniz...');
			return;
		}
/*
		if ($('#startTime').val().length == 0) {
			alert('Başlangıç Zamanını Seçmelisiniz...');
			return;
		}
*/		
		if ($('#endDate').val().length == 0) {
			alert('Bitiş Tarihini Seçmelisiniz...');
			return;
		}
/*
		if ($('#endTime').val().length == 0) {
			alert('Bitiş Zamanını Seçmelisiniz...');
			return;
		}
*/		
		$
				.ajax({
					url : "getRequestWithHttp",
					type : 'POST',
					contentType : "application/json;charset=UTF-8",
					data : JSON.stringify({
						"command" : command,
						"reportType":$('#reportType').val(),
						"username" : $('#username').val(),
						"startDate" : $('#startDate').val(),
						"startTime" : $('#startTime').val(),
						"endDate" : $('#endDate').val(),
						"endTime" : $('#endTime').val()
					}),
					timeout : 30000,
					async : false,
					success : function(result) {
						if (result != null) {
							// alert( "Status: " +result.status +  "\r\nStatus Detail:" + result.statusDetail  + "\r\nStatus Detail Message:" + result.statusDetailMessage );
							//alert( "Status: " +result.status );
							if (result.status == 'success') {

								if (result.status == 'success') {
									alert("Rapor isteğini başarıyla alınmıştır, \r\nRapor bitince listeden görebilirsiniz.");
									$('#reportType').val('');
									$('#username').val('');
									$('#startDate').val('');
									$('#startTime').val('');
									$('#endDate').val('');
									$('#endTime').val('');
								}else{
									alert("İşlem sırasında bir hata oluştu. Lütfen daha sonra tekrar deneyiniz.")
									}


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

</script>
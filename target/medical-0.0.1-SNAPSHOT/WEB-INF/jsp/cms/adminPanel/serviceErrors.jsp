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
										<h4 class="page-title">Zaman Seçiniz</h4>
									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-12">
									<div class="form-group">
												<input class="form-control" type="date" placeholder="yyyy-mm-dd" id="searchText" value ="${dateTime}">
											<button type="button"
												class="btn btn-danger waves-effect waves-light mt-2 mb-2"
												onclick="searchUser()">
										<span class="btn-label"><i class="fa fa-search"></i></span>Hatalı Servisleri Getir</button>
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
															<th>Servis Adı</th>
															<th>Hata Sayısı</th>
															<th>İndir</th>

														</tr>
													</thead>
													<tbody id ="usersList">
															<c:forEach var="entry" items="${serviceErrors}">
															<tr id="row_${entry}">
																<td><c:out value="${entry.key}" /></td>
																<td><c:out value="${entry.value}" /></td>
																<td>														
																	<button class="btn btn-warning btn-sm" title="İndir"
																		onclick="downloadFile ('${dateTime}','${entry.key}')">
																		<i class="fa fa-download" aria-hidden="true"></i>
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
		
	function downloadFile(id,srv) {

    	window.open("/belbim/getErrorReport?id=" + id + "&srv=" + srv, '_blank');
	
	}

	function searchUser() {
		var command = "serviceErrors.searchErrors";
		$('#usersList').html('');
		$.ajax({
			url : "getRequestWithHttp",
			type : 'POST',
			contentType : "application/json;charset=UTF-8",
			data : JSON.stringify({
				"command" : command,
				"srchTxt" :  $('#searchText').val()
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
					services = result.services;
					ht = "";
					for(i=0;i<services.length;i++) {
						ht = ht + "<tr id=\"" + services[i].id + "\">" + 
							"<td>" + services[i].id + "</td>"  + 
							"<td>" + services[i].count  + "</td>" +
							"<td>" +
							"<button class=\"btn btn-warning btn-sm\" title=\"İndir\"" + 
								"onclick=\"downloadFile ('" +  $('#searchText').val() + "','" +  services[i].id  + "')\">" +
								"<i class=\"fa fa-download\" aria-hidden=\"true\"></i>" +
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
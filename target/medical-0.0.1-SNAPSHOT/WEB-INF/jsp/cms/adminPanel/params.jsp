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
            <div id="content">
                <div class="container-fluid">
                    <!--start : page-title-->
                    <div class="row">
                        <div class="col-xl-8 col-lg-6 col-md-6 col-sm-12 col-xs-12">
                            <div class="page-title-box">
                                <h4 class="page-title">Parametreler</h4>
                            </div>
                        </div>
                    </div>
                    <!--end : page-title-->
                    <div class="row">
                        <div class="col-12">
                            <div class="card-box">
	                            <div class="table-responsive">
	                            	<table class="table grid">
	                                    <thead>
	                                        <tr>
	                                            <th scope="col">Durum</th>
	                                            <th scope="col">Açıklama</th>
	                                            <th scope="col">İşlem</th>
	                                        </tr>
	                                    </thead>
	                                    <tbody>
	                                    <c:set var="count" value="0" scope="page" />
		        					<c:forEach var="entry" items="${params}">
		            					<c:set var="count" value="${count + 1}" scope="page"/>
		                					<tr>
		                						<td><c:out value="${entry.key}"/></td> 
		                						<td>
			                						<textarea id="val_${count + 1}" rows="2" cols="100">${entry.value}</textarea>
		                						</td>
		                						<td>
		                							<button class="btn btn-warning button" id ="updateButton" onclick="show('${entry.key}', '${count + 1}')">Güncelle</button>
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
        <!--end : #content-->
        
     <jsp:include page="..\footer.jsp"/>
        
    </main>
    <!--end : #wrapper-->
</body>
</html>

<!-- Start : Script -->
<script>

function show(key,valIndex) {
	var command = "params.setParam" ;
	var callResult;
	var val = $('#val_' + valIndex).val();
	//alert(key + "-" + val );
		

	$.ajax({
		  url: "getRequestWithHttp",
		  type: 'POST',
		  contentType:"application/json;charset=UTF-8",
		  data: JSON.stringify({ "command": command, "key":key, "value":val }),
		  timeout: 30000,
		  async:false,
	/* 			  data: {
			    zipcode: 97201
			  }, */
		  success: function( result ) {
			  if(result !=null)  {

				  if(result.status == "success"){
						alert("İşlem başarıyla gerçekleştirildi.")
					  }else{
						  alert("İşlem sırasında bir hata oluştu. Lütfen daha sonra tekrar deneyiniz.")
						  }
				 // alert( "Status: " +result.status +  "\r\nStatus Detail:" + result.statusDetail  + "\r\nStatus Detail Message:" + result.statusDetailMessage );
				  
			  }else	alert( "Status: Failed" +  "\r\nStatus Detail: Communication Error" );
		  },
		  error: function(xhr, textStatus, errorThrown){
			  alert( "Status: Failed" +  "\r\nStatus Detail: Communication Error" );
		     }
	});
	}
	


</script>
<!-- End : Script -->
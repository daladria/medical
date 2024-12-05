			<%@include file="/WEB-INF/jsp/layoutItems/content_wrapper_begin.jsp"%>

            <!-- Content -->

            <div class="container-xxl flex-grow-1 container-p-y">
              <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light"><spring:message code="menu.systemParameters.submenu.title"/>/<spring:message code="menu.hospital.title"/></h4>

              <!-- Basic Layout & Basic with Icons -->
                <!-- Basic with Icons -->
                <div class="col-xxl">
                  <div class="card mb-4">
                    <div class="card-header d-flex align-items-center justify-content-between">
                        <h5 class="mb-0"><spring:message code="systemParameters.hospital.info.title"/></h5> 
                        <small class="text-muted float-end"><spring:message code="systemParameters.hospital.info.desc"/></small> 
                    </div>
                    <div class="card-body">
                      <form action="hospital"  enctype="multipart/form-data" method="post">

                        <div class="row mb-3">
                          <label class="col-sm-2 form-label" for="itemNameSelect"><spring:message code="systemParameters.hospital.userNameSelect"/></label>
                          <div class="col-sm-10">
                            <div class="input-group  input-group-merge">
	                            <span id="itemNameSelect2" class="input-group-text">
	                             	<i class="bx bx-user-circle"></i>
	                            </span>                              
								<select id="itemNameSelect" name="itemNameSelect" class="form-select"
	                                aria-label="<spring:message code="systemParameters.hospital.userNameSelect"/>"
	                                aria-describedby="itemNameSelect2">
	                                <option value="0"><spring:message code="systemParameters.itemSelect.exp"/></option>
	                                <c:forEach items="${items}" var="itm">
										<option value="${itm.id}">${itm.key}</option>
	                                </c:forEach>
	                        	</select>
                            </div>
                          </div>
                        </div>  

                        <div class="row mb-3">
                          <label class="col-sm-2 col-form-label" for="itemId"><spring:message code="systemParameters.hospital.id"/></label>
                          <div class="col-sm-10">
                            <div class="input-group">
                              <span id="itemId2" class="input-group-text">
                              	<i class="bx bx-user-pin"></i>
                              </span>
                              <input disabled
                                type="text"
                                class="form-control"
                                id="itemId"
                                name="itemId"
                                placeholder="<spring:message code="systemParameters.hospital.id"/>"
                                aria-label="<spring:message code="systemParameters.hospital.id"/>"
                                aria-describedby="itemId2"
                                value="${newItem.hospitalId}"
                              />
                            </div>
                          </div>
                        </div>
                                              
                        <div class="row mb-3">
                          <label class="col-sm-2 col-form-label" for="name"><spring:message code="systemParameters.hospital.name"/></label>
                          <div class="col-sm-10">
                            <div class="input-group input-group-merge">
                              <span id="name2" class="input-group-text">
                              	<i class="bx bx-user"></i>
                              </span>
                              <input
                                type="text"
                                class="form-control"
                                id="name"
                                name="name"
                                placeholder="<spring:message code="systemParameters.hospital.name"/>"
                                aria-label="<spring:message code="systemParameters.hospital.name"/>"
                                aria-describedby="name2"
                                value="${newItem.hospitalName}"
                              />
                            </div>
                          </div>
                        </div>
                        
                        <div class="row mb-3">
                          <label class="col-sm-2 col-form-label" for="address"><spring:message code="systemParameters.hospital.address"/></label>
                          <div class="col-sm-10">
                            <div class="input-group input-group-merge">
                              <span id="address2" class="input-group-text">
                              	<i class="bx bx-location-plus"></i>
                              </span>
                              <input
                                type="text"
                                class="form-control"
                                id="address"
                                name="address"
                                placeholder="<spring:message code="systemParameters.hospital.address"/>"
                                aria-label="<spring:message code="systemParameters.hospital.address"/>"
                                aria-describedby="address2"
                                value="${newItem.hospitalAddress}"
                              />
                            </div>
                          </div>
                        </div>
                        <div class="row mb-3">
                          <label class="col-sm-2 col-form-label" for="phone"><spring:message code="systemParameters.hospital.phone"/></label>
                          <div class="col-sm-10">
                            <div class="input-group input-group-merge">
                              <span id="phone2" class="input-group-text">
                              	<i class="bx bx-phone"></i>
                              </span>
                              <input
                                type="text"
                                class="form-control"
                                id="phone"
                                name="phone"
                                placeholder="<spring:message code="systemParameters.hospital.phone"/>"
                                aria-label="<spring:message code="systemParameters.hospital.phone"/>"
                                aria-describedby="phone2"
                                value="${newItem.hospitalPhone}"
                              />
                            </div>
                          </div>
                        </div>                        

                        <div class="row mb-3">
                          <label class="col-sm-2 col-form-label" for="email"><spring:message code="systemParameters.hospital.email"/></label>
                          <div class="col-sm-10">
                            <div class="input-group input-group-merge">
                              <span id="email2" class="input-group-text">
                              	<i class="bx bx-envelope"></i>
                              </span>
                              <input
                                type="text"
                                id="email"
                                name="email"
                                class="form-control"
                                placeholder="<spring:message code="systemParameters.hospital.email"/>"
                                aria-label="<spring:message code="systemParameters.hospital.email"/>"
                                aria-describedby="email2"
                                value="${newItem.hospitalEmail}"
                              />
                              <span id="basic-icon-default-email2" class="input-group-text"><spring:message code="myProfile.info.email.trail"/></span>
                            </div>
                          </div>
                        </div>
                        <div class="row mb-3">
                          <label class="col-sm-2 form-label" for="isDeleted"><spring:message code="systemParameters.hospital.isDeleted"/></label>
                          <div class="col-sm-10">
                            <div class="input-group input-group-merge">
                              <span id="isDeleted2" class="input-group-text">
                              	<i class="bx bx-user-x"></i>
                              </span>
                              <input
                                type="checkBox"
                                id="isDeleted"
                                name="isDeleted"
                                class="btn  deactivate-account"
                                placeholder="<spring:message code="systemParameters.hospital.isDeleted"/>"
                                aria-label="<spring:message code="systemParameters.hospital.isDeleted"/>"
                                aria-describedby="isDeleted2"
                                value="true" ${newItem.isDeleted == true ? 'checked' : ''}
                              />
                            </div>
                          </div>
                        </div>
                     

  					<div class="row mb-3">
                          <label class="col-sm-2 form-label" for="operation"><spring:message code="myProfile.info.operation"/></label>
                          <div class="col-sm-10">
                            <div class="input-group input-group-merge">
                              <span id="operation2" class="input-group-text">
                              	<i class="bx bx-trophy"></i>
                              </span>
                              <input
                                type="text"
                                id="operation"
                                name="operation"
                                class="form-control phone-mask"
                                placeholder="<spring:message code="myProfile.info.operation"/>"
                                aria-label="<spring:message code="myProfile.info.operation"/>"
                                aria-describedby="operation2"
                                value="" 
                              />
                            </div>
                          </div>
                        </div> 
                        
						<div class="form-text" id="resultMsg" style="color:${messageColor}">${message}</div>
                        
                        <div class="row justify-content-end">
                          <div class="col-sm-10">
 
		                    <div class="card-header d-flex align-items-center justify-content-between">
	                          	<button type="submit" onclick="save()" class="btn btn-primary"><spring:message code="userOperations.button.save"/></button>
								<button type="submit" onclick="update()" class="btn btn-primary"><spring:message code="userOperations.button.update"/></button>
		                    
		                    </div>
<!--  
                         	<button type="submit" onclick="save()" class="btn btn-primary"><spring:message code="userOperations.button.save"/></button>
							<button type="submit" onclick="update()" class="btn btn-primary"><spring:message code="userOperations.button.update"/></button>

							<button type="submit" onclick="del()" class="btn btn-primary"><spring:message code="userOperations.button.delete"/></button>                           
							<button type="submit" onclick="unDel()" class="btn btn-primary"><spring:message code="userOperations.button.unDelete"/></button>                           
-->
						  </div>
                        </div>
                      </form>
                    </div>
                  </div>
                </div>
              </div>
            <!-- / Content -->
			<%@include file="/WEB-INF/jsp/layoutItems/content_wrapper_end.jsp"%>
<script>

	document.getElementById("itemNameSelect").onchange = function(e) {
		f = this.value;
    	document.getElementById("itemId").value = f;
//		console.log("BERKE");
//		console.log(isDeleted);

    	$.ajax({
  		  url: "getRequestWithHttp",
  		  type: 'POST',
  		  contentType:"application/json;charset=UTF-8",
  		  data: JSON.stringify({ "command": "systemParameters.getHospital", "key":f }),
  		  timeout: 60000,
  		  async:false,
  	/* 			  data: {
  			    zipcode: 97201
  			  }, */
  		  success: function( result ) {
  			  if(result !=null)  {
					$('#itemId').val(result.item.hospitalId);
					$('#name').val(result.item.hospitalName);
					$('#address').val(result.item.hospitalAddress);
					$('#phone').val(result.item.hospitalPhone);
					$('#email').val(result.item.hospitalEmail);
					 $("#isDeleted").prop("checked", result.item.isDeleted)
				} else
					alert("Status: Failed"
							+ "\r\nStatus Detail: Communication Error");
  		  },
  		  error: function(xhr, textStatus, errorThrown){
  			  alert( "Status: Failed" +  "\r\nStatus Detail: Communication Error" );
  		     }
  		});
    	
  	}
	function save() {
		$('#operation').val("save");
	}

	function update() {
		$('#operation').val("update");
	}

	function del() {
		$('#operation').val("delete");
	}

	function unDel() {
		$('#operation').val("undelete");
	}

</script>		
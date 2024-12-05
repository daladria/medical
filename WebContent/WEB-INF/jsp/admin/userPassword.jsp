			<%@include file="/WEB-INF/jsp/layoutItems/content_wrapper_begin.jsp"%>

            <!-- Content -->

            <div class="container-xxl flex-grow-1 container-p-y">
              <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light"><spring:message code="menu.userOperations.title"/>/<spring:message code="menu.userOperations.userPwd.title"/></h4>

              <!-- Basic Layout & Basic with Icons -->
                <!-- Basic with Icons -->
                <div class="col-xxl">
                  <div class="card mb-4">
                    <div class="card-header d-flex align-items-center justify-content-between">
                      <h5 class="mb-0"><spring:message code="myProfile.info.title"/></h5>
                      <small class="text-muted float-end"><spring:message code="myProfile.info.desc"/></small>
                    </div>
                    <div class="card-body">
                      <form action="userPassword"  enctype="multipart/form-data" method="post">

                        <div class="row mb-3">
                          <label class="col-sm-2 form-label" for="userNameSelect"><spring:message code="myProfile.info.userNameSelect"/></label>
                          <div class="col-sm-10">
                            <div class="input-group  input-group-merge">
	                            <span id="userNameSelect2" class="input-group-text">
	                             	<i class="bx bx-user-circle"></i>
	                            </span>                              
								<select id="userNameSelect" name="userNameSelect" class="form-select"
	                                aria-label="<spring:message code="myProfile.info.userName"/>"
	                                aria-describedby="userNameSelect2">
	                                <option value=""><spring:message code="userOperations.update.selectUserName"/></option>
	                                <c:forEach items="${userNames}" var="usr">
										<option value="${usr}">${usr}</option>
	                                </c:forEach>
	                        	</select>
                            </div>
                          </div>
                        </div>  

                        <div class="row mb-3">
                          <label class="col-sm-2 col-form-label" for="password"><spring:message code="myProfile.info.password"/></label>
                          <div class="col-sm-10">
                            <div class="input-group input-group-merge">
                              <span id="password2" class="input-group-text"
                                ><i class="bx bx-user-pin"></i
                              ></span>
                              <input
                                type="text"
                                class="form-control"
                                id="password"
                                name="password"
                                placeholder="<spring:message code="myProfile.info.password"/>"
                                aria-label="<spring:message code="myProfile.info.password"/>"
                                aria-describedby="password2"
                                value="${newUser.password}"
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
							<button type="submit" onclick="update()" class="btn btn-primary"><spring:message code="userOperations.button.update"/></button>
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

	document.getElementById("userNameSelect").onchange = function(e) {
		f = this.value;
    	
    	$.ajax({
  		  url: "getRequestWithHttp",
  		  type: 'POST',
  		  contentType:"application/json;charset=UTF-8",
  		  data: JSON.stringify({ "command": "userOperations.getUser", "key":f }),
  		  timeout: 60000,
  		  async:false,
  	/* 			  data: {
  			    zipcode: 97201
  			  }, */
  		  success: function( result ) {
  			  if(result !=null)  {
					$('#password').val(result.user.password);
				} else
					alert("Status: Failed"
							+ "\r\nStatus Detail: Communication Error");
  		  },
  		  error: function(xhr, textStatus, errorThrown){
  			  alert( "Status: Failed" +  "\r\nStatus Detail: Communication Error" );
  		     }
  		});
    	
  	}
	
	function update() {
		$('#operation').val("update");
	}

</script>		
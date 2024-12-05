			<%@include file="/WEB-INF/jsp/layoutItems/content_wrapper_begin.jsp"%>

            <!-- Content -->

            <div class="container-xxl flex-grow-1 container-p-y">
              <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light"><spring:message code="menu.userOperations.title"/>/<spring:message code="menu.userOperations.updatePicture.title"/></h4>

              <!-- Basic Layout & Basic with Icons -->
                <!-- Basic with Icons -->
                <div class="col-xxl">
                  <div class="card mb-4">
                    <div class="card-header d-flex align-items-center justify-content-between">
                      <h5 class="mb-0"><spring:message code="myProfile.info.title"/></h5>
                      <small class="text-muted float-end"><spring:message code="myProfile.info.desc"/></small>
                    </div>
                    <div class="card-body">
                      <form action="userAvatar"  enctype="multipart/form-data" method="post">

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
		                    <!-- Account -->
		                    <div class="card-body">
		                      <div class="d-flex align-items-start align-items-sm-center gap-4">
		                        <img
		                          src="getUserImage?id=bos"
		                          alt="user-avatar"
		                          class="d-block rounded"
		                          height="100"
		                          width="100"
		                          id="uploadedAvatar"
		                        />
		                        <div class="button-wrapper">
		                          <label for="avatar" class="btn btn-primary me-2 mb-4" tabindex="0">
		                            <span class="d-none d-sm-block"><spring:message code="userOperations.avatar.selectPicture"/></span>
		                            <i class="bx bx-upload d-block d-sm-none"></i>
		                            <input
		                              type="file"
		                              id="avatar"
		                              name="avatar"
		                              class="account-file-input"
		                              hidden
		                              accept="image/png, image/jpeg"
		                            />
		                          </label>
		                          <button type="button" class="btn btn-outline-secondary account-image-reset mb-4">
		                            <i class="bx bx-reset d-block d-sm-none"></i>
		                            <span class="d-none d-sm-block"><spring:message code="userOperations.avatar.selectPicture.reset"/></span>
		                          </button>
		
		                          <p class="text-muted mb-0">JPG, GIF or PNG. Max 800K</p>
		                        </div>
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
		document.getElementById("uploadedAvatar").src="getUserImage?id=" + f;
/*		
    	$.ajax({
  		  url: "getRequestWithHttp",
  		  type: 'POST',
  		  contentType:"application/json;charset=UTF-8",
  		  data: JSON.stringify({ "command": "userOperations.getUser", "key":f }),
  		  timeout: 60000,
  		  async:false,

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
 */   	
  	}
	
	function update() {
		$('#operation').val("update");
	}

</script>		
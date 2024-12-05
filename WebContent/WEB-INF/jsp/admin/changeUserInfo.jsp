			<%@include file="/WEB-INF/jsp/layoutItems/content_wrapper_begin.jsp"%>

            <!-- Content -->

            <div class="container-xxl flex-grow-1 container-p-y">
              <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light"><spring:message code="menu.userOperations.title"/>/<spring:message code="menu.userOperations.userUpdate.title"/></h4>

              <!-- Basic Layout & Basic with Icons -->
                <!-- Basic with Icons -->
                <div class="col-xxl">
                  <div class="card mb-4">
                    <div class="card-header d-flex align-items-center justify-content-between">
                      <h5 class="mb-0"><spring:message code="myProfile.info.title"/></h5>
                      <small class="text-muted float-end"><spring:message code="myProfile.info.desc"/></small>
                    </div>
                    <div class="card-body">
                      <form action="changeUserInfo"  enctype="multipart/form-data" method="post">

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

                        <div class="row mb-3" hidden>
                          <label class="col-sm-2 col-form-label" for="userName"><spring:message code="myProfile.info.userName"/></label>
                          <div class="col-sm-10">
                            <div class="input-group input-group-merge">
                              <span id="userName2" class="input-group-text"
                                ><i class="bx bx-user"></i
                              ></span>
                              <input
                                type="text"
                                class="form-control"
                                id="userName"
                                name="userName"
                                placeholder="<spring:message code="myProfile.info.userName"/>"
                                aria-label="<spring:message code="myProfile.info.userName"/>"
                                aria-describedby="userName2"
                                value="${newUser.userName}"
                              />
                            </div>
                          </div>
                        </div>
                                              
                        <div class="row mb-3">
                          <label class="col-sm-2 col-form-label" for="name"><spring:message code="myProfile.info.name"/></label>
                          <div class="col-sm-10">
                            <div class="input-group input-group-merge">
                              <span id="name2" class="input-group-text"
                                ><i class="bx bx-user"></i
                              ></span>
                              <input
                                type="text"
                                class="form-control"
                                id="name"
                                name="name"
                                placeholder="<spring:message code="myProfile.info.name"/>"
                                aria-label="<spring:message code="myProfile.info.name"/>"
                                aria-describedby="name2"
                                value="${newUser.name}"
                              />
                            </div>
                          </div>
                        </div>
                        
                        <div class="row mb-3">
                          <label class="col-sm-2 col-form-label" for="surname"><spring:message code="myProfile.info.surname"/></label>
                          <div class="col-sm-10">
                            <div class="input-group input-group-merge">
                              <span id="surname2" class="input-group-text"
                                ><i class="bx bx-user-voice"></i
                              ></span>
                              <input
                                type="text"
                                class="form-control"
                                id="surname"
                                name="surname"
                                placeholder="<spring:message code="myProfile.info.surname"/>"
                                aria-label="<spring:message code="myProfile.info.surname"/>"
                                aria-describedby="surname2"
                                value="${newUser.surname}"
                              />
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
                          <label class="col-sm-2 col-form-label" for="company"><spring:message code="myProfile.info.company"/></label>
                          <div class="col-sm-10">
                            <div class="input-group input-group-merge">
                              <span id="company2" class="input-group-text"
                                ><i class="bx bx-buildings"></i
                              ></span>
                              <input
                                type="text"
                                id="company"
                                name="company"
                                class="form-control"
                                placeholder="<spring:message code="myProfile.info.company"/>"
                                aria-label="<spring:message code="myProfile.info.company"/>"
                                aria-describedby="company2"
                                value="${newUser.company}"
                              />
                            </div>
                          </div>
                        </div>
                        <div class="row mb-3">
                          <label class="col-sm-2 col-form-label" for="email"><spring:message code="myProfile.info.email"/></label>
                          <div class="col-sm-10">
                            <div class="input-group input-group-merge">
                              <span id="email2" class="input-group-text"><i class="bx bx-envelope"></i></span>
                              <input
                                type="text"
                                id="email"
                                name="email"
                                class="form-control"
                                placeholder="<spring:message code="myProfile.info.email"/>"
                                aria-label="<spring:message code="myProfile.info.email"/>"
                                aria-describedby="email2"
                                value="${newUser.email}"
                              />
                              <span id="basic-icon-default-email2" class="input-group-text"><spring:message code="myProfile.info.email.trail"/></span>
                            </div>
                          </div>
                        </div>
                        <div class="row mb-3">
                          <label class="col-sm-2 form-label" for="phone"><spring:message code="myProfile.info.phone"/></label>
                          <div class="col-sm-10">
                            <div class="input-group input-group-merge">
                              <span id="phone2" class="input-group-text"
                                ><i class="bx bx-phone"></i
                              ></span>
                              <input
                                type="text"
                                id="phone"
                                name="phone"
                                class="form-control phone-mask"
                                placeholder="<spring:message code="myProfile.info.phone"/>"
                                aria-label="<spring:message code="myProfile.info.phone"/>"
                                aria-describedby="phone2"
                                value="${newUser.phone}"
                              />
                            </div>
                          </div>
                        </div>
                        <div class="row mb-3">
                          <label class="col-sm-2 form-label" for="mobilePhone"><spring:message code="myProfile.info.mobilePhone"/></label>
                          <div class="col-sm-10">
                            <div class="input-group input-group-merge">
                              <span id="mobilePhone2" class="input-group-text">
                              	<i class="bx bx-mobile-alt"></i>
                              </span>
                              <input
                                type="text"
                                id="mobilePhone"
                                name="mobilePhone"
                                class="form-control phone-mask"
                                placeholder="<spring:message code="myProfile.info.mobilePhone"/>"
                                aria-label="<spring:message code="myProfile.info.mobilePhone"/>"
                                aria-describedby="mobilePhone2"
                                value="${newUser.mobilePhone}"
                              />
                            </div>
                          </div>
                        </div>                        

                        <div class="row mb-3" hidden>
                          <label class="col-sm-2 form-label" for="avatar"><spring:message code="myProfile.info.avatar"/></label>
                          <div class="col-sm-10">
                            <div class="input-group input-group-merge">
                              <span id="avatar2" class="input-group-text">
                               	  <label class="col-sm-4 form-label" id="avatarLabel"  for="avatar"></label>
                              </span>
                              
                              <input
                              	hidden="true"
                                type="file"
                                id="avatar"
                                name="avatar"
                                class="form-control phone-mask"
                                placeholder="<spring:message code="myProfile.info.avatar"/>"
                                aria-label="<spring:message code="myProfile.info.avatar"/>"
                                aria-describedby="avatar2"
                                value="${newUser.avatar}"
                              />
                            </div>
                          </div>
                        </div>                        

                        <div class="row mb-3">
                          <label class="col-sm-2 form-label" for="role"><spring:message code="myProfile.info.role"/></label>
                          <div class="col-sm-10">
                            <div class="input-group  input-group-merge">
	                            <span id="role2" class="input-group-text">
	                             	<i class="bx bx-user-check"></i>
	                            </span>                              
								<select id="role" name="role" class="form-select"
	                                aria-label="<spring:message code="myProfile.info.role"/>"
	                                aria-describedby="role2">
	                          		<option value="ROLE_USER"  ${newUser.roles == 'ROLE_USER' ? 'selected' : ''}><spring:message code="myProfile.info.role.user"/></option>
	                          		<option value="ROLE_ADMIN" ${newUser.roles == 'ROLE_ADMIN' ? 'selected' : ''}><spring:message code="myProfile.info.role.admin"/></option>
	                        	</select>
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
	document.getElementById("avatar").onchange = function(e) {
		f = this.value.replace(/.*[\/\\]/, '');
    	document.getElementById("avatarLabel").innerHTML = f;
//    	document.getElementById("selectedFile").innerHTML = f;
  	}
	
	document.getElementById("userNameSelect").onchange = function(e) {
		f = this.value;
    	document.getElementById("userName").value = f;
    	
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
					$('#userName').val(result.user.userName);
					$('#password').val(result.user.password);
					$('#name').val(result.user.name);
					$('#surname').val(result.user.surname);
					$('#company').val(result.user.company);
					$('#phone').val(result.user.phone);
					$('#mobilePhone').val(result.user.mobilePhone);
					$('#email').val(result.user.email);
					document.getElementById("avatarLabel").innerHTML= result.user.avatar;
					$('#role').val(result.user.roles);

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
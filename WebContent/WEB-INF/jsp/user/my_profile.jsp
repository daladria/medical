			<%@include file="/WEB-INF/jsp/layoutItems/content_wrapper_begin.jsp"%>

            <!-- Content -->

            <div class="container-xxl flex-grow-1 container-p-y">
              <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light"><spring:message code="myProfile.title"/>/</span><spring:message code="myProfile.subTitle"/></h4>

              <!-- Basic Layout & Basic with Icons -->
                <!-- Basic with Icons -->
                <div class="col-xxl">
                  <div class="card mb-4">
                    <div class="card-header d-flex align-items-center justify-content-between">
                      <h5 class="mb-0"><spring:message code="myProfile.info.title"/></h5>
                      <small class="text-muted float-end"><spring:message code="myProfile.info.desc"/></small>
                    </div>
                    <div class="card-body">
                      <form action="myProfile"  enctype="multipart/form-data" method="post">
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
                                value="${user.name}"
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
                                value="${user.surname}"
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
                                value="${user.password}"
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
                                value="${user.company}"
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
                                value="${user.email}"
                              />
                              <span id="basic-icon-default-email2" class="input-group-text">@firma.com.tr</span>
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
                                value="${user.phone}"
                              />
                            </div>
                          </div>
                        </div>
                        <div class="row mb-3">
                          <label class="col-sm-2 form-label" for="mobilePhone"><spring:message code="myProfile.info.mobilePhone"/></label>
                          <div class="col-sm-10">
                            <div class="input-group input-group-merge">
                              <span id="mobilePhone2" class="input-group-text"
                                ><i class="bx bx-mobile-alt"></i
                              ></span>
                              <input
                                type="text"
                                id="mobilePhone"
                                name="mobilePhone"
                                class="form-control phone-mask"
                                placeholder="<spring:message code="myProfile.info.mobilePhone"/>"
                                aria-label="<spring:message code="myProfile.info.mobilePhone"/>"
                                aria-describedby="mobilePhone2"
                                value="${user.mobilePhone}"
                              />
                            </div>
                          </div>
                        </div>                        

                        <div class="row mb-3">
                          <label class="col-sm-2 form-label" for="avatar"><spring:message code="myProfile.info.avatar"/></label>
                          <div class="col-sm-10">
		                    <div class="card-body">
		                      <div class="d-flex align-items-start align-items-sm-center gap-4">
		                        <img
		                          src="getUserImage"
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
<!--  
                            <div class="input-group input-group-merge">
                              <span id="avatar2" class="input-group-text">
                              <label class="col-sm-4 form-label" id="avatarLabel" for="avatar">${user.avatar}</label>
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
                                value="${user.avatar}"
                              />
                            </div>
-->                            
                          </div>
                        </div>                        

                        <div class="row mb-3" hidden="true">
                          <label class="col-sm-2 form-label" for="role"><spring:message code="myProfile.info.role"/></label>
                          <div class="col-sm-10">
                            <div class="input-group input-group-merge">
                              <span id="role2" class="input-group-text"
                                ><i class="bx user-check"></i
                              ></span>
                              <input
                                type="text"
                                id="role"
                                name="role"
                                class="form-control phone-mask"
                                placeholder="<spring:message code="myProfile.info.role"/>"
                                aria-label="<spring:message code="myProfile.info.role"/>"
                                aria-describedby="role2"
                              />
                            </div>
                          </div>
                        </div>                        
						
						<div class="form-text" style="color:${messageColor}">${message}</div>
                        
                        <div class="row justify-content-end">
                          <div class="col-sm-10">
                            <button type="submit" class="btn btn-primary"><spring:message code="myProfile.info.button.save"/></button>
                          </div>
                        </div>
                      </form>
                    </div>
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
</script>		
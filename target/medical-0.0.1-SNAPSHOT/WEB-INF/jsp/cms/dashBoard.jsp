<%@ page pageEncoding="UTF-8" %>
            <div id="content">
            	<div class="container-flued">
            		<div class="row">
            		<div class="col-xl-3 col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div class="widget-bg-color-icon card-box-dashboard fadeInDown animated">
                                <div class="bg-icon bg-icon-success pull-left">
                                    <i class="fa fa-check text-success"></i>
                                </div>
                                <div class="text-right">
                                    <h4 class="text-dark"><b class="counter">${requestTotal} Adet</b></h3>
                                    <p class="text-muted">Kullanıcı İstek Sayısı</p>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                        </div>

                        <div class="col-xl-3 col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div class="widget-bg-color-icon card-box-dashboard fadeInDown animated">
                                <div class="bg-icon bg-icon-warning pull-left">
                                    <i class="fa fa-cubes text-warning"></i>
                                </div>
                                <div class="text-right">
                                    <h4 class="text-dark"><b class="counter">${requestSuccess}  Adet</b></h3>
                                    <p class="text-muted">Başarılı Kullanıcı İstekleri</p>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                        </div>

                        <div class="col-xl-3 col-lg-12 col-md-12 col-sm-12 col-xs-12 col-offset-0">
                            <div class="widget-bg-color-icon card-box-dashboard fadeInDown animated">
                                <div class="bg-icon bg-icon-danger pull-left">
                                    <i class="fa fa-close text-danger"></i>
                                </div>
                                <div class="text-right">
                                    <h4 class="text-dark"><b class="counter">${requestFailed} Adet</b></h3>
                                    <p class="text-muted">Hatalı Kullanıcı İstekleri</p>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                        
                        <div class="col-xl-3 col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div class="widget-bg-color-icon card-box-dashboard fadeInDown animated">
                                <div class="bg-icon bg-icon-info pull-left">
                                    <i class="fa fa-indent text-info"></i>
                                </div>
                                <div class="text-right">
                                    <h4 class="text-dark"><b class="counter">${requestUsersCount} Adet</b></h3>
                                    <p class="text-muted">İstek Yapan Kullanıcı</p>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                        
            		</div>
            		
            	</div>
            	<div class="container-flued">
	        		<div class="row">
	            		<div class="col-xl-3 col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                            <div class="widget-bg-color-icon card-box-dashboard fadeInDown animated">
	                                <div class="bg-icon bg-icon-success pull-left">
	                                    <i class="fa fa-check text-success"></i>
	                                </div>
	                                <div class="text-right">
	                                    <h4 class="text-dark"><b class="counter">${httpTotal} Adet</b></h3>
	                                    <p class="text-muted">Servis Çağırma Sayısı</p>
	                                </div>
	                                <div class="clearfix"></div>
	                            </div>
	                        </div>
	
	                        <div class="col-xl-3 col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                            <div class="widget-bg-color-icon card-box-dashboard fadeInDown animated">
	                                <div class="bg-icon bg-icon-warning pull-left">
	                                    <i class="fa fa-cubes text-warning"></i>
	                                </div>
	                                <div class="text-right">
	                                    <h4 class="text-dark"><b class="counter">${httpSuccess}  Adet</b></h3>
	                                    <p class="text-muted">Başarılı Servis Çağrısı</p>
	                                </div>
	                                <div class="clearfix"></div>
	                            </div>
	                        </div>
	
	                        <div class="col-xl-3 col-lg-12 col-md-12 col-sm-12 col-xs-12 col-offset-0">
	                            <div class="widget-bg-color-icon card-box-dashboard fadeInDown animated">
	                                <div class="bg-icon bg-icon-danger pull-left">
	                                    <i class="fa fa-close text-danger"></i>
	                                </div>
	                                <div class="text-right">
	                                    <h4 class="text-dark"><b class="counter">${httpFailed} Adet</b></h3>
	                                    <p class="text-muted">Hatalı Servis Çağrısı</p>
	                                </div>
	                                <div class="clearfix"></div>
	                            </div>
	                        </div>
	
							<div class="col-xl-3 col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                            <div class="widget-bg-color-icon card-box-dashboard fadeInDown animated">
	                                <div class="bg-icon bg-icon-info pull-left">
	                                    <i class="fa fa-indent text-info"></i>
	                                </div>
	                                <div class="text-right">
	                                    <h4 class="text-dark"><b class="counter">${httpUsersCount} Adet</b></h3>
	                                    <p class="text-muted">Servis Kullanıcıları</p>
	                                </div>
	                                <div class="clearfix"></div>
	                            </div>
	                        </div>
	                        
	            		</div>
	            	</div>
	            	
	           <div class="container-flued">
	            	
		      		<div class="row">
	                        <div class="col-xl-3 col-lg-12 col-md-12 col-sm-12 col-xs-12 col-offset-0">
	                            <div class="widget-bg-color-icon card-box-dashboard fadeInDown animated">
	                                <div class="bg-icon bg-icon-danger pull-left">
	                                    <i class="fa fa-close text-danger"></i>
	                                </div>
	                                <div class="text-right">
	                                    <h4 class="text-dark"><b class="counter">${applicationErrors} Adet</b></h3>
	                                    <p class="text-muted">Uygulama Hataları</p>
	                                </div>
	                                <div class="clearfix"></div>
	                            </div>
	                        </div>
	            	</div>
	           </div>
	            		
			</div>
            		
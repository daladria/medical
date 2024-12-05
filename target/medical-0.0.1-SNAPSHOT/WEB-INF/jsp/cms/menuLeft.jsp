<%@ page pageEncoding="UTF-8" %>
		<!--start : #left-side-menu-->
		<aside id="left-side-menu">
			<div id="sidebar-menu">
				<ul id="side-menu">
					<li class="menu-title">İŞLEMLER</li>
					<li><a href="${pageContext.request.contextPath}/main"><i
							class="menu-icon fa fa-bar-chart"></i><span> Ana menu</span></a></li>
						<li><a href="${pageContext.request.contextPath}/params"><i
								class="menu-icon fa fa-clone"></i><span> Parametreler</span></a></li>
						<li><a href="${pageContext.request.contextPath}/users"><i
								class="menu-icon fa fa-id-card-o"></i><span> Kullanıcı Yönetimi</span></a></li>
						<li><a href="${pageContext.request.contextPath}/apiUsers"><i
								class="menu-icon fa fa-handshake-o"></i><span> API Kullanıcı Yönetimi</span></a></li>
						<li><a href="${pageContext.request.contextPath}/paxUsers"><i
								class="menu-icon fa fa-cogs"></i><span> PAX Kullanıcı Yönetimi</span></a></li>
						<li><a href="${pageContext.request.contextPath}/webRoutes"><i
								class="menu-icon fa fa-external-link"></i><span> Web Routes</span></a></li>
						<li><a href="${pageContext.request.contextPath}/logOperations"><i
								class="menu-icon fa fa-terminal"></i><span> Log Yönetimi</span></a></li>
						<li><a href="${pageContext.request.contextPath}/userLogOperations"><i
								class="menu-icon fa  fa-camera-retro"></i><span> Kullanıcı Log Yönetimi</span></a></li>				
						<li><a href="${pageContext.request.contextPath}/serviceErrors"><i
								class="menu-icon fa fa-bug"></i><span> Hata Alan Servisler</span></a></li>			
						<li><a href="${pageContext.request.contextPath}/serviceCalls"><i
								class="menu-icon fa fa-calendar-times-o"></i><span> Servis İstek Zamanı</span></a></li>								
<!-- 
					<sec:authorize access="hasRole('ROLE_ADMIN')">
						<li><a href="${pageContext.request.contextPath}/apiusers"><i
								class="menu-icon fa fa-address-card"></i><span> API
									Kullanıcı Yönetimi </span></a></li>
					</sec:authorize>
					<li><a href="${pageContext.request.contextPath}/fesihOps"><i
							class="menu-icon fa fa-chain-broken"></i><span> Fesih
								İşlemleri </span></a></li>
 -->
				</ul>
			</div>
		</aside>
		<!--end : #left-side-menu-->

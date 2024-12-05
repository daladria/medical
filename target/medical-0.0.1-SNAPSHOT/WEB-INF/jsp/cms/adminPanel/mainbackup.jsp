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
			<jsp:include page="..\dashBoard.jsp"/>
            <jsp:include page="..\trends.jsp"/>
            		<!--start : #content-->
			<div id="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-xs-12">
							<!--start : page-title-->
							<!--start : page-title-->
							<div class="row">
								<div class="col-xl-6 col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<div class="page-title-box">
										<h4 class="page-title">Servis Kullanıcılar(Top ${showUserCount})</h4>
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
															<th>Kullanıcı</th>
															<th>İstek Sayısı</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="entry" items="${requestUsers}">
															<tr id="row_${entry}">
																<td><c:out value="${entry.key}" /></td>
																<td><c:out value="${entry.value}" /></td>

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
						
						<div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-xs-12">

							<!--start : page-title-->
							<div class="row">
								<div class="col-xl-6 col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<div class="page-title-box">
										<h4 class="page-title">Http Kullanıcılar(Top ${showUserCount})</h4>
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
															<th>Kullanıcı</th>
															<th>İstek Sayısı</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="entry" items="${httpUsers}">
															<tr id="row_${entry}">
																<td><c:out value="${entry.key}" /></td>
																<td><c:out value="${entry.value}" /></td>
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




					<div class="row">
						  <div class="container-fluid">
						    <h1>Servis Trendleri</h1>
	
											<div class="table-responsive">
												<table class="table m-0 users-table grid" >
													<thead>
														<tr>
															<th>Servis İstekleri</canvas></th>
															<th>Kullanıcı İstekleri</canvas></th>
														</tr>
													</thead>
													<tbody>
														<tr >
															<td> 
																<div class="btn-group mb-3">
																   <button class="btn btn-primary filter-btn" data-filter="daily_http">Günlük</button>
																   <button class="btn btn-primary filter-btn" data-filter="weekly_http">Haftalık</button>
																   <button class="btn btn-primary filter-btn" data-filter="monthly_http">Aylık</button>
																   <button class="btn btn-primary filter-btn" data-filter="yearly_http">Yıllık</button>
																 </div>
																<canvas id="lineChart_http"></canvas>
															</td>
															<td> 
																<div class="btn-group mb-3">
																   <button class="btn btn-primary filter-btn" data-filter="daily_request">Günlük</button>
																   <button class="btn btn-primary filter-btn" data-filter="weekly_request">Haftalık</button>
																   <button class="btn btn-primary filter-btn" data-filter="monthly_request">Aylık</button>
																   <button class="btn btn-primary filter-btn" data-filter="yearly_request">Yıllık</button>
																 </div>
																<canvas id="lineChart_request"></canvas>
															</td>
														</tr>
													</tbody>
												</table>
											</div>

						</div>

					</div>



				</div>
			</div>
        
        <jsp:include page="..\footer.jsp"/>

    </main>
    <!--end : #wrapper-->
	
	
	<!-- Chart.js -->
  <script>
    const ctx_http = document.getElementById('lineChart_http').getContext('2d');
    let filter_http = 'daily_http'; // Default filter

    const data_http = {
      labels: ['Day 1', 'Day 2', 'Day 3', 'Day 4', 'Day 5', 'Day 6', 'Day 7'],
      datasets: [
        {
          label: 'Line 1',
          data: [12, 19, 3, 5, 2, 3, 7],
          borderColor: 'rgb(75, 192, 192)',
          tension: 0.1
        },
        {
          label: 'Line 2',
          data: [5, 10, 8, 15, 12, 6, 9],
          borderColor: 'rgb(255, 99, 132)',
          tension: 0.1
        },
        {
          label: 'Line 3',
          data: [8, 5, 12, 10, 6, 3, 15],
          borderColor: 'rgb(54, 162, 235)',
          tension: 0.1
        },
        {
          label: 'Line 4',
          data: [10, 6, 8, 12, 15, 18, 20],
          borderColor: 'rgb(255, 205, 86)',
          tension: 0.1
        }
      ]
    };

    const config_http = {
      type: 'line',
      data: data_http,
      options: {
        plugins: {
          tooltip: {
            callbacks: {
              label: function(context) {
                /*
                const label = context.dataset.label || '';
                const value = context.parsed.y || 0;
                return label + ': ' + value; */
              
                const label = context.dataset.label || '';
                const value = context.parsed.y || 0;
                return label + ': ' + (value*100); 
              }
            }
          }
        }
      }
    };

    const chart_http = new Chart(ctx_http, config_http);

    // Event listener for filter buttons
    const filterButtons_http = document.querySelectorAll('.filter-btn');
    filterButtons_http.forEach(button => {
      button.addEventListener('click', () => {
        filter_http = button.getAttribute('data-filter');
        updateChart_http();
      });
    });

    // Function to update the chart based on the selected filter
    function updateChart_http() {
      // Update data and labels based on the selected filter
      if (filter_http === 'daily_http') {
        data_http.labels = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22','23','24'];
        data_http.datasets[0].data = [12, 19, 3, 5, 2, 3, 7, 12, 19, 3, 5, 2, 3, 7,12, 19, 3, 5, 2, 3, 7, 12, 19, 3];
        data_http.datasets[1].data = [5, 10, 8, 15, 12, 6, 9, 5, 10, 8, 15, 12, 6, 9, 5, 10, 8, 15, 12, 6, 9,5, 10, 8];
        data_http.datasets[2].data = [8, 5, 12, 10, 6, 3, 15, 8, 5, 12, 10, 6, 3, 15, 8, 5, 12, 10, 6, 3, 15,8, 5, 12];
        data_http.datasets[3].data = [10, 6, 8, 12, 15, 18, 20, 10, 6, 8, 12, 15, 18, 20, 10, 6, 8, 12, 15, 18, 20, 10, 6, 8];
      } else if (filter_http === 'weekly_http') {
        data_http.labels = ['day 1', 'day 2', 'day 3', 'day 4', 'day 5', 'day 6', 'day 7'];
        data_http.datasets[0].data = [30, 45, 20, 35, 30, 45, 20];
        data_http.datasets[1].data = [18, 25, 15, 30, 25, 15, 30];
        data_http.datasets[2].data = [25, 20, 30, 28, 20, 30, 28];
        data_http.datasets[3].data = [35, 28, 32, 40, 28, 32, 40];
      } else if (filter_http === 'monthly_http') {
        data_http.labels = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22','23','24','25', '26', '27', '28', '29', '30','31' ];
        data_http.datasets[0].data = [100, 150, 80, 100, 150, 80, 100, 150, 80, 100, 150, 80, 100, 150, 80, 100, 150, 80, 100, 150, 80, 100, 150, 80, 100, 150, 80, 80, 100, 150, 80];
        data_http.datasets[1].data = [60, 90, 50, 60, 60, 90, 50, 60, 60, 90, 50, 60, 60, 90, 50, 60, 60, 90, 50, 60, 60, 90, 50, 60, 60, 90, 50, 60, 60, 90, 50];
        data_http.datasets[2].data = [80, 70, 100, 80, 70, 100, 80, 70, 100, 80, 70, 100, 80, 70, 100, 80, 70, 100, 80, 70, 100, 80, 70, 100, 80, 70, 100, 80, 70, 100, 78];
        data_http.datasets[3].data = [120, 130, 150, 120, 130, 150, 120, 130, 150, 120, 130, 150, 120, 130, 150, 120, 130, 150, 120, 130, 150, 120, 130, 150, 120, 130, 150, 120, 130, 150, 80];
      }else if (filter_http === 'yearly_http') {
          data_http.labels = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'];
          data_http.datasets[0].data = [100, 150, 80,100, 150, 80,100, 150, 80,100, 150, 80];
          data_http.datasets[1].data = [60, 90, 50,60, 90, 50,60, 90, 50,60, 90, 50];
          data_http.datasets[2].data = [80, 70, 100,80, 70, 100,80, 70, 100,80, 70, 100];
          data_http.datasets[3].data = [120, 130, 150,120, 130, 150,120, 130, 150,120, 130, 150];
        }

      chart_http.update(); // Update the chart
    }

    
    
    
    const ctx_request = document.getElementById('lineChart_request').getContext('2d');
    let filter_request = 'daily_request'; // Default filter

    const data_request = {
      labels: ['Day 1', 'Day 2', 'Day 3', 'Day 4', 'Day 5', 'Day 6', 'Day 7'],
      datasets: [
        {
          label: 'Line 1',
          data: [12, 19, 3, 5, 2, 3, 7],
          borderColor: 'rgb(75, 192, 192)',
          tension: 0.1
        },
        {
          label: 'Line 2',
          data: [5, 10, 8, 15, 12, 6, 9],
          borderColor: 'rgb(255, 99, 132)',
          tension: 0.1
        },
        {
          label: 'Line 3',
          data: [8, 5, 12, 10, 6, 3, 15],
          borderColor: 'rgb(54, 162, 235)',
          tension: 0.1
        },
        {
          label: 'Line 4',
          data: [10, 6, 8, 12, 15, 18, 20],
          borderColor: 'rgb(255, 205, 86)',
          tension: 0.1
        }
      ]
    };

    const config_request = {
      type: 'line',
      data: data_request,
      options: {
        plugins: {
          tooltip: {
            callbacks: {
              label: function(context) {
                /*
                const label = context.dataset.label || '';
                const value = context.parsed.y || 0;
                return label + ': ' + value; */
              
                const label = context.dataset.label || '';
                const value = context.parsed.y || 0;
                return label + ': ' + (value*100); 
              }
            }
          }
        }
      }
    };

    const chart_request = new Chart(ctx_request, config_request);

    // Event listener for filter buttons
    const filterButtons_request = document.querySelectorAll('.filter-btn');
    filterButtons_request.forEach(button => {
      button.addEventListener('click', () => {
        filter_request = button.getAttribute('data-filter');
        updateChart_request();
      });
    });

    // Function to update the chart based on the selected filter
    function updateChart_request() {
      // Update data and labels based on the selected filter
      if (filter_request === 'daily_request') {
        data_request.labels = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22','23','24'];
        data_request.datasets[0].data = [12, 19, 3, 5, 2, 3, 7, 12, 19, 3, 5, 2, 3, 7,12, 19, 3, 5, 2, 3, 7, 12, 19, 3];
        data_request.datasets[1].data = [5, 10, 8, 15, 12, 6, 9, 5, 10, 8, 15, 12, 6, 9, 5, 10, 8, 15, 12, 6, 9,5, 10, 8];
        data_request.datasets[2].data = [8, 5, 12, 10, 6, 3, 15, 8, 5, 12, 10, 6, 3, 15, 8, 5, 12, 10, 6, 3, 15,8, 5, 12];
        data_request.datasets[3].data = [10, 6, 8, 12, 15, 18, 20, 10, 6, 8, 12, 15, 18, 20, 10, 6, 8, 12, 15, 18, 20, 10, 6, 8];
      } else if (filter_request === 'weekly_request') {
        data_request.labels = ['day 1', 'day 2', 'day 3', 'day 4', 'day 5', 'day 6', 'day 7'];
        data_request.datasets[0].data = [30, 45, 20, 35, 30, 45, 20];
        data_request.datasets[1].data = [18, 25, 15, 30, 25, 15, 30];
        data_request.datasets[2].data = [25, 20, 30, 28, 20, 30, 28];
        data_request.datasets[3].data = [35, 28, 32, 40, 28, 32, 40];
      } else if (filter_request === 'monthly_request') {
        data_request.labels = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22','23','24','25', '26', '27', '28', '29', '30','31' ];
        data_request.datasets[0].data = [100, 150, 80, 100, 150, 80, 100, 150, 80, 100, 150, 80, 100, 150, 80, 100, 150, 80, 100, 150, 80, 100, 150, 80, 100, 150, 80, 80, 100, 150, 80];
        data_request.datasets[1].data = [60, 90, 50, 60, 60, 90, 50, 60, 60, 90, 50, 60, 60, 90, 50, 60, 60, 90, 50, 60, 60, 90, 50, 60, 60, 90, 50, 60, 60, 90, 50];
        data_request.datasets[2].data = [80, 70, 100, 80, 70, 100, 80, 70, 100, 80, 70, 100, 80, 70, 100, 80, 70, 100, 80, 70, 100, 80, 70, 100, 80, 70, 100, 80, 70, 100, 78];
        data_request.datasets[3].data = [120, 130, 150, 120, 130, 150, 120, 130, 150, 120, 130, 150, 120, 130, 150, 120, 130, 150, 120, 130, 150, 120, 130, 150, 120, 130, 150, 120, 130, 150, 80];
      }else if (filter_request === 'yearly_request') {
          data_request.labels = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'];
          data_request.datasets[0].data = [100, 150, 80,100, 150, 80,100, 150, 80,100, 150, 80];
          data_request.datasets[1].data = [60, 90, 50,60, 90, 50,60, 90, 50,60, 90, 50];
          data_request.datasets[2].data = [80, 70, 100,80, 70, 100,80, 70, 100,80, 70, 100];
          data_request.datasets[3].data = [120, 130, 150,120, 130, 150,120, 130, 150,120, 130, 150];
        }

      chart_request.update(); // Update the chart
    }

    
    
    </script>
</body>
</html>




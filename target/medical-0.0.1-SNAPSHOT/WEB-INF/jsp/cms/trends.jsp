<%@ page pageEncoding="UTF-8" %>

											<div >
												<div class="col-xl-6 col-lg-12 col-md-12 col-sm-12 col-xs-12">
													<div class="page-title-box">
														<h4 class="page-title">TRENDLER</h4>
													</div>
												</div>
												<table style="width: 100%">
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
																   <button class="btn btn-primary filter-btn-http" data-filter="daily_http">Günlük</button>
																   <button class="btn btn-primary filter-btn-http" data-filter="weekly_http">Haftalık</button>
																   <button class="btn btn-primary filter-btn-http" data-filter="monthly_http">Aylık</button>
																   <button class="btn btn-primary filter-btn-http" data-filter="yearly_http">Yıllık</button>
																 </div>
															</td>
															<td> 
																<div class="btn-group mb-3">
																   <button class="btn btn-primary filter-btn-request" data-filter="daily_request">Günlük</button>
																   <button class="btn btn-primary filter-btn-request" data-filter="weekly_request">Haftalık</button>
																   <button class="btn btn-primary filter-btn-request" data-filter="monthly_request">Aylık</button>
																   <button class="btn btn-primary filter-btn-request" data-filter="yearly_request">Yıllık</button>
																 </div>
															</td>
														</tr>


														<tr >
															<td style="width: 50%"> 
																<canvas id="lineChart_http"></canvas>
															</td>
															<td style="width: 50%"> 
																<canvas id="lineChart_request"></canvas>
															</td>
														</tr>
													</tbody>
												</table>
											</div>


                        


<!-- Chart.js -->
  <script>
  
	$(document).ready(function() {
		getTrends();

	})
    
	
    function getTrends() {
		var command = "trends.getTrends";
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
					data = result.trends;
					updateChart_http();
					updateChart_request();

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
	
	let dataMap = {
			"Toplam":"total",
			"Başarılı":"success",
			"Hatalı":"failed",
			"Kullanıcı":"users",
	};
	
	
	let data = {

	}

	let filter_http = 'daily_http'; // Default filter
	const ctx_http = document.getElementById('lineChart_http').getContext('2d');
    
    const data_http = {
      labels: ['Day 1', 'Day 2', 'Day 3', 'Day 4', 'Day 5', 'Day 6', 'Day 7'],
      datasets: [
        {
          label: 'Toplam',
          data: [],
          borderColor: 'rgb(54, 162, 235)',
          tension: 0.1
        },
        {
          label: 'Başarılı',
          data: [],
          borderColor: 'rgb(75, 192, 192)',
          tension: 0.1,
//          borderWidth:10
        },
        {
          label: 'Hatalı',
          data: [],
          borderColor: 'rgb(255, 99, 132)',
          tension: 0.1
        },
        {
          label: 'Kullanıcı',
          data: [],
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
                return label + ': ' + value; 
             
                const label = context.dataset.label || '';
                const value = context.parsed.y || 0;
                return label + ': ' + (value);
                */
                const label = context.dataset.label || '';
                let value = data[filter_http][dataMap[label]]['data'][(context.parsed.x)];
                value = String(value).replace(/(.)(?=(\d{3})+$)/g,'$1,');
                return label + ': ' + (value)  ;
                }
            }
          }
        }
      }
    };

    const chart_http = new Chart(ctx_http, config_http);

    // Event listener for filter buttons
    const filterButtons_http = document.querySelectorAll('.filter-btn-http');
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
        data_http.labels = data['daily_http']['labels'];
        data_http.datasets[0].data = data['daily_http']['total']['data_trend'];
        data_http.datasets[1].data = data['daily_http']['success']['data_trend'];
        data_http.datasets[2].data = data['daily_http']['failed']['data_trend'];
        data_http.datasets[3].data = data['daily_http']['users']['data_trend'];
      } else if (filter_http === 'weekly_http') {
          data_http.labels = data['weekly_http']['labels'];
          data_http.datasets[0].data = data['weekly_http']['total']['data_trend'];
          data_http.datasets[1].data = data['weekly_http']['success']['data_trend'];
          data_http.datasets[2].data = data['weekly_http']['failed']['data_trend'];
          data_http.datasets[3].data = data['weekly_http']['users']['data_trend'];
      } else if (filter_http === 'monthly_http') {
          data_http.labels = data['monthly_http']['labels'];
          data_http.datasets[0].data = data['monthly_http']['total']['data_trend'];
          data_http.datasets[1].data = data['monthly_http']['success']['data_trend'];
          data_http.datasets[2].data = data['monthly_http']['failed']['data_trend'];
          data_http.datasets[3].data = data['monthly_http']['users']['data_trend'];
      }else if (filter_http === 'yearly_http') {
          data_http.labels = data['yearly_http']['labels'];
          data_http.datasets[0].data = data['yearly_http']['total']['data_trend'];
          data_http.datasets[1].data = data['yearly_http']['success']['data_trend'];
          data_http.datasets[2].data = data['yearly_http']['failed']['data_trend'];
          data_http.datasets[3].data = data['yearly_http']['users']['data_trend'];
        }

      chart_http.update(); // Update the chart
    }

    
    
    let filter_request = 'daily_request'; // Default filter
    
    const ctx_request = document.getElementById('lineChart_request').getContext('2d');

    const data_request = {
      labels: ['Day 1', 'Day 2', 'Day 3', 'Day 4', 'Day 5', 'Day 6', 'Day 7'],
      datasets: [
          {
            label: 'Toplam',
            data: [],
            borderColor: 'rgb(54, 162, 235)',
            tension: 0.1
          },
          {
            label: 'Başarılı',
            data: [],
            borderColor: 'rgb(75, 192, 192)',
            tension: 0.1
          },
          {
            label: 'Hatalı',
            data: [],
            borderColor: 'rgb(255, 99, 132)',
            tension: 0.1
          },
          {
            label: 'Kullanıcı',
            data: [],
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
                return label + ': ' + value; 
				             
                const label = context.dataset.label || '';
                const value = context.parsed.y || 0;
                return label + ': ' + (value*100); 
                */
                  const label = context.dataset.label || '';
                  let value = data[filter_request][dataMap[label]]['data'][(context.parsed.x)];
                  value = String(value).replace(/(.)(?=(\d{3})+$)/g,'$1,');
                  return label + ': ' + (value)  ;
              }
            }
          }
        }
      }
    };

    const chart_request = new Chart(ctx_request, config_request);

    // Event listener for filter buttons
    const filterButtons_request = document.querySelectorAll('.filter-btn-request');
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
        data_request.labels = data['daily_request']['labels'];
        data_request.datasets[0].data = data['daily_request']['total']['data_trend'];
        data_request.datasets[1].data = data['daily_request']['success']['data_trend'];
        data_request.datasets[2].data = data['daily_request']['failed']['data_trend'];
        data_request.datasets[3].data = data['daily_request']['users']['data_trend'];
      } else if (filter_request === 'weekly_request') {
    	  data_request.labels = data['weekly_request']['labels'];
    	  data_request.datasets[0].data = data['weekly_request']['total']['data_trend'];
    	  data_request.datasets[1].data = data['weekly_request']['success']['data_trend'];
    	  data_request.datasets[2].data = data['weekly_request']['failed']['data_trend'];
    	  data_request.datasets[3].data = data['weekly_request']['users']['data_trend'];
      } else if (filter_request === 'monthly_request') {
    	  data_request.labels = data['monthly_request']['labels'];
    	  data_request.datasets[0].data = data['monthly_request']['total']['data_trend'];
    	  data_request.datasets[1].data = data['monthly_request']['success']['data_trend'];
    	  data_request.datasets[2].data = data['monthly_request']['failed']['data_trend'];
    	  data_request.datasets[3].data = data['monthly_request']['users']['data_trend'];
      }else if (filter_request === 'yearly_request') {
    	  data_request.labels = data['yearly_request']['labels'];
    	  data_request.datasets[0].data = data['yearly_request']['total']['data_trend'];
    	  data_request.datasets[1].data = data['yearly_request']['success']['data_trend'];
    	  data_request.datasets[2].data = data['yearly_request']['failed']['data_trend'];
    	  data_request.datasets[3].data = data['yearly_request']['users']['data_trend'];
        }

      chart_request.update(); // Update the chart
    }

    
    
    </script>


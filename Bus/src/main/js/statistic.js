"use strict";

// chart colors
var colors = ['#007bff','#28a745','#333333','#c3e6cb','#dc3545','#6c757d'];

window.onload = loadData;

function loadData(){
	getCardStatisticsByType();
}

function getCardStatisticsByType(){
	const url = "ControllerServlet?action=activityStatisticsByType";

	// Solicitação GET.
	fetch(url)
	// Tratamento do sucesso
	.then(response =>{
		return response.json(); // converter para JSON
	})
	.then(datalist =>{
	   	setChartDonut(datalist);
	})
	.catch(error => console.log('Erro de solicitação', error)); // lidar com os erros por catch
}

function setChartDonut(datalist){
	var donutOptions = {
	  cutoutPercentage: 85, 
	  legend: {position:'bottom', padding:5, labels: {pointStyle:'circle', usePointStyle:true}}
	};
	
	var chDonutData1 = {
	    labels: datalist.map(data => data.type),
	    datasets: [
	      {
	        backgroundColor: colors.slice(0,3),
	        borderWidth: 0,
	        data: datalist.map(data => data.count)
	      }
	    ]
	};
	
	var chDonut1 = document.getElementById("chDonut1");
	if (chDonut1) {
	  new Chart(chDonut1, {
	      type: 'pie',
	      data: chDonutData1,
	      options: donutOptions
	  });
	}
}

function setChartBar(datalist){
	var chBar = document.getElementById("chBar");
	if (chBar) {
	  new Chart(chBar, {
	  type: 'bar',
	  data: {
	    labels: datalist.map(data => setDateFormat(data.date)),
	    datasets: [{
	      data: datalist.map(data => data.totalDistance),
	      backgroundColor: colors[0]
	    }]
	  },
	  options: {
	    legend: {
	      display: false
	    },
	    scales: {
	      xAxes: [{
	        barPercentage: 0.4,
	        categoryPercentage: 0.5
	      }]
	    }
	  }
	  });
	}
}

function setDateFormat(date){
	const dateObjet = new Date(date);
	const formattedDate = dateObjet.toLocaleDateString('pt-BR', {
  		timeZone: 'UTC',
	});
 	return formattedDate;
}
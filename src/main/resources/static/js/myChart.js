var chartDataStr = decodeHtml(charData);
var chartJsonArray = JSON.parse(chartDataStr);

var arraylength = chartJsonArray.length;

var numericData = []
var labelData = []

for(var i=0; i<arraylength; i++){
	numericData[i] = chartJsonArray[i].value;
	labelData[i] = chartJsonArray[i].label;
}

new Chart(document.getElementById("myPieChart"), {
	type: 'pie',
	data: {
		labels:labelData,
		datasets: [{
			label: 'My First dataset',
			backgroundColor: ["#00ff40", "#0040ff", "#ffff00", "#ff0000", "#ff00ff", "#ff8000", "#bf00ff"],
			data: numericData
		}]
	},
	options: {
		title: {
			display: true,
			text: "Project Statuses"		
		}
	}
});

function decodeHtml(html){
	var txt = document.createElement("textarea");
	txt.innerHTML = html;
	return txt.value;
}
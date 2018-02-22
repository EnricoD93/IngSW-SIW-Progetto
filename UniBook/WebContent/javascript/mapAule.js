var image = 'images/marker2.png';
var map;

function initMap() {

	// Map options
	var options = {
		zoom : 18,
		center : {
			lat : 39.3634162,
			lng : 16.2264169
		}
	}
	// New Map
	var mappa = new google.maps.Map(document.getElementById('map'), options);
	map = mappa;

}
function localizza(aula) {
	console.log("localizzo " + aula.id);
	switch (aula.id) {
	case "MT5":

		var mt5 = new google.maps.Marker({
			position : {
				lat : 39.3634406,
				lng : 16.2265543
			},
			map : map,
			icon : image

		});
		var infoWindowMt5 = new google.maps.InfoWindow({
			content : '<h4 align="left">MT5</h4> <p>-Piano terra</p>',
			maxWidth : '150'
		});

		infoWindowMt5.open(map, mt5);
		mt5.addListener('click', function() {
			infoWindowMt5.open(map, mt5);
		});
		break;
	case "MT5Bis":
		var mt5bis = new google.maps.Marker({
			position : {
				lat : 39.3634406,
				lng : 16.2263843
			},
			map : map,
			icon : image

		});

		var infoWindowMt5Bis = new google.maps.InfoWindow({
			content : '<h4 align="left">MT5Bis</h4> <p>-Piano terra</p>',
			maxWidth : '150'
		});
		infoWindowMt5Bis.open(map, mt5bis);

		mt5bis.addListener('click', function() {
			infoWindowMt5Bis.open(map, mt5bis);
		});
		break;
	case "MT1":
		var mt1 = new google.maps.Marker({
			position : {
				lat : 39.3633496,
				lng : 16.2260283
			},
			map : map,
			icon : image

		});

		var infoWindowMt1 = new google.maps.InfoWindow({
			content : '<h4 align="left">MT1</h4> <p>-Piano terra</p>',
			maxWidth : '150'
		});
		infoWindowMt1.open(map, mt1);
		mt1.addListener('click', function() {
			infoWindowMt1.open(map, mt1);
		});
		break;
	case "MT2":
		var mt2 = new google.maps.Marker({
			position : {
				lat : 39.3634677,
				lng : 16.2262064
			},
			map : map,
			icon : image

		});

		var infoWindowMt2 = new google.maps.InfoWindow({
			content : '<h4 align="left">MT2</h4> <p>-Piano terra</p>',
			maxWidth : '150'
		});
		infoWindowMt2.open(map, mt2);
		mt2.addListener('click', function() {
			infoWindowMt2.open(map, mt2);
		});
		break;
	case "MT3":
		var mt3 = new google.maps.Marker({
			position : {
				lat : 39.3633179,
				lng : 16.2262171
			},
			map : map,
			icon : image

		});

		var infoWindowMt3 = new google.maps.InfoWindow({
			content : '<h4 align="left">MT3</h4> <p>-Piano terra</p>',
			maxWidth : '150'
		});
		infoWindowMt3.open(map, mt3);
		mt3.addListener('click', function() {
			infoWindowMt3.open(map, mt3);
		});
		break;
	case "MT4":
		var mt4 = new google.maps.Marker({
			position : {
				lat : 39.3631086,
				lng : 16.2262513
			},
			map : map,
			icon : image

		});

		var infoWindowMt4 = new google.maps.InfoWindow({
			content : '<h4 align="left">MT4</h4> <p>-Piano terra</p>',
			maxWidth : '150'
		});
		infoWindowMt4.open(map, mt4);
		mt4.addListener('click', function() {
			infoWindowMt4.open(map, mt4);
		});
		break;
	case "MT6":
		var mt6 = new google.maps.Marker({
			position : {
				lat : 39.3630931,
				lng : 16.2260729
			},
			map : map,
			icon : image

		});

		var infoWindowMt6 = new google.maps.InfoWindow({
			content : '<h4 align="left">MT6</h4><p>-Piano terra</p> ',
			maxWidth : '150'
		});
		infoWindowMt6.open(map, mt6);

		mt6.addListener('click', function() {
			infoWindowMt6.open(map, mt6);
		});
		break;
	case "MT8":
		var mt8 = new google.maps.Marker({
			position : {
				lat : 39.3631957,
				lng : 16.2260595
			},
			map : map,
			icon : image

		});

		var infoWindowMt8 = new google.maps.InfoWindow({
			content : '<h4 align="left">MT8</h4><p>-Piano terra</p>  ',
			maxWidth : '150'
		});
		infoWindowMt8.open(map, mt8);
		mt8.addListener('click', function() {
			infoWindowMt8.open(map, mt8);
		});
		break;
	case "MT13":
		var mt13 = new google.maps.Marker({
			position : {
				lat : 39.3633258,
				lng : 16.2262284
			},
			map : map,
			icon : image

		});

		var infoWindowMt13 = new google.maps.InfoWindow({
			content : '<h4 align="left">MT13</h4><p>-Primo piano</p> ',
			maxWidth : '150'
		});
		infoWindowMt13.open(map, mt13);
		mt13.addListener('click', function() {
			infoWindowMt13.open(map, mt13);
		});
		break;
	case "MT14":
		var mt14 = new google.maps.Marker({
			position : {
				lat : 39.3634113,
				lng : 16.2262128
			},
			map : map,
			icon : image

		});

		var infoWindowMt14 = new google.maps.InfoWindow({
			content : '<h4 align="left">MT14</h4><p>-Primo piano</p> ',
			maxWidth : '150'
		});
		infoWindowMt14.open(map, mt14);
		mt14.addListener('click', function() {
			infoWindowMt14.open(map, mt14);
		});
		break;
	case "MT15":
		var mt15 = new google.maps.Marker({
			position : {
				lat : 39.3633529,
				lng : 16.2264763
			},
			map : map,
			icon : image

		});

		var infoWindowMt15 = new google.maps.InfoWindow({
			content : '<h4 align="left">MT15</h4><p>-Primo piano</p> ',
			maxWidth : '150'
		});
		infoWindowMt15.open(map, mt15);
		mt15.addListener('click', function() {
			infoWindowMt15.open(map, mt15);
		});
		break;
	case "Laboratorio31B":
		var lab = new google.maps.Marker({
			position : {
				lat : 39.3633622,
				lng : 16.2260138
			},
			map : map,
			icon : image

		});

		var infoWindowLab = new google.maps.InfoWindow({
			content : '<h4 align="left">Laboratorio 31 B</h4><p>-Primo piano</p> ',
			maxWidth : '150'
		});
		infoWindowLab.open(map, lab);
		lab.addListener('click', function() {
			infoWindowLab.open(map, lab);
		});
		break;
	default:
		break;
	}
	$('html,body').animate({
		scrollTop : 0
	}, 'slow');
}

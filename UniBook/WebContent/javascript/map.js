function initMap() {

	// Map options
	var options = {
		zoom : 12,
		center : {
			lat : 39.36589,
			lng : 16.225432
		}
	}
	// New Map
	var map = new google.maps.Map(document.getElementById('map'), options);

	// add Marker
	var marker = new google.maps.Marker({
		position : {
			lat : 39.36589,
			lng : 16.225432
		},
		map : map,

	});

	var infoWindow = new google.maps.InfoWindow({
		content : '<h4 align="left">Unical</h4> <img style="width:100%;" src="images/unical.jpg"></img><h4> <a href="http://www.unical.it/portale"><span>www.unical.it</span></a></h4>',
		maxWidth: '150'
	});
	marker.addListener('click', function() {
		infoWindow.open(map, marker);
	});

}
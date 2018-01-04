function uploadImage() {
	var data = new FormData();
	data.append('request', "profile");
	data.append('image', $("input[type=file]")[0].files[0]);
};
function date() {
	$('#date').click(function() {
		$('#date').bootstrapMaterialDatePicker('setDate', moment());
	});
};
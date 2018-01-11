$(document).ready(init);
function init() {
	$(document)
			.ready(
					function() {
						var date = new Date();
						var d = date.getDate();
						var m = date.getMonth();
						var y = date.getFullYear();

						/*
						 * className colors
						 * 
						 * className: default(transparent), important(red),
						 * chill(pink), success(green), info(blue)
						 * 
						 */

						/*
						 * initialize the external events
						 * -----------------------------------------------------------------
						 */

						$('#external-events div.external-event').each(
								function() {

									// create an Event Object
									// (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
									// it doesn't need to have a start or end
									var eventObject = {
										title : $.trim($(this).text())
									// use the element's text as the event title
									};

									// store the Event Object in the DOM element
									// so we can get to it later
									$(this).data('eventObject', eventObject);

									// make the event draggable using jQuery UI
									$(this).draggable({
										zIndex : 999,
										revert : true, // will cause the event
										// to go back to its
										revertDuration : 0
									// original position after the drag
									});

								});

						/*
						 * initialize the calendar
						 * -----------------------------------------------------------------
						 */

						var calendar = $('#calendar')
								.fullCalendar(
										{
											header : {
												left : 'title',
												center : 'agendaDay,agendaWeek,month',
												right : 'prev,next today'
											},
											editable : true,
											firstDay : 1, // 1(Monday) this
											// can be changed to
											// 0(Sunday) for the
											// USA system
											selectable : true,
											defaultView : 'month',

											axisFormat : 'h:mm',
											columnFormat : {
												month : 'ddd', // Mon
												week : 'ddd d', // Mon 7
												day : 'dddd M/d', // Monday
												// 9/7
												agendaDay : 'dddd d'
											},
											titleFormat : {
												month : 'MMMM yyyy', // September
												// 2009
												week : "MMMM yyyy", // September
												// 2009
												day : 'MMMM yyyy' // Tuesday,
											// Sep 8,
											// 2009
											},
											allDaySlot : false,
											selectHelper : true,
											select : function(start, end,
													allDay) {
												swal(
														{
															title : "Crea un Evento!",
															text : "Scrivi il nome dell'evento:",
															type : "input",
															showCancelButton : true,
															closeOnConfirm : false,
															inputPlaceholder : "Nome Evento"
														},
														function(inputValue) {
															if (inputValue) {
																if (inputValue === false)
																	return false;
																if (inputValue === "") {
																	swal
																			.showInputError("Scrivi qualcosa per memorizzare il tuo Evento!");
																	return false
																}
																swal(
																		"Ben Fatto!",
																		"Evento creato con successo!",
																		"success");

																calendar
																		.fullCalendar(
																				'renderEvent',
																				{
																					title : inputValue,
																					start : start,
																					end : end,
																					allDay : allDay
																				},
																				true // make
																		// the
																		// event
																		// "stick"
																		);
															}
															calendar
																	.fullCalendar('unselect');
														});
											},
											droppable : true, // this allows
											// things to be
											// dropped onto
											// the calendar
											// !!!
											drop : function(date, allDay) { // this
												// function
												// is
												// called
												// when
												// something
												// is
												// dropped

												// retrieve the dropped
												// element's stored Event Object
												var originalEventObject = $(
														this).data(
														'eventObject');

												// we need to copy it, so that
												// multiple events don't have a
												// reference to the same object
												var copiedEventObject = $
														.extend({},
																originalEventObject);

												// assign it the date that was
												// reported
												copiedEventObject.start = date;
												copiedEventObject.allDay = allDay;

												// render the event on the
												// calendar
												// the last `true` argument
												// determines if the event
												// "sticks"
												// (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
												$('#calendar')
														.fullCalendar(
																'renderEvent',
																copiedEventObject,
																true);

												// is the "remove after drop"
												// checkbox checked?
												if ($('#drop-remove').is(
														':checked')) {
													// if so, remove the element
													// from the "Draggable
													// Events" list
													$(this).remove();
												}

											},
											events : getEvent()
//												function(data) {
//												$.ajax({
//													type : "POST",
//													url : "calendarManager",
//													datatype : 'text',
//													data : {
//														matricola : "555",
//														request : "Eventi"
//													},
//													success : function(data) {
//														for (var i = 0; i < data.result.length; i++) 
//															[{
//																title:'Lezione',
//																start: new Date(result[i].anno,result[i].mese,result[i].giorno),
//																end: new Date(result[i].anno,result[i].mese,result[i].giorno),
//																className:'success'
//															}],
//													}
//												});
//
//											};
										
										// [ {
										// title : 'Click for Google',
										// start : new Date(y, m, 28),
										// end : new Date(y, m, 29),
										// url : 'http://google.com/',
										// className : 'success'
										// } ]
										// ,
										});

					});
};


function getEvent(){
	var events=[];
	
	$.ajax({
		type : "POST",
		url : "calendarManager",
		async: false,
		datatype : 'text',
		data : {
			matricola : "555",
			request : "Eventi"
		},
		success : function(data) {
			for (var i = 0; i < data.result.length; i++){
				var event={
					title: data.result[i].title,
					start: new Date(data.result[i].anno,data.result[i].mese-1 , data.result[i].giorno),
					end: new Date(data.result[i].anno,data.result[i].mese-1 , data.result[i].giorno),
					className: 'success'
			};
				events.push(event);
				console.log(events.length)
				
			}
			
		}
	});
	
	
return events;	
}







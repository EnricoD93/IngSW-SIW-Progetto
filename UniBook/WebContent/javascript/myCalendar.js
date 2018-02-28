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
												if ($('#ruolo').val() == 1) {
													if (document
															.getElementById('lezione').checked == 1
															&& $('#corsoid')
																	.val() == "") {
														swal(
																"Non hai selezionato il corso",
																"Seleziona un corso per poter salvare la lezione",
																"info");
														return;
													}
													if (document
															.getElementById('lezione').checked == 1
															&& $('#aulaid')
																	.val() == "") {
														swal(
																"Non hai selezionato l'aula",
																"Seleziona un'aula per poter salvare la lezione",
																"info");
														return;
													}
												}
												swal(
														{
															title : "Crea un Evento!",
															text : "Scrivi il nome dell'evento:",
															content: {
															    element: "input",
															    attributes: {
															      placeholder: "Nome Evento",
															      type: "text",
															    }}
														}).then((inputValue)=>{
															if (inputValue) {
																if (inputValue === false)
																	return false;
																if (inputValue === "") {
																	swal
																			.showInputError("Scrivi qualcosa per memorizzare il tuo Evento!");
																	return false
																}
																if ($('#ruolo')
																		.val() == 1) {
																	$
																			.ajax({
																				type : "POST",
																				url : "calendarManager",
																				async : false,
																				datatype : 'text',
																				data : {
																					matricola : $(
																							'#currentUser')
																							.val(),
																					title : inputValue,
																					request : "creaEvento",
																					start : start
																							.getTime(),
																					end : end
																							.getTime(),
																					lezione : document
																							.getElementById('lezione').checked,
																					evento : document
																							.getElementById('evento').checked,
																					corso : $(
																							'#corsoid')
																							.val(),
																					aula : $(
																							'#aulaid')
																							.val(),
																					oraInizio: $('#oraInizio').val(),
																					
																					oraFine: $('#oraFine').val()
																				},success: function(data){
																					id: data.id
																				}
																			})
																} else {
																	$
																			.ajax({
																				type : "POST",
																				url : "calendarManager",
																				async : false,
																				datatype : 'text',
																				data : {
																					matricola : $(
																							'#currentUser')
																							.val(),
																					title : inputValue,
																					request : "creaEvento",
																					start : start
																							.getTime(),
																					end : end
																							.getTime(),
																					lezione : false,
																					evento : false
																				
																				},success: function(data){
																					id: data.id
																				}
																			});
																}
																swal(
																		"Ben Fatto!",
																		"Evento creato con successo!",
																		"success").then(()=>{window.location.href="calendarManager?request=Eventi"});
																calendar
																		.fullCalendar(
																				'renderEvent',
																				{
																					title : inputValue,
																					start : start,
																					end : end,
																					allDay : allDay,
																					id:id
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
										// function(){
										// var events=getEvent();
										// console.log(events);
										// for(var i=0;i<events.length; i++){
										// console.log(events[i].start+"anno");
										// [{
										// title: events[i].title,
										// start:events[i].start,
										// end: events[i].end,
										// className: 'success'
										// }]

										// }}

										// getEvent()
										// function(data) {
										// $.ajax({
										// type : "POST",
										// url : "calendarManager",
										// datatype : 'text',
										// data : {
										// matricola : "555",
										// request : "Eventi"
										// },
										// success : function(data) {
										// for (var i = 0; i <
										// data.result.length; i++)
										// [{
										// title:'Lezione',
										// start: new
										// Date(result[i].anno,result[i].mese,result[i].giorno),
										// end: new
										// Date(result[i].anno,result[i].mese,result[i].giorno),
										// className:'success'
										// }],
										// }
										// });
										//
										// };

										// [ {
										// title : 'Click for Google',
										// start : new Date(y, m, 28),
										// end : new Date(y, m, 29),
										// url : 'http://google.com/',
										// className : 'success'
										// } ]

										});

					});
};

function getEvent() {
	var events = [];

	$.ajax({
		type : "POST",
		url : "calendarManager",
		async : false,
		datatype : 'text',
		data : {
			matricola : $('#currentUser').val(),
			request : "Eventi"
		},
		success : function(data) {
			for (var i = 0; i < data.result.length; i++) {

				var event = {
					title : data.result[i].title,
					start : new Date(data.result[i].annoIn,
							data.result[i].meseIn, data.result[i].giornoIn),
					end : new Date(data.result[i].annoFi,
							data.result[i].meseFi, data.result[i].giornoFi),
					className : 'success',
					id : data.result[i].id

				};
				events.push(event);

			}

		}
	});

	return events;
}
function lessonVerify() {
	var evento = document.getElementById('evento').checked;
	var lezione = document.getElementById('lezione').checked;
	if (lezione == 1) {
		$('#evento').prop('checked', false);
		$('#corsi').removeClass("hidden");
	} else {
		$('#corsi').addClass("hidden");
	}
};
function eventVerify() {
	var evento = document.getElementById('evento').checked;
	var lezione = document.getElementById('lezione').checked;
	if (lezione == 1 && evento == 1) {
		$('#lezione').prop('checked', false);
		$('#corsi').addClass("hidden");
	}
};
function rimuoviEvento(id) {
	var ev="#idevento"+id;
	swal({
		  title: "Sei sicuro che vuoi eliminare l'evento selezionato?",
		  text: "Una volta eliminato non puoi più recuperarlo!",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		})
		.then((willDelete) => {
		  if (willDelete) {
			  $.ajax({
					type : "POST",
					url : "calendarManager",
					async : false,
					datatype : 'text',
					data : {
						matricola : $('#currentUser').val(),
						request : "rimuoviEvento",
						id : $(ev).val()
					},
					success : function(data) {
					}
					
				});
		    swal("Evento Rimosso", "Evento eliminato correttamente dal calendario", {
		      icon: "success",
		    }).then(()=>{window.location.href="calendarManager?request=Eventi"});
		  } 
		});
	
};


class Client
	constructor: (@container, @content) ->

	run: ->
		@setUpListeners()

	setUpListeners: =>
		document.getElementById("createFlight").addEventListener  'click', @showFlightForm, false
		document.getElementById("listFlights").addEventListener  'click', @showFlightsList, false
		document.getElementById("createDestination").addEventListener  'click', @showDestinationForm, false
		document.getElementById("listDestinations").addEventListener  'click', @showDestinationsList, false
		document.getElementById("createReservation").addEventListener  'click', @showReservationForm, false
		document.getElementById("listReservations").addEventListener  'click', @showReservationsList, false

	showFlightForm: (e) =>
		form = new FlightForm @content

	showFlightsList: (e) =>
		list = new FlightList @content

	showDestinationForm: (e) =>
		form = new DestinationForm @content

	showDestinationsList: (e) =>
		list = new DestinationList @content

	showReservationForm: (e) =>
		form = new ReservationForm @content

	showReservationsList: (e) =>
		list = new ReservationList @content

class FlightForm
	constructor: (@content) ->
		$(@content).empty()
		@createForm()
		$('form').submit @sendForm

	createForm: =>
		form = document.createElement "form"
		form.method = "post"
		form.action = "#"
		table = document.createElement "table"
		tbody = document.createElement "tbody"
		tbody.innerHTML = @getForm()
		table.appendChild(tbody);
		form.appendChild(table);
		@content.appendChild(form);
		return form

	sendForm: (e) =>
		e.preventDefault()
		ar = $(e.target).serializeArray()
		@data = {}
		@data[ar[0].name] = ar[0].value
		@data[ar[1].name] = ar[1].value
		@data[ar[2].name] = Number(ar[2].value)
		@data[ar[3].name] = Number(ar[3].value)
		@data[ar[4].name] = Number(ar[4].value)
		@data[ar[5].name] = Number(ar[5].value)
		@data[ar[6].name] = Number(ar[6].value)
		@data["url"] = ""

		$.ajax({
			url: 'http://localhost:8080/airline/rest/flight',
			type: 'POST',
			dataType: 'json',
			contentType: 'application/json',
			headers: {
				Authorization: 'YWRtaW46YWRtaW4='
			},
			data: JSON.stringify(@data)
		})

		$(@content).empty()

	getForm: ->
		"<tr>
			<th>Name: </th>
			<td><input type=\"text\" name=\"name\" id=\"name\"></td>
		</tr>
		<tr>
			<th>Date of departure: </th>
			<td><input type=\"text\" name=\"dateOfDeparture\" id=\"dateOfdeparture\"></td>
		</tr>
		<tr>
			<th>Distance: </th>
			<td><input type=\"text\" name=\"distance\" id=\"distance\"></td>
		</tr>
		<tr>
			<th>Seats: </th>
			<td><input type=\"number\" name=\"seats\" id=\"seats\"></td>
		</tr>

		<tr>
			<th>Price: </th>
			<td><input type=\"text\" name=\"price\" id=\"price\"></td>
		</tr>
		<tr>
			<th>From destination: </th>
			<td><input type=\"text\" name=\"from\" id=\"from\"></td>
		</tr>
		<tr>
			<th>To destination: </th>
			<td><input type=\"text\" name=\"to\" id=\"to\"></td>
		</tr>
		<tr>
			<th></th>
			<td><input type=\"submit\" value=\"Create\"></td>
		</tr>";

class DestinationForm
	constructor: (@content) ->
		$(@content).empty()
		@createForm()
		$('form').submit @sendForm

	createForm: =>
		form = document.createElement "form"
		form.method = "post"
		form.action = "#"
		table = document.createElement "table"
		tbody = document.createElement "tbody"
		tbody.innerHTML = @getForm()
		table.appendChild(tbody);
		form.appendChild(table);
		@content.appendChild(form);
		return form

	sendForm: (e) =>
		e.preventDefault()
		ar = $(e.target).serializeArray()
		@data = {}
		@data[ar[0].name] = ar[0].value
		@data[ar[1].name] = Number(ar[1].value)
		@data[ar[2].name] = Number(ar[2].value)
		@data["url"] = ""

		$.ajax({
			url: 'http://localhost:8080/airline/rest/destination',
			type: 'POST',
			dataType: 'json',
			contentType: 'application/json',
			headers: {
				Authorization: 'YWRtaW46YWRtaW4='
			},
			data: JSON.stringify(@data)
		})

		$(@content).empty()

	getForm: ->
		"<tr>
			<th>Name: </th>
			<td><input type=\"text\" name=\"name\" id=\"name\"></td>
		</tr>
		<tr>
			<th>Latitude: </th>
			<td><input type=\"text\" name=\"latitude\" id=\"latitude\"></td>
		</tr>
		<tr>
			<th>Longitude: </th>
			<td><input type=\"text\" name=\"longitude\" id=\"longitude\"></td>
		</tr>
		<tr>
			<th></th>
			<td><input type=\"submit\" value=\"Create\"></td>
		</tr>";

class ReservationForm
	constructor: (@content) ->
		$(@content).empty()
		@createForm()
		$('form').submit @sendForm

	createForm: =>
		form = document.createElement "form"
		form.method = "post"
		form.action = "#"
		table = document.createElement "table"
		tbody = document.createElement "tbody"
		tbody.innerHTML = @getForm()
		table.appendChild(tbody);
		form.appendChild(table);
		@content.appendChild(form);
		return form

	sendForm: (e) =>
		e.preventDefault()
		ar = $(e.target).serializeArray()
		@data = {}
		@data[ar[0].name] = ar[0].value
		@data[ar[1].name] = ar[1].value
		@data[ar[2].name] = ar[2].value
		@data[ar[3].name] = Number(ar[3].value)
		@data["url"] = ""
		@data["state"] = "NEW"

		$.ajax({
			url: 'http://localhost:8080/airline/rest/reservation',
			type: 'POST',
			dataType: 'json',
			contentType: 'application/json',
			data: JSON.stringify(@data)
		})

		$(@content).empty()

	getForm: ->
		"<tr>
			<th>Flight: </th>
			<td><input type=\"text\" name=\"flight\" id=\"flight\"></td>
		</tr>
		<tr>
			<th>Created: </th>
			<td><input type=\"text\" name=\"created\" id=\"created\"></td>
		</tr>
		<tr>
			<th>Password: </th>
			<td><input type=\"text\" name=\"password\" id=\"password\"></td>
		</tr>
		<tr>
			<th>Seats: </th>
			<td><input type=\"number\" name=\"seats\" id=\"seats\"></td>
		</tr>
		<tr>
			<th></th>
			<td><input type=\"submit\" value=\"Create\"></td>
		</tr>";

class FlightList
	constructor: (@content) ->
		$(@content).empty()
		$.ajax({
			url: 'http://localhost:8080/airline/rest/flight',
			type: 'GET',
			dataType: 'json',
			headers: {
				Authorization: 'YWRtaW46YWRtaW4='
			}
		}).done(@printList);

	printList: (data, status, request) =>
		table = document.createElement "table"
		thead = document.createElement "thead"
		row = document.createElement "tr"
		row.innerHTML =
					"<th>ID</th>" +
					"<th>Name</th>" +
					"<th>Date of departure</th>" +
					"<th>Distance</th>" +
					"<th>Seats</th>" +
					"<th>Price</th>" +
					"<th>From</th>" +
					"<th>To</th>" +
					"<th>URL</th>" +
					"<th>Action</th>";
		thead.appendChild(row);
		table.appendChild(thead);
		@tbody = document.createElement "tbody"
		table.appendChild(@tbody);
		@content.appendChild(table);
		for flight in data
			do (flight) =>
				row = document.createElement "tr"
				row.innerHTML =
					"<td>" + flight.id + "</td>" +
					"<td>" + flight.name + "</td>" +
					"<td>" + flight.dateOfDeparture + "</td>" +
					"<td>" + flight.distance + "</td>" +
					"<td>" + flight.seats + "</td>" +
					"<td>" + flight.price + "</td>" +
					"<td>" + flight.from + "</td>" +
					"<td>" + flight.to + "</td>" +
					"<td>" + flight.url + "</td>" +
							"<td><a id=\"update" + flight.id + "\" data-flight-id=\"" + flight.id + "\" href=\"#\">U</a><a id=\"delete" + flight.id + "\" data-flight-id=\"" + flight.id + "\" href=\"#\">D</a></td>";
				@tbody.appendChild(row);
				document.getElementById("update" + flight.id).addEventListener  'click', @updateData, false
				document.getElementById("delete" + flight.id).addEventListener  'click', @deleteData, false

	deleteData: (e) =>
		id = document.getElementById(e.target.id).getAttribute "data-flight-id"
		$.ajax({
			url: 'http://localhost:8080/airline/rest/flight/' + id,
			type: 'DELETE',
			dataType: 'json',
			contentType: 'application/json',
			headers: {
				Authorization: 'YWRtaW46YWRtaW4='
			}
		})

		setTimeout (->), 1000
		$(@content).empty()
		$.ajax({
			url: 'http://localhost:8080/airline/rest/flight',
			type: 'GET',
			dataType: 'json',
			headers: {
				Authorization: 'YWRtaW46YWRtaW4='
			}
		}).done(@printList);

	updateData: (e) =>
		@id = document.getElementById(e.target.id).getAttribute "data-flight-id"
		$(@content).empty()
		$.ajax({
			url: 'http://localhost:8080/airline/rest/flight/' + @id,
			type: 'GET',
			dataType: 'json',
			headers: {
				Authorization: 'YWRtaW46YWRtaW4='
			}
		}).done(@createForm);

	createForm: (data) =>
		form = document.createElement "form"
		form.method = "post"
		form.action = "#"
		table = document.createElement "table"
		tbody = document.createElement "tbody"
		tbody.innerHTML = @getForm data
		table.appendChild(tbody);
		form.appendChild(table);
		@content.appendChild(form);
		$('form').submit @sendForm

	sendForm: (e) =>
		e.preventDefault()
		ar = $(e.target).serializeArray()
		@data = {}
		@data[ar[0].name] = ar[0].value
		@data[ar[1].name] = ar[1].value
		@data[ar[2].name] = Number(ar[2].value)
		@data[ar[3].name] = Number(ar[3].value)
		@data[ar[4].name] = Number(ar[4].value)
		@data[ar[5].name] = Number(ar[5].value)
		@data[ar[6].name] = Number(ar[6].value)
		@data["url"] = ""

		$.ajax({
			url: 'http://localhost:8080/airline/rest/flight/' + @id,
			type: 'PUT',
			dataType: 'json',
			contentType: 'application/json',
			headers: {
				Authorization: 'YWRtaW46YWRtaW4='
			},
			data: JSON.stringify(@data)
		})

		$(@content).empty()

	getForm: (data) ->
		"<tr>
			<th>Name: </th>
			<td><input type=\"text\" name=\"name\" id=\"name\" value=\"" + data.name + "\"></td>
		</tr>
		<tr>
			<th>Date of departure: </th>
			<td><input type=\"text\" name=\"dateOfDeparture\" id=\"dateOfDeparture\" value=\"" + data.dateOfDeparture + "\"></td>
		</tr>
		<tr>
			<th>Distance: </th>
			<td><input type=\"text\" name=\"distance\" id=\"distance\" value=\"" + data.distance + "\"></td>
		</tr>
		<tr>
			<th>Seats: </th>
			<td><input type=\"number\" name=\"seats\" id=\"seats\" value=\"" + data.seats + "\"></td>
		</tr>

		<tr>
			<th>Price: </th>
			<td><input type=\"text\" name=\"price\" id=\"price\" value=\"" + data.price + "\"></td>
		</tr>
		<tr>
			<th>From destination: </th>
			<td><input type=\"text\" name=\"from\" id=\"from\" value=\"" + data.from + "\"></td>
		</tr>
		<tr>
			<th>To destination: </th>
			<td><input type=\"text\" name=\"to\" id=\"to\" value=\"" + data.to + "\"></td>
		</tr>
		<tr>
			<th></th>
			<td><input type=\"submit\" value=\"Update\"></td>
		</tr>";

class DestinationList
	constructor: (@content) ->
		$(@content).empty()
		$.ajax({
			url: 'http://localhost:8080/airline/rest/destination',
			type: 'GET'
			dataType: 'json',
			headers: {
				Authorization: 'YWRtaW46YWRtaW4='
			}
		}).done(@printList);

	printList: (data, status, request) =>
		table = document.createElement "table"
		thead = document.createElement "thead"
		row = document.createElement "tr"
		row.innerHTML =
					"<th>ID</th>" +
					"<th>Name</th>" +
					"<th>Latitude</th>" +
					"<th>Longitude</th>" +
					"<th>URL</th>" +
					"<th>Action</th>";
		thead.appendChild(row);
		table.appendChild(thead);
		@tbody = document.createElement "tbody"
		table.appendChild(@tbody);
		@content.appendChild(table);
		for destination in data
			do (destination) =>
				row = document.createElement "tr"
				row.innerHTML =
					"<td>" + destination.id + "</td>" +
					"<td>" + destination.name + "</td>" +
					"<td>" + destination.latitude + "</td>" +
					"<td>" + destination.longitude + "</td>" +
					"<td>" + destination.url + "</td>" +
							"<td><a id=\"update" + destination.id + "\" data-destination-id=\"" + destination.id + "\" href=\"#\">U</a><a id=\"delete" + destination.id + "\" data-destination-id=\"" + destination.id + "\" href=\"#\">D</a></td>";
				@tbody.appendChild(row);
				document.getElementById("update" + destination.id).addEventListener  'click', @updateData, false
				document.getElementById("delete" + destination.id).addEventListener  'click', @deleteData, false

	deleteData: (e) =>
		id = document.getElementById(e.target.id).getAttribute "data-destination-id"
		$.ajax({
			url: 'http://localhost:8080/airline/rest/destination/' + id,
			type: 'DELETE',
			dataType: 'json',
			contentType: 'application/json',
			headers: {
				Authorization: 'YWRtaW46YWRtaW4='
			}
		})

		setTimeout (->), 1000
		$(@content).empty()
		$.ajax({
			url: 'http://localhost:8080/airline/rest/destination',
			type: 'GET'
			dataType: 'json',
			headers: {
				Authorization: 'YWRtaW46YWRtaW4='
			}
		}).done(@printList);

	updateData: (e) =>
		@id = document.getElementById(e.target.id).getAttribute "data-destination-id"
		$(@content).empty()
		$.ajax({
			url: 'http://localhost:8080/airline/rest/destination/' + @id,
			type: 'GET',
			dataType: 'json',
			headers: {
				Authorization: 'YWRtaW46YWRtaW4='
			}
		}).done(@createForm);

	createForm: (data) =>
		form = document.createElement "form"
		form.method = "post"
		form.action = "#"
		table = document.createElement "table"
		tbody = document.createElement "tbody"
		tbody.innerHTML = @getForm data
		table.appendChild(tbody);
		form.appendChild(table);
		@content.appendChild(form);
		$('form').submit @sendForm

	sendForm: (e) =>
		e.preventDefault()
		ar = $(e.target).serializeArray()
		@data = {}
		@data[ar[0].name] = ar[0].value
		@data[ar[1].name] = Number(ar[1].value)
		@data[ar[2].name] = Number(ar[2].value)
		@data["url"] = ""

		$.ajax({
			url: 'http://localhost:8080/airline/rest/destination/' + @id,
			type: 'PUT',
			dataType: 'json',
			contentType: 'application/json',
			data: JSON.stringify(@data)
		})

		$(@content).empty()

	getForm: (data) ->
		"<tr>
			<th>Name: </th>
			<td><input type=\"text\" name=\"name\" id=\"name\" value=\"" + data.name + "\"></td>
		</tr>
		<tr>
			<th>Latitude: </th>
			<td><input type=\"text\" name=\"latitude\" id=\"latitude\" value=\"" + data.latitude + "\"></td>
		</tr>
		<tr>
			<th>Longitude: </th>
			<td><input type=\"text\" name=\"longitude\" id=\"longitude\" value=\"" + data.longitude + "\"></td>
		</tr>
		<tr>
			<th></th>
			<td><input type=\"submit\" value=\"Update\"></td>
		</tr>";

class ReservationList
	constructor: (@content) ->
		$(@content).empty()
		$.ajax({
			url: 'http://localhost:8080/airline/rest/reservation',
			type: 'GET'
			dataType: 'json'
		}).done(@printList);

	printList: (data, status, request) =>
		table = document.createElement "table"
		thead = document.createElement "thead"
		row = document.createElement "tr"
		row.innerHTML =
					"<th>ID</th>" +
					"<th>Flight</th>" +
					"<th>Created</th>" +
					"<th>Password</th>" +
					"<th>Seats</th>" +
					"<th>State</th>" +
					"<th>URL</th>" +
					"<th>Action</th>";
		thead.appendChild(row);
		table.appendChild(thead);
		@tbody = document.createElement "tbody"
		table.appendChild(@tbody);
		@content.appendChild(table);
		for reservation in data
			do (reservation) =>
				row = document.createElement "tr"
				row.innerHTML =
					"<td>" + reservation.id + "</td>" +
					"<td>" + reservation.flight + "</td>" +
					"<td>" + reservation.created + "</td>" +
					"<td>" + reservation.password + "</td>" +
					"<td>" + reservation.seats + "</td>" +
					"<td>" + reservation.state + "</td>" +
					"<td>" + reservation.url + "</td>" +
					"<td><a id=\"update" + reservation.id + "\" data-reservation-id=\"" + reservation.id + "\" href=\"#\">U</a><a id=\"delete" + reservation.id + "\" data-reservation-id=\"" + reservation.id + "\" href=\"#\">D</a></td>";
				@tbody.appendChild(row);
				document.getElementById("update" + reservation.id).addEventListener  'click', @updateData, false
				document.getElementById("delete" + reservation.id).addEventListener  'click', @deleteData, false

	deleteData: (e) =>
		id = document.getElementById(e.target.id).getAttribute "data-reservation-id"
		$.ajax({
			url: 'http://localhost:8080/airline/rest/reservation/' + id,
			type: 'DELETE',
			dataType: 'json',
			contentType: 'application/json'
		})

		setTimeout (->), 1000
		$(@content).empty()
		$.ajax({
			url: 'http://localhost:8080/airline/rest/reservation',
			type: 'GET'
			dataType: 'json'
		}).done(@printList);

	updateData: (e) =>
		@id = document.getElementById(e.target.id).getAttribute "data-reservation-id"
		$(@content).empty()
		$.ajax({
			url: 'http://localhost:8080/airline/rest/reservation/' + @id,
			type: 'GET',
			dataType: 'json'
		}).done(@createForm);

	createForm: (data) =>
		form = document.createElement "form"
		form.method = "post"
		form.action = "#"
		table = document.createElement "table"
		tbody = document.createElement "tbody"
		tbody.innerHTML = @getForm data
		table.appendChild(tbody);
		form.appendChild(table);
		@content.appendChild(form);
		$('form').submit @sendForm

	sendForm: (e) =>
		e.preventDefault()
		ar = $(e.target).serializeArray()
		@data = {}
		@data[ar[0].name] = ar[0].value
		@data[ar[1].name] = ar[1].value
		@data[ar[2].name] = ar[2].value
		@data[ar[3].name] = Number(ar[3].value)
		@data["url"] = ""
		@data["state"] = "NEW"

		$.ajax({
			url: 'http://localhost:8080/airline/rest/reservation/' + @id,
			type: 'PUT',
			dataType: 'json',
			contentType: 'application/json',
			data: JSON.stringify(@data)
		})

		$(@content).empty()

	getForm: (data) ->
		"<tr>
			<th>Flight: </th>
			<td><input type=\"text\" name=\"flight\" id=\"flight\" value=\"" + data.flight + "\"></td>
		</tr>
		<tr>
			<th>Created: </th>
			<td><input type=\"text\" name=\"created\" id=\"created\" value=\"" + data.created + "\"></td>
		</tr>
		<tr>
			<th>Password: </th>
			<td><input type=\"text\" name=\"password\" id=\"password\" value=\"" + data.password + "\"></td>
		</tr>
		<tr>
			<th>Seats: </th>
			<td><input type=\"number\" name=\"seats\" id=\"seats\" value=\"" + data.seats + "\"></td>
		</tr>
		<tr>
			<th></th>
			<td><input type=\"submit\" value=\"Update\"></td>
		</tr>";


client = new Client(document.getElementById("container"), document.getElementById("content"));
client.run();
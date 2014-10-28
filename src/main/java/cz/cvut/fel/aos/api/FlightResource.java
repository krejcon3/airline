package cz.cvut.fel.aos.api;

import cz.cvut.fel.aos.api.data.Flight;
import cz.cvut.fel.aos.persistence.PersistenceException;
import cz.cvut.fel.aos.service.FlightService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Created by krejcir on 27.10.14.
 */
@Path(value = "flight")
public class FlightResource {

	protected FlightService service;

	public FlightResource() {
		this.service = new FlightService();
	}

	@GET
	@Path("/")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getAll() {
		ArrayList<Flight> list = this.service.find();
		Response.ResponseBuilder builder = Response.ok(list);
		return builder.build();
	}

	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Flight getData(@PathParam("id") Long id) {
		return this.service.find(id);
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveFlight(Flight data) {
		try {
			this.service.create(data);
			return Response.status(Response.Status.OK).entity("Flight " + data.getId() + " created.").type(MediaType.APPLICATION_JSON).build();
		} catch(PersistenceException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
	}

	@PUT
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response updateData(@PathParam("id") Long id, Flight data) {
		try {
			this.service.update(id, data);
			return Response.status(Response.Status.OK).entity("Flight " + data.getId() + " updated.").type(MediaType.APPLICATION_JSON).build();
		} catch(PersistenceException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
	}

	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteData(@PathParam("id") Long id) {
		try {
			this.service.delete(id);
			return Response.status(Response.Status.OK).entity("Flight " + id + " deleted.").type(MediaType.APPLICATION_JSON).build();
		} catch(PersistenceException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
	}
}

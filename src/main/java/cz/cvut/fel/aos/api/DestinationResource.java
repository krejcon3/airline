package cz.cvut.fel.aos.api;

import cz.cvut.fel.aos.api.data.Destination;
import cz.cvut.fel.aos.persistence.PersistenceException;
import cz.cvut.fel.aos.service.DestinationService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Created by krejcir on 27.10.14.
 */
@Path(value = "destination")
public class DestinationResource {

	protected DestinationService service;

	public DestinationResource() {
		this.service = new DestinationService();
	}

	@GET
	@Path("/")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getAll() {
		ArrayList<Destination> list = this.service.find();
		Response.ResponseBuilder builder = Response.ok(list);
		return builder.build();
	}

	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Destination getData(@PathParam("id") Long id) {
		return this.service.find(id);
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveDestination(Destination data) {
		try {
			this.service.create(data);
			return Response.status(Response.Status.OK).entity("Destination " + data.getId() + " created.").type(MediaType.APPLICATION_JSON).build();
		} catch(PersistenceException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
	}

	@PUT
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response updateData(@PathParam("id") Long id, Destination data) {
		try {
			this.service.update(id, data);
			return Response.status(Response.Status.OK).entity("Destination " + data.getId() + " updated.").type(MediaType.APPLICATION_JSON).build();
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
			return Response.status(Response.Status.OK).entity("Destination " + id + " deleted.").type(MediaType.APPLICATION_JSON).build();
		} catch(PersistenceException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
	}
}

package cz.cvut.fel.aos.api;

import cz.cvut.fel.aos.api.data.Destination;
import cz.cvut.fel.aos.persistence.PersistenceException;
import cz.cvut.fel.aos.service.DestinationService;
import cz.cvut.fel.aos.service.UserService;

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
	protected UserService userService;

	public DestinationResource() {
		this.service = new DestinationService();
		this.userService = new UserService();
	}

	@GET
	@Path("/")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getAll(@HeaderParam("Authorization") String authorization) {
		try {
			this.userService.authenticate(authorization);
			ArrayList<Destination> list = this.service.find();
			Response.ResponseBuilder builder = Response.ok(list);
			return builder.build();
		} catch (UnauthorizedException e) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
	}

	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getData(@HeaderParam("Authorization") String authorization, @PathParam("id") Long id) {
		try {
			this.userService.authenticate(authorization);
			Response.ResponseBuilder builder = Response.ok(this.service.find(id));
			return builder.build();
		} catch (UnauthorizedException e) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveDestination(@HeaderParam("Authorization") String authorization, Destination data) {
		try {
			this.userService.authenticate(authorization);
			this.service.create(data);
			return Response.status(Response.Status.OK).entity("Destination " + data.getId() + " created.").type(MediaType.APPLICATION_JSON).build();
		} catch(PersistenceException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		} catch (UnauthorizedException e) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
	}

	@PUT
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response updateData(@HeaderParam("Authorization") String authorization, @PathParam("id") Long id, Destination data) {
		try {
			this.userService.authenticate(authorization);
			this.service.update(id, data);
			return Response.status(Response.Status.OK).entity("Destination " + data.getId() + " updated.").type(MediaType.APPLICATION_JSON).build();
		} catch(PersistenceException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		} catch (UnauthorizedException e) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
	}

	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteData(@HeaderParam("Authorization") String authorization, @PathParam("id") Long id) {
		try {
			this.userService.authenticate(authorization);
			this.service.delete(id);
			return Response.status(Response.Status.OK).entity("Destination " + id + " deleted.").type(MediaType.APPLICATION_JSON).build();
		} catch(PersistenceException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		} catch (UnauthorizedException e) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
	}
}

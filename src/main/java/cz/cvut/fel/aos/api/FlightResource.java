package cz.cvut.fel.aos.api;

import cz.cvut.fel.aos.api.data.Flight;
import cz.cvut.fel.aos.persistence.PersistenceException;
import cz.cvut.fel.aos.service.FlightService;
import cz.cvut.fel.aos.service.ServiceException;
import cz.cvut.fel.aos.service.UserService;

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
	protected UserService userService;

	public FlightResource() {
		this.service = new FlightService();
		this.userService = new UserService();
	}

	@GET
	@Path("/")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getFlights(
		@HeaderParam("Authorization") String authorization,
		@HeaderParam("X-Filter") String filter,
		@HeaderParam("X-Base") String b,
		@HeaderParam("X-Offset") String of,
		@HeaderParam("X-Order") String order
	) {
		try {
			this.userService.authenticate(authorization);
		} catch (UnauthorizedException e) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}

		int limit = b == null ? 0 : Integer.parseInt(b);
		int offset = of == null ? 0 : Integer.parseInt(of);
		ArrayList<Flight> list;
		try {
			list = this.service.find(offset, limit, order, filter);
		} catch (PersistenceException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
		Response.ResponseBuilder builder = Response.ok(list);
		if (b == null) {
			builder.header("X-Count-records", list.size());
		}
		return builder.build();
	}

	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getFlight(@HeaderParam("Authorization") String authorization, @PathParam("id") Long id) {
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
	public Response saveFlight(@HeaderParam("Authorization") String authorization, Flight data) {
		try {
			this.userService.authenticate(authorization);
			this.service.create(data);
			return Response.status(Response.Status.OK).entity("Flight " + data.getId() + " created.").type(MediaType.APPLICATION_JSON).build();
		} catch(PersistenceException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		} catch(ServiceException e) {
			return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		} catch (UnauthorizedException e) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
	}

	@PUT
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response updateFlight(@HeaderParam("Authorization") String authorization, @PathParam("id") Long id, Flight data) {
		try {
			this.userService.authenticate(authorization);
			this.service.update(id, data);
			return Response.status(Response.Status.OK).entity("Flight " + data.getId() + " updated.").type(MediaType.APPLICATION_JSON).build();
		} catch(PersistenceException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		} catch(ServiceException e) {
			return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		} catch (UnauthorizedException e) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
	}

	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteFlight(@HeaderParam("Authorization") String authorization, @PathParam("id") Long id) {
		try {
			this.userService.authenticate(authorization);
			this.service.delete(id);
			return Response.status(Response.Status.OK).entity("Flight " + id + " deleted.").type(MediaType.APPLICATION_JSON).build();
		} catch(PersistenceException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		} catch (UnauthorizedException e) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
	}
}

package cz.cvut.fel.aos.api;

import cz.cvut.fel.aos.api.data.Destination;
import cz.cvut.fel.aos.persistence.PersistenceException;
import cz.cvut.fel.aos.service.DestinationService;
import cz.cvut.fel.aos.service.GeocodeService;
import cz.cvut.fel.aos.service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Class provides API for operations over destinations.
 *
 * @author Ondřej Krejčíř
 */
@Path(value = "destination")
public class DestinationResource {

	protected DestinationService service;
	protected UserService userService;
	protected GeocodeService geocodeService;

	public DestinationResource() {
		this.service = new DestinationService();
		this.userService = new UserService();
		this.geocodeService = new GeocodeService();
	}

	/**
	 * API method to get all destinations.
	 * [server]/destination/
	 *
	 * @param authorization authorization string
	 * @return Response
	 */
	@GET
	@Path("/")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getDestinations(@HeaderParam("Authorization") String authorization) {
		try {
			this.userService.authenticate(authorization);
			ArrayList<Destination> list = this.service.find();
			Response.ResponseBuilder builder = Response.ok(list);
			return builder.build();
		} catch (UnauthorizedException e) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
	}

	/**
	 * /**
	 * API method to get the one destination.
	 * [server]/destination/{id}
	 *
	 * @param authorization authorization string
	 * @param id identificator of the destination
	 * @return Response
	 */
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getDestination(@HeaderParam("Authorization") String authorization, @PathParam("id") Long id) {
		try {
			this.userService.authenticate(authorization);
			Response.ResponseBuilder builder = Response.ok(this.service.find(id));
			return builder.build();
		} catch (UnauthorizedException e) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
	}

	/**
	 * API method to create new destination
	 * [server]/destination/
	 *
	 * @param authorization authorization string
	 * @param data json object descripting the destination
	 * @return Response
	 */
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveDestination(@HeaderParam("Authorization") String authorization, Destination data) {
		try {
			this.userService.authenticate(authorization);
			double[] coordinates = this.geocodeService.getCoordinates(data.getName());
			if (coordinates == null) {
				return Response.status(Response.Status.BAD_REQUEST).entity("Location not found.").type(MediaType.APPLICATION_JSON).build();
			}
			data.setLatitude(coordinates[0]);
			data.setLongitude(coordinates[1]);
			this.service.create(data);
			return Response.status(Response.Status.OK).entity("Destination " + data.getId() + " created.").type(MediaType.APPLICATION_JSON).build();
		} catch(PersistenceException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		} catch (UnauthorizedException e) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
	}

	/**
	 * API method to update the destination
	 * [server]/destination/{id}
	 *
	 * @param authorization authorization string
	 * @param id identificator of the destination
	 * @param data json object descripting the destination
	 * @return Response
	 */
	@PUT
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response updateDestination(@HeaderParam("Authorization") String authorization, @PathParam("id") Long id, Destination data) {
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

	/**
	 * API method to delete the destination
	 * [server]/destination/{id}
	 *
	 * @param authorization authorization string
	 * @param id identificator of the destination
	 * @return Response
	 */
	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteDestination(@HeaderParam("Authorization") String authorization, @PathParam("id") Long id) {
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

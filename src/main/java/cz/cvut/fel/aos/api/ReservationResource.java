package cz.cvut.fel.aos.api;

import cz.cvut.fel.aos.api.data.Reservation;
import cz.cvut.fel.aos.persistence.PersistenceException;
import cz.cvut.fel.aos.service.ReservationService;
import cz.cvut.fel.aos.service.ServiceException;
import cz.cvut.fel.aos.service.UserService;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Class provides API for operations over reservations.
 *
 * @author Ondřej Krejčíř
 */
@Path(value = "reservation")
public class ReservationResource {

	protected ReservationService service;
	protected UserService userService;

	public ReservationResource() {
		this.service = new ReservationService();
		this.userService = new UserService();
	}

	/**
	 * API method to get all flights.
	 * [server]/flight/
	 *
	 * @param authorization authorization string
	 * @return Response
	 */
	@GET
	@Path("/")
	@Produces({MediaType.APPLICATION_JSON})
	@RolesAllowed({"manager", "admin"})
	public Response getAll(@HeaderParam("Authorization") String authorization) {
		try {
			this.userService.authenticate(authorization);
			ArrayList<Reservation> list = this.service.find();
			Response.ResponseBuilder builder = Response.ok(list);
			return builder.build();
		} catch (UnauthorizedException e) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
	}

	/**
	 * API method to get the flight.
	 * [server]/flight/{id}
	 *
	 * @param password password key to access the reservation
	 * @param id identificator of the reservation
	 * @return Response
	 */
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getReservation(@HeaderParam("X-Password") String password, @PathParam("id") Long id) {
		Reservation reservation = this.service.find(id);
		if (reservation == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Reservation not found.").type(MediaType.APPLICATION_JSON).build();
		}
		if (reservation.getPassword().equals(password)) {
			Response.ResponseBuilder builder = Response.ok(reservation);
			return builder.build();
		} else {
			return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid password.").type(MediaType.APPLICATION_JSON).build();
		}
	}

	/**
	 * API method to create new flight.
	 * [server]/flight/
	 *
	 * @param data json object descripting the reservation
	 * @return Response
	 */
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveReservation(Reservation data) {
		try {
			this.service.create(data);
			return Response.status(Response.Status.OK).entity("Reservation " + data.getId() + " created.").type(MediaType.APPLICATION_JSON).build();
		} catch(PersistenceException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		} catch(ServiceException e) {
			return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
	}

	/**
	 * API method to update the flight.
	 * [server]/flight/{id}
	 *
	 * @param password password key to access the reservation
	 * @param id identificator of the reservation
	 * @param data json object descripting the reservation
	 * @return Response
	 */
	@PUT
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response updateRservation(@HeaderParam("X-Password") String password, @PathParam("id") Long id, Reservation data) {
		Reservation reservation = this.service.find(id);
		if (reservation.getPassword().equals(password)) {
			return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid password.").type(MediaType.APPLICATION_JSON).build();
		}
		try {
			this.service.update(id, data);
			return Response.status(Response.Status.OK).entity("Reservation " + data.getId() + " updated.").type(MediaType.APPLICATION_JSON).build();
		} catch(PersistenceException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		} catch(ServiceException e) {
			return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
	}

	/**
	 * API method to delete the flight.
	 * [server]/flight/{id}
	 *
	 * @param authorization authorization string
	 * @param id identificator of the reservation
	 * @return Response
	 */
	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed({"manager", "admin"})
	public Response deleteData(@HeaderParam("Authorization") String authorization, @PathParam("id") Long id) {
		try {
			this.userService.authenticate(authorization);
			this.service.delete(id);
			return Response.status(Response.Status.OK).entity("Reservation " + id + " deleted.").type(MediaType.APPLICATION_JSON).build();
		} catch(PersistenceException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		} catch (UnauthorizedException e) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
	}
}

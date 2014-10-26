package au.com.example.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import au.com.example.api.data.Customer;
import au.com.example.service.CustomerService;
import au.com.example.service.CustomerServiceImpl;

@Path(value = "customer")
public class CustomerResource {

	private final CustomerService customerService;


	public CustomerResource() {
		this.customerService = new CustomerServiceImpl();
	}

    @GET
    @Path(value = "text")
    @Produces(MediaType.TEXT_PLAIN)
    public String getText(@PathParam("id") Long id) {
        return "TExT";
    }

	@GET
	@Path(value = "retrieve/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomer(@PathParam("id") Long id) {
		return customerService.retrieve(id);
	}

    @POST
    @Path(value = "delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteCustomer(Long id) {
        customerService.delete(id);

        return Response.status(Status.OK).entity("customer has been successfully deleted").type(MediaType.APPLICATION_JSON).build();
    }
	
	@POST
	@Path(value = "save")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveCustomer(Customer customer) {
		customerService.save(customer);
		
		return Response.status(Status.OK).entity("customer has been successfully saved").type(MediaType.APPLICATION_JSON).build();
	}
}

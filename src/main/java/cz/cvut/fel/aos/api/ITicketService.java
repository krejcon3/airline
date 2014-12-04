package cz.cvut.fel.aos.api;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface ITicketService {

	@WebMethod
	public byte[] getTicket(String ticket);
}

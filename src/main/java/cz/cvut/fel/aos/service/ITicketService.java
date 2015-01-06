package cz.cvut.fel.aos.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

/**
 * PrintService service interface
 *
 * @author Ondřej Krejčíř
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface ITicketService {

	/**
	 * @param ticket ticket content
	 * @return byte[] file
	 */
	@WebMethod
	public byte[] getTicket(String ticket);
}

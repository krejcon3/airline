package cz.cvut.fel.aos.service;

import javax.jws.WebService;
import javax.xml.ws.soap.MTOM;
import java.io.*;

/**
 * PrintService service
 *
 * @author Ondřej Krejčíř
 */
@MTOM
@WebService(endpointInterface = "cz.cvut.fel.aos.service.ITicketService")
public class TicketService implements ITicketService {

	/**
	 * Save content to file and send that file back.
	 *
	 * @param ticket ticket content
	 * @return byte[] file
	 */
	@Override
	public byte[] getTicket(String ticket) {
		String path = "./files/" + System.currentTimeMillis() + ".txt";
		File file = new File(path);
		try {
			// Saving
			BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(path));
			outputStream.write(ticket.getBytes());
			outputStream.close();

			// Sending
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			byte[] fileBytes = new byte[(int)file.length()];
			bis.read(fileBytes);
			bis.close();
			file.delete();
			return fileBytes;
		} catch (FileNotFoundException e) {
			file.delete();
			return null;
		} catch (IOException e) {
			file.delete();
			return null;
		}
	}
}

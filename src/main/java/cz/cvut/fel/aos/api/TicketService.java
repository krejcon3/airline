package cz.cvut.fel.aos.api;

import javax.jws.WebService;
import javax.xml.ws.soap.MTOM;
import java.io.*;

/**
 * Created by krejcir on 3.12.14.
 */

@MTOM
@WebService(endpointInterface = "cz.cvut.fel.aos.api.ITicketService")
public class TicketService implements ITicketService {

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

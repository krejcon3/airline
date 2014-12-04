package cz.cvut.fel.aos.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by krejcir on 3.12.14.
 */
public abstract class AbstractRestService {

	protected String getFromUrl(String uri) throws IOException {
		return this.query(new URL(uri).openConnection());
	}

	protected String getFromUrl(String uri, String[][] headers) throws IOException {
		URLConnection conn = new URL(uri).openConnection();
		if (headers != null) {
			for (String[] header : headers) {
				conn.setRequestProperty(header[0], header[1]);
			}
		}
		return this.query(conn);
	}

	private String query(URLConnection conn) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String content = "";
		String line;
		while ((line = br.readLine()) != null) {
			content += line.trim();
			System.out.println(line);
		}
		return content;
	}
}

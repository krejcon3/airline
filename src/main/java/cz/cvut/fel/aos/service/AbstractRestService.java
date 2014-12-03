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
		URL url = new URL(uri);
		URLConnection conn = url.openConnection();
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

		String content = "";
		String line;
		while ((line = br.readLine()) != null) {
			content += line.trim();
		}
		System.out.println(content);
		return content;
	}
}

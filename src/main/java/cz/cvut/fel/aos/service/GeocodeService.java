package cz.cvut.fel.aos.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * Created by krejcir on 3.12.14.
 */
public class GeocodeService extends AbstractRestService {

	public double[] getCoordinates(String location) {
		double[] coordinates = null;
		try {
			String response = this.getFromUrl("http://maps.googleapis.com/maps/api/geocode/json?address=" + location + "&sensor=false");
			System.out.println(response);

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject)jsonParser.parse(response);

			if (jsonObject != null && jsonObject.containsKey("results")) {
				JSONArray results = (JSONArray)jsonObject.get("results");
				if (results != null && results.size() > 0) {
					JSONObject result = (JSONObject)results.get(0);
					if (result != null && result.containsKey("geometry")) {
						JSONObject geometry = (JSONObject)result.get("geometry");
						if (geometry != null && geometry.containsKey("location")) {
							JSONObject loc = (JSONObject)geometry.get("location");
							coordinates = new double[2];
							if (loc != null && loc.containsKey("lat")) {
								coordinates[0] = Double.parseDouble(loc.get("lat").toString());
							}
							if (loc != null && loc.containsKey("lng")) {
								coordinates[1] = Double.parseDouble(loc.get("lng").toString());
							}
						}
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return coordinates;
	}
}

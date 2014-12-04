package cz.cvut.fel.aos.service;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * Created by krejcir on 3.12.14.
 */
public class DistanceService extends AbstractRestService {

	public double getDistance(double[] from, double[] to) {
		double distance = -1;
		try {
			String response = this.getFromUrl(
					"https://orfeomorello-flight.p.mashape.com/mashape/distancebetweenpoints/" +
							"startlat/" + from[0] + "/startlng/" + from[1] + "/" +
							"endlat/" + to[0] + "/endlng/" + to[1] + "/" +
							"unit/K",
					new String[][]{
							new String[]{
									"X-Mashape-Key",
									"f4wyxje9kzmshVRs0kNms5nX28lQp1PfvE8jsnamxc5S2rr3zq"
							}
					});
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject)jsonParser.parse(response);
			if (jsonObject != null && jsonObject.containsKey("distance")) {
				distance = Double.parseDouble(jsonObject.get("distance").toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(distance);
		return distance;
	}

}

package cz.cvut.fel.aos.api.data;

import java.io.Serializable;

/**
 * @author Ondřej Krejčíř
 */
public class Destination implements Serializable {

	/**
	 * Identificator
	 */
	private Long id;

	/**
	 * Destination name
	 */
	private String name;

	/**
	 * Latitude of destination
	 */
	private double latitude;

	/**
	 * Longitude of destination
	 */
	private double longitude;

	/**
	 * URL of REST source of destination
	 */
	private String url;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}

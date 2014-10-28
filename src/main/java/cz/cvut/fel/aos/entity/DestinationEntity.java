package cz.cvut.fel.aos.entity;

import javax.persistence.*;

/**
 * Created by krejcir on 27.10.14.
 */
@Entity
@Table(name = "destination")
public class DestinationEntity extends AbstractEntity {

	@Id
	@Column(name = "id")
	protected Long id;

	@Column(name = "destination_name", nullable = false)
	private String name;

	@Column(name = "latitude", nullable = false)
	private double latitude;

	@Column(name = "longitude", nullable = false)
	private double longitude;

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
}

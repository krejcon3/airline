package cz.cvut.fel.aos.entity;

import javax.persistence.*;

/**
 * Describes destination entity
 *
 * @author Ondřej Krejčíř
 */
@Entity
@Table(name = "destination")
public class DestinationEntity extends AbstractEntity {

	/**
	 * Identificator
	 */
	@Id
	@Column(name = "id")
	protected Long id;

	/**
	 * Destination name
	 */
	@Column(name = "destination_name", nullable = false)
	private String name;

	/**
	 * Latitude of destination
	 */
	@Column(name = "latitude", nullable = false)
	private double latitude;

	/**
	 * Longitude of destination
	 */
	@Column(name = "longitude", nullable = false)
	private double longitude;

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
}

package cz.cvut.fel.aos.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Describes reservation entity
 *
 * @author Ondřej Krejčíř
 */
@Entity
@Table(name = "reservation")
public class ReservationEntity extends AbstractEntity {

	/**
	 * Identificator
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	/**
	 * Identificator of reserved flight
	 */
	@Column(name = "flight", nullable = false)
	private int flight;

	/**
	 * Count of seats to reserve
	 */
	@Column(name = "seats", nullable = false)
	private int seats;

	/**
	 * Reservation password
	 */
	@Column(name = "password", nullable = false)
	private String password;

	/**
	 * State of reservation (new, canceled, paid)
	 */
	@Column(name = "state", nullable = false)
	private String state;

	/**
	 * Creation date
	 */
	@Column(name = "created", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getFlight() {
		return flight;
	}

	public void setFlight(int flight) {
		this.flight = flight;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
}

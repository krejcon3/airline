package cz.cvut.fel.aos.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Describes user entity
 *
 * @author Ondřej Krejčíř
 */
@Entity
@Table(name = "users")
public class UserEntity extends AbstractEntity {

	/**
	 * Identificator
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	/**
	 * User nickname
	 */
	@Column(name = "nickname", nullable = false)
	private String nickname;

	/**
	 * User password
	 */
	@Column(name = "password", nullable = false)
	private String password;

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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
}

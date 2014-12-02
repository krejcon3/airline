package cz.cvut.fel.aos.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by krejcir on 27.10.14.
 */
@Entity
@Table(name = "users")
public class UserEntity extends AbstractEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@Column(name = "nickname", nullable = false)
	private String nickname;

	@Column(name = "password", nullable = false)
	private String password;

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

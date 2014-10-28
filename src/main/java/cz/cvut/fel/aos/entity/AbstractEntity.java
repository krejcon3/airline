package cz.cvut.fel.aos.entity;

import java.io.Serializable;

/**
 * Created by krejcir on 27.10.14.
 */
public abstract class AbstractEntity implements Serializable {

	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return 17 + (this.id == null ? 0 : this.id.hashCode()) + this.getClass().getName().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		return this.id.equals(((AbstractEntity) obj).getId()) && this.id != null;
	}
}

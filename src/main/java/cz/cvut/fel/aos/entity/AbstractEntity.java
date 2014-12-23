package cz.cvut.fel.aos.entity;

import java.io.Serializable;

/**
 * Abstract entity class describes database entity
 *
 * @author Ondřej Krejčíř
 */
public abstract class AbstractEntity implements Serializable {

	/**
	 * Identificator
	 */
	protected Long id;

	public abstract Long getId();

	public abstract void setId(Long id);

	/**
	 * Provides hashCode
	 *
	 * @return String hashCode
	 */
	@Override
	public int hashCode() {
		return 17 + (this.id == null ? 0 : this.id.hashCode()) + this.getClass().getName().hashCode();
	}

	/**
	 * Compares AbstractEntity object with another AbstractEntity object
	 *
	 * @param obj AbstractEntity object
	 * @return boolean value if one object equal other
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		return this.id.equals(((AbstractEntity) obj).getId()) && this.id != null;
	}
}

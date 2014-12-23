package cz.cvut.fel.aos.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Abstract dao for accessing database
 *
 * @author Ondřej Krejčíř
 */
public class AbstractDAO {

	protected EntityManagerFactory factory = Persistence.createEntityManagerFactory("persist-unit");

	protected EntityManager getEntityManager() {
		return this.factory.createEntityManager();
	}
}

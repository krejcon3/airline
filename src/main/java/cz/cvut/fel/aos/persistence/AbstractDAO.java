package cz.cvut.fel.aos.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by krejcir on 28.10.14.
 */
public class AbstractDAO {

	protected EntityManagerFactory factory = Persistence.createEntityManagerFactory("persist-unit");

	protected EntityManager getEntityManager() {
		return this.factory.createEntityManager();
	}
}

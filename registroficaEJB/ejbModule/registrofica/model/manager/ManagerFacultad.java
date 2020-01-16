package registrofica.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import registrofica.model.entities.aca_facultad;

/**
 * Session Bean implementation class ManagerFacultad
 */
@Stateless
@LocalBean
public class ManagerFacultad {
	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public ManagerFacultad() {
		// TODO Auto-generated constructor stub
	}

	public List<aca_facultad> findAllFacultad() {
		return em.createNamedQuery("aca_facultad.findAll").getResultList();
	}

	public aca_facultad findFacultadbyNombre(String nombre) {
		return em.find(aca_facultad.class, nombre);
	}

	public void insertarFacultad(aca_facultad facultad) throws Exception {
		em.persist(facultad);
	}
}

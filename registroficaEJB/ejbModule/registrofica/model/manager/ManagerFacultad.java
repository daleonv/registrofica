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

	public aca_facultad findFacultadbyId(int id) {
		return em.find(aca_facultad.class, id);
	}

	public void insertarFacultad(aca_facultad facultad) throws Exception {
		em.persist(facultad);
	}

	public void eliminarFacultad(int id) throws Exception {
		aca_facultad facu = findFacultadbyId(id);
		if (facu != null)
			em.remove(facu);
	}
	public void modificarFacultad(aca_facultad facultad) throws Exception {
		aca_facultad facu = findFacultadbyId(facultad.getIdFacultad());
		if (facu == null)
			throw new Exception("No existe esa facultad");
		facu.setNombre(facultad.getNombre());
		facu.setAbreviatura(facultad.getAbreviatura());
		em.merge(facu);
	}

}

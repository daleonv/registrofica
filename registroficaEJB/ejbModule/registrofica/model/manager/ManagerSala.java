package registrofica.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import registrofica.model.entities.aca_facultad;
import registrofica.model.entities.aca_sala;

/**
 * Session Bean implementation class ManagerSala
 */
@Stateless
@LocalBean
public class ManagerSala {
	@PersistenceContext
	private EntityManager em;

	public ManagerSala() {
		// TODO Auto-generated constructor stub
	}

	public List<aca_sala> findAllSalas() {
		return em.createNamedQuery("aca_sala.findAll").getResultList();
	}
	public aca_sala findSalabyId(int id) {
		return em.find(aca_sala.class, id);
	}

	public void insertarSala(aca_sala sala) throws Exception {
		em.persist(sala);
	}

	public void eliminarSala(int id) throws Exception {
		aca_sala sal = findSalabyId(id);
		if (sal != null)
			em.remove(sal);
	}
	public void modificarSala(aca_sala sala) throws Exception {
		aca_sala sal = findSalabyId(sala.getIdSala());
		if (sal == null)
			throw new Exception("No existe esa facultad");
		sal.setNombre(sala.getNombre());
		em.merge(sal);
	}
}

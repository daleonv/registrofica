package registrofica.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import registrofica.model.entities.aca_facultad;
import registrofica.model.entities.reg_estado;

/**
 * Session Bean implementation class ManagerEstado
 */
@Stateless
@LocalBean
public class ManagerEstado {
	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public ManagerEstado() {
		// TODO Auto-generated constructor stub
	}

	public List<reg_estado> findAllEstado() {
		return em.createNamedQuery("reg_estado.findAll").getResultList();
	}

	public reg_estado findEstadobyId(int id) {
		return em.find(reg_estado.class, id);
	}

	public void insertarEstado(reg_estado estado) throws Exception {
		em.persist(estado);
	}

	public void eliminarEstado(int id) throws Exception {
		reg_estado est = findEstadobyId(id);
		if (est != null)
			em.remove(est);
	}

	public void modificarEstado(reg_estado estado) throws Exception {
		reg_estado est = findEstadobyId(estado.getIdEstado());
		if (est == null)
			throw new Exception("No existe este estado");
		est.setNombre(estado.getNombre());
		em.merge(est);
	}
}

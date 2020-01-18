package registrofica.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import registrofica.model.entities.aca_facultad;
import registrofica.model.entities.reg_motivo;

/**
 * Session Bean implementation class ManagerMotivo
 */
@Stateless
@LocalBean
public class ManagerMotivo {
	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public ManagerMotivo() {
		// TODO Auto-generated constructor stub
	}

	public List<reg_motivo> findAllMotivo() {
		return em.createNamedQuery("reg_motivo.findAll").getResultList();
	}

	public reg_motivo findMotivobyID(int id) {
		return em.find(reg_motivo.class, id);
	}

	public void insertarMotivo(reg_motivo motivo) throws Exception {
		em.persist(motivo);
	}

	public void eliminarMotivo(int id) throws Exception {
		reg_motivo moti = findMotivobyID(id);
		if (moti != null)
			em.remove(moti);
	}

	public void modificarMotivo(reg_motivo motivo) throws Exception {
		reg_motivo moti = findMotivobyID(motivo.getIdMotivo());
		if (moti == null)
			throw new Exception("No existe ese motivo");
		moti.setDetalle(motivo.getDetalle());
		em.merge(moti);
	}

}

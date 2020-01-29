package registrofica.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import registrofica.model.entities.adm_administrador;

/**
 * Session Bean implementation class ManagerPersona
 */
@Stateless
@LocalBean
public class ManagerAdministrador {
	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public ManagerAdministrador() {
		// TODO Auto-generated constructor stub
	}

	public adm_administrador iniciarSesion(adm_administrador us) {
		adm_administrador administrador = null;
		String consulta;
		try {
			consulta = "FROM adm_administrador u WHERE u.username= ?1 and u.password= ?2";
			Query query = em.createQuery(consulta);
			query.setParameter(1, us.getUsername());
			query.setParameter(2, us.getPassword());
			
			List<adm_administrador> lista = query.getResultList();
			if (!lista.isEmpty()) {
				administrador = lista.get(0);
			}
		} catch (Exception e) {
			throw e;
		} 
		return administrador;
	}

}

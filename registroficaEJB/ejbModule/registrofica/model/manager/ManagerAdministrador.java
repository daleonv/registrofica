package registrofica.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import registrofica.model.entities.aca_carrera;
import registrofica.model.entities.adm_administrador;
import registrofica.model.entities.reg_persona;

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

	public List<adm_administrador> findAllAdministrador() {
		return em.createNamedQuery("adm_administrador.findAll").getResultList();
	}
	public adm_administrador findAdministradorbyId(String user) {
		return em.find(adm_administrador.class, user);
	}
	public void insertarAdministrador(adm_administrador admin) throws Exception {
		em.persist(admin);
	}
	public void eliminarAdministrador(String user) throws Exception {
		adm_administrador admin = findAdministradorbyId(user);
		if (admin != null)
			em.remove(admin);
	}
	public void modificarAdministrador(adm_administrador admin, String user) throws Exception {
		adm_administrador adm = findAdministradorbyId(user);
		if (adm == null)
			throw new Exception("No existe esta usuario");
		adm.setUsername(admin.getUsername());
		adm.setPassword(admin.getPassword());
		em.merge(adm);
	}
}

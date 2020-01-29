package registrofica.model.manager;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import registrofica.model.entities.aca_carrera;
import registrofica.model.entities.aca_facultad;
import registrofica.model.entities.reg_persona;

/**
 * Session Bean implementation class ManagerPersona
 */
@Stateless
@LocalBean
public class ManagerPersona {
	@PersistenceContext
	private EntityManager em;
	@EJB
	private ManagerCarrera managerCarrera;

	/**
	 * Default constructor.
	 */
	public ManagerPersona() {
		// TODO Auto-generated constructor stub
	}

	public reg_persona iniciarSesion(reg_persona us) {
		reg_persona persona = null;
		String consulta;
		try {
			consulta = "FROM reg_persona u WHERE u.cedula= ?1 and u.password= ?2";
			Query query = em.createQuery(consulta);
			query.setParameter(1, us.getCedula());
			query.setParameter(2, us.getPassword());
			
			List<reg_persona> lista = query.getResultList();
			if (!lista.isEmpty()) {
				persona = lista.get(0);
			}
		} catch (Exception e) {
			throw e;
		} 
		return persona;
	}
	
	public List<reg_persona> findAllPersona() {
		return em.createNamedQuery("reg_persona.findAll").getResultList();
	}
	
	public reg_persona findPersonabyId(String cedula) {
		return em.find(reg_persona.class, cedula);
	}
	
	public void insertarPersona(reg_persona persona, int codigoCarrera) throws Exception {
		aca_carrera carrera;
		carrera = managerCarrera.findCarrerabyId(codigoCarrera);
		persona.setAcaCarrera(carrera);
		em.persist(persona);
	}
	
	public void eliminarPersona(String cedula) throws Exception {
		reg_persona per = findPersonabyId(cedula);
		if (per != null)
			em.remove(per);
	}
	
	public void modificarPersona(reg_persona persona, int codigoCarrera) throws Exception {
		reg_persona per = findPersonabyId(persona.getCedula());
		aca_carrera carrera;
		carrera = managerCarrera.findCarrerabyId(codigoCarrera);
		if (per == null)
			throw new Exception("No existe esta persona");
		per.setCedula(persona.getCedula());
		per.setNombre(persona.getNombre());
		per.setApellido(persona.getApellido());
		per.setCorreo(persona.getCorreo());
		per.setCelular(persona.getCelular());
		per.setPassword(persona.getPassword());
		per.setAcaCarrera(carrera);
		em.merge(per);
	}

}

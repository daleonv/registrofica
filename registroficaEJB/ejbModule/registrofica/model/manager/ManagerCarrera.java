package registrofica.model.manager;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import registrofica.model.entities.aca_carrera;
import registrofica.model.entities.aca_facultad;
import registrofica.model.entities.reg_estado;

/**
 * Session Bean implementation class ManagerCarrera
 */
@Stateless
@LocalBean
public class ManagerCarrera {
	@PersistenceContext
	private EntityManager em;
	@EJB
	private ManagerFacultad managerFacultad;

	/**
	 * Default constructor.
	 */
	public ManagerCarrera() {
		// TODO Auto-generated constructor stub
	}

	public List<aca_carrera> findAllCarrera() {
		return em.createNamedQuery("aca_carrera.findAll").getResultList();
	}


	public aca_carrera findCarrerabyId(int id) {
		return em.find(aca_carrera.class, id);
	}

	public void insertarCarrera(aca_carrera carrera, int codigoFacultad) throws Exception {
		aca_facultad facultad;
		facultad = managerFacultad.findFacultadbyId(codigoFacultad);
		carrera.setAcaFacultad(facultad);
		em.persist(carrera);
	}

	public void eliminarCarrera(int id) throws Exception {
		aca_carrera car = findCarrerabyId(id);
		if (car != null)
			em.remove(car);
	}

	public void modificarCarrera(aca_carrera carrera, int codigoFacultad) throws Exception {
		aca_carrera car = findCarrerabyId(carrera.getIdCarrera());
		aca_facultad facultad;
		facultad = managerFacultad.findFacultadbyId(codigoFacultad);
		if (car == null)
			throw new Exception("No existe esta carrera");
		car.setNombre(carrera.getNombre());
		car.setAbreviatura(carrera.getAbreviatura());
		car.setAcaFacultad(facultad);
		em.merge(car);
	}
}

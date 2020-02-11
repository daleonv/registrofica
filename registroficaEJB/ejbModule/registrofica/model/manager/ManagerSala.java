package registrofica.model.manager;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import registrofica.model.dto.aca_sala_dto;
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

	//búsqueda en lista
	public List<aca_sala> findAllSalas() {
		return em.createNamedQuery("aca_sala.findAll").getResultList();
	}
	
	public List<aca_sala_dto> findAllSalasDTO() {

		List<aca_sala> salas = em.createNamedQuery("aca_sala.findAll").getResultList();
		List<aca_sala_dto> salasDTO = new ArrayList<aca_sala_dto>();
		for (aca_sala s: salas) {
			aca_sala_dto sala = new aca_sala_dto();
			sala.setIdSala(s.getIdSala());
			sala.setNombre(s.getNombre());
			salasDTO.add(sala);
		}
		return salasDTO;
	}
	//búsqueda por id 
	
	public aca_sala findSalabyId(int id) {
		return em.find(aca_sala.class, id);
	}
	
	public aca_sala_dto findSalabyIdDTO(int id) {
		aca_sala salas = em.find(aca_sala.class, id);
		aca_sala_dto salasDTO = new aca_sala_dto();
		salasDTO.setNombre(salas.getNombre());
		return salasDTO;
	}

	//insertar 
	public void insertarSala(aca_sala sala) throws Exception {
		em.persist(sala);
	}
	
	//eliminar
	public void eliminarSala(int id) throws Exception {
		aca_sala sal = findSalabyId(id);
		if (sal != null)
			em.remove(sal);
	}
	//modificar
	public void modificarSala(aca_sala sala) throws Exception {
		aca_sala sal = findSalabyId(sala.getIdSala());
		if (sal == null)
			throw new Exception("No existe esa facultad");
		sal.setNombre(sala.getNombre());
		em.merge(sal);
	}
}

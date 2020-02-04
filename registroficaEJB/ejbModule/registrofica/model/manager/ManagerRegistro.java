package registrofica.model.manager;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import registrofica.model.entities.aca_carrera;
import registrofica.model.entities.aca_sala;
import registrofica.model.entities.reg_estado;
import registrofica.model.entities.reg_motivo;
import registrofica.model.entities.reg_persona;
import registrofica.model.entities.reg_registro;

/**
 * Session Bean implementation class ManagerRegistro
 */
@Stateless
@LocalBean
public class ManagerRegistro {
	@PersistenceContext
	private EntityManager em;
	@EJB
	private ManagerPersona managerPersona;
	@EJB
	private ManagerSala managerSala;
	@EJB
	private ManagerMotivo managerMotivo;
	@EJB
	private ManagerEstado managerEstado;
	@EJB
	private ManagerRegistro managerRegistro;

	/**
	 * Default constructor.
	 */
	public ManagerRegistro() {
		// TODO Auto-generated constructor stub
	}

	public List<reg_registro> findAllRegistro() {
		return em.createNamedQuery("reg_registro.findAll").getResultList();
	}

	public List<reg_registro> findRegistroEspera() {
		return em.createNamedQuery("reg_registro.findEspera").getResultList();
	}

	public reg_registro findRegistrobyId(int id) {
		return em.find(reg_registro.class, id);
	}

	public reg_estado findEstadobyId(int id_estado) {
		return em.find(reg_estado.class, id_estado);
	}

	public void insertarRegistro(String cedula, int codigoSala, int codigoMotivo, int codigoEstado, Date inicio,
			Date fin, String descripcion) throws Exception {
		reg_registro registro = new reg_registro();
		reg_persona persona;
		aca_sala sala;
		reg_motivo motivo;
		reg_estado estado;

		persona = managerPersona.findPersonabyId(cedula);
		sala = managerSala.findSalabyId(codigoSala);
		motivo = managerMotivo.findMotivobyID(codigoMotivo);
		estado = managerEstado.findEstadobyId(codigoEstado);

		Timestamp ini = new Timestamp(inicio.getTime());
		Timestamp end = new Timestamp(fin.getTime());

		registro.setRegPersona(persona);
		registro.setAcaSala(sala);
		registro.setRegMotivo(motivo);
		registro.setRegEstado(estado);
		registro.setInicio(ini);
		registro.setFin(end);
		registro.setDescripcion(descripcion);
		em.persist(registro);
	}

	public void eliminarRegistro(int id) throws Exception {
		reg_registro reg = findRegistrobyId(id);
		if (reg != null)
			em.remove(reg);
	}

	public void confirmarRegistro(int id, int id_estado) throws Exception {
		reg_registro registro;
		reg_estado est;
		registro = managerRegistro.findRegistrobyId(id);
		est = managerEstado.findEstadobyId(id_estado);
		if (est == null)
			throw new Exception("No existe este estado");
		registro.setRegEstado(est);
	}

	public void rechazarRegistro(int id, int id_estado) throws Exception {
		reg_registro registro;
		reg_estado est;
		registro = managerRegistro.findRegistrobyId(id);
		est = managerEstado.findEstadobyId(id_estado);
		if (est == null)
			throw new Exception("No existe este estado");
		registro.setRegEstado(est);
	}

	public void rechazarRegistro() {

	}
//
//	public void modificarRegistro(reg_registro registro, String cedula, int codigoSala, int codigoMotivo,
//			int codigoEstado, date inicio, String fin, String descripcion) throws Exception {
//		reg_registro reg = findRegistrobyId(registro.getIdRegistro());
//		reg_persona persona;
//		aca_sala sala;
//		reg_motivo motivo;
//		reg_estado estado;
//
//		persona = managerPersona.findPersonabyId(cedula);
//		sala = managerSala.findSalabyId(codigoSala);
//		motivo = managerMotivo.findMotivobyID(codigoMotivo);
//		estado = managerEstado.findEstadobyId(codigoEstado);
//
//		if (reg == null)
//			throw new Exception("No existe este registro");
//		reg.setRegPersona(persona);
//		reg.setAcaSala(sala);
//		reg.setRegMotivo(motivo);
//		reg.setRegEstado(estado);
//		reg.setInicio(inicio);
//		
//		
//		per.setCedula(persona.getCedula());
//		per.setNombre(persona.getNombre());
//		per.setApellido(persona.getApellido());
//		per.setCorreo(persona.getCorreo());
//		per.setCelular(persona.getCelular());
//		per.setPassword(persona.getPassword());
//		per.setAcaCarrera(carrera);
//		em.merge(per);
//	}

}

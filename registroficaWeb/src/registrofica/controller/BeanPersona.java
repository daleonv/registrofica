package registrofica.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import registrofica.model.entities.aca_carrera;
import registrofica.model.entities.aca_facultad;
import registrofica.model.entities.reg_persona;
import registrofica.model.manager.ManagerCarrera;
import registrofica.model.manager.ManagerPersona;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanPersona implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerPersona managerPersona;
	@EJB
	private ManagerCarrera managerCarrera;

	private reg_persona persona;
	private List<reg_persona> listaPersona;
	private int id_carrera;
	private int id_carrera_seleccionada;
	private aca_carrera carrera;
	private aca_carrera carreraSeleccionada;
	private reg_persona personaSeleccionada;
	private boolean panelColapsado;

	@PostConstruct
	public void init() {
		listaPersona = managerPersona.findAllPersona();
		persona = new reg_persona();
		carrera = new aca_carrera();
		panelColapsado = true;
	}
	
	public void actionListenerColapsarPanel() {
		panelColapsado = !panelColapsado;
	}
	
	public void actionListenerInsertarPersona() {
		try {
			managerPersona.insertarPersona(persona, id_carrera);
			listaPersona = managerPersona.findAllPersona();
			persona = new reg_persona();
			JSFUtil.crearMensajeInfo("Datos de persona ingresados");
		} catch (Exception e) {
			e.getMessage();
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerEliminarPersona(String cedula) {
		try {
			managerPersona.eliminarPersona(cedula);
			listaPersona = managerPersona.findAllPersona();
			JSFUtil.crearMensajeInfo("Datos eliminados");
		} catch (Exception e) {
			e.getMessage();
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerPersonaSeleccionado(reg_persona per) {
		personaSeleccionada = per;
	}
	
	public void actualizarPersona() {
		try {
			managerPersona.modificarPersona(personaSeleccionada, id_carrera_seleccionada);
			listaPersona = managerPersona.findAllPersona();
			JSFUtil.crearMensajeInfo("Persona modificada");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public String iniciarSesion() {
		reg_persona pe;
		String redireccion = null;
		try {
			pe = managerPersona.iniciarSesion(persona);
			if (pe != null) {
				redireccion = "/faces/registro.xhtml?faces-redirect=true";
				JSFUtil.crearMensajeInfo("Acceso correcto");
			} else {
				JSFUtil.crearMensajeError("Datas incorrectos");
			}
		} catch (Exception e) {
		}
		return redireccion;
	}

	public reg_persona getPersona() {
		return persona;
	}

	public void setPersona(reg_persona persona) {
		this.persona = persona;
	}

	public List<reg_persona> getListaPersona() {
		return listaPersona;
	}

	public void setListaPersona(List<reg_persona> listaPersona) {
		this.listaPersona = listaPersona;
	}

	public int getId_carrera() {
		return id_carrera;
	}

	public void setId_carrera(int id_carrera) {
		this.id_carrera = id_carrera;
	}

	public int getId_carrera_seleccionada() {
		return id_carrera_seleccionada;
	}

	public void setId_carrera_seleccionada(int id_carrera_seleccionada) {
		this.id_carrera_seleccionada = id_carrera_seleccionada;
	}

	public aca_carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(aca_carrera carrera) {
		this.carrera = carrera;
	}

	public aca_carrera getCarreraSeleccionada() {
		return carreraSeleccionada;
	}

	public void setCarreraSeleccionada(aca_carrera carreraSeleccionada) {
		this.carreraSeleccionada = carreraSeleccionada;
	}

	public reg_persona getPersonaSeleccionada() {
		return personaSeleccionada;
	}

	public void setPersonaSeleccionada(reg_persona personaSeleccionada) {
		this.personaSeleccionada = personaSeleccionada;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}

}

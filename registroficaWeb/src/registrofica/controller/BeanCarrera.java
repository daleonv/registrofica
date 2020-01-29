package registrofica.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import registrofica.model.entities.aca_carrera;
import registrofica.model.entities.aca_facultad;
import registrofica.model.manager.ManagerCarrera;
import registrofica.model.manager.ManagerFacultad;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanCarrera implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerCarrera managerCarrera;
	@EJB
	private ManagerFacultad managerFacultad;
	private List<aca_carrera> listaCarrera;
	private int id_facultad;
	private int id_facultad_seleccionada;
	private aca_carrera carrera;
	private aca_facultad facultad;
	private aca_carrera carreraSeleccionada;
	private aca_facultad facultadSeleccionada;
	private boolean panelColapsado;

	@PostConstruct
	public void init() {
		listaCarrera = managerCarrera.findAllCarrera();
		carrera = new aca_carrera();
		facultad = new aca_facultad();
		panelColapsado = true;
	}

	public void actionListenerColapsarPanel() {
		panelColapsado = !panelColapsado;
	}

	public void actionListenerInsertarCarrera() {
		try {
			managerCarrera.insertarCarrera(carrera, id_facultad);
			listaCarrera = managerCarrera.findAllCarrera();
			carrera = new aca_carrera();
			JSFUtil.crearMensajeInfo("Datos de carrera ingresados");
		} catch (Exception e) {
			e.getMessage();
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerEliminarCarrera(int id) {
		try {
			managerCarrera.eliminarCarrera(id);
			listaCarrera = managerCarrera.findAllCarrera();
			JSFUtil.crearMensajeInfo("Datos eliminados");
		} catch (Exception e) {
			e.getMessage();
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerCarreraSeleccionado(aca_carrera car) {
		carreraSeleccionada = car;
	}

	public void actualizarCarrera() {
		try {
			managerCarrera.modificarCarrera(carreraSeleccionada,id_facultad_seleccionada);
			listaCarrera = managerCarrera.findAllCarrera();
			JSFUtil.crearMensajeInfo("Carrera modificada");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<aca_carrera> getListaCarrera() {
		return listaCarrera;
	}

	public void setListaCarrera(List<aca_carrera> listaCarrera) {
		this.listaCarrera = listaCarrera;
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

	public aca_facultad getFacultadSeleccionada() {
		return facultadSeleccionada;
	}

	public void setFacultadSeleccionada(aca_facultad facultadSeleccionada) {
		this.facultadSeleccionada = facultadSeleccionada;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}

	public aca_facultad getFacultad() {
		return facultad;
	}

	public void setFacultad(aca_facultad facultad) {
		this.facultad = facultad;
	}

	public int getId_facultad() {
		return id_facultad;
	}

	public void setId_facultad(int id_facultad) {
		this.id_facultad = id_facultad;
	}

	public int getId_facultad_seleccionada() {
		return id_facultad_seleccionada;
	}

	public void setId_facultad_seleccionada(int id_facultad_seleccionada) {
		this.id_facultad_seleccionada = id_facultad_seleccionada;
	}

	

	

}

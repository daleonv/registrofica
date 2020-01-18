package registrofica.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import registrofica.model.entities.aca_facultad;
import registrofica.model.manager.ManagerFacultad;

import java.awt.print.Printable;
import java.io.Serializable;
import java.util.List;
@Named
@SessionScoped
public class BeanFacultad implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerFacultad managerFacultad;
	private List<aca_facultad> listaFacultad;
	private aca_facultad facultad;
	private aca_facultad facultadSeleccionada;
	private boolean panelColapsado;

	@PostConstruct
	public void init() {
		listaFacultad = managerFacultad.findAllFacultad();
		facultad = new aca_facultad();
		panelColapsado = true;
	}

	public void actionListenerColapsarPanel() {
		panelColapsado = !panelColapsado;
	}

	public void actionListenerInsertarFacultad() {
		try {
			managerFacultad.insertarFacultad(facultad);
			listaFacultad = managerFacultad.findAllFacultad();
			facultad = new aca_facultad();
			JSFUtil.crearMensajeInfo("Datos de facultad ingresados");
		} catch (Exception e) {
			e.getMessage();
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void actionListenerEliminarFacultad(int id) {
		try {
			managerFacultad.eliminarFacultad(id);
			listaFacultad = managerFacultad.findAllFacultad();
			JSFUtil.crearMensajeInfo("Datos eliminados");
		} catch (Exception e) {
			e.getMessage();
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}
	public void actionListenerFacultadSeleccionada(aca_facultad fac) {
		facultadSeleccionada = fac;
	}
	public void actualizarFacultad() {
		try {
			managerFacultad.modificarFacultad(facultadSeleccionada);
			JSFUtil.crearMensajeInfo("Facultad modificada");
		}catch(Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	

	public List<aca_facultad> getListaFacultad() {
		return listaFacultad;
	}

	public void setListaFacultad(List<aca_facultad> listaFacultad) {
		this.listaFacultad = listaFacultad;
	}

	public aca_facultad getFacultad() {
		return facultad;
	}

	public void setFacultad(aca_facultad facultad) {
		this.facultad = facultad;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}

	public aca_facultad getFacultadSeleccionada() {
		return facultadSeleccionada;
	}

	public void setFacultadSeleccionada(aca_facultad facultadSeleccionada) {
		this.facultadSeleccionada = facultadSeleccionada;
	}

}

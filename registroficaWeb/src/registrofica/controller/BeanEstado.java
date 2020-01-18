package registrofica.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import registrofica.model.entities.aca_facultad;
import registrofica.model.entities.reg_estado;
import registrofica.model.entities.reg_motivo;
import registrofica.model.manager.ManagerEstado;
import registrofica.model.manager.ManagerMotivo;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped

public class BeanEstado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerEstado managerEstado;
	private List<reg_estado> listaEstado;
	private reg_estado estado;
	private reg_estado estadoSeleccionado;
	private boolean panelColapsado;

	@PostConstruct
	public void init() {
		listaEstado = managerEstado.findAllEstado();
		estado = new reg_estado();
		panelColapsado = true;
	}

	public void actionListenerColapsarPanel() {
		panelColapsado = !panelColapsado;
	}

	public void actionListenerInsertarEstado() {
		try {
			managerEstado.insertarEstado(estado);
			listaEstado = managerEstado.findAllEstado();
			estado = new reg_estado();
			JSFUtil.crearMensajeInfo("Datos de estado ingresados");
		} catch (Exception e) {
			e.getMessage();
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void actionListenerEliminarEstado(int id) {
		try {
			managerEstado.eliminarEstado(id);
			listaEstado = managerEstado.findAllEstado();
			JSFUtil.crearMensajeInfo("Datos eliminados");
		} catch (Exception e) {
			e.getMessage();
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerEstadoSeleccionado(reg_estado est) {
		estadoSeleccionado = est;
	}

	public void actualizarEstado() {
		try {
			managerEstado.modificarEstado(estadoSeleccionado);
			JSFUtil.crearMensajeInfo("Estado modificado");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<reg_estado> getListaEstado() {
		return listaEstado;
	}

	public void setListaEstado(List<reg_estado> listaEstado) {
		this.listaEstado = listaEstado;
	}

	public reg_estado getEstado() {
		return estado;
	}

	public void setEstado(reg_estado estado) {
		this.estado = estado;
	}

	public reg_estado getEstadoSeleccionado() {
		return estadoSeleccionado;
	}

	public void setEstadoSeleccionado(reg_estado estadoSeleccionado) {
		this.estadoSeleccionado = estadoSeleccionado;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}
}

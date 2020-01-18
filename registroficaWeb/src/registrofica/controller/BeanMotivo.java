package registrofica.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import registrofica.model.entities.aca_facultad;
import registrofica.model.entities.reg_motivo;
import registrofica.model.manager.ManagerMotivo;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanMotivo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerMotivo managerMotivo;
	private List<reg_motivo> listaMotivo;
	private reg_motivo motivo;
	private reg_motivo motivoSeleccionada;
	private boolean panelColapsado;

	@PostConstruct
	public void init() {
		listaMotivo = managerMotivo.findAllMotivo();
		motivo = new reg_motivo();
		panelColapsado = true;
	}

	public void actionListenerColapsarPanel() {
		panelColapsado = !panelColapsado;
	}

	public void actionListenerInsertarMotivo() {
		try {
			managerMotivo.insertarMotivo(motivo);
			listaMotivo = managerMotivo.findAllMotivo();
			motivo = new reg_motivo();
			JSFUtil.crearMensajeInfo("Datos ingresados");
		} catch (Exception e) {
			e.getMessage();
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerEliminarFacultad(int id) {
		try {
			managerMotivo.eliminarMotivo(id);
			listaMotivo = managerMotivo.findAllMotivo();
			JSFUtil.crearMensajeInfo("Datos eliminados");
		} catch (Exception e) {
			e.getMessage();
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerFacultadSeleccionada(reg_motivo moti) {
		motivoSeleccionada = moti;
	}

	public void actualizarMotivo() {
		try {
			managerMotivo.modificarMotivo(motivoSeleccionada);
			JSFUtil.crearMensajeInfo("Motivo modificado");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<reg_motivo> getListaMotivo() {
		return listaMotivo;
	}

	public void setListaMotivo(List<reg_motivo> listaMotivo) {
		this.listaMotivo = listaMotivo;
	}

	public reg_motivo getMotivo() {
		return motivo;
	}

	public void setMotivo(reg_motivo motivo) {
		this.motivo = motivo;
	}

	public reg_motivo getMotivoSeleccionada() {
		return motivoSeleccionada;
	}

	public void setMotivoSeleccionada(reg_motivo motivoSeleccionada) {
		this.motivoSeleccionada = motivoSeleccionada;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}
	
}

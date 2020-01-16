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
			facultad= new aca_facultad();
			JSFUtil.crearMensajeInfo("Datos ingresados");
		} catch (Exception e) {
			e.getMessage();
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

}

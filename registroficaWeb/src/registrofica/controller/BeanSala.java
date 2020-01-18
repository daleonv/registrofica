package registrofica.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import registrofica.model.entities.aca_sala;
import registrofica.model.entities.reg_estado;
import registrofica.model.manager.ManagerSala;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanSala implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerSala managerSala;
	private List<aca_sala> listaSala;
	private aca_sala sala;
	private aca_sala salaSeleccionada;
	private boolean panelColapsado;
	@PostConstruct
	public void init() {
		listaSala = managerSala.findAllSalas();
		sala = new aca_sala();
		panelColapsado = true;
	}
	public void actionListenerColapsarPanel() {
		panelColapsado = !panelColapsado;
	}

	public void actionListenerInsertarSala() {
		try {
			managerSala.insertarSala(sala);
			listaSala = managerSala.findAllSalas();
			sala = new aca_sala();
			JSFUtil.crearMensajeInfo("Datos de sala ingresada");
		} catch (Exception e) {
			e.getMessage();
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}
	public void actionListenerEliminarSala(int id) {
		try {
			managerSala.eliminarSala(id);
			listaSala = managerSala.findAllSalas();
			JSFUtil.crearMensajeInfo("Datos eliminados");
		} catch (Exception e) {
			e.getMessage();
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerSalaSeleccionada(aca_sala sal) {
		salaSeleccionada = sal;
	}

	public void actualizarSala() {
		try {
			managerSala.modificarSala(salaSeleccionada);
			JSFUtil.crearMensajeInfo("Estado modificado");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	public List<aca_sala> getListaSala() {
		return listaSala;
	}
	public void setListaSala(List<aca_sala> listaSala) {
		this.listaSala = listaSala;
	}
	public aca_sala getSala() {
		return sala;
	}
	public void setSala(aca_sala sala) {
		this.sala = sala;
	}
	public aca_sala getSalaSeleccionada() {
		return salaSeleccionada;
	}
	public void setSalaSeleccionada(aca_sala salaSeleccionada) {
		this.salaSeleccionada = salaSeleccionada;
	}
	public boolean isPanelColapsado() {
		return panelColapsado;
	}
	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}
	

}

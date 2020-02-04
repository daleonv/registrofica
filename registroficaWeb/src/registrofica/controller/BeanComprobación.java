package registrofica.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import registrofica.model.entities.reg_registro;
import registrofica.model.manager.ManagerRegistro;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanComprobación implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerRegistro managerRegistro;
	private List<reg_registro> lista;
	private List<reg_registro> lista_Enespera;
	private reg_registro registro;

	@PostConstruct
	public void init() {
		lista = managerRegistro.findRegistroEspera();
	}

	public void SolicitudConfirmada(int id_registro) {
		try {
			managerRegistro.confirmarRegistro(id_registro, 3);
			lista = managerRegistro.findRegistroEspera();
			JSFUtil.crearMensajeInfo("Reservación confirmada");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void rechazarSolicitud(int id_registro) {
		try {
			managerRegistro.rechazarRegistro(id_registro, 4);
			lista = managerRegistro.findRegistroEspera();
			JSFUtil.crearMensajeInfo("Reservación rechazada");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<reg_registro> getLista() {
		return lista;
	}

	public void setLista(List<reg_registro> lista) {
		this.lista = lista;
	}

	public List<reg_registro> getLista_Enespera() {
		return lista_Enespera;
	}

	public void setLista_Enespera(List<reg_registro> lista_Enespera) {
		this.lista_Enespera = lista_Enespera;
	}

	public reg_registro getRegistro() {
		return registro;
	}

	public void setRegistro(reg_registro registro) {
		this.registro = registro;
	}

}

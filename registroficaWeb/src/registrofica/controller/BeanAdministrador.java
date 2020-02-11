package registrofica.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import registrofica.model.entities.adm_administrador;
import registrofica.model.entities.reg_estado;
import registrofica.model.manager.ManagerAdministrador;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanAdministrador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerAdministrador managerAdministrador;
	private adm_administrador administrador;
	private adm_administrador administradorSeleccionado;
	private List<adm_administrador> lista_administradores;
	private String username;

	@PostConstruct
	public void init() {
		administrador = new adm_administrador();
		lista_administradores = managerAdministrador.findAllAdministrador();
	}

	public adm_administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(adm_administrador administrador) {
		this.administrador = administrador;
	}

	public void actionListenerInsertarAdministrador() {
		try {
			managerAdministrador.insertarAdministrador(administrador);
			lista_administradores = managerAdministrador.findAllAdministrador();
			administrador = new adm_administrador();
			JSFUtil.crearMensajeInfo("Datos de administrador ingresados");
		} catch (Exception e) {
			e.getMessage();
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void actionListenerEliminarEstado(String user) {
		try {
			managerAdministrador.eliminarAdministrador(user);
			lista_administradores = managerAdministrador.findAllAdministrador();
			administrador = new adm_administrador();
			JSFUtil.crearMensajeInfo("Datos eliminados");
		} catch (Exception e) {
			e.getMessage();
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	public void actionListenerAdministradorSeleccionado(adm_administrador adm) {
		administradorSeleccionado = adm;
	}

	public void actualizarAdministrador() {
		try {
			managerAdministrador.modificarAdministrador(administradorSeleccionado, username);
			JSFUtil.crearMensajeInfo("Usuario modificado");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public String iniciarSesion() {
		adm_administrador pe;
		String redireccion = null;
		try {
			pe = managerAdministrador.iniciarSesion(administrador);
			if (pe != null) {
				// aquí se debe cambiar la ruta
				redireccion = "menuAdmin";
				JSFUtil.crearMensajeInfo("Acceso correcto");
			} else {
				JSFUtil.crearMensajeError("Datas incorrectos");
			}
		} catch (Exception e) {
		}
		return redireccion;
	}

	public ManagerAdministrador getManagerAdministrador() {
		return managerAdministrador;
	}

	public void setManagerAdministrador(ManagerAdministrador managerAdministrador) {
		this.managerAdministrador = managerAdministrador;
	}

	public adm_administrador getAdministradorSeleccionado() {
		return administradorSeleccionado;
	}

	public void setAdministradorSeleccionado(adm_administrador administradorSeleccionado) {
		this.administradorSeleccionado = administradorSeleccionado;
	}

	public List<adm_administrador> getLista_administradores() {
		return lista_administradores;
	}

	public void setLista_administradores(List<adm_administrador> lista_administradores) {
		this.lista_administradores = lista_administradores;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}

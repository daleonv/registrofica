package registrofica.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import registrofica.model.entities.adm_administrador;
import registrofica.model.manager.ManagerAdministrador;

import java.io.Serializable;

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

	@PostConstruct
	public void init() {
		administrador = new adm_administrador();
	}

	public adm_administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(adm_administrador administrador) {
		this.administrador = administrador;
	}


	public String iniciarSesion() {
		adm_administrador pe;
		String redireccion = null;
		try {
			pe = managerAdministrador.iniciarSesion(administrador);
			if (pe != null) {
				//aquí se debe cambiar la ruta 
				redireccion = "menuAdmin";
				JSFUtil.crearMensajeInfo("Acceso correcto");
			}else {
				JSFUtil.crearMensajeError("Datas incorrectos");
			}
		} catch (Exception e) {
		}
		return redireccion;
	}

}

package registrofica.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.primefaces.component.*;


import registrofica.model.entities.aca_sala;
import registrofica.model.entities.reg_estado;
import registrofica.model.entities.reg_motivo;
import registrofica.model.entities.reg_persona;
import registrofica.model.entities.reg_registro;
import registrofica.model.manager.ManagerRegistro;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Named
@SessionScoped
public class BeanRegistro implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerRegistro managerRegistro;
	private ScheduleModel modelo;
	private ScheduleEvent event = new DefaultScheduleEvent();
	private List<reg_registro> listaRegistro;
	private reg_registro registro;
	private reg_persona persona;
	private aca_sala sala;
	private reg_motivo motivo;
	private reg_estado estado;
	private reg_registro registroSeleccionado;
	private reg_persona personaSeleccionada;
	private aca_sala salaSeleccionada;
	private reg_motivo motivoSeleccionado;
	private reg_estado estadoSeleccionado;
	private int id_registro;
	private String cedula;
	private int id_sala;
	private int id_motivo;
	private int id_estado;
	private Date inicio;
	private Date fin;
	private String descripcion;
	private boolean panelColapsado;
	private Date todayDate = new Date();


	@PostConstruct
	public void init() {
		listaRegistro = managerRegistro.findAllRegistro();
		modelo = new DefaultScheduleModel();
		modelo.clear();
		for (reg_registro reg: listaRegistro) {
			String motivo = reg.getRegMotivo().getDetalle();
			Date inicio = new Date(reg.getInicio().getTime());
			Date fin = new Date(reg.getFin().getTime());
			DefaultScheduleEvent event = new DefaultScheduleEvent();
			event.setTitle(motivo);
			event.setStartDate(inicio);
			event.setEndDate(fin);
			modelo.addEvent(event);
		}
		persona = new reg_persona();
		sala = new aca_sala();
		motivo = new reg_motivo();
		estado = new reg_estado();
		panelColapsado = true;
	}

	public void actionListenerColapsarPanel() {
		panelColapsado = !panelColapsado;
	}

	public void actionListenerInsertarRegistro() {
		try {
			managerRegistro.insertarRegistro(cedula, id_sala, id_motivo, id_estado, inicio, fin, descripcion);
			listaRegistro = managerRegistro.findAllRegistro();
			modelo.clear();
			for (reg_registro reg: listaRegistro) {
				String motivo = reg.getRegMotivo().getDetalle();
				Date inicio = new Date(reg.getInicio().getTime());
				Date fin = new Date(reg.getFin().getTime());
				DefaultScheduleEvent event = new DefaultScheduleEvent();
				event.setTitle(motivo);
				event.setStartDate(inicio);
				event.setEndDate(fin);
				modelo.addEvent(event);
			}
			registro = new reg_registro();
			JSFUtil.crearMensajeInfo("Datos ingresados");
		} catch (Exception e) {
			e.getMessage();
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerEliminarRegistro(int id) {
		try {
			managerRegistro.eliminarRegistro(id);
			listaRegistro = managerRegistro.findAllRegistro();
			JSFUtil.crearMensajeInfo("Datos eliminados");
		} catch (Exception e) {
			e.getMessage();
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<reg_registro> getListaRegistro() {
		return listaRegistro;
	}

	public void setListaRegistro(List<reg_registro> listaRegistro) {
		this.listaRegistro = listaRegistro;
	}

	public reg_registro getRegistro() {
		return registro;
	}

	public void setRegistro(reg_registro registro) {
		this.registro = registro;
	}

	public reg_persona getPersona() {
		return persona;
	}

	public void setPersona(reg_persona persona) {
		this.persona = persona;
	}

	public aca_sala getSala() {
		return sala;
	}

	public void setSala(aca_sala sala) {
		this.sala = sala;
	}

	public reg_motivo getMotivo() {
		return motivo;
	}

	public void setMotivo(reg_motivo motivo) {
		this.motivo = motivo;
	}

	public reg_estado getEstado() {
		return estado;
	}

	public void setEstado(reg_estado estado) {
		this.estado = estado;
	}

	public reg_registro getRegistroSeleccionado() {
		return registroSeleccionado;
	}

	public void setRegistroSeleccionado(reg_registro registroSeleccionado) {
		this.registroSeleccionado = registroSeleccionado;
	}

	public reg_persona getPersonaSeleccionada() {
		return personaSeleccionada;
	}

	public void setPersonaSeleccionada(reg_persona personaSeleccionada) {
		this.personaSeleccionada = personaSeleccionada;
	}

	public aca_sala getSalaSeleccionada() {
		return salaSeleccionada;
	}

	public void setSalaSeleccionada(aca_sala salaSeleccionada) {
		this.salaSeleccionada = salaSeleccionada;
	}

	public reg_motivo getMotivoSeleccionado() {
		return motivoSeleccionado;
	}

	public void setMotivoSeleccionado(reg_motivo motivoSeleccionado) {
		this.motivoSeleccionado = motivoSeleccionado;
	}

	public reg_estado getEstadoSeleccionado() {
		return estadoSeleccionado;
	}

	public void setEstadoSeleccionado(reg_estado estadoSeleccionado) {
		this.estadoSeleccionado = estadoSeleccionado;
	}

	public int getId_registro() {
		return id_registro;
	}

	public void setId_registro(int id_registro) {
		this.id_registro = id_registro;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public int getId_sala() {
		return id_sala;
	}

	public void setId_sala(int id_sala) {
		this.id_sala = id_sala;
	}

	public int getId_motivo() {
		return id_motivo;
	}

	public void setId_motivo(int id_motivo) {
		this.id_motivo = id_motivo;
	}

	public int getId_estado() {
		return id_estado;
	}

	public void setId_estado(int id_estado) {
		this.id_estado = id_estado;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public ScheduleModel getModelo() {
		return modelo;
	}

	public void setModelo(ScheduleModel modelo) {
		this.modelo = modelo;
	}

	public ScheduleEvent getEvent() {
		return event;
	}

	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}
	public Date getTodayDate() {
	    return todayDate;
	}


}

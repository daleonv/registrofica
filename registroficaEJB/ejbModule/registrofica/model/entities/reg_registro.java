package registrofica.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the reg_registro database table.
 * 
 */
@Entity
@Table(name="reg_registro")
@NamedQuery(name="reg_registro.findAll", query="SELECT r FROM reg_registro r")
public class reg_registro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_registro", unique=true, nullable=false)
	private Integer idRegistro;

	@Column(length=2147483647)
	private String descripcion;

	private Timestamp fin;

	private Timestamp inicio;

	//bi-directional many-to-one association to aca_sala
	@ManyToOne
	@JoinColumn(name="id_sala")
	private aca_sala acaSala;

	//bi-directional many-to-one association to reg_estado
	@ManyToOne
	@JoinColumn(name="id_estado")
	private reg_estado regEstado;

	//bi-directional many-to-one association to reg_motivo
	@ManyToOne
	@JoinColumn(name="id_motivo")
	private reg_motivo regMotivo;

	//bi-directional many-to-one association to reg_persona
	@ManyToOne
	@JoinColumn(name="cedula")
	private reg_persona regPersona;

	public reg_registro() {
	}

	public Integer getIdRegistro() {
		return this.idRegistro;
	}

	public void setIdRegistro(Integer idRegistro) {
		this.idRegistro = idRegistro;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Timestamp getFin() {
		return this.fin;
	}

	public void setFin(Timestamp fin) {
		this.fin = fin;
	}

	public Timestamp getInicio() {
		return this.inicio;
	}

	public void setInicio(Timestamp inicio) {
		this.inicio = inicio;
	}

	public aca_sala getAcaSala() {
		return this.acaSala;
	}

	public void setAcaSala(aca_sala acaSala) {
		this.acaSala = acaSala;
	}

	public reg_estado getRegEstado() {
		return this.regEstado;
	}

	public void setRegEstado(reg_estado regEstado) {
		this.regEstado = regEstado;
	}

	public reg_motivo getRegMotivo() {
		return this.regMotivo;
	}

	public void setRegMotivo(reg_motivo regMotivo) {
		this.regMotivo = regMotivo;
	}

	public reg_persona getRegPersona() {
		return this.regPersona;
	}

	public void setRegPersona(reg_persona regPersona) {
		this.regPersona = regPersona;
	}

}
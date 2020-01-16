package registrofica.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the reg_persona database table.
 * 
 */
@Entity
@Table(name="reg_persona")
@NamedQuery(name="reg_persona.findAll", query="SELECT r FROM reg_persona r")
public class reg_persona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=50)
	private String cedula;

	@Column(length=50)
	private String apellido;

	@Column(length=50)
	private String celular;

	@Column(length=50)
	private String correo;

	@Column(length=50)
	private String nombre;

	@Column(length=250)
	private String password;

	//bi-directional many-to-one association to aca_carrera
	@ManyToOne
	@JoinColumn(name="id_carrera")
	private aca_carrera acaCarrera;

	//bi-directional many-to-one association to reg_registro
	@OneToMany(mappedBy="regPersona")
	private List<reg_registro> regRegistros;

	public reg_persona() {
	}

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public aca_carrera getAcaCarrera() {
		return this.acaCarrera;
	}

	public void setAcaCarrera(aca_carrera acaCarrera) {
		this.acaCarrera = acaCarrera;
	}

	public List<reg_registro> getRegRegistros() {
		return this.regRegistros;
	}

	public void setRegRegistros(List<reg_registro> regRegistros) {
		this.regRegistros = regRegistros;
	}

	public reg_registro addRegRegistro(reg_registro regRegistro) {
		getRegRegistros().add(regRegistro);
		regRegistro.setRegPersona(this);

		return regRegistro;
	}

	public reg_registro removeRegRegistro(reg_registro regRegistro) {
		getRegRegistros().remove(regRegistro);
		regRegistro.setRegPersona(null);

		return regRegistro;
	}

}
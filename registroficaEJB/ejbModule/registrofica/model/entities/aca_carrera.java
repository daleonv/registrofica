package registrofica.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the aca_carrera database table.
 * 
 */
@Entity
@Table(name="aca_carrera")
@NamedQuery(name="aca_carrera.findAll", query="SELECT a FROM aca_carrera a")
public class aca_carrera implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_carrera", unique=true, nullable=false)
	private Integer idCarrera;

	@Column(length=25)
	private String abreviatura;

	@Column(length=100)
	private String nombre;

	//bi-directional many-to-one association to aca_facultad
	@ManyToOne
	@JoinColumn(name="id_facultad")
	private aca_facultad acaFacultad;

	//bi-directional many-to-one association to reg_persona
	@OneToMany(mappedBy="acaCarrera")
	private List<reg_persona> regPersonas;

	public aca_carrera() {
	}

	public Integer getIdCarrera() {
		return this.idCarrera;
	}

	public void setIdCarrera(Integer idCarrera) {
		this.idCarrera = idCarrera;
	}

	public String getAbreviatura() {
		return this.abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public aca_facultad getAcaFacultad() {
		return this.acaFacultad;
	}

	public void setAcaFacultad(aca_facultad acaFacultad) {
		this.acaFacultad = acaFacultad;
	}

	public List<reg_persona> getRegPersonas() {
		return this.regPersonas;
	}

	public void setRegPersonas(List<reg_persona> regPersonas) {
		this.regPersonas = regPersonas;
	}

	public reg_persona addRegPersona(reg_persona regPersona) {
		getRegPersonas().add(regPersona);
		regPersona.setAcaCarrera(this);

		return regPersona;
	}

	public reg_persona removeRegPersona(reg_persona regPersona) {
		getRegPersonas().remove(regPersona);
		regPersona.setAcaCarrera(null);

		return regPersona;
	}

}
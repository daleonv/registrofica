package registrofica.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the aca_facultad database table.
 * 
 */
@Entity
@Table(name="aca_facultad")
@NamedQuery(name="aca_facultad.findAll", query="SELECT a FROM aca_facultad a")
public class aca_facultad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_facultad", unique=true, nullable=false)
	private Integer idFacultad;

	@Column(length=50)
	private String abreviatura;

	@Column(length=250)
	private String nombre;

	//bi-directional many-to-one association to aca_carrera
	@OneToMany(mappedBy="acaFacultad")
	private List<aca_carrera> acaCarreras;

	public aca_facultad() {
	}

	public Integer getIdFacultad() {
		return this.idFacultad;
	}

	public void setIdFacultad(Integer idFacultad) {
		this.idFacultad = idFacultad;
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

	public List<aca_carrera> getAcaCarreras() {
		return this.acaCarreras;
	}

	public void setAcaCarreras(List<aca_carrera> acaCarreras) {
		this.acaCarreras = acaCarreras;
	}

	public aca_carrera addAcaCarrera(aca_carrera acaCarrera) {
		getAcaCarreras().add(acaCarrera);
		acaCarrera.setAcaFacultad(this);

		return acaCarrera;
	}

	public aca_carrera removeAcaCarrera(aca_carrera acaCarrera) {
		getAcaCarreras().remove(acaCarrera);
		acaCarrera.setAcaFacultad(null);

		return acaCarrera;
	}

}
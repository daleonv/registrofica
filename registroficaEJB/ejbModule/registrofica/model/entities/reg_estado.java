package registrofica.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the reg_estado database table.
 * 
 */
@Entity
@Table(name="reg_estado")
@NamedQuery(name="reg_estado.findAll", query="SELECT r FROM reg_estado r")
public class reg_estado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_estado", unique=true, nullable=false)
	private Integer idEstado;

	@Column(length=100)
	private String nombre;

	//bi-directional many-to-one association to reg_registro
	@OneToMany(mappedBy="regEstado")
	private List<reg_registro> regRegistros;

	public reg_estado() {
	}

	public Integer getIdEstado() {
		return this.idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<reg_registro> getRegRegistros() {
		return this.regRegistros;
	}

	public void setRegRegistros(List<reg_registro> regRegistros) {
		this.regRegistros = regRegistros;
	}

	public reg_registro addRegRegistro(reg_registro regRegistro) {
		getRegRegistros().add(regRegistro);
		regRegistro.setRegEstado(this);

		return regRegistro;
	}

	public reg_registro removeRegRegistro(reg_registro regRegistro) {
		getRegRegistros().remove(regRegistro);
		regRegistro.setRegEstado(null);

		return regRegistro;
	}

}
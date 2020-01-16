package registrofica.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the aca_sala database table.
 * 
 */
@Entity
@Table(name="aca_sala")
@NamedQuery(name="aca_sala.findAll", query="SELECT a FROM aca_sala a")
public class aca_sala implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_sala", unique=true, nullable=false)
	private Integer idSala;

	@Column(length=100)
	private String nombre;

	//bi-directional many-to-one association to reg_registro
	@OneToMany(mappedBy="acaSala")
	private List<reg_registro> regRegistros;

	public aca_sala() {
	}

	public Integer getIdSala() {
		return this.idSala;
	}

	public void setIdSala(Integer idSala) {
		this.idSala = idSala;
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
		regRegistro.setAcaSala(this);

		return regRegistro;
	}

	public reg_registro removeRegRegistro(reg_registro regRegistro) {
		getRegRegistros().remove(regRegistro);
		regRegistro.setAcaSala(null);

		return regRegistro;
	}

}
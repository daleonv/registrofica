package registrofica.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the reg_motivo database table.
 * 
 */
@Entity
@Table(name="reg_motivo")
@NamedQuery(name="reg_motivo.findAll", query="SELECT r FROM reg_motivo r")
public class reg_motivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_motivo", unique=true, nullable=false)
	private Integer idMotivo;

	@Column(length=500)
	private String detalle;

	//bi-directional many-to-one association to reg_registro
	@OneToMany(mappedBy="regMotivo")
	private List<reg_registro> regRegistros;

	public reg_motivo() {
	}

	public Integer getIdMotivo() {
		return this.idMotivo;
	}

	public void setIdMotivo(Integer idMotivo) {
		this.idMotivo = idMotivo;
	}

	public String getDetalle() {
		return this.detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public List<reg_registro> getRegRegistros() {
		return this.regRegistros;
	}

	public void setRegRegistros(List<reg_registro> regRegistros) {
		this.regRegistros = regRegistros;
	}

	public reg_registro addRegRegistro(reg_registro regRegistro) {
		getRegRegistros().add(regRegistro);
		regRegistro.setRegMotivo(this);

		return regRegistro;
	}

	public reg_registro removeRegRegistro(reg_registro regRegistro) {
		getRegRegistros().remove(regRegistro);
		regRegistro.setRegMotivo(null);

		return regRegistro;
	}

}
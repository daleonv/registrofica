package registrofica.model.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the adm_administrador database table.
 * 
 */
@Entity
@Table(name="adm_administrador")
@NamedQuery(name="adm_administrador.findAll", query="SELECT a FROM adm_administrador a")
public class adm_administrador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=50)
	private String username;

	@Column(nullable=false, length=250)
	private String password;

	public adm_administrador() {
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
package net.claudechaillet.projecte;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usuaris_usuari database table.
 * 
 */
@Entity
@Table(name="usuaris_usuari")
@NamedQuery(name="UsuarisUsuari.findAll", query="SELECT u FROM UsuarisUsuari u")
public class UsuarisUsuari implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String esAdminSistema;

	//bi-directional many-to-one association to AuthUser
	@ManyToOne
	@JoinColumn(name="usuari_id")
	private AuthUser authUser;

	public UsuarisUsuari() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEsAdminSistema() {
		return this.esAdminSistema;
	}

	public void setEsAdminSistema(String esAdminSistema) {
		this.esAdminSistema = esAdminSistema;
	}

	public AuthUser getAuthUser() {
		return this.authUser;
	}

	public void setAuthUser(AuthUser authUser) {
		this.authUser = authUser;
	}

}
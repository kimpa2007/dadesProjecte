package net.claudechaillet.projecte;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the auth_user database table.
 * 
 */
@Entity
@Table(name="auth_user")
@NamedQuery(name="AuthUser.findAll", query="SELECT a FROM AuthUser a")
public class AuthUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_joined")
	private Date dateJoined;

	private String email;

	@Column(name="first_name")
	private String firstName;

	@Column(name="is_active")
	private byte isActive;

	@Column(name="is_staff")
	private byte isStaff;

	@Column(name="is_superuser")
	private byte isSuperuser;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_login")
	private Date lastLogin;

	@Column(name="last_name")
	private String lastName;

	private String password;

	private String username;

	//bi-directional many-to-one association to ComandaComanda
	@OneToMany(mappedBy="authUser")
	private List<ComandaComanda> comandaComandas;

	//bi-directional many-to-one association to UsuarisUsuari
	@OneToMany(mappedBy="authUser")
	private List<UsuarisUsuari> usuarisUsuaris;

	public AuthUser() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateJoined() {
		return this.dateJoined;
	}

	public void setDateJoined(Date dateJoined) {
		this.dateJoined = dateJoined;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public byte getIsActive() {
		return this.isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}

	public byte getIsStaff() {
		return this.isStaff;
	}

	public void setIsStaff(byte isStaff) {
		this.isStaff = isStaff;
	}

	public byte getIsSuperuser() {
		return this.isSuperuser;
	}

	public void setIsSuperuser(byte isSuperuser) {
		this.isSuperuser = isSuperuser;
	}

	public Date getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<ComandaComanda> getComandaComandas() {
		return this.comandaComandas;
	}

	public void setComandaComandas(List<ComandaComanda> comandaComandas) {
		this.comandaComandas = comandaComandas;
	}

	public ComandaComanda addComandaComanda(ComandaComanda comandaComanda) {
		getComandaComandas().add(comandaComanda);
		comandaComanda.setAuthUser(this);

		return comandaComanda;
	}

	public ComandaComanda removeComandaComanda(ComandaComanda comandaComanda) {
		getComandaComandas().remove(comandaComanda);
		comandaComanda.setAuthUser(null);

		return comandaComanda;
	}

	public List<UsuarisUsuari> getUsuarisUsuaris() {
		return this.usuarisUsuaris;
	}

	public void setUsuarisUsuaris(List<UsuarisUsuari> usuarisUsuaris) {
		this.usuarisUsuaris = usuarisUsuaris;
	}

	public UsuarisUsuari addUsuarisUsuari(UsuarisUsuari usuarisUsuari) {
		getUsuarisUsuaris().add(usuarisUsuari);
		usuarisUsuari.setAuthUser(this);

		return usuarisUsuari;
	}

	public UsuarisUsuari removeUsuarisUsuari(UsuarisUsuari usuarisUsuari) {
		getUsuarisUsuaris().remove(usuarisUsuari);
		usuarisUsuari.setAuthUser(null);

		return usuarisUsuari;
	}

}
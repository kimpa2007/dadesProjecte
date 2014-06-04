package net.claudechaillet.projecte;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the comanda_comanda database table.
 * 
 */
@Entity
@Table(name="comanda_comanda")
@NamedQuery(name="ComandaComanda.findAll", query="SELECT c FROM ComandaComanda c")
public class ComandaComanda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHora;

	private String estat;

	private String metodePagament;

	private double total;

	//bi-directional many-to-one association to ComandaTaula
	@ManyToOne
	@JoinColumn(name="taula_id")
	private ComandaTaula comandaTaula;

	//bi-directional many-to-one association to AuthUser
	@ManyToOne
	@JoinColumn(name="usuari_id")
	private AuthUser authUser;

	//bi-directional many-to-one association to ComandaLiniacomanda
	@OneToMany(mappedBy="comandaComanda", cascade = CascadeType.PERSIST)
	private List<ComandaLiniacomanda> comandaLiniacomandas;

	public ComandaComanda() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDataHora() {
		return this.dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public String getEstat() {
		return this.estat;
	}

	public void setEstat(String estat) {
		this.estat = estat;
	}

	public String getMetodePagament() {
		return this.metodePagament;
	}

	public void setMetodePagament(String metodePagament) {
		this.metodePagament = metodePagament;
	}

	public double getTotal() {
		return this.total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public ComandaTaula getComandaTaula() {
		return this.comandaTaula;
	}

	public void setComandaTaula(ComandaTaula comandaTaula) {
		this.comandaTaula = comandaTaula;
	}

	public AuthUser getAuthUser() {
		return this.authUser;
	}

	public void setAuthUser(AuthUser authUser) {
		this.authUser = authUser;
	}

	public List<ComandaLiniacomanda> getComandaLiniacomandas() {
		return this.comandaLiniacomandas;
	}

	public void setComandaLiniacomandas(List<ComandaLiniacomanda> comandaLiniacomandas) {
		this.comandaLiniacomandas = comandaLiniacomandas;
	}

	public ComandaLiniacomanda addComandaLiniacomanda(ComandaLiniacomanda comandaLiniacomanda) {
		getComandaLiniacomandas().add(comandaLiniacomanda);
		comandaLiniacomanda.setComandaComanda(this);

		return comandaLiniacomanda;
	}

	public ComandaLiniacomanda removeComandaLiniacomanda(ComandaLiniacomanda comandaLiniacomanda) {
		getComandaLiniacomandas().remove(comandaLiniacomanda);
		comandaLiniacomanda.setComandaComanda(null);

		return comandaLiniacomanda;
	}

}
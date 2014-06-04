package net.claudechaillet.projecte;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the comanda_taula database table.
 * 
 */
@Entity
@Table(name="comanda_taula")
@NamedQuery(name="ComandaTaula.findAll", query="SELECT c FROM ComandaTaula c")
public class ComandaTaula implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private int capacitat;

	private String estat;

	//bi-directional many-to-one association to ComandaComanda
	@OneToMany(mappedBy="comandaTaula")
	private List<ComandaComanda> comandaComandas;

	public ComandaTaula() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCapacitat() {
		return this.capacitat;
	}

	public void setCapacitat(int capacitat) {
		this.capacitat = capacitat;
	}

	public String getEstat() {
		return this.estat;
	}

	public void setEstat(String estat) {
		this.estat = estat;
	}

	public List<ComandaComanda> getComandaComandas() {
		return this.comandaComandas;
	}

	public void setComandaComandas(List<ComandaComanda> comandaComandas) {
		this.comandaComandas = comandaComandas;
	}

	public ComandaComanda addComandaComanda(ComandaComanda comandaComanda) {
		getComandaComandas().add(comandaComanda);
		comandaComanda.setComandaTaula(this);

		return comandaComanda;
	}

	public ComandaComanda removeComandaComanda(ComandaComanda comandaComanda) {
		getComandaComandas().remove(comandaComanda);
		comandaComanda.setComandaTaula(null);

		return comandaComanda;
	}

}
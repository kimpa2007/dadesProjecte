package net.claudechaillet.projecte;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the comanda_momentapat database table.
 * 
 */
@Entity
@Table(name="comanda_momentapat")
@NamedQuery(name="ComandaMomentapat.findAll", query="SELECT c FROM ComandaMomentapat c")
public class ComandaMomentapat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String descripcio;

	//bi-directional many-to-one association to ComandaLiniacomanda
	@OneToMany(mappedBy="comandaMomentapat")
	private List<ComandaLiniacomanda> comandaLiniacomandas;

	public ComandaMomentapat() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcio() {
		return this.descripcio;
	}

	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}

	public List<ComandaLiniacomanda> getComandaLiniacomandas() {
		return this.comandaLiniacomandas;
	}

	public void setComandaLiniacomandas(List<ComandaLiniacomanda> comandaLiniacomandas) {
		this.comandaLiniacomandas = comandaLiniacomandas;
	}

	public ComandaLiniacomanda addComandaLiniacomanda(ComandaLiniacomanda comandaLiniacomanda) {
		getComandaLiniacomandas().add(comandaLiniacomanda);
		comandaLiniacomanda.setComandaMomentapat(this);

		return comandaLiniacomanda;
	}

	public ComandaLiniacomanda removeComandaLiniacomanda(ComandaLiniacomanda comandaLiniacomanda) {
		getComandaLiniacomandas().remove(comandaLiniacomanda);
		comandaLiniacomanda.setComandaMomentapat(null);

		return comandaLiniacomanda;
	}

}
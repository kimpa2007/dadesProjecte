package net.claudechaillet.projecte;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the comanda_liniacomanda database table.
 * 
 */
@Entity
@Table(name="comanda_liniacomanda")
@NamedQuery(name="ComandaLiniacomanda.findAll", query="SELECT c FROM ComandaLiniacomanda c")
public class ComandaLiniacomanda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Lob
	private String commentari;

	private String opcio;

	private double total;

	//bi-directional many-to-one association to ComandaComanda
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="comanda_id")
	private ComandaComanda comandaComanda;

	//bi-directional many-to-one association to ComandaMomentapat
	@ManyToOne
	@JoinColumn(name="momentApat_id")
	private ComandaMomentapat comandaMomentapat;

	//bi-directional many-to-one association to ProductesProducte
	@ManyToOne
	@JoinColumn(name="producte_id")
	private ProductesProducte productesProducte;

	public ComandaLiniacomanda() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCommentari() {
		return this.commentari;
	}

	public void setCommentari(String commentari) {
		this.commentari = commentari;
	}

	public String getOpcio() {
		return this.opcio;
	}

	public void setOpcio(String opcio) {
		this.opcio = opcio;
	}

	public double getTotal() {
		return this.total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public void sumaTotal(){
		total++;
	}
	
	public ComandaComanda getComandaComanda() {
		return this.comandaComanda;
	}

	public void setComandaComanda(ComandaComanda comandaComanda) {
		this.comandaComanda = comandaComanda;
	}

	public ComandaMomentapat getComandaMomentapat() {
		return this.comandaMomentapat;
	}

	public void setComandaMomentapat(ComandaMomentapat comandaMomentapat) {
		this.comandaMomentapat = comandaMomentapat;
	}

	public ProductesProducte getProductesProducte() {
		return this.productesProducte;
	}

	public void setProductesProducte(ProductesProducte productesProducte) {
		this.productesProducte = productesProducte;
	}

}
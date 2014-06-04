package net.claudechaillet.projecte;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the productes_producte database table.
 * 
 */
@Entity
@Table(name="productes_producte")
@NamedQuery(name="ProductesProducte.findAll", query="SELECT p FROM ProductesProducte p")
public class ProductesProducte implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String imatge;

	private BigDecimal preu;

	private String producte;

	//bi-directional many-to-one association to ComandaLiniacomanda
	@OneToMany(mappedBy="productesProducte")
	private List<ComandaLiniacomanda> comandaLiniacomandas;

	//bi-directional many-to-one association to ProductesCategoria
	@ManyToOne
	@JoinColumn(name="categoria_id")
	private ProductesCategoria productesCategoria;

	public ProductesProducte() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImatge() {
		return this.imatge;
	}

	public void setImatge(String imatge) {
		this.imatge = imatge;
	}

	public BigDecimal getPreu() {
		return this.preu;
	}

	public void setPreu(BigDecimal preu) {
		this.preu = preu;
	}

	public String getProducte() {
		return this.producte;
	}

	public void setProducte(String producte) {
		this.producte = producte;
	}

	public List<ComandaLiniacomanda> getComandaLiniacomandas() {
		return this.comandaLiniacomandas;
	}

	public void setComandaLiniacomandas(List<ComandaLiniacomanda> comandaLiniacomandas) {
		this.comandaLiniacomandas = comandaLiniacomandas;
	}

	public ComandaLiniacomanda addComandaLiniacomanda(ComandaLiniacomanda comandaLiniacomanda) {
		getComandaLiniacomandas().add(comandaLiniacomanda);
		comandaLiniacomanda.setProductesProducte(this);

		return comandaLiniacomanda;
	}

	public ComandaLiniacomanda removeComandaLiniacomanda(ComandaLiniacomanda comandaLiniacomanda) {
		getComandaLiniacomandas().remove(comandaLiniacomanda);
		comandaLiniacomanda.setProductesProducte(null);

		return comandaLiniacomanda;
	}

	public ProductesCategoria getProductesCategoria() {
		return this.productesCategoria;
	}

	public void setProductesCategoria(ProductesCategoria productesCategoria) {
		this.productesCategoria = productesCategoria;
	}

}
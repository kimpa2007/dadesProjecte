package net.claudechaillet.projecte;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the productes_opcio database table.
 * 
 */
@Entity
@Table(name="productes_opcio")
@NamedQuery(name="ProductesOpcio.findAll", query="SELECT p FROM ProductesOpcio p")
public class ProductesOpcio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Lob
	private String descripcio;

	//bi-directional many-to-one association to ProductesCategoriaOpcio
	@OneToMany(mappedBy="productesOpcio")
	private List<ProductesCategoriaOpcio> productesCategoriaOpcios;

	public ProductesOpcio() {
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

	public List<ProductesCategoriaOpcio> getProductesCategoriaOpcios() {
		return this.productesCategoriaOpcios;
	}

	public void setProductesCategoriaOpcios(List<ProductesCategoriaOpcio> productesCategoriaOpcios) {
		this.productesCategoriaOpcios = productesCategoriaOpcios;
	}

	public ProductesCategoriaOpcio addProductesCategoriaOpcio(ProductesCategoriaOpcio productesCategoriaOpcio) {
		getProductesCategoriaOpcios().add(productesCategoriaOpcio);
		productesCategoriaOpcio.setProductesOpcio(this);

		return productesCategoriaOpcio;
	}

	public ProductesCategoriaOpcio removeProductesCategoriaOpcio(ProductesCategoriaOpcio productesCategoriaOpcio) {
		getProductesCategoriaOpcios().remove(productesCategoriaOpcio);
		productesCategoriaOpcio.setProductesOpcio(null);

		return productesCategoriaOpcio;
	}

}
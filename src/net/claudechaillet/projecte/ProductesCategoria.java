package net.claudechaillet.projecte;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the productes_categoria database table.
 * 
 */
@Entity
@Table(name="productes_categoria")
@NamedQuery(name="ProductesCategoria.findAll", query="SELECT p FROM ProductesCategoria p")
public class ProductesCategoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String categoria;

	private String imatge;

	//bi-directional many-to-one association to ProductesCategoriaOpcio
	@OneToMany(mappedBy="productesCategoria")
	private List<ProductesCategoriaOpcio> productesCategoriaOpcios;

	//bi-directional many-to-one association to ProductesProducte
	@OneToMany(mappedBy="productesCategoria")
	private List<ProductesProducte> productesProductes;

	public ProductesCategoria() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getImatge() {
		return this.imatge;
	}

	public void setImatge(String imatge) {
		this.imatge = imatge;
	}

	public List<ProductesCategoriaOpcio> getProductesCategoriaOpcios() {
		return this.productesCategoriaOpcios;
	}

	public void setProductesCategoriaOpcios(List<ProductesCategoriaOpcio> productesCategoriaOpcios) {
		this.productesCategoriaOpcios = productesCategoriaOpcios;
	}

	public ProductesCategoriaOpcio addProductesCategoriaOpcio(ProductesCategoriaOpcio productesCategoriaOpcio) {
		getProductesCategoriaOpcios().add(productesCategoriaOpcio);
		productesCategoriaOpcio.setProductesCategoria(this);

		return productesCategoriaOpcio;
	}

	public ProductesCategoriaOpcio removeProductesCategoriaOpcio(ProductesCategoriaOpcio productesCategoriaOpcio) {
		getProductesCategoriaOpcios().remove(productesCategoriaOpcio);
		productesCategoriaOpcio.setProductesCategoria(null);

		return productesCategoriaOpcio;
	}

	public List<ProductesProducte> getProductesProductes() {
		return this.productesProductes;
	}

	public void setProductesProductes(List<ProductesProducte> productesProductes) {
		this.productesProductes = productesProductes;
	}

	public ProductesProducte addProductesProducte(ProductesProducte productesProducte) {
		getProductesProductes().add(productesProducte);
		productesProducte.setProductesCategoria(this);

		return productesProducte;
	}

	public ProductesProducte removeProductesProducte(ProductesProducte productesProducte) {
		getProductesProductes().remove(productesProducte);
		productesProducte.setProductesCategoria(null);

		return productesProducte;
	}

}
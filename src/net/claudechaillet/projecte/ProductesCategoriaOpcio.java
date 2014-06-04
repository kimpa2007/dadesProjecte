package net.claudechaillet.projecte;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the productes_categoria_opcio database table.
 * 
 */
@Entity
@Table(name="productes_categoria_opcio")
@NamedQuery(name="ProductesCategoriaOpcio.findAll", query="SELECT p FROM ProductesCategoriaOpcio p")
public class ProductesCategoriaOpcio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	//bi-directional many-to-one association to ProductesCategoria
	@ManyToOne
	@JoinColumn(name="categoria_id")
	private ProductesCategoria productesCategoria;

	//bi-directional many-to-one association to ProductesOpcio
	@ManyToOne
	@JoinColumn(name="opcio_id")
	private ProductesOpcio productesOpcio;

	public ProductesCategoriaOpcio() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ProductesCategoria getProductesCategoria() {
		return this.productesCategoria;
	}

	public void setProductesCategoria(ProductesCategoria productesCategoria) {
		this.productesCategoria = productesCategoria;
	}

	public ProductesOpcio getProductesOpcio() {
		return this.productesOpcio;
	}

	public void setProductesOpcio(ProductesOpcio productesOpcio) {
		this.productesOpcio = productesOpcio;
	}

}
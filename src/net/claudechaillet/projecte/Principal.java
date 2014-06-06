package net.claudechaillet.projecte;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Principal {
	static EntityManagerFactory emf =  Persistence.createEntityManagerFactory("tpv");
	static EntityManager em = emf.createEntityManager();
	
	public static void main(String[] args) {
		try {
			Document doc = Jsoup.connect("http://www.bdfutbol.com/es/a/j___barcelona.html").get();
			Elements trJugadors = doc.select("#taul").select("tr");
			ArrayList<String> usuaris = new ArrayList<String>();
			
			for (int i=1; i<1000; i++){
				String linkJugador = trJugadors.get(i).select("td").select("a").attr("href");
				String nomJugador =  trJugadors.get(i).select("td").get(1).text();
				String usuari = trJugadors.get(i).select("td").get(0).text().replaceAll(" ", "") ;
				if (usuaris.contains(usuari)){
					String u = usuari + "1";
					usuaris.add(u);
					System.out.println(u);
				}
				else{
					usuaris.add(usuari);
					System.out.println(usuari);
				}
				//Anar a la web del jugador i recuperar en quants equips ha estat
				Integer totalEquips = recuperarTotalEquips(linkJugador);
				System.out.println(nomJugador + " " + totalEquips);
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				//Crear 100 usuaris
				AuthUser a = new AuthUser();
				a.setUsername(usuari);
				a.setFirstName(nomJugador);
				a.setPassword("pbkdf2_sha256$12000$7fYPdEaXq2KL$KsTyIHO91sP0iqy4h5+m2uBBA+J8i5jx3vfqnjSmhOw=");
				a.setIsActive((byte) 1);
				a.setDateJoined(date);
				UsuarisUsuari u = new UsuarisUsuari();
				u.setAuthUser(a);
				
				em.getTransaction().begin();
				em.persist(a);
				em.persist(u);
				em.getTransaction().commit();
				
				for(int j=0; j<totalEquips; j++){
					generarDadesComanda(a);
				}
				
			}

		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private static int recuperarTotalEquips(String linkJ) {
		int totalEquips = 0;
		//Treure els dos punts de davant i substituir-ho per http://www.bdfutbol.com/es/j/j301267.html
		String linkJugador = "http://www.bdfutbol.com/es" + linkJ.substring(2);
		try {
			Document doc = Jsoup.connect(linkJugador).get();
			//Obtenir total de td. El resultat correcte es sempre el penultim td
			Elements tdEquips = doc.select("table.stats").select("tr").get(2).select("td");
			String tdEquip = tdEquips.get(tdEquips.size()-2).text();
			totalEquips = Integer.parseInt(tdEquip);
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}	
		
		return totalEquips;
	}
	
	
	
	
	private static void generarDadesComanda(AuthUser usuari){

		ArrayList<ComandaLiniacomanda> coml = new ArrayList<ComandaLiniacomanda>();
		Random r = new Random();

		
		TypedQuery c = (TypedQuery) em.createQuery("select c from ProductesCategoria c");
		List<ProductesCategoria> categories =  c.getResultList();
		
		TypedQuery t = (TypedQuery) em.createQuery("select t from ComandaTaula t");
		List<ComandaTaula> taules = t.getResultList();
		
		TypedQuery mp = (TypedQuery) em.createQuery("select mp from ComandaMomentapat mp");
		List<ComandaMomentapat> moments = mp.getResultList();
		
		Boolean taulaLliure = false;
		
		//1. Agafar una taula de forma aleatoria. Ens quedarem amb la capacitat que serà el nombre de persones que tindrem
		while(!taulaLliure){
			int posicioArray = r.nextInt( taules.size());
			int capacitat = taules.get(posicioArray).getCapacitat();
			
			// S'ha de comprovar que la taula agafada sigui disponible.
			if(taules.get(posicioArray).getEstat().equals("disponible")){
				taulaLliure = true;
				ComandaTaula taula = taules.get(posicioArray);
				System.out.println("num t" + taules.get(posicioArray).getId() + " capactitat" + capacitat);
				//Creo la comanda
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				
				ComandaComanda comandeta = new ComandaComanda();
				comandeta.setDataHora(date);
				comandeta.setAuthUser(usuari);
				comandeta.setComandaTaula(taula);
				comandeta.setEstat("Pagada");

				int idComanda = comandeta.getId();
				//Si la taula es disponible passem a les linies de factura.
				
				for(int i=0; i<capacitat; i++){
						//Busca només un
						TypedQuery p = (TypedQuery) em.createQuery("select p from ProductesProducte p WHERE p.productesCategoria.id = 3");
						List<ProductesProducte> productes = p.getResultList();
						int posicioProducte = r.nextInt(productes.size());
						ProductesProducte pr = productes.get(posicioProducte);
						
						TypedQuery op = (TypedQuery) em.createQuery("select o from ProductesCategoriaOpcio o WHERE o.productesCategoria.id = 3");
						List<ProductesCategoriaOpcio> opcions = op.getResultList();
						
						int posicioOpcio = r.nextInt(opcions.size());
						String opcioneta = opcions.get(posicioOpcio).getProductesOpcio().getDescripcio();
						ComandaMomentapat ma = moments.get(0);
						Boolean existeix = false;
						Integer posicio = null;
						
						ComandaLiniacomanda cl = new ComandaLiniacomanda();

						//Tot això per evitar multivaluats en la base de dades...
						//Comprovem que el producte es vulgui al mateix moment, mateixa opcio i tal. 
						//Si no fos perquè no hi haurà cap comentari, s'hauria de comprovar que els comentaris siguin els mateixos.
						
						for(int j=0; j<coml.size(); j++){
							System.out.println(coml.get(j).getProductesProducte().getId());
							if(coml.get(j).getProductesProducte().getId() == pr.getId()){
								if(coml.get(j).getComandaMomentapat().getId() == ma.getId()){
									if(coml.get(j).getOpcio() == opcioneta){
										existeix = true;
										posicio = j;
										break;
									}
								}
							}
						}
						//I festa, cafe per la mitat de la gent que hi hagi a la taula!
						if (existeix){
							coml.get(posicio).sumaTotal();
						}
						
						else{
							cl.setComandaComanda(comandeta);
							cl.setProductesProducte(pr);
							cl.setTotal(1);
							cl.setOpcio(opcioneta);
							cl.setComandaMomentapat(ma);
							coml.add(cl);
						}
						
						
						//Posare un aigua i un vi anda...
						TypedQuery a = (TypedQuery) em.createQuery("select p from ProductesProducte p WHERE p.productesCategoria.categoria = 'Aigües'");
						List<ProductesProducte> aigues = a.getResultList();

						TypedQuery vi = (TypedQuery) em.createQuery("select p from ProductesProducte p WHERE p.productesCategoria.categoria = 'Vins'");
						List<ProductesProducte> vins = vi.getResultList();
						
						int aig = r.nextInt(aigues.size());
						int vi1 = r.nextInt(vins.size());
						
						ComandaLiniacomanda cl1 = new ComandaLiniacomanda();
						cl1.setComandaComanda(comandeta);
						cl1.setProductesProducte(vins.get(vi1));
						cl1.setTotal(1);
						cl1.setComandaMomentapat(ma);
						coml.add(cl1);
						
						ComandaLiniacomanda cl2 = new ComandaLiniacomanda();
						cl2.setComandaComanda(comandeta);
						cl2.setProductesProducte(aigues.get(aig));
						cl2.setTotal(1);
						cl2.setComandaMomentapat(ma);
						coml.add(cl2);
						
						double total = 0.00;
						double iva = 7.00;
						for(int n=0; n<coml.size(); n++){
							BigDecimal preuUnitari = coml.get(n).getProductesProducte().getPreu();
							double quantitat = coml.get(n).getTotal();
							double producte = preuUnitari.doubleValue();
							total = total + (producte*quantitat);
						}
						comandeta.setTotal(total);
						comandeta.setComandaLiniacomandas(coml);
						em.getTransaction().begin();
						em.persist(comandeta);
						em.getTransaction().commit();
					}
			}
		}		
	}
}

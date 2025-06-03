package juego;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;


public class Mago {
	
	private int alto;
	private int ancho;
	private int x;
	private int y;
	private int margen;
	private double tamañoMago;
	private boolean direccion;				//IZQUIERDA O DERECHA
	
	//imagenes
	private Image izq;
	private Image der;
	private Image arriba;
	private Image abajo;
	 
	private String direccionHechizo = "abajo"; // puede ser: "arriba", "abajo", "izquierda", "derecha"

	private int energiaMagica = 100;  // Energía inicial
	private int vida = 100; // Vida inicial del mago

	
	
	public Mago(int alto, int ancho, int x, int y){
	this.x = x;
	this.y = y;
	this.margen = 20;
	this.alto = alto;
	this.ancho = ancho;
	this.tamañoMago = 0.3;		//tamaño de la imagen
	this.arriba	= Herramientas.cargarImagen("imagenes/mago-arriba.png");
	this.abajo = Herramientas.cargarImagen("imagenes/mago-abajo.png");
	this.izq = Herramientas.cargarImagen("imagenes/mago-der.png");
	this.der = Herramientas.cargarImagen("imagenes/mago-izq.png");
	}
	
	public void dibujar(Entorno e) {
	    if (direccionHechizo.equals("izquierda")) {
	        e.dibujarImagen(this.der, this.x, this.y, 0, this.tamañoMago);
	    } else if (direccionHechizo.equals("derecha")) {
	        e.dibujarImagen(this.izq, this.x, this.y, 0, this.tamañoMago);
	    } else if (direccionHechizo.equals("arriba")) {
	        e.dibujarImagen(this.arriba, this.x, this.y, 0, this.tamañoMago);
	    } else {
	        e.dibujarImagen(this.abajo, this.x, this.y, 0, this.tamañoMago);
	    }
	}


///////////////////////////////////////////////////////////////////////////	

	
	
	public int getVida() {
	    return this.vida;
	}
	public void setVida(int vida) {
	    this.vida = vida;
	}
	
	public void quitarVida(int dañoMurcielago) {
		this.setVida(this.getVida() - dañoMurcielago);
	}

	public boolean dentroLimiteIzquierdo() {
		return this.x - this.ancho/2 - 2 > 0;
	}

	public boolean dentroLimiteDerecho(){
		return this.x + this.ancho/2 + 2 < 800;
	}
	public int limiteDerecho() {
		return this.x + this.ancho / 2;
	}
	

	public int limiteIzquierdo() {
		return this.x - this.ancho / 2;
	}

	public int limiteSuperior() {
		return this.y - this.alto / 2 + 1;
		
	}

	public int limiteInferior() {
		return this.y + this.alto / 2 ;
	}
	
	public boolean dentroLimiteSuperior() {
	    return this.limiteSuperior() > 0 + this.margen;  //con esto evito que sobresalga el mago
	}

	public boolean dentroLimiteInferior() {
	    return this.limiteInferior() < 600 - this.margen; // altura de la pantalla
	}


	public void moverDerecha() {
	    this.x += 2;
	    this.direccionHechizo = "derecha";
	}

	public void moverIzquirda() {
	    this.x -= 2;
	    this.direccionHechizo = "izquierda";
	}

	public void moverArriba() {
	    this.y -= 2;
	    this.direccionHechizo = "arriba";
	}

	public void moverAbajo() {
	    this.y += 2;
	    this.direccionHechizo = "abajo";
	}

	public int getAlto() {
		return alto;
	}
	public int getAncho() {
		return ancho;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public void getDireccion(boolean direccion) {
		this.direccion = direccion;
	}

	public int getEnergiaMagica() {
	    return this.energiaMagica;
	}

	
}

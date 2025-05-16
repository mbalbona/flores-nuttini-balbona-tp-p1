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
	double velocidad;
	double escala;
	boolean direccion;
	boolean estaApoyado;
	Image izq;
	Image der;
	
	public Mago(int alto, int ancho, int x, int y){
	this.x = x;
	this.y = y;
	this.margen = 20;
	this.alto = alto;
	this.ancho = ancho;
	this.escala = 0.3;		//tamaño de la imagen
	this.izq = Herramientas.cargarImagen("imagenes/mago-der.png");
	this.der = Herramientas.cargarImagen("imagenes/mago-izq.png");
	}
	
	public void dibujar(Entorno e) {
		if(direccion) {
			e.dibujarImagen(this.der, this.x, this.y,0, this.escala);
		}
		else {
			e.dibujarImagen(this.izq, this.x, this.y,0, this.escala);
		}
	}
	
	public boolean dentroLimiteIzquierdo() {
		return this.x - this.ancho/2 - 2 > 0;
	}

	public boolean dentroLimiteDerecho(){
		return this.x + this.ancho/2 + 2 < 800;
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


	public int limiteDerecho() {
		return this.x + this.ancho / 2;
	}

	public int limiteIzquierdo() {
		return this.x - this.ancho / 2;
	}

	public void moverDerecha() {
		this.x=x+2;
	}

	public void moverIzquirda() {
		this.x=x-2;
	}
	
	public void moverArriba() {
		this.y=y-2;
	}
	
	public void moverAbajo() {
		this.y=y+2;
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
	
	
	
	
	
	
}

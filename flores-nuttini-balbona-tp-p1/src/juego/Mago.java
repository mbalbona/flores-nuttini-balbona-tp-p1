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
	boolean direccion;				//IZQUIERDA O DERECHA
	
	//hechizo de fuego
	private int fuegoX;
	private int fuegoY;
	private boolean fuegoActivo;
	private boolean direccionFuego;
	
	//hechizo de agua
	private int aguaX;
	private int aguaY;
	private boolean aguaActivo;
	private boolean direccionAgua;
	
	
	private Image izq;
	private Image der;
	private Image fuego;
	private Image agua;
	
	private String hechizoSeleccionado = "fuego"; 
	
	

	
	
	public Mago(int alto, int ancho, int x, int y){
	this.x = x;
	this.y = y;
	this.margen = 20;
	this.alto = alto;
	this.ancho = ancho;
	this.tamañoMago = 0.3;		//tamaño de la imagen
	this.izq = Herramientas.cargarImagen("imagenes/mago-der.png");
	this.der = Herramientas.cargarImagen("imagenes/mago-izq.png");
	this.fuego = Herramientas.cargarImagen("imagenes/hechizo-fuego.png");
	this.agua = Herramientas.cargarImagen("imagenes/hechizo-agua.png");
	}
	
	public void dibujar(Entorno e) {    //DIBUJO EL MAGO
		if(direccion) {
			e.dibujarImagen(this.der, this.x, this.y,0, this.tamañoMago);
		}
		else {
			e.dibujarImagen(this.izq, this.x, this.y,0, this.tamañoMago);
		}
	}
	
	
//////////////////////////////// HECHIZO FUEGO //////////////////////	
	public void lanzarFuego() {
	    if (!fuegoActivo) {
	        this.fuegoX = this.x;
	        this.fuegoY = this.y;
	        this.direccionFuego = this.direccion;
	        this.fuegoActivo = true;
	    }
	}

	public void variosFuegos(Entorno e) {
	    if (fuegoActivo) {				//si hay un fuego activo lo crea
	        if (direccionFuego) {		//si la direccionFuego es true va a la izquierda sino a la derecha
	            fuegoX -= 5; // izquierda
	        } else {
	            fuegoX += 5; // derecha
	        }

	        e.dibujarImagen(this.fuego, fuegoX, fuegoY, 0, this.tamañoMago);

	        if (fuegoX < 0 || fuegoX > 800) {
	            fuegoActivo = false;
	        }
	    }
	}

	
////////////////////////////////////////////////////////////////////////	

///////////////////////////////// HECHIZO AGUA ////////////////////////
	public void lanzarAgua() {
	    if (!aguaActivo) {
	        this.aguaX = this.x;
	        this.aguaY = this.y;
	        this.direccionAgua = this.direccion;
	        this.aguaActivo = true;
	    }
	}

	public void variasAguas(Entorno e) {
	    if (aguaActivo) {
	        if (direccionAgua) {
	            aguaX -= 5; // izquierda
	        } else {
	            aguaX += 5; // derecha
	        }

	        e.dibujarImagen(this.agua, aguaX, aguaY, 0, this.tamañoMago);

	        if (aguaX < 0 || aguaX > 800) {
	            aguaActivo = false;
	        }
	    }
	}

///////////////////////////////////////////////////////////////////////////	

	
	public void seleccionarFuego() {
	    this.hechizoSeleccionado = "fuego";
	}

	public void seleccionarAgua() {
	    this.hechizoSeleccionado = "agua";
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

	public String getHechizoSeleccionado() {
	    return this.hechizoSeleccionado;
	}

	
	
	
	
	
	
}

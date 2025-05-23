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
	
	
	//hechizo de fuego
	private int fuegoX;
	private int fuegoY;
	private boolean fuegoActivo;
	private String direccionFuego;
	
	//hechizo de agua
	private int aguaX;
	private int aguaY;
	private boolean aguaActivo;
	private String direccionAgua;
	
	
	private Image izq;
	private Image der;
	private Image fuego;
	private Image agua;
	private Image arriba;
	private Image abajo;
	
	private String hechizoSeleccionado = "fuego"; 
	private String ultimaDireccion = "abajo"; // puede ser: "arriba", "abajo", "izquierda", "derecha"

	

	
	
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
	this.fuego = Herramientas.cargarImagen("imagenes/hechizo-fuego.png");
	this.agua = Herramientas.cargarImagen("imagenes/hechizo-agua.png");
	}
	
	public void dibujar(Entorno e) {
	    if (ultimaDireccion.equals("izquierda")) {
	        e.dibujarImagen(this.der, this.x, this.y, 0, this.tamañoMago);
	    } else if (ultimaDireccion.equals("derecha")) {
	        e.dibujarImagen(this.izq, this.x, this.y, 0, this.tamañoMago);
	    } else if (ultimaDireccion.equals("arriba")) {
	        e.dibujarImagen(this.arriba, this.x, this.y, 0, this.tamañoMago);
	    } else {
	        e.dibujarImagen(this.abajo, this.x, this.y, 0, this.tamañoMago);
	    }
	}


	
	
	
	
//////////////////////////////// HECHIZO FUEGO //////////////////////	
	public void lanzarFuego() {
	    if (!fuegoActivo) {    // Solo lanza si no hay otro fuego en pantalla
	        this.fuegoX = this.x;
	        this.fuegoY = this.y;
	        this.direccionFuego = this.ultimaDireccion;
	        this.fuegoActivo = true;
	    }
	}
	// El hechizo se lanza en la dirección en la que el mago mira.
	public void variosFuegos(Entorno e) {
	    if (fuegoActivo) {
	        if (direccionFuego.equals("izquierda")) {
	            fuegoX -= 5;
	        } else if (direccionFuego.equals("derecha")) {
	            fuegoX += 5;   //velocidad del hechizo
	        } else if (direccionFuego.equals("arriba")) {
	            fuegoY -= 5;
	        } else if (direccionFuego.equals("abajo")) {
	            fuegoY += 5;
	        }

	        e.dibujarImagen(this.fuego, fuegoX, fuegoY, 0, this.tamañoMago);

	        // Si el hechizo se va fuera de la pantalla, se desactiva
	        if (fuegoX < 0 || fuegoX > 800 || fuegoY < 0 || fuegoY > 600) {
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
	        this.direccionAgua = this.ultimaDireccion;
	        this.aguaActivo = true;
	    }
	}

	public void variasAguas(Entorno e) {
	    if (aguaActivo) {
	        if (direccionAgua.equals("izquierda")) {
	            aguaX -= 5;
	        } else if (direccionAgua.equals("derecha")) {
	            aguaX += 5;
	        } else if (direccionAgua.equals("arriba")) {
	            aguaY -= 5;
	        } else if (direccionAgua.equals("abajo")) {
	            aguaY += 5;
	        }

	        e.dibujarImagen(this.agua, aguaX, aguaY, 0, this.tamañoMago);

	        if (aguaX < 0 || aguaX > 800 || aguaY < 0 || aguaY > 600) {
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
	    this.x += 2;
	    this.ultimaDireccion = "derecha";
	}

	public void moverIzquirda() {
	    this.x -= 2;
	    this.ultimaDireccion = "izquierda";
	}

	public void moverArriba() {
	    this.y -= 2;
	    this.ultimaDireccion = "arriba";
	}

	public void moverAbajo() {
	    this.y += 2;
	    this.ultimaDireccion = "abajo";
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

	public boolean isDireccion() {
		return direccion;
	}

	public void setDireccion(boolean direccion) {
		this.direccion = direccion;
	}

	
	
	
	
	
	
}

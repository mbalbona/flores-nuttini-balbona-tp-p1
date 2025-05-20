package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Fondo {
	private int x;
	private int y;
	private Image fondoPantalla;
	
	public void fondo (Image fondoPantalla, int x, int y) {
		this.x = x;
		this.y = y;
		this.fondoPantalla = Herramientas.cargarImagen("imagenes/fondo-pantalla.png");
	}
	
	Fondo(){
		fondo(this.fondoPantalla, 400, 300);
	}
	
	public void dibujar(Entorno e) {
		e.dibujarImagen(this.fondoPantalla, this.x, this.y, 0, 1.8);
	}
}

package juego;
import java.awt.Color;
import entorno.Entorno;
import entorno.Herramientas;

import java.awt.Image;

public class Roca {
	private int x;
	private int y;
	private int alto;
	private int ancho;
	private Image piedra;
	Roca[] rocas;
	
	public Roca (Image piedra, int x, int y, int alto, int ancho) {
		this.x = x;
		this.y = y;
		this.alto = alto;
		this.ancho = ancho;
		this.piedra = Herramientas.cargarImagen("imagenes/piedras.png");
	}	
	
	Roca(){
		this.rocas = new Roca[5];
		for(int i=0; i<rocas.length;i++) {
			if(i==0) {
				rocas[i] = new Roca(piedra, 200,100,40,40);
			}
			if(i==1) {
				rocas[i] = new Roca(piedra, 500,100,40,40);
			}
			if(i==2) {
				rocas[i] = new Roca(piedra, 200,400,40,40);
			}
			if(i==3) {
				rocas[i] = new Roca(piedra, 500,400,40,40);
			}
			if(i==4) {
				rocas[i] = new Roca(piedra, 350,250,40,40);
			}
		}
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public Image getPiedra() {
		return piedra;
	}

	public void setPiedra(Image piedra) {
		this.piedra = piedra;
	}

	public Roca[] getRocas() {
		return rocas;
	}

	public void setRocas(Roca[] rocas) {
		this.rocas = rocas;
	}

	public void dibujar(Entorno e) {
		for(int i = 0; i<rocas.length;i++) {
			e.dibujarImagen(rocas[i].piedra, rocas[i].x, rocas[i].y, 0, 0.2);
		}
			
	}
}









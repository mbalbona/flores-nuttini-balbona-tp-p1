package juego;
import java.awt.Color;
import entorno.Entorno;
import entorno.Herramientas;

import java.awt.Image;
import java.awt.Point;

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
				rocas[i] = new Roca(piedra, 150,125,90,40);
			}
			if(i==1) {
				rocas[i] = new Roca(piedra, 450,125,90,40);
			}
			if(i==2) {
				rocas[i] = new Roca(piedra, 150,425,90,40);
			}
			if(i==3) {
				rocas[i] = new Roca(piedra, 450,425,90,40);
			}
			if(i==4) {
				rocas[i] = new Roca(piedra, 300,275,90,40);
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
			e.dibujarImagen(rocas[i].piedra, rocas[i].x, rocas[i].y, 0, 0.15);
		}
			
	}
	
	//////PUNTOS DIAGONELES DE LAS PIEDRAS//////
	
	public Point puntoDerechoSuperiorPiedra(Roca roca) {
		Point punto = new Point(roca.getX() + roca.getAncho()/2, roca.getY() - roca.getAlto()/2);
		return punto; 
	}
	public Point puntoIzquierdoSuperiorPiedra(Roca roca) {
		Point punto = new Point(roca.getX() - roca.getAncho()/2, roca.getY() - roca.getAlto()/2);
		return punto; 
	}
	public Point puntoDerechoInferiorPiedra(Roca roca) {
		Point punto = new Point(roca.getX() + roca.getAncho()/2, roca.getY() + roca.getAlto()/2);
		return punto; 
	}
	public Point puntoIzquierdoInferiorPiedra(Roca roca) {
		Point punto = new Point(roca.getX() - roca.getAncho()/2, roca.getY() + roca.getAlto()/2);
		return punto; 
	}
	
	//////LIMITE DE LAS PIEDRAS PARA QUE EL MAGO NO LAS ATRAVIESE//////
	
	public boolean limiteDerechoEnPiedra(Point punto, Roca [] rocas) {
		for (int i = 0; i < rocas.length; i++) {
		if (punto.x <= puntoDerechoSuperiorPiedra(rocas[i]).x && punto.x >= puntoIzquierdoSuperiorPiedra(rocas[i]).x &&
				punto.y >= puntoDerechoSuperiorPiedra(rocas[i]).y && punto.y <= puntoDerechoInferiorPiedra(rocas[i]).y) {
			return true;
			}
		}
		return false;
		
	}
	public boolean limiteIzquierdoEnPiedra(Point punto, Roca [] rocas) {
		for (int i = 0; i < rocas.length; i++) {
			if (punto.x >= puntoIzquierdoSuperiorPiedra(rocas[i]).x && punto.x <= puntoDerechoSuperiorPiedra(rocas[i]).x &&
					punto.y >= puntoIzquierdoSuperiorPiedra(rocas[i]).y && punto.y <= puntoIzquierdoInferiorPiedra(rocas[i]).y) {
				return true;
			}
		}
		return false;
	}
	public boolean limiteSuperiorEnPiedra(Point punto, Roca [] rocas) {
		for (int i = 0; i < rocas.length; i++) {
			if (punto.x <= puntoDerechoSuperiorPiedra(rocas[i]).x && punto.x >= puntoIzquierdoSuperiorPiedra(rocas[i]).x &&
					punto.y >= puntoIzquierdoSuperiorPiedra(rocas[i]).y && punto.y <= puntoIzquierdoInferiorPiedra(rocas[i]).y) {
				return true;
			}
		}
		return false;
	}
	public boolean limiteInferiorEnPiedra(Point punto, Roca [] rocas) {
		for (int i = 0; i < rocas.length; i++) {
			if (punto.x <= puntoDerechoInferiorPiedra(rocas[i]).x && punto.x >= puntoIzquierdoInferiorPiedra(rocas[i]).x &&
					punto.y <= puntoIzquierdoInferiorPiedra(rocas[i]).y && punto.y >= puntoIzquierdoSuperiorPiedra(rocas[i]).y ) {
				return true;
			}
		}
		return false;
	}
	
}









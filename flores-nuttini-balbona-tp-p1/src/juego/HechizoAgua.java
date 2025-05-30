package juego;
import java.awt.Image;
import java.awt.Point;

import entorno.Entorno;
import entorno.Herramientas;


public class HechizoAgua {
	private int x;
	private int y;
	private Point destino;
	private Image agua;
	boolean activo;
	
	public HechizoAgua(int x, int y, Point destino) {
		this.x = x;
		this.y = y;
		this.destino = new Point();
		this.activo = true;
		this.agua = Herramientas.cargarImagen("imagenes/hechizo-agua.png");
	}
	


	
	public void avanzar(Entorno e, int destinoX, int destinoY) {
		if (this.x != destinoX && this.y != destinoY) {
			double x = destinoX - this.x;
			double y = destinoY - this.y;
			double distancia = Math.sqrt(x * x + y * y);
			
			double distanciaX = x / distancia;	
			double distanciaY = y / distancia;		
			
			this.x += distanciaX * 5;
			this.y += distanciaY * 5;
			
			
			e.dibujarImagen(agua, this.x, this.y, 0, 0.5);
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
	public Point getDestino() {
		return destino;
	}
	public void setDestino(Point destino) {
		this.destino = destino;
	}
	public Image getAgua() {
		return agua;
	}
	public void setAgua(Image agua) {
		this.agua = agua;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}

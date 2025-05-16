package juego;

public class Vampiros {
	private int vida;
	private int defensa;
	private int x, y;
	private int alto, ancho;
	private int margen;
	private double escala;
	private double velocidad;
	private boolean estaVivo;
	
	Vampiros(int x, int y, int alto, int ancho){
		this.x = x;
		this.y = y;
		this.alto = alto;
		this.ancho = ancho;
		this.escala = 0.2;
		this.margen = 20;
		this.vida = 10;
		this.defensa = 10;
		this.velocidad = 2;
		
		
	}
	public void getX() {};
	public void getY() {};
	public void getVelocidad() {};
	public void getAncho() {};
	public void getAlto() {};
	public void getEscala() {};
	public void getEstaVivo() {};
	public void getVida() {};
	public void getDefensa() {};
	
	
}

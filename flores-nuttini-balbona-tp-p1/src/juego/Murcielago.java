package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Murcielago {
	private int x, y;
	private double velocidad;
	private double escala;
	private int vida;
	private int defensa;
	Image imgMurcielago;
	
	public Murcielago() {
		this.x = 300;
		this.y = 200;
		this.velocidad = 3;
		this.escala = 0.5;
		this.vida = 100;
		this.defensa = 100;
		imgMurcielago = Herramientas.cargarImagen("imagenes/bat.gif");
	}
	
	////////GETTERS Y SETTERS////////////
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
	public double getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}
	public double getEscala() {
		return escala;
	}
	public void setEscala(double escala) {
		this.escala = escala;
	}
	public int getVida() {
		return vida;
	}
	public void setVida(int vida) {
		this.vida = vida;
	}
	public int getDefensa() {
		return defensa;
	}
	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}

	//////////////////////// DIBUJAR MURCIELAGO ///////////////////////
	public void dibujar(Entorno e) {
		e.dibujarImagen(this.imgMurcielago, this.x, this.y, 0, this.escala);
	}
	//////////////////////// MOVIMIENTO HACIA EL JUGADOR///////////////////////
	public void moverHaciaJugador(int posJugadorX, int posJugadorY) {

		// Calcula la diferencia en X e Y entre el murciélago y el jugador
		double x = posJugadorX - this.x;
		double y = posJugadorY - this.y;

		// Calcula la distancia total entre el murciélago y el jugador
		// Usamos la fórmula de la distancia euclidiana: sqrt(dx^2 + dy^2)
		///(HIPOTENUSA) * (HIPOTENUSA) TEOREMA DE PITAGORAS PARA CALCULAR LA DISTANCIA MAS CORTA EN LINEA RECTA
		///LUEGO ESE RESULTADO CON MATH.SQRT QUE SIGNIFICA RAIZ CUADRADA REALIZAMOS LA OPERACION Y
		///OBTENEMOS EL LARGO DE LA HIPOTENUSA DEFINITIVA
		double distancia = Math.sqrt(x * x + y * y);   

		// Evita dividir por cero si el murciélago ya está en la posición del jugador
		if (distancia > 0) {
			// Calcula los componentes del vector de dirección normalizado
			// (un vector de longitud 1 que apunta hacia el jugador)
			double dirX = x / distancia;
			double dirY = y / distancia;

			// Actualiza la posición del murciélago
			// Multiplica el vector de dirección y lo normaliza con la velocidad seteada
			this.x += dirX * this.velocidad;
			this.y += dirY * this.velocidad;

			//evita que el murciélago "oscile" alrededor del jugador.
			if (Math.abs(this.x - posJugadorX) < this.velocidad && Math.abs(this.y - posJugadorY) < this.velocidad) {
				this.x = posJugadorX;
				this.y = posJugadorY;
			}
		}
	}
	
	
}

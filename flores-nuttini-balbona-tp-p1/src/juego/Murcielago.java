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
	private int daño;
	private boolean estaVivo;
	Image imgMurcielago;
	
	public Murcielago() {
		this.x = -100;
		this.y = -100;
		this.velocidad = 2;
		this.escala = 0.1;
		this.vida = 100;
		this.defensa = 100;
		this.daño = 10;
		this.setEstaVivo(true);
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
	
	public boolean getEstaVivo() {
		return estaVivo;
	}

	public void setEstaVivo(boolean estaVivo) {
		this.estaVivo = estaVivo;
	}

	public int getDaño() {
		return daño;
	}

	public void setDaño(int daño) {
		this.daño = daño;
	}

	//////////////////////// DIBUJAR MURCIELAGO ///////////////////////
	public void dibujar(Entorno e) {
		e.dibujarImagen(this.imgMurcielago, this.x, this.y, 0, this.escala);
	}
	//////////////////////// MOVIMIENTO HACIA EL JUGADOR///////////////////////
	public void moverHaciaJugador(int posJugadorX, int posJugadorY) {

		/// Calcula la diferencia en X e Y entre el murciélago y el jugador
		double x = posJugadorX - this.x;
		double y = posJugadorY - this.y;

		/// Calcula la distancia total entre el murciélago y el jugador
		///OBTENEMOS LA DISTANCIA MAS CORTA EN LINEA RECTA 
		///ENTRE DOS PUNTOS EN UN MAPA (EN ESTE CASO LA PANTALLA) PERMITIENDONOS SABER CUANTOS "PASOS" DEBE DAR
		///EL MURCIELAGO PARA LLEGAR AL JUGADOR COMBINANDO MOVIMIENTOS VERTICALES Y HORIZONTALES.
		double distancia = Math.sqrt(x * x + y * y);   

		/// Evita dividir por cero si el murciélago ya está en la posición del jugador
		if (distancia > 0) {
			
			/// Crea un vector de direccion y calcula los componentes del vector
			/// (un vector de longitud 1 que apunta hacia el jugador)
			/// Tiene direccion 1 para que la distancia siempre sea la misma para que apunte perfectamente al jugador
			/// y no "cuanta" distancia hay, asi podemos controlarlo con la variable "Velocidad"
			
			double direccionX = x / distancia;	
			double direccionY = y / distancia;

			/// Actualiza la posición del murciélago
			/// Multiplica el vector de dirección y lo normaliza con la velocidad seteada
			///
			this.x += direccionX * this.velocidad;
			this.y += direccionY * this.velocidad;

			///evita que el murciélago "oscile" alrededor del jugador.
			///calcula el valor absoluto entre el X e Y del murcielago y el X e Y del jugador
			///y compara la distancia que le falta para llegar al jugador y si es verdadero,
			///la posicion del murcielga queda igual a la posicion del jugador para que no se sobrepase
			///
			///Supongamos que la posicion del murcielago es (98,100) y la del jugador es (100,100) y tenemos una velocidad de 5
			///la proxima vez que se mueva el murcielago quedaria en (103,100) lo cual se pasaria la posicion X, esto evita que eso pase

			if (Math.abs(this.x - posJugadorX) < this.velocidad && Math.abs(this.y - posJugadorY) < this.velocidad) { 
				this.x = posJugadorX;
				this.y = posJugadorY;
			}
		}
	}

	//////////////////////// DESACTIVAR MURCIELAGO///////////////////////
	public void desactivar() {
		this.estaVivo = false;
	}
	
	
}

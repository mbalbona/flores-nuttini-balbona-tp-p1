package juego;
import java.awt.Color;
import java.util.Random;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	
	private Entorno entorno;
	private boolean espacioPresionado = false;
	private boolean enterPresionado = false;
	Mago gondolf;
	Murcielago[] murcielagos;
	Roca rocas;
	Fondo fondo;
	
	///VARIABLES QUE CONTROLAN LA APARICION DE MOBS
	private Random random;
	private static final int cantMurcielagos = 50;
	private int intervaloAparicion = 60;
	private int contadorAparicion = 0;
	
	///DIMENSIONES DE LA VENTANA DE JUEGO
	private int anchoPantalla = 800;
	private int altoPantalla = 600;
	private int margenPantalla = 50;
	
	///CONSTRUCTOR
	public Juego(){
		///INICIAMOS OBJETOS ESENCIALES
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
		this.gondolf = new Mago (10,40,390,530);
		this.rocas = new Roca();
		this.fondo = new Fondo();
		this.random = new Random();
		
		///MOBS
		this.murcielagos = new Murcielago[this.cantMurcielagos];
		///LLENAMOS EL ARRAY DE OBJETOS DE MURCIELAGO
				for(int i = 0; i < this.cantMurcielagos; i++) {
					murcielagos[i] = new Murcielago();
				}
		
			
		///SE INICIA EL ENTORNO DEL JUEGO
		this.entorno.iniciar();
	}
	
	private void añadirMurcielagoEnPosicionAleatoria() {
		for(int i = 0; i < this.cantMurcielagos;i++) {
			if(this.murcielagos[i].getX() == -100 && this.murcielagos[i].getY() == -100) {
				int nuevaX = random.nextInt(this.anchoPantalla);
				int nuevaY = random.nextInt(this.altoPantalla);
				this.murcielagos[i].setX(nuevaX);
				this.murcielagos[i].setY(nuevaY);
				return;
			}
		}
	}
		
	
	
	public void tick()
	{
		
		
		/////////////////// DIBUJAR FONDO Y ROCAS DENTRO DEL MAPA/////////////////////
		
		this.fondo.dibujar(entorno);
		this.rocas.dibujar(entorno);
		
		/////////////////// DIBUJAR A GONDOLF,MOVER,LANZAR///////////////////// 
	
		this.gondolf.dibujar(entorno);
		if (entorno.estaPresionada(entorno.TECLA_DERECHA)) {
			gondolf.direccion = false;	//cambia la direccion
			if (gondolf.dentroLimiteDerecho()) {
			this.gondolf.moverDerecha();
		}
		}
		
		if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
			gondolf.direccion = true;
			if (gondolf.dentroLimiteIzquierdo()) {
			this.gondolf.moverIzquirda();
			}
		}
		
		if (entorno.estaPresionada(entorno.TECLA_ARRIBA)) {
		    if (gondolf.dentroLimiteSuperior()) {
		        this.gondolf.moverArriba();
		    }
		}

		if (entorno.estaPresionada(entorno.TECLA_ABAJO)) {
		    if (gondolf.dentroLimiteInferior()) {
		        this.gondolf.moverAbajo();
		    }
		}
				
		
		
		//////////////////////// LANZAR FUEGO O AGUA CON 1 CLICK ///////////////////////
		if (entorno.estaPresionada('F')) {  // F para fuego
		    gondolf.seleccionarFuego();
		}
		if (entorno.estaPresionada('A')) {  // A para agua
		    gondolf.seleccionarAgua();
		}

		
		if (entorno.estaPresionado(entorno.BOTON_IZQUIERDO)) {
			if (gondolf.getHechizoSeleccionado().equals("fuego")) {
	            gondolf.lanzarFuego();
	        } else if (gondolf.getHechizoSeleccionado().equals("agua")) {
	            gondolf.lanzarAgua();
	        }
		}
		
		////////////////////////CONTROL DE APARICION///////////////////////
		this.contadorAparicion++;
		if(this.contadorAparicion >= this.intervaloAparicion) {
			System.out.println("Tiempo de aparicion");
			añadirMurcielagoEnPosicionAleatoria();
			this.contadorAparicion = 0;
		}
		
		//////////////////////CICLO PARA DIBUJAR LOS MURCIELAGOS///////////////////////
		for(int i = 0; i < this.cantMurcielagos; i++) {
			Murcielago m = this.murcielagos[i];
			if(m.getX() != -100 || m.getY() != -100) {
				m.moverHaciaJugador(this.gondolf.getX(), this.gondolf.getY());
				m.dibujar(entorno);
			}
		}
		
		
	}



	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}

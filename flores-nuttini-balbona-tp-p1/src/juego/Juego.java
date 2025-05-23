package juego;


import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	
	private Entorno entorno;
	
	private Menu menu;

	Mago gondolf;
	Murcielago murcielago;
	Roca rocas;
	Fondo fondo;
	
	
	Juego(){
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
		this.gondolf = new Mago (30,45,390,530);
		this.murcielago = new Murcielago();
		this.rocas = new Roca();
		this.fondo = new Fondo();
		this.menu = new Menu();

		this.entorno.iniciar();
	}
	
	
	public void tick()
	{
		
		
		/////////////////// DIBUJAR FONDO Y ROCAS DENTRO DEL MAPA/////////////////////
		
		this.fondo.dibujar(entorno);
		this.rocas.dibujar(entorno);
		this.menu.dibujar(entorno);
		this.gondolf.dibujar(entorno);
		///////////////////GONDOLF,MOVER,LANZAR///////////////////// 
	
		
		if (entorno.estaPresionada(entorno.TECLA_DERECHA)) {
		    gondolf.getDireccion(false); // mirar a la derecha
		    if (gondolf.dentroLimiteDerecho()) {
		        if (gondolf.limiteDerecho() + 2 < menu.getBordeIzquierdo()) {
		            gondolf.moverDerecha();
		        }
		    }
		}

		
		if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
			gondolf.getDireccion(true);
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
		

		    gondolf.variosFuegos(entorno);
		    gondolf.variasAguas(entorno);
		
		////////////////////////DIBUJAR MURCIELAGO ///////////////////////
		this.murcielago.dibujar(entorno);
		this.murcielago.moverHaciaJugador(this.gondolf.getX(), this.gondolf.getY());

		

		



		}
	
	
	
		
	
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
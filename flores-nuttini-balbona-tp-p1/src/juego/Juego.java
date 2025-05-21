package juego;
import java.awt.Color;

import enemigos.Vampiro;
import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	
	private Entorno entorno;
	private boolean espacioPresionado = false;
	private boolean enterPresionado = false;
	Mago gondolf;
	Vampiro vampiro;
	Roca rocas;
	Fondo fondo;
	
	Juego(){
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
		this.gondolf = new Mago (10,40,390,530);
		this.vampiro = new Vampiro();
		this.rocas = new Roca();
		//this.fondo = new Fondo();
		
		this.entorno.iniciar();
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
		
		///////////////////////////////////////////////////////////////////////////////
		
		
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

		

		//////////////////////////////////////////////////////////////////////
	     //////////////////////// DIBUJAR VAMPIRO ///////////////////////
	    this.vampiro.dibujar(entorno);
		
		
		
		
		
		
		

		}
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}

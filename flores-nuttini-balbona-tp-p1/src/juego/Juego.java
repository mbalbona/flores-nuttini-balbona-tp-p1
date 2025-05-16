package juego;
import java.awt.Color;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	
	private Entorno entorno;
	Mago gondolf;
	
	
	Juego(){
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
		this.gondolf = new Mago (10,40,390,530);
		
		
		this.entorno.iniciar();
	}

	
	public void tick()
	{
		
		
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

	}
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}

package juego;
import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.util.Random;
import javax.sound.sampled.Clip;
import entorno.Entorno;
import entorno.Herramientas;
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
	Menu menu;
	
	Clip game_music;
	
	private boolean enMenu = true;
	private Image imagenMenu;
	
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
		this.gondolf = new Mago (30,45,390,530);
		this.rocas = new Roca();
		this.fondo = new Fondo();
		this.random = new Random();
		this.menu = new Menu();
		this.imagenMenu = Herramientas.cargarImagen("imagenes/juego-menu.png");

		game_music = Herramientas.cargarSonido("sonido/sonido1.wav");
		game_music.loop(Clip.LOOP_CONTINUOUSLY);  
		
		
		
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
		/////////////////////////MENU DE JUEGO INICIO//////////////////////////////////////
		if (enMenu) {
		    entorno.dibujarImagen(imagenMenu, entorno.ancho() / 2, entorno.alto() / 2, 0);
		    
		    if (entorno.sePresiono(entorno.TECLA_ENTER)) {
		        enMenu = false; 
		    }
		    if (entorno.sePresiono(entorno.TECLA_ESCAPE)) {
		        System.exit(0); // 
		    }
		    return; 
		}
		///////////////////////////////////////////////////////////////////////////////////
		
		
		Point puntoLimiteDerechoMago = new Point (gondolf.limiteDerecho(), this.gondolf.getY());      //Delimita en un punto el límite derecho del mago
		Point puntoLimiteIzquierdoMago = new Point (gondolf.limiteIzquierdo(), this.gondolf.getY());  //Delimita en un punto el límite izquierdo del mago
		Point puntoLimiteSuperiorMago = new Point (this.gondolf.getX(), gondolf.limiteSuperior());    //Delimita en un punto el límite superior del mago
		Point puntoLimiteInferiorMago = new Point (this.gondolf.getX(), gondolf.limiteInferior());    //Delimita en un punto el límite inferior del mago
		
		/////////////////// DIBUJAR FONDO Y ROCAS DENTRO DEL MAPA/////////////////////
		
		this.fondo.dibujar(entorno);
		this.rocas.dibujar(entorno);
		this.menu.dibujar(entorno);
		
		/////////////////// DIBUJAR A GONDOLF,MOVER,LANZAR///////////////////// 
	
		this.gondolf.dibujar(entorno);
		if (entorno.estaPresionada(entorno.TECLA_DERECHA)) {
			  gondolf.getDireccion(false); // mirar a la derecha
			  if (gondolf.dentroLimiteDerecho() && rocas.limiteIzquierdoEnPiedra(puntoLimiteDerechoMago, rocas.rocas)==false) {
			      if (gondolf.limiteDerecho() + 2 < menu.getBordeIzquierdo()) {
			          gondolf.moverDerecha();
			      }
			  }
			}
			
			
			if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
				gondolf.getDireccion(true);
				if (gondolf.dentroLimiteIzquierdo() && rocas.limiteDerechoEnPiedra(puntoLimiteIzquierdoMago, rocas.rocas)==false) {
				this.gondolf.moverIzquirda();
				}
			}
			
			if (entorno.estaPresionada(entorno.TECLA_ARRIBA)) {
			  if (gondolf.dentroLimiteSuperior() && rocas.limiteInferiorEnPiedra(puntoLimiteSuperiorMago, rocas.rocas)==false) {
			      this.gondolf.moverArriba();
			  }
			}
			
			if (entorno.estaPresionada(entorno.TECLA_ABAJO)) {
			  if (gondolf.dentroLimiteInferior() && rocas.limiteSuperiorEnPiedra(puntoLimiteInferiorMago, rocas.rocas)==false) {
			      this.gondolf.moverAbajo();
			  }
			}
				
		
		
		//////////////////////// LANZAR FUEGO O AGUA CON 1 CLICK Y CANT HECHIZOS ///////////////////////
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
				
				entorno.cambiarFont(null, 20, Color.RED);
				entorno.escribirTexto("Energía: " + gondolf.getEnergiaMagica(), 650, 578);

		////////////////////////CONTROL DE APARICION/////////////////////////////////////////////////////
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

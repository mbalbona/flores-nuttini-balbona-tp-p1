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
	private int cantMurcielagosMatados = 0;
	private int contadorMurcielagos = 0;
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
	private static int cantMurcielagosTotales = 50;
	private static int maxMurcielagosPantalla = 10;
	private int intervaloAparicion = 100;
	private int contadorAparicion = 0;
	
	///DIMENSIONES DE LA VENTANA DE JUEGO
	private int anchoPantalla = 800;
	private int altoPantalla = 600;
	private int margenPantalla = 50;
	private int margenAparicion = 50;

	
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
		this.murcielagos = new Murcielago[this.cantMurcielagosTotales];	
			
		///SE INICIA EL ENTORNO DEL JUEGO
		this.entorno.iniciar();
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
//			if (entorno.estaPresionada('F')) {  // F para fuego
//				  gondolf.seleccionarFuego();
//				}
//				if (entorno.estaPresionada('A')) {  // A para agua
//				  gondolf.seleccionarAgua();
//				}
				
				
			

			if (entorno.mousePresente() && entorno.sePresionoBoton(1)) {
			    menu.detectarClick(entorno.mouseX(), entorno.mouseY(), gondolf);
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
				
				
				entorno.cambiarFont(null, 30, Color.RED);
				entorno.escribirTexto("Energía: " + gondolf.getEnergiaMagica(), 615, 585);

				//VIDA DEL MAGO
				entorno.cambiarFont(null, 30, Color.GREEN);
				entorno.escribirTexto("Vida: " + gondolf.getVida(), 635, 550);

			
				
		//////////////////////////////////////////////////////////////////////////////////////7
				
	
				
		////////////////////////CONTROL DE APARICION/////////////////////////////////////////////////////
		this.contadorAparicion++;
		if(this.contadorAparicion >= this.intervaloAparicion) {
			if(cantMobsActivos() < maxMurcielagosPantalla) {
				System.out.println("Mobs Activos:" + cantMobsActivos());
				añadirMurcielagoEnPosicionAleatoria();
			}else {
				System.out.println("Maxima cantidad de mobs en pantalla alcanzado:" + maxMurcielagosPantalla);
			}
			this.contadorAparicion = 0;
		}
		
		//////////////////////CICLO PARA DIBUJAR LOS MURCIELAGOS///////////////////////
		for(int i = 0; i < this.cantMurcielagosTotales; i++) {
			Murcielago m = this.murcielagos[i];
			if(m != null) {
				m.moverHaciaJugador(this.gondolf.getX(), this.gondolf.getY());
				
				int margenDistancia = (int)m.getVelocidad();
				
				///Calculo de distancia real
				double auxX = this.gondolf.getX() - m.getX();
				double auxY = this.gondolf.getY() - m.getY();
				double distanciaActual = Math.sqrt(auxX * auxX + auxY * auxY);
				
				if(distanciaActual <= margenDistancia) {
					System.out.println("Murcielago alcanczo al jugador");
					this.murcielagos[i] = null;
					this.gondolf.quitarVida(m.getDaño());
				}
				else {
					m.dibujar(entorno);
					this.contadorMurcielagos++;
				}
			
			}
		}
		//////////////////////CANTIDAD DE MURCIELAGOS TOTALES Y CANTIDAD DE MURCIELAGOS ACTUALES EN PANTALLA///////////////////////
		//entorno.escribirTexto(this.contadorMurcielagos + "/" + this.cantMurcielagos, maxMurcielagosPantalla, altoPantalla, 10, 10);
		
		//////////////////////FINALIZACION DE JUEGO POR VIDA AGOTADA (GAME OVER)////////////////////////
		if(this.gondolf.getVida() <= 0) {
			System.exit(0);
		}
		//////////////////////FINALIZACION DE JUEGO POR HABER MATADO A LOS 50 MURCIELAGOS///////////////////////
		else if(this.cantMurcielagosMatados == 50){
			System.out.println("Ganaste");
		}
	}
	
	private void añadirMurcielagoEnPosicionAleatoria() {
		for(int i = 0; i < this.cantMurcielagosTotales;i++) {
			if(this.murcielagos[i] == null) {
				int nuevaX;
				int nuevaY;
				int anchoJugable = this.anchoPantalla - this.menu.getAncho();
				
				int bordeRandom = this.random.nextInt(4); ///Se elige aleatoriamente un numero que representa los lados
				
				switch(bordeRandom) {
					case 0: ///Borde Superior
						nuevaX = this.random.nextInt(anchoJugable);
						nuevaY = -this.margenAparicion;
						break;
					case 1: ///Borde inferior
						nuevaX = this.random.nextInt(anchoJugable);
						nuevaY = this.altoPantalla + this.margenAparicion;
						break;
					case 2: ///Borde izquierdo
						nuevaX = -this.margenAparicion;
						nuevaY = this.random.nextInt(this.altoPantalla);
						break;
					case 3: ///Borde Derecho
						nuevaX = anchoJugable;
						nuevaY = this.random.nextInt(this.altoPantalla);
						break;
					default:
						nuevaX = -this.margenAparicion;
						nuevaY = -this.margenAparicion;
						break;
						
				}
				this.murcielagos[i] = new Murcielago();
				this.murcielagos[i].setX(nuevaX);
				this.murcielagos[i].setY(nuevaY);
				return;
			}
		}
	}
	
	public int cantMobsActivos() {
		int aux = 0;
		for(int i = 0; i < cantMurcielagosTotales; i++) {
			if(this.murcielagos[i] != null) {
				aux++;
			}
		}
		return aux;
	}

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}

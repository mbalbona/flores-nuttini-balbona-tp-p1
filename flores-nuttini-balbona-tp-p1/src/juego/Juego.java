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
	private Point posMouse = new Point(0,0);
	private boolean eligeHechizo = false;
	private double contadorFuego = 0;
	private double contadorAgua = 0;
	private Point puntoColision = new Point(0,0);
	
	//PERSONAJES - COSAS
	private Mago gondolf;
	private Murcielago[] murcielagos;
	private Roca rocas;
	private Fondo fondo;
	private Menu menu;
	private Image imagenMenu;
	private Image imagenGameOver;
	private Image imagenVictoria;
	private Oleada gestionadorOleadas;
	private HechizoFuego hechizoFuego;
	private HechizoAgua hechizoAgua;
	
	//IMAGENES - SONIDOS
	private Clip game_music;
	private Clip sonidoVictoria;
	private Clip sonidoGameOver;
	private boolean sonidoVictoriaReproducido = false;
	private boolean juegoGanado = false;
	private boolean sonidoGameOverReproducido = false;
	private boolean juegoTerminado = false;
	private boolean enMenu = true;
	

	///VARIABLES QUE CONTROLAN LA APARICION DE MOBS
	private Random random;
	private int cantMurcielagosMatados = 0;
	private static int cantMurcielagosTotales = 50;
	private static int maxMurcielagosPantalla = 10;
	private int intervaloAparicion = 20;
	private int contadorAparicion = 0;
	
	///DIMENSIONES DE LA VENTANA DE JUEGO
	private int anchoPantalla = 800;
	private int altoPantalla = 600;
	private int margenPantalla = 50;
	private int margenAparicion = 50;
	
	private int vidaMago;
	private int energiaMago;
	
	///CONSTRUCTOR
	public Juego(){
		
		
		///INICIAMOS OBJETOS ESENCIALES
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
		this.gondolf = new Mago (30,45,390,530);
		this.rocas = new Roca();
		this.fondo = new Fondo();
		this.random = new Random();
		this.menu = new Menu();
		this.hechizoFuego = new HechizoFuego(gondolf.getX(), gondolf.getY(), posMouse);
		this.hechizoAgua = new HechizoAgua(gondolf.getX(), gondolf.getY(), posMouse);
		this.imagenMenu = Herramientas.cargarImagen("imagenes/juego-menu.png");
		this.imagenVictoria = Herramientas.cargarImagen("imagenes/victoria.png");
		this.sonidoVictoria = Herramientas.cargarSonido("sonido/sonido3.wav");
		this.imagenGameOver = Herramientas.cargarImagen("imagenes/game-over.png");
		this.sonidoGameOver = Herramientas.cargarSonido("sonido/sonido2.wav");
		this.game_music = Herramientas.cargarSonido("sonido/sonido1.wav");
		this.game_music.loop(Clip.LOOP_CONTINUOUSLY);  
		vidaMago = gondolf.getVida();
		energiaMago = gondolf.getEnergiaMagica();

		
		///MOBS
		this.murcielagos = new Murcielago[cantMurcielagosTotales];	
		
		
		////SETEAMOS ARRAY DE MURCIELAGOS EN NULL
		for(int i = 0; i < cantMurcielagosTotales; i++) {
			murcielagos[i] = null;
		}
		
		////INICIALIZAMOS GESTOR DE OLEADAS
		int tiempoDescanso = 300;
		int murcielagosBase = 10;
		int incrementoMurcielagos = 2;
		this.gestionadorOleadas = new Oleada(tiempoDescanso, murcielagosBase, incrementoMurcielagos);
		
		///SE INICIA EL ENTORNO DEL JUEGO
		this.entorno.iniciar();
	}
	
	//RELACION TIEMPO Y TICKS
	//1 SEGUNDO = 100 TICKS
	public void tick()
	{
		//DIBUJAR LA IMAGEN DE VICTORIA Y PAUSAR SONIDO DE INICIO CUANDO GANA
		if (juegoGanado) {
		    entorno.dibujarImagen(imagenVictoria, entorno.ancho() / 2, entorno.alto() / 2, 0);
		    
		    if (!sonidoVictoriaReproducido) {
		        sonidoVictoria.start();
		        sonidoVictoriaReproducido = true;
		   
		    }

		    return; 
		}


		if (juegoTerminado) {
		    entorno.dibujarImagen(imagenGameOver, entorno.ancho() / 2, entorno.alto() / 2, 0);

		    if (!sonidoGameOverReproducido) {
		        sonidoGameOver.start();
		        sonidoGameOverReproducido = true;
		    }

		    return; //COLOCAMOS ARRIBA DE TODO PARA QUE NO SE SIGAN DIBUJANDO MAS LOS PERSONAJES
		}
		///////////////////////////////////////////////////////////////////////////////////
		
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
		
		/////////////////// DIBUJAR A GONDOLF, HECHIZOS, FONDO Y ROCAS DENTRO DEL MAPA/////////////////////
		
		this.fondo.dibujar(entorno);
		this.rocas.dibujar(entorno);
		this.menu.dibujar(entorno);
		this.gondolf.dibujar(entorno);
		
		///////////////////ACA GONDOLF SE MUEVE,LANZA HECHIZOS///////////////////// 
	
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
			///////////////////////ELEGIR QUE HECHIZO LANZAR CON COORDENADAS MOUSE//////////////////////
			if (entorno.mousePresente() && entorno.sePresionoBoton(1) && entorno.mouseX() >= 600) {
			    eligeHechizo = menu.detectarClick(entorno.mouseX(), entorno.mouseY());
			}
			
			
			
			
			
			if (entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
			    posMouse.x = entorno.mouseX();
			    posMouse.y = entorno.mouseY();

			    if (posMouse.x >= 600) {
			        eligeHechizo = menu.detectarClick(posMouse.x, posMouse.y);
			    } else {
			        if (eligeHechizo) {
			            hechizoFuego.lanzar(gondolf.getX(), gondolf.getY(), posMouse.x, posMouse.y);
			        } else {
			            hechizoAgua.lanzar(gondolf.getX(), gondolf.getY(), posMouse.x, posMouse.y);
			        }
			    }
			}
			//encuadra el hechizo seleccionado
			if (eligeHechizo==false) {
				menu.dibRecAgua(entorno);
			}else {
				menu.dibRecFuego(entorno);
			}
			
			hechizoFuego.avanzar();
			if (eligeHechizo == true && entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO) && entorno.mouseX()<600) {
				energiaMago -= hechizoFuego.costoFuego();
			}
			hechizoFuego.dibujar(entorno);

			hechizoAgua.avanzar();
			if (eligeHechizo == false && entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO) && entorno.mouseX()<600) {
				energiaMago -= hechizoAgua.costoAgua();
			}
			hechizoAgua.dibujar(entorno);
			if(hechizoAgua.getX() == posMouse.x && hechizoAgua.getY() == posMouse.y) {
				hechizoAgua.cambiarEstado();
			}

			if(hechizoFuego.estadoExplotar == true && contadorFuego <= 66.00) {
				//hechizoFuego.dibujarAreaExplosion(entorno, puntoColision);
				hechizoFuego.dibujarExplosion(entorno, puntoColision);				
				contadorFuego++;
			}else{
				hechizoFuego.setEstadoExplotar(false);
				contadorFuego = 0;
			}
			
			if(hechizoAgua.estadoExplotar == true && contadorAgua <= 20.00) {
				//hechizoAgua.dibujarAreaExplosion(entorno, puntoColision);
				hechizoAgua.dibujarExplosion(entorno, puntoColision);				
				contadorAgua++;
			}else{
				hechizoAgua.setEstadoExplotar(false);
				contadorAgua = 0;
			}

				//ENERGIA
				entorno.cambiarFont(null, 30, Color.RED);
				entorno.escribirTexto("Energía: " + energiaMago, 615, 585);

				//VIDA DEL MAGO
				entorno.cambiarFont(null, 30, Color.GREEN);
				entorno.escribirTexto("Vida: " + vidaMago, 635, 550);

			
				
		//////////////////////////////////////////////////////////////////////////////////////
		
		////////////////////////////GESTION DE OLEADAS///////////////////////////////////////
		int mobsActivos = cantMobsActivos();
		this.gestionadorOleadas.actualizar(mobsActivos);
	
		
		if(this.gestionadorOleadas.necesitaGenerarEnemigo()) {
			this.contadorAparicion++;
			if(this.contadorAparicion >= this.intervaloAparicion) {
				if(cantMobsActivos() < maxMurcielagosPantalla) {
					añadirMurcielagoEnPosicionAleatoria();
					for(int i = 0; i < this.cantMurcielagosTotales; i++) {
						if(this.murcielagos[i] != null && this.murcielagos[i].getDaño() == 10) {
							this.murcielagos[i].setDaño(this.gestionadorOleadas.getDañoActualMurcielagos());
							System.out.println("Daño:" + this.murcielagos[i].getDaño());
						}
					}
					this.gestionadorOleadas.mobGenerado();
					System.out.println("Mobs Activos:" + cantMobsActivos());
				}else {
					System.out.println("Maxima cantidad de mobs en pantalla alcanzado:" + maxMurcielagosPantalla);
				}
				this.contadorAparicion = 0; ///VUELVE EL CONTADOR A CERO PARA QUE SE CUMPLA EL INTERVALO DE APARICION
			}
		}
		/////////////////////////////////////////////////////////////////////////////////////

		chequearColisionesConHechizos(); //LLAMAMOS AL METODO PARA ELIMINAR MURCIELAGOS ANTES DE DIBUJAR
		if (hechizoAgua.estadoExplotar == true || hechizoFuego.estadoExplotar == true) {
			chequearColisionConExplosion();
		}
		
		//////////////////////CICLO PARA DIBUJAR LOS MURCIELAGOS///////////////////////
		for (int i = 0; i < cantMurcielagosTotales; i++) {
		    Murcielago m = this.murcielagos[i];
		    if (m != null) {
		        m.moverHaciaJugador(this.gondolf.getX(), this.gondolf.getY());
		        
		      
				//////////////////////LOGICA DAÑO AL MAGO CUANDO LOS ENEMIGOS CHOCAN CON EL///////////////////////

		        int margenDistancia = (int)m.getVelocidad();
		        double auxX = this.gondolf.getX() - m.getX();
		        double auxY = this.gondolf.getY() - m.getY();
		        double distanciaActual = Math.sqrt(auxX * auxX + auxY * auxY);
		        
		        if (distanciaActual <= margenDistancia) {
		            this.murcielagos[i] = null;
		            vidaMago -= m.getDaño();
		            this.gestionadorOleadas.mobDerrotado();
		        } else {
		            m.dibujar(entorno);
		        }
				////////////////////////////////////////////////////////////////////////////////

		    }
		}
		////////////////////////////////////////////////////////////////////////////////
		

		//////////////////////TEXTOS RELACIONADOS CON MOBS///////////////////////
		entorno.cambiarFont("consola", 20, Color.WHITE);
		entorno.escribirTexto("Cant. Matados:" + this.cantMurcielagosMatados, 400, 20);
		
		entorno.cambiarFont("consola", 20, Color.WHITE);
		entorno.escribirTexto("Cant Mobs Oleada:" + this.gestionadorOleadas.getmurcielagosEnEstaOleada(), 400, 40);
		
		entorno.cambiarFont("consola", 20, Color.WHITE);
		entorno.escribirTexto("Oleada:" + this.gestionadorOleadas.getNumOleadaActual(), 10, 20);
		
		//////////////////////FINALIZAR JUEGO Y MOSTRAR IMAGEN-SONIDO GAME-OVER)/////////////////////
		if (this.gestionadorOleadas.getNumOleadaActual()-1 == this.gestionadorOleadas.getOleadaGanadora()) {
			juegoGanado = true;
			return;
		}else if(vidaMago <= 0) {
			juegoTerminado = true;
			game_music.stop();
			return;
		}



		

		


	}
		//////////////////////////////////////////////////////////////////////////////////////////////
	
	
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
	
	
	
	//////////////////////////METODOS MAGO ELIMINA MURCIELAGOS//////////////////////////////
	
	private boolean magoFuegoColisionaCon(Murcielago m) {
	    return hechizoFuego.activo &&
	           distancia(m.getX(), m.getY(), hechizoFuego.getX(), hechizoFuego.getY()) < 30;
	}

	private boolean magoAguaColisionaCon(Murcielago m) {
	    return hechizoAgua.activo &&
	           distancia(m.getX(), m.getY(), hechizoAgua.getX(), hechizoAgua.getY()) < 30;
	}

	private double distancia(int x1, int y1, int x2, int y2) {
	    return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}

	private void chequearColisionesConHechizos() {
	    for (int i = 0; i < murcielagos.length; i++) {
	        Murcielago murcielago = murcielagos[i];
	        if (murcielago != null && murcielago.getEstaVivo()) {
	            if (magoFuegoColisionaCon(murcielago)) {
	            	puntoColision.x = murcielagos[i].getX();
	            	puntoColision.y = murcielagos[i].getY();
	                murcielagos[i] = null; // lo eliminás del arreglo
	                cantMurcielagosMatados++;
	               	hechizoFuego.cambiarEstadoExplotar();
		            hechizoFuego.cambiarEstado();
	            } else if (magoAguaColisionaCon(murcielago)) {
	            	puntoColision.x = murcielagos[i].getX();
	            	puntoColision.y = murcielagos[i].getY();
	                murcielagos[i] = null; 
	                hechizoAgua.cambiarEstadoExplotar();
	                hechizoAgua.cambiarEstado();
	                cantMurcielagosMatados++;
	            }
	        }
	    }
	}
	//CUANDO HACE CONTACTO CON LOS VAMPIROS HACE UNA EXPLOSION QUE PERMITE QUE ELIMINE A LOS QUE ESTEN
	//A SU ALREDEDOR
	private boolean colisionConExplosionFuego(Murcielago m) {
		int dx = Math.abs(m.getX()-puntoColision.x);
		int dy = Math.abs(m.getY()-puntoColision.y);
		double radio = hechizoFuego.getDiametroExplosion()/2;
		if(dx * dx + dy * dy < radio * radio) {
			return true;
		}
		return false;
	}
	//COLISION AGUA CON VAMPIROS
	private boolean colisionConExplosionAgua(Murcielago m) {
		int dx = Math.abs(m.getX()-puntoColision.x);
		int dy = Math.abs(m.getY()-puntoColision.y);
		double radio = hechizoAgua.getDiametroExplosion()/2;
		if(dx * dx + dy * dy < radio * radio) {
			return true;
		}
		return false;
	}
	
	private void chequearColisionConExplosion() {
		for (int i = 0; i < murcielagos.length; i++) {
	        Murcielago murcielago = murcielagos[i];
	        if (murcielago != null && murcielago.getEstaVivo()) {
	            if (colisionConExplosionFuego(murcielago) == true) {
	            	murcielagos[i] = null; // lo eliminás del arreglo
	                cantMurcielagosMatados++;
	            }else if (colisionConExplosionAgua(murcielago)) {
		            	murcielagos[i] = null; // lo eliminás del arreglo
		                cantMurcielagosMatados++;
	            }
	        }
		}
	}
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}

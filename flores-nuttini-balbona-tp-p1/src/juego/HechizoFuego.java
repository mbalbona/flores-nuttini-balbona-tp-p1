package juego;
	import java.awt.Image;
	import java.awt.Point;

	import entorno.Entorno;
	import entorno.Herramientas;

	public class HechizoFuego {
		private int x;
		private int y;
		private Image fuego;
		private Image fuegoExplosion;
		boolean activo;
		boolean estadoExplotar;
		private double dx, dy;
	    private int velocidad = 5;
	    private int costoFuego;

	    public HechizoFuego(int x, int y, Point objetivo) {
	        this.x = x;
	        this.y = y;	       
			this.activo = false;
			this.fuego = Herramientas.cargarImagen("imagenes/hechizo-fuego.png");	
			this.fuegoExplosion = Herramientas.cargarImagen("imagenes/fuego-explosion.gif");
			this.estadoExplotar = false;
			this.costoFuego = 5;
	    }


		
	    public void lanzar(int origenX, int origenY, int destinoX, int destinoY) {
	        this.x = origenX;
	        this.y = origenY;

	        double distancia = Math.sqrt(Math.pow(destinoX - origenX, 2) + Math.pow(destinoY - origenY, 2));
	        this.dx = velocidad * (destinoX - origenX) / distancia;
	        this.dy = velocidad * (destinoY - origenY) / distancia;

	        this.activo = true;
	    }
		public void dibujar(Entorno e) {
		    if (activo) {
		        e.dibujarImagen(this.fuego, this.x, this.y, 0);
		    }
		}		
		
		public void dibujarExplosion(Entorno e, Point punto) {
			if (estadoExplotar == true) {
				e.dibujarImagen(this.fuegoExplosion, punto.x, punto.y, 0, 1);
			}
		}
		
		public void restauraExplosion() {
			this.fuegoExplosion = Herramientas.cargarImagen("imagenes/fuego-explosion.gif");
		}
		
		public boolean isEstadoExplotar() {
			return estadoExplotar;
		}



		public void setEstadoExplotar(boolean estadoExplotar) {
			this.estadoExplotar = estadoExplotar;
		}



		public void cambiarEstadoExplotar() {
			estadoExplotar = true;
		}

		public void avanzar() {
	        if (!activo) return;

	        this.x += dx;
	        this.y += dy;

	        // Área jugable: ancho < 600, alto entre 0 y 600
	        if (x < 0 || x > 600 || y < 0 || y > 600) {
	            this.activo = false;
	        }
	    }

		public void cambiarEstado() {
			this.activo = false;
		}
		
			
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

		public Image getFuego() {
			return fuego;
		}

		public Image getFuegoExplosion() {
			return fuegoExplosion;
		}



		public void setFuegoExplosion(Image fuegoExplosion) {
			this.fuegoExplosion = fuegoExplosion;
		}



		public void setFuego(Image fuego) {
			this.fuego = fuego;
		}
		public int costoFuego() {
			return costoFuego;
		}
	}




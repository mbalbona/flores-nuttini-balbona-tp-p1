package juego;
	import java.awt.Image;
	import java.awt.Point;

	import entorno.Entorno;
	import entorno.Herramientas;

	public class HechizoAgua {
		private int x;
		private int y;
		private Image agua;
		boolean activo;
		private double dx, dy;
	    private int velocidad = 5;
	    private int costoAgua;

	    public HechizoAgua(int x, int y, Point objetivo) {
	        this.x = x;
	        this.y = y;	       
			this.activo = false;
			this.agua = Herramientas.cargarImagen("imagenes/hechizo-agua.png");
			this.costoAgua = 0;		
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
		        e.dibujarImagen(this.agua, this.x, this.y, 0,0.5);
		    }
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

		public Image getAgua() {
			return agua;
		}

		public void setAgua(Image fuego) {
			this.agua = agua;
		}
		public int costoAgua() {
			return costoAgua;
		}
		
	}




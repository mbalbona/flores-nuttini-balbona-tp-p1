package juego;
	import java.awt.Image;
	import java.awt.Point;

	import entorno.Entorno;
	import entorno.Herramientas;

	public class HechizoFuego {
		private int x;
		private int y;
		private Point destino;
		private Image fuego;
		boolean activo;
		
		public HechizoFuego(int x, int y, Point destino) {
			this.x = x;
			this.y = y;
			this.destino = new Point();
			this.activo = true;
			this.fuego = Herramientas.cargarImagen("imagenes/hechizo-fuego.png");
		}
		


		
		public void avanzar(Entorno e, int destinoX, int destinoY) {
			if (this.x != destinoX && this.y != destinoY) {
				double x = destinoX - this.x;
				double y = destinoY - this.y;
				double distancia = Math.sqrt(x * x + y * y);
				
				double direccionX = x / distancia;	
				double direccionY = y / distancia;		
				
				this.x += direccionX * 5;
				this.y += direccionY * 5;
				
				
				e.dibujarImagen(fuego, this.x, this.y, 0);
		        }
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

		public void setFuego(Image fuego) {
			this.fuego = fuego;
		}

		
	}




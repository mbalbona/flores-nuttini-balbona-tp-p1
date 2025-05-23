package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;



public class Menu {
    private int x;
    private int y;
    private Image menu;
    private int ancho;

    public Menu() {
        this.menu = Herramientas.cargarImagen("imagenes/menu.png");
        this.x = 700;
        this.y = 300;
        this.ancho = menu.getWidth(null); // obtener ancho real de la imagen
    }
    
    public int getAncho() {
        return ancho;
    }
    
    public int getX() {
        return x;
    }
    
    public int getBordeIzquierdo() {
        return x - ancho / 2;
    }
    
    public void dibujar(Entorno e) {
        e.dibujarImagen(this.menu, x, y, 0);
    }

	
}
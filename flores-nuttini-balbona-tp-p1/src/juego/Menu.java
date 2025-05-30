package juego;
import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Menu {
    private int x;
    private int y;
    private Image menu;
    private int ancho;
    private int alto;

    // Botones para hechizos
    private int botonFuegoX, botonFuegoY, botonFuegoAncho, botonFuegoAlto;
    private int botonAguaX, botonAguaY, botonAguaAncho, botonAguaAlto;

    public Menu() {
        this.menu = Herramientas.cargarImagen("imagenes/menu.png");
        this.x = 700;
        this.y = 300;
        this.ancho = menu.getWidth(null);
        this.alto = menu.getHeight(null);

        // Botón de FUEGO
        this.botonFuegoAncho = 150;
        this.botonFuegoAlto = 150;
        this.botonFuegoX = x - 70;
        this.botonFuegoY = y + 70;

        // Botón de AGUA
        this.botonAguaAncho = 100;
        this.botonAguaAlto = 100;
        this.botonAguaX = x - 40;
        this.botonAguaY = y -80;
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
         // Dibujar botón de fuego   	
        e.dibujarRectangulo(botonFuegoX + botonFuegoAncho / 2, botonFuegoY + botonFuegoAlto 
        		/ 2, botonFuegoAncho, botonFuegoAlto, 0,Color.red);
        e.escribirTexto("Fuego", botonFuegoX + 10, botonFuegoY + 25);

        // Dibujar botón de agua
        e.dibujarRectangulo(botonAguaX + botonAguaAncho / 2, botonAguaY + botonAguaAlto 
        		/ 2, botonAguaAncho, botonAguaAlto, 0, Color.BLUE);
        e.escribirTexto("Agua", botonAguaX + 15, botonAguaY + 25);
        e.dibujarImagen(this.menu, x, y, 0);        
    }
    
    public void dibRecAgua(Entorno e) {
    	e.dibujarRectangulo(botonFuegoX+69, botonFuegoY-25, 165, 5, 0, Color.BLUE);
    	e.dibujarRectangulo(botonFuegoX+69, botonFuegoY-185, 165, 5, 0, Color.BLUE);
    	e.dibujarRectangulo(botonFuegoX+150, botonFuegoY-105, 5, 160, 0, Color.BLUE);
    	e.dibujarRectangulo(botonFuegoX-12, botonFuegoY-105, 5, 160, 0, Color.BLUE);
    	
    }
    public void dibRecFuego(Entorno e) {
    	e.dibujarRectangulo(botonFuegoX+69, botonFuegoY-10, 165, 5, 0, Color.red);
    	e.dibujarRectangulo(botonFuegoX+69, botonFuegoY+140, 165, 5, 0, Color.red);
    	e.dibujarRectangulo(botonFuegoX+150, botonFuegoY+65, 5, 152, 0, Color.red);
    	e.dibujarRectangulo(botonFuegoX-12, botonFuegoY+65, 5, 152, 0, Color.red);
    	
    }

    // Método para detectar si se hizo clic en un botón
    public boolean detectarClick(int mouseX, int mouseY) {
        if (mouseX >= botonFuegoX && mouseX <= botonFuegoX + botonFuegoAncho &&
            mouseY >= botonFuegoY && mouseY <= botonFuegoY + botonFuegoAlto) {
            return true;
        }

        if (mouseX >= botonAguaX && mouseX <= botonAguaX + botonAguaAncho &&
            mouseY >= botonAguaY && mouseY <= botonAguaY + botonAguaAlto) {
        	return false;
        }return false;
    }
}

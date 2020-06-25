package juego;

import entorno.Herramientas;
import java.awt.Image;
import entorno.Entorno;

public class Nube {
	private int x;
	private int y;
	private int ancho;
	private int largo;
	private int valorY = 200;
	private int valorAncho = 56;
	private int valorLargo = 56;
	Image imagen;
	
	public Nube(int valorX) {
		this.x = valorX;
		this.y = valorY;
		this.ancho = valorAncho;
		this.largo = valorLargo;
		this.imagen=Herramientas.cargarImagen("nube.png");
	}
	
	void moverDerecha() {
		this.x -= 3;
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

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getLargo() {
		return largo;
	}

	public void setLargo(int largo) {
		this.largo = largo;
	}

	void dibujoNube(Entorno entorno) {
		entorno.dibujarImagen(imagen, x, y, 0);
	}
}

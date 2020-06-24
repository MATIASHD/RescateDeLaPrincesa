package juego;

import entorno.Herramientas;
import java.awt.Image;
import entorno.Entorno;

public class Piso {
	private int x;
	private int y;
	private int ancho;
	private int largo;
	private int valorX = 405;
	private int valorY = 587;
	private int valorAncho = 50;
	private int valorLargo = 820;
	Image imagen;
	
	
	public Piso() {
		this.x = valorX;
		this.y = valorY;
		this.ancho = valorAncho;
		this.largo = valorLargo;
		this.imagen=Herramientas.cargarImagen("piso.png");
	}
	
	
	
	public int getY() {
		return y;
	}



	public int getLargo() {
		return largo;
	}



	public int getX() {
		return x;
	}



	public int getAncho() {
		return ancho;
	}



	void dibujoPiso(Entorno entorno) {
		entorno.dibujarImagen(imagen, x, y, 0);
	}
}

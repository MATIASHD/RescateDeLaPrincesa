package juego;

import java.awt.Color;

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
	
	
	public Piso() {
		this.x = valorX;
		this.y = valorY;
		this.ancho = valorAncho;
		this.largo = valorLargo;
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
		entorno.dibujarRectangulo(x, y, largo, ancho, 0, Color.GREEN);
	}
}

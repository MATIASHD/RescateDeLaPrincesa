package juego;

import java.awt.Color;

import entorno.Entorno;

public class Obstaculo {
	private int x;
	private int y;
	private int ancho;
	private int largo;
	private int valorY = 534;
	private int valorAncho = 56;
	private int valorLargo = 56;
	
	public Obstaculo(int valorX) {
		this.x = valorX;
		this.y = valorY;
		this.ancho = valorAncho;
		this.largo = valorLargo;
	}
	
	void moverDerecha() {
		this.x -= 2;
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

	void dibujoObstaculo(Entorno entorno) {
		entorno.dibujarRectangulo(x, y, largo, ancho, 0, Color.GRAY);
	}
}

package juego;

import java.awt.Color;

import entorno.Entorno;

public class Princesa {
	// To do

	private int x;
	private int y;
	private int ancho;
	private int alto;
	private int valorX = 50;
	private int valorY = 500;
	private int valorAlto = 56;
	private int valorAncho = 16;

	public Princesa() {
		this.x = valorX;
		this.y = valorY;
		this.ancho = valorAncho;
		this.alto = valorAlto;
	}


	public void moverIzq() {
		this.x -= 5;
	}

	public void moverDer() {
		this.x += 5;
	}
	
	public void mueveObstaculo() {
		this.x -= 5;
	}

	public void moverArriba() {
		this.y -= 120;
	}
	
	public void moverAbajo() {
		this.y += 5;
	}

	public void Poder() {

	}
	public BolaDeFuego disparar() {
		return new BolaDeFuego(this.x, this.y);
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

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	void dibujoPrincesa(Entorno entorno) {
		entorno.dibujarRectangulo(x, y, ancho, alto, 0, Color.PINK);
	}

}

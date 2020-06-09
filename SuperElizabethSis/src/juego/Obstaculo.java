package juego;

import java.awt.Color;

import entorno.Entorno;

public class Obstaculo {
	
		private int x;
		private int y;
		private int ancho;
		private int largo;
		//private int valorX;
		private int valorY = 150;
		private int valorAncho, valorLargo = 80;
		
		
		public Obstaculo(int valorX) {
			this.x = valorX;
			this.y = valorY;
			this.ancho = valorAncho;
			this.largo = valorLargo;
		}
		
		public void moverIzquerda() {
			this.x -= 3;
		}
		
		public int getX() {
			return x;
		}
		
		public void setX(int valor) {
			this.x = valor;
		}
		
		public int getAncho() {
			return valorAncho;
		}
		
		void dibujoObstaculo(Entorno entorno) {
			entorno.dibujarRectangulo(x, y, largo, ancho, 0, Color.RED);
		}
}

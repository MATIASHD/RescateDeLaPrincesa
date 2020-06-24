package juego;

import java.awt.Color;

import entorno.Entorno;

public class Soldado {
	
		private int x;
		private int y;
		private int alto;
		private int largo;
		private int valorAlto = 56;
		private int valorAncho = 16;
		
		
		public Soldado(int valorX, int valorY) {
			this.x = valorX;
			this.y = valorY;
			this.alto = valorAlto;
			this.largo = valorAncho;
		}
		
		void moverDerecha(){
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

		public int getAlto() {
			return alto;
		}

		public int getLargo() {
			return largo;
		}

		void dibujoSoldado(Entorno entorno) {
			entorno.dibujarRectangulo(this.x, this.y, this.largo, this.alto, 0, Color.CYAN);
		}
}

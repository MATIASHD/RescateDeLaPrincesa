package juego;

import java.awt.Color;

import entorno.Entorno;

public class BolaDeFuego {

		private int x;
		private int y;
		private int diametro;
		private int valorDiametro = 20;
		
		
		public BolaDeFuego(int valorX, int valorY) {
			this.x = valorX;
			this.y = valorY;
			this.diametro = valorDiametro;

		}
		public void moverDer(){
			this.x += 5;
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


		public int getDiametro() {
			return diametro;
		}


		void dibujoBolaDeFuego(Entorno entorno) {
			entorno.dibujarCirculo(x, y, diametro, Color.ORANGE);
		}

}

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
		
		void dibujoBolaDeFuego(Entorno entorno) {
			entorno.dibujarCirculo(x, y, diametro, Color.ORANGE);
		}

}

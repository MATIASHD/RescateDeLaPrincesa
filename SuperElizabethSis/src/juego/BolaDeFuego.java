package juego;

import java.awt.Color;

import entorno.Entorno;

public class BolaDeFuego {

		private int x;
		private int y;
		private int diametro;
		private int valorX = 80;
		private int valorY = 525;
		private int valorDiametro = 20;
		
		
		public BolaDeFuego() {
			this.x = valorX;
			this.y = valorY;
			this.diametro = valorDiametro;

		}
		
		void dibujoBolaDeFuego(Entorno entorno) {
			entorno.dibujarCirculo(x, y, diametro, Color.ORANGE);
		}

}

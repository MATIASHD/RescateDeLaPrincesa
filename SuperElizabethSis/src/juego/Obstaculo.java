package juego;

import java.awt.Color;

import entorno.Entorno;

public class Obstaculo {
	
		private int x;
		private int y;
		private int ancho;
		private int largo;
		private int valorX = 300;
		private int valorY = 530;
		private int valorAncho = 60;
		private int valorLargo = 60;
		
		
		public Obstaculo() {
			this.x = valorX;
			this.y = valorY;
			this.ancho = valorAncho;
			this.largo = valorLargo;
		}
		
		void dibujoObstaculo(Entorno entorno) {
			entorno.dibujarRectangulo(x, y, largo, ancho, 0, Color.BLUE);
		}
}

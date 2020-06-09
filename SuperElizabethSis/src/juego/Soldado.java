package juego;

import java.awt.Color;

import entorno.Entorno;

public class Soldado {
	
		private int x;
		private int y;
		private int ancho;
		private int largo;
		private int valorAncho = 56;
		private int valorLargo = 16;
		
		
		public Soldado(int valorX, int valorY) {
			this.x = valorX;
			this.y = valorY;
			this.ancho = valorAncho;
			this.largo = valorLargo;
		}
		
		void dibujoSoldado(Entorno entorno) {
			entorno.dibujarRectangulo(x, y, largo, ancho, 0, Color.CYAN);
		}
}

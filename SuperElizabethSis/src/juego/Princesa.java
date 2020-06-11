package juego;

import java.awt.Color;

import entorno.Entorno;

public class Princesa {
	//To do

		private int x;
		private int y;
		private int ancho;
		private int largo;
		private int valorX = 50;
		private int valorY = 530;
		private int valorAncho = 56;
		private int valorLargo = 16;
		
		
		public Princesa() {
			this.x = valorX;
			this.y = valorY;
			this.ancho = valorAncho;
			this.largo = valorLargo;
		}
		
		void dibujoPrincesa(Entorno entorno) {
			entorno.dibujarRectangulo(x, y, largo, ancho, 0, Color.PINK);
		}

}

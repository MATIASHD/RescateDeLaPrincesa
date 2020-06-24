package juego;

import entorno.Herramientas;
import java.awt.Image;
import entorno.Entorno;

public class BolaDeFuego {

		private int x;
		private int y;
		private int diametro;
		private int valorDiametro = 20;
		Image imagen;
		
		
		public BolaDeFuego(int valorX, int valorY) {
			this.x = valorX;
			this.y = valorY;
			this.diametro = valorDiametro;
			this.imagen=Herramientas.cargarImagen("boladefuego.gif");

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
			entorno.dibujarImagen(imagen, x, y, 0);
		}

}

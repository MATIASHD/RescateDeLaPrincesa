package juego;

import entorno.Herramientas;
import java.awt.Image;
import entorno.Entorno;

public class Soldado {
	
		private int x;
		private int y;
		private int alto;
		private int largo;
		private int valorAlto = 56;
		private int valorAncho = 16;
		Image imagen;
		
		
		public Soldado(int valorX, int valorY) {
			this.x = valorX;
			this.y = valorY;
			this.alto = valorAlto;
			this.largo = valorAncho;
			this.imagen=Herramientas.cargarImagen("soldado.gif");
		}
		
		void moverDerecha(){
			this.x -= 3;
		}
		
		void moverDerechaObs(){
			this.x -= 2;
		}
		
		public void moverArriba() {
			this.y -= 1;
		}
		
		public void moverAbajo() {
			if(this.y < 530) {
				this.y += 1;
			}
			
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
			entorno.dibujarImagen(imagen, x, y, 0);
		}
}

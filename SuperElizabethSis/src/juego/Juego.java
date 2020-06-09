package juego;

import java.awt.Color;


import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Piso piso;
	private Obstaculo[] obstaculo;
	private Soldado[] soldado;
	private Princesa princesa;
	private BolaDeFuego[] bolaDeFuego;

	// Variables y métodos propios de cada grupo
	// ...
	///////////// Constantes /////////////////
	private int  cero,  puntuacion, vida,  tres, fuente; /// pantallaDer se debe usar para los limites
														///dos, sesenta,

	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Super Elizabeth Sis - Grupo ... - v1", 800, 600);

		// Inicializar lo que haga falta para el juego
		// ...
		this.piso = new Piso();
		this.obstaculo = new Obstaculo[5];
		this.soldado = new Soldado[10];
		this.princesa = new Princesa();
		this.bolaDeFuego = new BolaDeFuego[10];

		// Constante
		//this.dos = 2;
		this.cero = 0;
		//this.sesenta = 60;
		this.tres = 3;
		this.fuente = 28;

		// variable
		this.puntuacion = this.cero;
		this.vida = this.tres;

		// El valor 60 corresponde al largo de nuestro obstaculo y se divide por dos
		// para posicionarnos en el extremo
		//this.pantallaDer = entorno.ancho() + this.sesenta / this.dos;

		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y por lo
	 * tanto es el método más importante de esta clase. Aquí se debe actualizar el
	 * estado interno del juego para simular el paso del tiempo (ver el enunciado
	 * del TP para mayor detalle).
	 */
	public void tick() {
		// Procesamiento de un instante de tiempo
		// ...

		crearObjetos();
		// moverObstaculo();
		dibujarObjetos();
		// FinalObstaculo();
		
	}

	void dibujarObjetos() {
		this.piso.dibujoPiso(entorno);
		this.princesa.dibujoPrincesa(entorno);
		dibujarObstaculos();
		dibujarSoldado();
		dibujarBolasDeFuego();
		visualizaciones();
	}

	void crearObjetos() {
		posicionObstaculos();
		posicionSoldado();
		posicionBolasDeFuego();
	}

	///////////////////////////////// Obstaculo

	void posicionObstaculos() {
		int separacionObstaculo = 100;
		for (int i = 0; i < obstaculo.length; i++) {
			if (obstaculo[i] == null) {
				obstaculo[i] = new Obstaculo(separacionObstaculo);
				separacionObstaculo += 80;
			}
		}
	}

	void dibujarObstaculos() {
		for (int i = 0; i < obstaculo.length; i++) {
			if (obstaculo[i] != null) {
				this.obstaculo[i].dibujoObstaculo(entorno);
			}
		}
	}

///////////////////////// Soldado ///////////
	void posicionSoldado() {
		int separacionSoldado = 60;
		for (int i = 0; i < soldado.length; i++) {
			if (soldado[i] == null) {
				soldado[i] = new Soldado(separacionSoldado, 150);
				separacionSoldado += 80;
			}
		}
	}

	void dibujarSoldado() {
		for (int i = 0; i < soldado.length; i++) {
			if (soldado[i] != null) {
				this.soldado[i].dibujoSoldado(entorno);
			}
		}
	}

///////////////////////// Bola de fuego ///////////

	void posicionBolasDeFuego() {
		int separacionBola = 70;
		for (int i = 0; i < bolaDeFuego.length; i++) {
			if (bolaDeFuego[i] == null) {
				bolaDeFuego[i] = new BolaDeFuego(separacionBola, 450);
				separacionBola += 100;
			}
		}
	}

	void dibujarBolasDeFuego() {
		for (int i = 0; i < bolaDeFuego.length; i++) {
			if (bolaDeFuego[i] != null) {
				this.bolaDeFuego[i].dibujoBolaDeFuego(entorno);
			}
		}
	}

	///////////////////////////////// Escena
	/*
	 * void moverObstaculo() { for (int i = 0; i < obstaculo.length; i++) { if
	 * (obstaculo[i] != null) { System.out.println(obstaculo[i].getX() +
	 * obstaculo[i].getAncho()/ this.dos); obstaculo[i].moverIzquerda(); if
	 * (obstaculo[i].getX() + obstaculo[i].getAncho()/ this.dos < cero) {
	 * obstaculo[i].setX(this.pantallaDer); } }
	 * 
	 * } }
	 */

	void visualizaciones() {
		entorno.cambiarFont("Copperplate Gothic Bold", this.fuente, Color.BLUE);
		entorno.escribirTexto("Puntos: " + this.puntuacion, 240, 30);
		entorno.cambiarFont("Copperplate Gothic Bold", this.fuente, Color.RED);
		entorno.escribirTexto("Vidas: " + this.vida, 410, 30);
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}

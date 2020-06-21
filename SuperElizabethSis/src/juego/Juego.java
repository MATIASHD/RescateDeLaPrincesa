package juego;

import java.awt.Color;
import java.util.Random;

import entorno.Entorno;
import entorno.InterfaceJuego;
//HECHO: Comportamiento de la princesa, correccion de la colicion con el objeto, en el salto, descuento de vidas
//NO CORREJIDO: COLISION ENTRE BOLA DE FUEGO Y SOLDADO.
public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Piso piso;
	private Obstaculo[] obstaculo;
	private Soldado[] soldado;
	private Princesa princesa;
	private BolaDeFuego[] bolaDeFuego;
	private Random random;

	// Variables y métodos propios de cada grupo
	// ...
	///////////// Constantes /////////////////
	private int cero, puntuacion, vida, tres, fuente, dos, cinco, apareceSoldado, tiempo, tiempoSalto;
	boolean gameOver, estadoGravedad, estaEnAire;

	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Super Elizabeth Sis - Grupo ... - v1", 800, 600);

		// Inicializar lo que haga falta para el juego
		// ...
		this.piso = new Piso();
		this.soldado = new Soldado[8];
		this.princesa = new Princesa();
		this.bolaDeFuego = new BolaDeFuego[10];
		this.obstaculo = new Obstaculo[5];
		this.random = new Random();

		// Constante
		this.cero = 0;
		this.tiempo = this.cero;
		this.tres = 3;
		this.fuente = 28;
		this.dos = 2;
		this.cinco = 5;

		// variable
		this.puntuacion = this.cero;
		this.vida = this.tres;
		this.gameOver = false;
		this.tiempoSalto = this.cero;
		this.apareceSoldado = random.nextInt(100)+1;
		this.estadoGravedad = false;
		this.estaEnAire = false;

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
		if ( this.gameOver != true) {
			crearObjetos();
			acciones();
			dibujarObjetos();
			this.tiempo++;
			this.tiempoSalto++;

		} else {
			entorno.cambiarFont("Copperplate Gothic Bold", this.fuente, Color.BLUE);
			entorno.escribirTexto("JUEGO TERMINADO", 240, 50);
			entorno.cambiarFont("Copperplate Gothic Bold", this.fuente, Color.BLUE);
			entorno.escribirTexto("Puntos: " + this.puntuacion, 240, 200);

		}

	}

	void dibujarObjetos() {
		this.piso.dibujoPiso(entorno);
		this.princesa.dibujoPrincesa(entorno);
		dibujarObstaculo();
		dibujarSoldado();
		dibujarBolasDeFuego();
		visualizaciones();
	}

	void crearObjetos() {
		posicionSoldado();
		posicionObstaculo();
	}

	void acciones() {
		moverDerechaObj();
		moverDerechaSol();
		limiteDePantallaSol();
		limiteDePantallaObj();
		colisionesPriObs();
		colisionesArmSol();
		colisionesPriSol();	
		colisionesArmObs();
		contadorVida();
		movimientoPrincess();
		limiteBolaDeFuego();
		tiempo();
		gravedad();
		terminaJuego();
	}

	///////////////////////////////// Obstaculo
	void posicionObstaculo() {
		int separacionObstaculo = 800;
		for (int i = 0; i < obstaculo.length; i++) {
			if (obstaculo[i] == null) {
				obstaculo[i] = new Obstaculo(separacionObstaculo);
				separacionObstaculo += 165;
			}
		}
	}

	void dibujarObstaculo() {
		for (int i = 0; i < obstaculo.length; i++) {
			if (obstaculo[i] != null) {
				obstaculo[i].dibujoObstaculo(entorno);
			}
		}
	}

	void moverDerechaObj() {
		for (int i = 0; i < obstaculo.length; i++) {
			if (obstaculo[i] != null) {
				obstaculo[i].moverDerecha();
			}
		}
	}

///////////////////////// Soldado ///////////
	void posicionSoldado() {
		for (int i = 0; i < soldado.length; i++) {
			if (soldado[i] == null) {
				if (this.apareceSoldado < 5) {
					soldado[i] = new Soldado(entorno.ancho(), 530);
				}
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

	void moverDerechaSol() {
		for (int i = 0; i < soldado.length; i++) {
			if (soldado[i] != null) {
				soldado[i].moverDerecha();
			}
		}
	}

///////////////////////// Bola de fuego ///////////

	void dibujarBolasDeFuego() {
		for (int i = 0; i < bolaDeFuego.length; i++) {
			if (bolaDeFuego[i] != null) {
				this.bolaDeFuego[i].moverDer();
				this.bolaDeFuego[i].dibujoBolaDeFuego(entorno);
			}
		}
	}

	///////////////////////////////// COLISIONES
	public boolean colisionesPriObs() {
		for (int i = 0; i < obstaculo.length; i++) {
			if (obstaculo[i] != null) {
				/*if (princesa.getX() - princesa.getAncho() / this.dos < obstaculo[i].getX()
						+ obstaculo[i].getAncho() / this.dos
						&& princesa.getX() + princesa.getAncho() / this.dos > obstaculo[i].getX()
								- obstaculo[i].getAncho() / this.dos
						&& princesa.getY() - princesa.getAlto() / this.dos < obstaculo[i].getY()
								+ obstaculo[i].getLargo() / this.dos
						&& princesa.getY() + princesa.getAlto() / this.dos > obstaculo[i].getY()
								- obstaculo[i].getLargo() / this.dos) {
					return true;
				}*/
				if(colisionesPriObsX(i) && colisionesPriObsY(i)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean colisionesPriObsX(int i) {
		if (princesa.getX() - princesa.getAncho() / this.dos < obstaculo[i].getX()
				+ obstaculo[i].getAncho() / this.dos
				&& princesa.getX() + princesa.getAncho() / this.dos > obstaculo[i].getX()
				- obstaculo[i].getAncho() / this.dos) {
			return true;
		}
		return false;
	}
	
	public boolean colisionesPriObsY(int i) {
		if (princesa.getY() - princesa.getAlto() / this.dos < obstaculo[i].getY()
				+ obstaculo[i].getLargo() / this.dos
				&& princesa.getY() + princesa.getAlto() / this.dos > obstaculo[i].getY()
				- obstaculo[i].getLargo() / this.dos) {
			return true;
		}
		return false;
	}
		
	public boolean colisionesPriPiso() {
				if (princesa.getX() - princesa.getAncho() / this.dos < piso.getX()
						+ piso.getLargo() / this.dos
						&& princesa.getX() + princesa.getAncho() / this.dos > piso.getX()
								- piso.getLargo() / this.dos
						&& princesa.getY() - princesa.getAlto() / this.dos < piso.getY()
								+ piso.getAncho() / this.dos
						&& princesa.getY() + princesa.getAlto() / this.dos > piso.getY()
								- piso.getAncho() / this.dos) {
					return true;
		}
		return false;
	}

	public boolean colisionesPriSol() {
		for (int i = 0; i < soldado.length; i++) {
			if (soldado[i] != null) {
				if (princesa.getX() - princesa.getAncho() / this.dos < soldado[i].getX()
						+ soldado[i].getAlto() / this.dos
						&& princesa.getX() + princesa.getAncho() / this.dos > soldado[i].getX()
								- soldado[i].getAlto() / this.dos
						&& princesa.getY() - princesa.getAlto() / this.dos < soldado[i].getY()
								+ soldado[i].getLargo() / this.dos
						&& princesa.getY() + princesa.getAlto() / this.dos > soldado[i].getY()
								- soldado[i].getLargo() / this.dos) {
					soldado[i] = null;
					return true;
				}
			}
		}
		return false;
	}


	public boolean colisionesArmSol() {
		for (int i = 0; i < soldado.length; i++) {
			for (int j = 0; j < bolaDeFuego.length; j++) {
				if (bolaDeFuego[j] != null) {
					if (soldado[i] != null) {
						if (bolaDeFuego[j].getX() < soldado[i].getX() + soldado[i].getAlto() / this.dos && 
								bolaDeFuego[j].getX() > soldado[i].getX() - soldado[i].getAlto() / this.dos
								&& bolaDeFuego[j].getY() < soldado[i].getY() + soldado[i].getLargo() / this.dos
								&& bolaDeFuego[j].getY() > soldado[i].getY() - soldado[i].getLargo() / this.dos) {
							bolaDeFuego[j] = null;
							soldado[i] = null;
							this.puntuacion += this.cinco;
							return true;
						}
					}

				}

			}

		}
		return false;
	}

	public boolean colisionesArmObs() {
		for (int i = 0; i < obstaculo.length; i++) {
			for (int j = 0; j < bolaDeFuego.length; j++) {
				if (bolaDeFuego[j] != null) {
					if (obstaculo[i] != null) {
						if (bolaDeFuego[j].getX() < obstaculo[i].getX() + obstaculo[i].getAncho() / this.dos
								&& bolaDeFuego[j].getX() > obstaculo[i].getX() - obstaculo[i].getAncho() / this.dos
								&& bolaDeFuego[j].getY() < obstaculo[i].getX() + obstaculo[i].getLargo() / this.dos
								&& bolaDeFuego[j].getY() > obstaculo[i].getX() + obstaculo[i].getLargo() / this.dos) {
							bolaDeFuego[j] = null;
							return true;
						}
					}

				}

			}

		}
		return false;
	}

	///////////////////////////////// Escena
	void limiteDePantallaObj() {
		for (int i = 0; i < obstaculo.length; i++) {
			if (obstaculo[i] != null) {
				if (obstaculo[i].getX() < -30) {
					obstaculo[i].setX(800);
				}
			}
		}
	}

	void limiteDePantallaSol() {
		for (int i = 0; i < soldado.length; i++) {
			if (soldado[i] != null) {
				if (soldado[i].getX() < -20) {
					soldado[i].setX(800);
				}
			}
		}
	}

	void limiteBolaDeFuego() {
		for (int i = 0; i < bolaDeFuego.length; i++) {
			if (bolaDeFuego[i] != null) {
				if (bolaDeFuego[i].getX() - bolaDeFuego[i].getDiametro() / this.dos > entorno.ancho()) {
					bolaDeFuego[i] = null;
				}
			}
		}
	}

	boolean limitePrincesaDer() {
		if (this.princesa.getX() + this.princesa.getAncho() / this.dos >= entorno.ancho() / this.dos) {
			return false;
		}
		return true;
	}

	boolean limitePrincesaIzq() {
		if (this.princesa.getX() - this.princesa.getAncho() / this.dos <= this.cero) {
			return false;
		}
		return true;
	}

	void tiempo() {
		if (this.tiempo == 100) {
			this.apareceSoldado = (int) (Math.random() * 10 + 1);
			this.tiempo = 0;
		}
	}

	//////////////////// Movimiento

	void movimientoPrincess() {
		if (!colisionesPriObs() && limitePrincesaDer() && this.entorno.estaPresionada(entorno.TECLA_DERECHA) && colisionesPriPiso()) {
			this.princesa.moverDer();
		}
		if (!colisionesPriObs() && limitePrincesaIzq() && this.entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && colisionesPriPiso()) {
			this.princesa.moverIzq();
		}
		if (this.estaEnAire == false && this.entorno.sePresiono(entorno.TECLA_ARRIBA) && (colisionesPriPiso() || colisionesPriObs())) {
			this.estaEnAire = true;			
		}
		if(this.estaEnAire) {
			if(this.princesa.getY() > 380) {
				saltar();
			}else {
				this.estaEnAire = false;
			}			
		}
		if (this.entorno.sePresiono(entorno.TECLA_ESPACIO)) {
			for (int i = 0; i < bolaDeFuego.length; i++) {
				if (bolaDeFuego[i] == null) {
					bolaDeFuego[i] = this.princesa.disparar();
					return; // Se utiliza para evitar que se siga agregando en el array mas objetos bola de
							// fuego
				}
			}
		}
		moverObstaculo();
	}
	
	void moverObstaculo() { /// VER como hacer para que la colicion lo atraiga
		for (int i = 0; i < obstaculo.length; i++) {
			if (obstaculo[i] != null) {
				/*if (princesa.getX() - princesa.getAncho() / this.dos < obstaculo[i].getX()
						+ obstaculo[i].getAncho() / this.dos
						&& princesa.getX() + princesa.getAncho() / this.dos > obstaculo[i].getX()
								- obstaculo[i].getAncho() / this.dos) {
					princesa.mueveObstaculo();
				}*/
				if(colisionesPriObsX(i) && colisionesPriPiso() ) {
					princesa.mueveObstaculo();
				}
			}
		}
	}
	
	void gravedad() {
		if (!colisionesPriPiso() && !colisionesPriObs() && !this.estaEnAire) { 
			this.princesa.moverAbajo();
		}
	}
	void saltar() {
			this.princesa.moverArriba();
			if(colisionesPriObs()) {
				this.princesa.mueveObstaculo();
			}
	}

	void visualizaciones() {
		entorno.cambiarFont("Copperplate Gothic Bold", this.fuente, Color.BLUE);
		entorno.escribirTexto("Puntos: " + this.puntuacion, 200, 30);
		entorno.cambiarFont("Copperplate Gothic Bold", this.fuente, Color.RED);
		entorno.escribirTexto("Vidas: " + this.vida, 430, 30);
	}

	void contadorVida() {
		if (colisionesPriSol() || this.princesa.getX() < 0) {
			this.vida--;
			this.princesa.setX(60);
			this.princesa.setY(450);
		}
		if (this.vida == 0) {
			this.gameOver = true;
		}
	}
	
	void terminaJuego() {
		if(!limitePrincesaIzq() && colisionesPriObs()) {
			this.gameOver = true;
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}

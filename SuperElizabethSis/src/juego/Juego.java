package juego;

import java.awt.Color;

import java.util.Random;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;
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
	private Nube[] nube;
	Image fondo;

	// Variables y métodos propios de cada grupo
	// ...
	/////////////  /////////////////
	private int dos, tiempoDeVida;
	private int puntuacion;
	private int tiempo;
	private int vida;
	private int cero;
	private int cinco;
	private int tres;
	private int fuente;
	private int gano;
	private int apareceSoldado;

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
		this.nube = new Nube[5];
		this.random = new Random();

		// Constante
		this.cero = 0;
		this.tres = 3;
		this.fuente = 28;
		this.dos = 2;
		this.cinco = 5;
		this.tiempoDeVida = 25;

		// variable
		this.puntuacion = this.cero;
		this.vida = this.tres;
		this.gameOver = false;

		this.estadoGravedad = false;
		this.estaEnAire = false;
		this.gano = this.tiempoDeVida;
		this.tiempo = this.cero;

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

		if (!esGanador() && this.gameOver != true) {
			fondo = Herramientas.cargarImagen("fondonubes.jpg");
			entorno.dibujarImagen(fondo, 405, 305, 0);
			crearObjetos();
			acciones();
			dibujarObjetos();
			this.tiempo++;

		} else {
			if (this.vida > this.cero) {
				fondo = Herramientas.cargarImagen("gameover.jpg");
				entorno.dibujarImagen(fondo, 405, 305, 0);
				entorno.cambiarFont("Copperplate Gothic Bold", this.fuente, Color.RED);
				entorno.escribirTexto("Ganador", 240, 200);
				entorno.cambiarFont("Copperplate Gothic Bold", this.fuente, Color.RED);
				entorno.escribirTexto("Puntos: " + this.puntuacion, 100, 300);
			} else {
				fondo = Herramientas.cargarImagen("gameover.jpg");
				entorno.dibujarImagen(fondo, 405, 305, 0);
				entorno.cambiarFont("Copperplate Gothic Bold", this.fuente, Color.RED);
				entorno.escribirTexto("Perdio el juego", 240, 200);
				entorno.cambiarFont("Copperplate Gothic Bold", this.fuente, Color.RED);
				entorno.escribirTexto("Puntos: " + this.puntuacion, 240, 300);
			}

		}

	}

	void dibujarObjetos() {
		this.piso.dibujoPiso(entorno);
		this.princesa.dibujoPrincesa(entorno);
		dibujarObstaculo();
		dibujarNube();
		dibujarSoldado();
		dibujarBolasDeFuego();
		visualizaciones();
	}

	void crearObjetos() {
		posicionSoldado();
		posicionObstaculo();
		posicionNube();
		this.apareceSoldado = random.nextInt(100) + 1;
	}

	void acciones() {
		moverDerechaObj();
		moverDerechaNub();
		moverDerechaSol();
		limiteDePantallaSol();
		limiteDePantallaObj();
		colisionesPriObs();
		colisionesArmSol();
		colisionesPriSol();
		colisionesArmObs();
		colisionesObsSolX();
		colisionesObsSolY();
		contadorVida();
		movimientoPrincess();
		limiteBolaDeFuego();
		limiteTiempo();
		gravedad();
		//terminaJuego();
	}

	///////////////////////////////// Obstaculo
	void posicionObstaculo() {
		int separacionObstaculo = 500;
		for (int i = 0; i < obstaculo.length; i++) {
			if (obstaculo[i] == null) {
				obstaculo[i] = new Obstaculo(separacionObstaculo);
				separacionObstaculo += 300;
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
				if (this.apareceSoldado == 10) {
					soldado[i] = new Soldado(entorno.ancho(), 530);
				}

			}
			return; // Se agrego para evitar que se creen varios objetos soldados a la vez
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
				if (!colisionesObsSolX()) {
					soldado[i].moverDerecha();
					soldado[i].moverAbajo();
				} else {
					if (colisionesObsSolY()) {
						soldado[i].moverArriba();
						soldado[i].moverDerechaObs();
					} else {
						soldado[i].moverDerecha();
					}
				}
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
///////////////////////// Nube ///////////

	void dibujarNube() {
		for (int i = 0; i < nube.length; i++) {
			if (nube[i] != null) {
				nube[i].dibujoNube(entorno);
			}
		}
	}

	void posicionNube() {
		int separacionNube = 1500;
		for (int i = 0; i < nube.length; i++) {
			if (nube[i] == null) {
				nube[i] = new Nube(separacionNube);
				separacionNube += 850;
			}
		}
	}

	void moverDerechaNub() {
		for (int i = 0; i < nube.length; i++) {
			if (nube[i] != null) {
				nube[i].moverDerecha();
			}
		}
	}

	///////////////////////////////// COLISIONES
	public boolean colisionesPriObs() {
		for (int i = 0; i < obstaculo.length; i++) {
			if (obstaculo[i] != null) {
				if (colisionesPriObsX(i) && colisionesPriObsY(i)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean colisionesPriObsX(int i) {
		if (princesa.getX() - princesa.getAncho() / this.dos < obstaculo[i].getX() + obstaculo[i].getAncho() / this.dos
				&& princesa.getX() + princesa.getAncho() / this.dos > obstaculo[i].getX()
						- obstaculo[i].getAncho() / this.dos) {
			return true;
		}
		return false;
	}

	public boolean colisionesPriObsY(int i) {
		if (princesa.getY() - princesa.getAlto() / this.dos < obstaculo[i].getY() + obstaculo[i].getLargo() / this.dos
				&& princesa.getY() + princesa.getAlto() / this.dos > obstaculo[i].getY()
						- obstaculo[i].getLargo() / this.dos) {
			return true;
		}
		return false;
	}

	public boolean colisionesPriPiso() {
		if (princesa.getX() - princesa.getAncho() / this.dos < piso.getX() + piso.getLargo() / this.dos
				&& princesa.getX() + princesa.getAncho() / this.dos > piso.getX() - piso.getLargo() / this.dos
				&& princesa.getY() - princesa.getAlto() / this.dos < piso.getY() + piso.getAncho() / this.dos
				&& princesa.getY() + princesa.getAlto() / this.dos > piso.getY() - piso.getAncho() / this.dos) {
			return true;
		}
		return false;
	}

	public void colisionesPriSol() {
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
					this.vida--; //resta vida cuando coliciona princesa con soldado
				}
			}
		}
	}

	public boolean colisionesArmSol() {
		for (int i = 0; i < soldado.length; i++) {
			for (int j = 0; j < bolaDeFuego.length; j++) {
				if (bolaDeFuego[j] != null) {
					if (soldado[i] != null) {
						if (bolaDeFuego[j].getX() < soldado[i].getX() + soldado[i].getAlto() / this.dos
								&& bolaDeFuego[j].getX() > soldado[i].getX() - soldado[i].getAlto() / this.dos
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

	public boolean colisionesObsSolX() {
		for (int i = 0; i < soldado.length; i++) {
			for (int j = 0; j < obstaculo.length; j++) {
				if (obstaculo[j] != null) {
					if (soldado[i] != null) {
						if (obstaculo[j].getX() + obstaculo[j].getAncho() / this.dos > soldado[i].getX()
								- soldado[i].getLargo() / this.dos
								&& obstaculo[j].getX() - obstaculo[j].getAncho() / this.dos < soldado[i].getX()
										+ soldado[i].getLargo() / this.dos) {

							return true;
						}
					}

				}

			}

		}
		return false;
	}

	public boolean colisionesObsSolY() {
		for (int i = 0; i < soldado.length; i++) {
			for (int j = 0; j < obstaculo.length; j++) {
				if (obstaculo[j] != null) {
					if (soldado[i] != null) {
						if (obstaculo[j].getY() + obstaculo[j].getLargo() / this.dos > soldado[i].getY()
								- soldado[i].getAlto() / this.dos
								&& obstaculo[j].getY() - obstaculo[j].getLargo() / this.dos < soldado[i].getY()
										+ soldado[i].getAlto() / this.dos) {

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

	void limiteDePantallaNub() {
		for (int i = 0; i < nube.length; i++) {
			if (nube[i] != null) {
				if (nube[i].getX() < 800) {
					nube[i].setX(1500);
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

	void limiteTiempo() {
		if (this.tiempo >= 100) {
			this.tiempo = this.cero;
			this.gano--;
		}
	}

	boolean esGanador() {

		if (this.gano <= 0) {
			return true;
		} else {
			return false;
		}
	}

	//////////////////// Movimiento

	void movimientoPrincess() {
		if (!colisionesPriObs() && limitePrincesaDer() && this.entorno.estaPresionada(entorno.TECLA_DERECHA)
				&& colisionesPriPiso()) {
			this.princesa.moverDer();
		}
		if (!colisionesPriObs() && limitePrincesaIzq() && this.entorno.estaPresionada(entorno.TECLA_IZQUIERDA)
				&& colisionesPriPiso()) {
			this.princesa.moverIzq();
		}
		if (this.estaEnAire == false && this.entorno.sePresiono(entorno.TECLA_ARRIBA)
				&& (colisionesPriPiso() || colisionesPriObs())) {
			this.estaEnAire = true;
		}
		if (this.estaEnAire) {
			if (this.princesa.getY() > 380) {
				saltar();
			} else {
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
				if (colisionesPriObsX(i) && colisionesPriPiso()) {
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
		if (colisionesPriObs()) {
			this.princesa.mueveObstaculo();
		}
	}

	void visualizaciones() {
		entorno.cambiarFont("Copperplate Gothic Bold", this.fuente, Color.BLUE);
		entorno.escribirTexto("Puntos: " + this.puntuacion, 200, 30);
		entorno.cambiarFont("Copperplate Gothic Bold", this.fuente, Color.RED);
		entorno.escribirTexto("Vidas: " + this.vida, 430, 30);
		entorno.cambiarFont("Copperplate Gothic Bold", 45, Color.RED);
		entorno.escribirTexto("Tiempo: " + this.gano, 250, 94);
	}

	void contadorVida() {
		if (this.princesa.getX() < 0) {
			this.vida--;
			this.princesa.setX(60);
			this.princesa.setY(450);
		}
		if (this.vida == 0) {
			this.gameOver = true;
		}
	}


	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}

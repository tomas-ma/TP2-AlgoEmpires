package modelo.juego;

import modelo.jugador.Jugador;
import modelo.jugador.PosicionDesocupadaError;
import modelo.jugador.PosicionOcupadaError;
import modelo.mapa.Mapa;
import modelo.mapa.Posicion;
import modelo.unidad.MovimientosPorTurnoExcedidosError;
import modelo.unidad.PosicionFueraDelMapaError;
import modelo.unidad.Posicionable;
import modelo.unidad.Unidad;
import modelo.edificio.Edificio;
import modelo.edificio.TamanioIncorrectoError;

public class Juego {
	
	private Mapa mapa;
	private ListaCircular<Jugador> jugadores;
	private Jugador jugadorActual;	
	
	public Juego () {
		
		this.mapa = new Mapa ();
        this.mapa.iniciarMapaVacio();
        this.jugadores = new ListaCircular<Jugador>();
        Jugador jugador1 = new Jugador(this.mapa, "ABC");
        Jugador jugador2 = new Jugador(this.mapa, "XYZ");
        jugadores.insertarPrimero(jugador2);
        jugadores.insertarPrimero(jugador1);

		this.jugadorActual = new Jugador (mapa,"Pepito");
	}

	public void agregarUnidadEnFilaColumna(Unidad unidad, int fila, int columna) throws PosicionFueraDelMapaError, PosicionOcupadaError {
		
		this.mapa.posicionarEnFilaColumna(unidad, fila, columna);
		this.jugadorActual.agregarPosicionableEnFilaColumna (unidad, fila, columna);
		
	}
	
	public void agregarEdifcioDesdeHasta (Edificio edificio, int desdeX, int desdeY, int hastaX, int hastaY) throws PosicionOcupadaError, PosicionFueraDelMapaError {
		
		this.mapa.ponerEdificioDesdeHasta(edificio, desdeX, desdeY, hastaX, hastaY);
	}

	public void desplazarFilaColumnaHaciaArriba(int fila, int columna, int cantidadDePosiciones) throws MovimientosPorTurnoExcedidosError, PosicionOcupadaError, PosicionDesocupadaError {
		
		
		this.jugadorActual.desplazarFilaColumnaHaciaArriba (fila, columna, cantidadDePosiciones);
		
	}

	public void iniciarJuego() throws PosicionFueraDelMapaError, PosicionOcupadaError, TamanioIncorrectoError {
		
		Jugador jugadorActual = this.jugadores.devolverPrimero();
		
		jugadorActual.crearCastilloDesdeHasta(1, 1, 4, 4);
		jugadorActual.crearPlazaCentralDesdeHasta(1, 8, 2, 9);
		jugadorActual.iniciarAldeanosDesde(5, 5);
		
		this.jugadores.siguiente();
		jugadorActual = this.jugadores.devolverPrimero();
		
		//jugadorActual.crearCastilloDesdeHasta(47, 47, 50, 50);
		jugadorActual.crearCastilloDesdeHasta(this.mapa.getFilas() - 3, this.mapa.getColumnas() - 3, this.mapa.getFilas(), this.mapa.getColumnas());
		//jugadorActual.crearPlazaCentralDesdeHasta(49, 42, 50, 43);
		jugadorActual.crearPlazaCentralDesdeHasta(this.mapa.getFilas() - 1, this.mapa.getColumnas() - 8, this.mapa.getFilas(), this.mapa.getColumnas() - 7);
		//jugadorActual.iniciarAldeanosDesde(46, 44);
		jugadorActual.iniciarAldeanosDesde(this.mapa.getFilas() - 4, this.mapa.getColumnas() - 6);
		
//		jugadorActual.crearCastilloDesdeHasta(this.mapa.getColumnas() - 3, this.mapa.getFilas() - 3 , this.mapa.getColumnas() , this.mapa.getFilas());
//		jugadorActual.crearPlazaCentralDesdeHasta(this.mapa.getColumnas() - 8,this.mapa.getFilas() - 1, this.mapa.getColumnas() - 7, this.mapa.getFilas());
//		jugadorActual.iniciarAldeanosDesde(this.mapa.getColumnas() - 6,this.mapa.getFilas() - 4);
		
						
	}
	
	public Posicionable obtenerPosicionableEn(Posicion posicion) {
		return this.mapa.obtenerPosicionableEn(posicion);
	}
	
	public void desplazarFilaColumnaHaciaAbajo(int fila, int columna, int cantidadDePosiciones) throws MovimientosPorTurnoExcedidosError, PosicionOcupadaError, PosicionDesocupadaError {
		
		
		this.jugadorActual.desplazarFilaColumnaHaciaAbajo(fila, columna, cantidadDePosiciones);
		
	}

	public void desplazarFilaColumnaHaciaLaDerecha(int fila, int columna, int cantidadDePosiciones) throws MovimientosPorTurnoExcedidosError, PosicionOcupadaError, PosicionDesocupadaError {
	
	
		this.jugadorActual.desplazarFilaColumnaHaciaLaDerecha (fila, columna, cantidadDePosiciones);
	
	}

	public void desplazarFilaColumnaHaciaLaIzquierda(int fila, int columna, int cantidadDePosiciones) throws MovimientosPorTurnoExcedidosError, PosicionOcupadaError, PosicionDesocupadaError {
	
	
		this.jugadorActual.desplazarFilaColumnaHaciaLaIzquierda (fila, columna, cantidadDePosiciones);
	
	}

	public void desplazarFilaColumnaHaciaLaDiagonalSuperiorDerecha(int fila, int columna, int cantidadDePosiciones) throws MovimientosPorTurnoExcedidosError, PosicionOcupadaError, PosicionDesocupadaError {
	
	
		this.jugadorActual.desplazarFilaColumnaHaciaLaDiagonalSuperiorDerecha (fila, columna, cantidadDePosiciones);
	
	}

	public void desplazarFilaColumnaHaciaLaDiagonalSuperiorIzquierda(int fila, int columna, int cantidadDePosiciones) throws MovimientosPorTurnoExcedidosError, PosicionOcupadaError, PosicionDesocupadaError {
	
	
		this.jugadorActual.desplazarFilaColumnaHaciaLaDiagonalSuperiorIzquierda(fila, columna, cantidadDePosiciones);
	
	}

	public void desplazarFilaColumnaHaciaLaDiagionalInferiorDerecha(int fila, int columna, int cantidadDePosiciones) throws MovimientosPorTurnoExcedidosError, PosicionOcupadaError, PosicionDesocupadaError {
	
	
		this.jugadorActual.desplazarFilaColumnaHaciaLaDiagonalInferiorDerecha(fila, columna, cantidadDePosiciones);
	
	}
	
	public void desplazarFilaColumnaHaciaLaDiagionalInferiorIzquierda(int fila, int columna, int cantidadDePosiciones) throws MovimientosPorTurnoExcedidosError, PosicionOcupadaError, PosicionDesocupadaError {
		
		
		this.jugadorActual.desplazarFilaColumnaHaciaLaDiagonalInferiorIzquierda (fila, columna, cantidadDePosiciones);
		
	}

		
	

}

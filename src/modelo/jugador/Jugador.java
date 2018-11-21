package modelo.jugador;

import modelo.edificio.Edificio;

import modelo.edificio.cuartel.Cuartel;
import modelo.edificio.TamanioIncorrectoError;
import modelo.edificio.castillo.Castillo;
import modelo.edificio.plazaCentral.PlazaCentral;
import modelo.mapa.Mapa;
import modelo.mapa.Posicion;
import modelo.unidad.MovimientosPorTurnoExcedidosError;
import modelo.unidad.Posicionable;
import modelo.unidad.Unidad;
import modelo.unidad.aldeano.Aldeano;
import modelo.vacio.Vacio;
import modelo.unidad.PosicionFueraDelMapaError;


import java.util.HashMap;
import java.util.Map;



public class Jugador {
	private Map  <Posicion, Posicionable> posicionables;
	private Mapa mapa;
	private String nombre;
	private int poblacion;
	private int oro;

	public Jugador(Mapa mapa,String nombre) {

		this.posicionables = new HashMap <Posicion, Posicionable> ();
		this.mapa = mapa;
		this.nombre = nombre;
		this.poblacion = 0;
		this.oro = 200;
	}


	public String getNombre(){
		return this.nombre;
	}
	
	public Edificio construirCuartel(int fila,int columna) {
		//se debe pasar el aldeano que construye
		Edificio cuartel = new Cuartel();
		Posicionable posicionable = cuartel;
		this.agregarPosicionableEnFilaColumna(posicionable, fila, columna);
		return cuartel;
		
	}
	
	public Unidad crearAldeano(Posicion plazaCentral) throws PosicionFueraDelMapaError, PosicionOcupadaError {
		int costoAldeano = 25;
		int topePoblacional = 50;
		Unidad aldeano = null;
		PlazaCentral plaza = (PlazaCentral)this.posicionables.get(plazaCentral);
		
		if(this.oro >= costoAldeano && this.poblacion < topePoblacional) {
			aldeano = plaza.crearAldeano();
			
			if(aldeano == null) {
				throw new PlazaCentralCrearAldeanoException();
			}
			this.poblacion++;
			this.oro -= costoAldeano;
			
			//busco la posicion desocupada para crear el aldeano
			int fila  = plazaCentral.getFila()+3;
			int columna = plazaCentral.getColumna()+3;
			Posicion posicionAux = new Posicion(fila,columna);
			while (this.mapa.estaOcupado(posicionAux)) {
				fila++;
				columna++;
				posicionAux.posicionarEnFilaColumna(fila, columna);
			}
			aldeano.posicionarEnFilaColumna(posicionAux.getFila(), posicionAux.getColumna());
			
			//actualizo mapa y posicionables de jugador
			this.agregarPosicionableEnFilaColumna(aldeano,aldeano.getPosicion().getFila(), aldeano.getPosicion().getColumna());
			this.mapa.posicionarEnFilaColumna(aldeano, aldeano.getPosicion().getFila(),aldeano.getPosicion().getColumna());
			return aldeano;
		}	
		throw new JugadorSinOroException();			
	}
	
	public void avanzarTurno() {
		//lo que deberia hacer es recorrer el diccionario y avanzar el turno de cada posicionable
		//Posicion posicionDelPosicionable = new Posicion (1,1);	//temporal
		//this.posicionables.get(posicionDelPosicionable).avanzarTurno();
		
		Posicionable vacio = new Vacio();
		posicionables.forEach((k,v) -> {
			if(v.getVida() == 0) {
				posicionables.replace(k,v, vacio);
			}
			this.oro += v.avanzarTurno();
		});
	}

	public void agregarPosicionableEnFilaColumna(Posicionable posicionable, int fila, int columna) {
		
		Posicion posicionDelPosicionable = new Posicion (fila, columna);
		
		this.posicionables.put(posicionDelPosicionable, posicionable);
		
	}
	

	public void desplazarFilaColumnaHaciaArriba(int fila, int columna, int cantidadDePosiciones) throws MovimientosPorTurnoExcedidosError, PosicionDesocupadaError, PosicionOcupadaError {
		
		Posicion posicionDesplazable = new Posicion (fila, columna);
		
		if (!this.mapa.estaOcupado (posicionDesplazable)) {
			
			throw new PosicionDesocupadaError ();
			
		}
		
		Posicionable posicionableEnPosicion = this.posicionables.get(posicionDesplazable);
		posicionableEnPosicion.desplazarHaciaArriba (1);
		posicionDesplazable.desplazarHaciaArriba (1);
		this.mapa.posicionarPosicionableEnPosicion (posicionableEnPosicion, posicionDesplazable);
		
		
	}
	
	public void desplazarFilaColumnaHaciaAbajo(int fila, int columna, int cantidadDePosiciones) throws MovimientosPorTurnoExcedidosError, PosicionOcupadaError, PosicionDesocupadaError {
		
		Posicion posicionDesplazable = new Posicion (fila, columna);
		
		if (!this.mapa.estaOcupado (posicionDesplazable)) {
			
			throw new PosicionDesocupadaError ();
			
		}
		
		Posicionable posicionableEnPosicion = this.posicionables.get(posicionDesplazable);
		posicionableEnPosicion.desplazarHaciaAbajo (1);
		posicionDesplazable.desplazarHaciaAbajo (1);
		this.mapa.posicionarPosicionableEnPosicion (posicionableEnPosicion, posicionDesplazable);
		
		
	}
	
	public void desplazarFilaColumnaHaciaLaDerecha(int fila, int columna, int cantidadDePosiciones) throws MovimientosPorTurnoExcedidosError, PosicionOcupadaError, PosicionDesocupadaError {
		
		Posicion posicionDesplazable = new Posicion (fila, columna);
		
		if (!this.mapa.estaOcupado (posicionDesplazable)) {
			
			throw new PosicionDesocupadaError ();
			
		}
		
		Posicionable posicionableEnPosicion = this.posicionables.get(posicionDesplazable);
		posicionableEnPosicion.desplazarHaciaLaDerecha (1);
		posicionDesplazable.desplazarHaciaLaDerecha (1);
		this.mapa.posicionarPosicionableEnPosicion (posicionableEnPosicion, posicionDesplazable);
		
		
	}
	
	public void desplazarFilaColumnaHaciaLaIzquierda(int fila, int columna, int cantidadDePosiciones) throws MovimientosPorTurnoExcedidosError, PosicionOcupadaError, PosicionDesocupadaError {
		
		Posicion posicionDesplazable = new Posicion (fila, columna);
		
		if (!this.mapa.estaOcupado (posicionDesplazable)) {
			
			throw new PosicionDesocupadaError ();
			
		}
		
		Posicionable posicionableEnPosicion = this.posicionables.get(posicionDesplazable);
		posicionableEnPosicion.desplazarHaciaLaIzquierda (1);
		posicionDesplazable.desplazarHaciaLaIzquierda (1);
		this.mapa.posicionarPosicionableEnPosicion (posicionableEnPosicion, posicionDesplazable);
		
		
	}
	
	public void desplazarFilaColumnaHaciaLaDiagonalSuperiorDerecha(int fila, int columna, int cantidadDePosiciones) throws MovimientosPorTurnoExcedidosError, PosicionOcupadaError, PosicionDesocupadaError {
		
		Posicion posicionDesplazable = new Posicion (fila, columna);
		
		if (!this.mapa.estaOcupado (posicionDesplazable)) {
			
			throw new PosicionDesocupadaError ();
			
		}
		
		Posicionable posicionableEnPosicion = this.posicionables.get(posicionDesplazable);
		posicionableEnPosicion.desplazarHaciaLaDiagonalSuperiorDerecha (1);
		posicionDesplazable.desplazarHaciaLaDiagonalSuperiorDerecha (1);
		this.mapa.posicionarPosicionableEnPosicion (posicionableEnPosicion, posicionDesplazable);
		
		
	}
	
	public void desplazarFilaColumnaHaciaLaDiagonalSuperiorIzquierda(int fila, int columna, int cantidadDePosiciones) throws MovimientosPorTurnoExcedidosError, PosicionOcupadaError, PosicionDesocupadaError {
		
		Posicion posicionDesplazable = new Posicion (fila, columna);
		
		if (!this.mapa.estaOcupado (posicionDesplazable)) {
			
			throw new PosicionDesocupadaError ();
			
		}
		
		Posicionable posicionableEnPosicion = this.posicionables.get(posicionDesplazable);
		posicionableEnPosicion.desplazarHaciaLaDiagonalSuperiorIzquierda(1);
		posicionDesplazable.desplazarHaciaLaDiagonalSuperiorIzquierda (1);
		this.mapa.posicionarPosicionableEnPosicion (posicionableEnPosicion, posicionDesplazable);
		
		
	}
	
	public void desplazarFilaColumnaHaciaLaDiagonalInferiorIzquierda(int fila, int columna, int cantidadDePosiciones) throws MovimientosPorTurnoExcedidosError, PosicionOcupadaError, PosicionDesocupadaError {
		
		Posicion posicionDesplazable = new Posicion (fila, columna);
		
		if (!this.mapa.estaOcupado (posicionDesplazable)) {
			
			throw new PosicionDesocupadaError ();
			
		}
		
		Posicionable posicionableEnPosicion = this.posicionables.get(posicionDesplazable);
		posicionableEnPosicion.desplazarHaciaLaDiagonalInferiorIzquierda (1);
		posicionDesplazable.desplazarHaciaLaDiagonalInferiorIzquierda (1);
		this.mapa.posicionarPosicionableEnPosicion (posicionableEnPosicion, posicionDesplazable);
		
		
	}
	
	public void desplazarFilaColumnaHaciaLaDiagonalInferiorDerecha(int fila, int columna, int cantidadDePosiciones) throws MovimientosPorTurnoExcedidosError, PosicionOcupadaError, PosicionDesocupadaError {
		
		Posicion posicionDesplazable = new Posicion (fila, columna);
		
		if (!this.mapa.estaOcupado (posicionDesplazable)) {
			
			throw new PosicionDesocupadaError ();
			
		}
		
		Posicionable posicionableEnPosicion = this.posicionables.get(posicionDesplazable);
		posicionableEnPosicion.desplazarHaciaLaDiagonalInferiorIzquierda (1);
		posicionDesplazable.desplazarHaciaLaDiagonalInferiorIzquierda(1);
		this.mapa.posicionarPosicionableEnPosicion (posicionableEnPosicion, posicionDesplazable);
		
		
	}

	public void iniciarUnidades() throws PosicionFueraDelMapaError, PosicionOcupadaError {
	
		for (int i = 5; i <= 7; i++ ) {
		    Posicionable aldeano = new Aldeano();
		    this.agregarPosicionableEnFilaColumna(aldeano, 5, i);
		    this.mapa.posicionarEnFilaColumna(aldeano, 5, i);
        }
		
	}

	public void iniciarEdificios() throws TamanioIncorrectoError, PosicionFueraDelMapaError, PosicionOcupadaError {

		this.crearCastilloDesdeHasta(1,1,4,4);
		this.crearPlazaCentralDesdeHasta(1,8,2,9);	
	}

	public void crearCastilloDesdeHasta(int desdeX, int desdeY, int hastaX, int hastaY) throws TamanioIncorrectoError, PosicionFueraDelMapaError, PosicionOcupadaError{

		Edificio castillo = new Castillo();

		if (! castillo.tieneTamanioCorrecto(desdeX, desdeY, hastaX, hastaY)){
			throw new TamanioIncorrectoError();
		}

		Posicion posicionDesde = new Posicion (desdeX, desdeY);
		Posicion posicionHasta = new Posicion (hastaX, hastaY);

		if (posicionDesde.noPerteneceAlRango(this.mapa.getFilas(), this.mapa.getColumnas())){
			throw new PosicionFueraDelMapaError ();
		}

		if (posicionHasta.noPerteneceAlRango(this.mapa.getFilas(), this.mapa.getColumnas())){
			throw new PosicionFueraDelMapaError ();
		}

		this.mapa.ponerEdificioDesdeHasta(castillo, desdeX, desdeY, hastaX, hastaY);
		this.agregarEdificioAPosicionables(castillo, desdeX, desdeY, hastaX, hastaY);

	}
	
	public void crearPlazaCentralDesdeHasta(int desdeX, int desdeY, int hastaX, int hastaY) throws TamanioIncorrectoError, PosicionFueraDelMapaError, PosicionOcupadaError{
		
		Edificio plazaCentral = new PlazaCentral();

		if (! plazaCentral.tieneTamanioCorrecto(desdeX, desdeY, hastaX, hastaY)){
			throw new TamanioIncorrectoError();
		}

		Posicion posicionDesde = new Posicion (desdeX, desdeY);
		Posicion posicionHasta = new Posicion (hastaX, hastaY);

		if (posicionDesde.noPerteneceAlRango(this.mapa.getFilas(), this.mapa.getColumnas())){
			throw new PosicionFueraDelMapaError ();
		}

		if (posicionHasta.noPerteneceAlRango(this.mapa.getFilas(), this.mapa.getColumnas())){
			throw new PosicionFueraDelMapaError ();
		}

		this.mapa.ponerEdificioDesdeHasta(plazaCentral, desdeX, desdeY, hastaX, hastaY);
		this.agregarEdificioAPosicionables(plazaCentral, desdeX, desdeY, hastaX, hastaY);
	}

	private void agregarEdificioAPosicionables(Posicionable edificio, int desdeX, int desdeY, int hastaX, int hastaY){

		for (int i = desdeX; i <= hastaX; i++){
			for (int j = desdeY; j <= hastaY; j++){
				this.agregarPosicionableEnFilaColumna(edificio, i, j);
			}
		}

	}


	public Object getPoblacion() {
		return this.poblacion;
	}


	public Object getOro() {
		return this.oro;
	}
}

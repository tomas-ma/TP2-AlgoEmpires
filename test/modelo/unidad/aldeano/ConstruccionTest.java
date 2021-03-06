package modelo.unidad.aldeano;

import static org.junit.Assert.*;

import org.junit.Test;

import modelo.edificio.Edificio;
import modelo.edificio.EdificioOcupadoException;
import modelo.edificio.cuartel.Cuartel;
import modelo.edificio.plazaCentral.PlazaCentral;
import modelo.jugador.Jugador;
import modelo.jugador.PosicionOcupadaError;
import modelo.mapa.Mapa;
import modelo.mapa.Posicion;
import modelo.unidad.PosicionFueraDelMapaError;
import modelo.unidad.Posicionable;
import modelo.unidad.aldeano.Aldeano;
import modelo.unidad.arquero.Arquero;

public class ConstruccionTest {

//	private int vidaCuartel = 450;
//	private int vidaCuartelDaniado = 400;
	
	@Test(expected=EdificioOcupadoException.class)
	public void test01CreoUnCuartelYComoEstaEnConstruccionCrearArqueroDevuelveException() {
		Mapa mapa = new Mapa ();
		Jugador jugador = new Jugador (mapa, "anto", "juan");
		Cuartel cuartel = new Cuartel();
		cuartel.crearUnidadPropia('A', jugador);
	}

	/*

	// ESTA CLASE DEPENDE DEL ATAQUE Y DEBERIA MODIFICARSE PARA OBTENER EL JUGADOR. LA DEJO PARA DESPUES
	
	@Test
	public void test02CreoUnCuartelYComoEstaConstruidoCrearArqueroDevuelveElArqueroCreadoYAtacarAldeanoLeResta15DeVida()  {
		Cuartel cuartel = new Cuartel();
		Posicionable aldeano = new Aldeano();
		
		cuartel.avanzarTurno();
		cuartel.avanzarTurno();
		cuartel.avanzarTurno();
		
		Unidad arquero = cuartel.crearUnidad('A');
		
		arquero.atacar(aldeano);
		assertEquals (aldeano.getVida(),35);
		
	}
	*/


	@Test(expected=EdificioOcupadoException.class)
	public void test03CreoUnaPlazaCentralYComoEstaEnConstruccionCrearAldeanoDevuelveException() {
		Mapa mapa = new Mapa ();
		Jugador jugador = new Jugador (mapa, "anto", "juan");
		PlazaCentral plaza = new PlazaCentral();
		
		plaza.crearUnidadPropia('A', jugador);

	}
	
	@Test
	public void test04CreoUnaPlazaCentralYComoEstaConstruidaCrearAldeanoDevuelveElAdeanoCreadoYRepararCuartelDaniadoLoRepara()  {
		
		Mapa mapa = new Mapa ();
		Jugador primerJugador = new Jugador (mapa, "Lucas", "Juan");
		Jugador segundoJugador = new Jugador (mapa, "Juan", "Lucas");
		Arquero arquero = new Arquero (5, 5, primerJugador);
		PlazaCentral plaza = new PlazaCentral (5, 6, 6, 7, segundoJugador);
		Cuartel cuartel = new Cuartel (7, 4, 8, 5, segundoJugador);

		primerJugador.agregarPosicionableEnFilaColumna(arquero, 5, 5);
		segundoJugador.agregarPosicionableEnFilaColumna (plaza, 5, 6);
		segundoJugador.agregarPosicionableEnFilaColumna(cuartel, 7, 4);
			
		cuartel.avanzarTurno();
		cuartel.actualizar();
		cuartel.avanzarTurno();
		cuartel.actualizar();
		cuartel.avanzarTurno();
		cuartel.actualizar();
		
		
		arquero.atacar(cuartel,new Posicion (7,4));	//le resta 10 de vida
		
		plaza.avanzarTurno();
		plaza.actualizar();
		plaza.avanzarTurno();
		plaza.actualizar();
		plaza.avanzarTurno();
		plaza.actualizar();
		
		Aldeano aldeano = (Aldeano)plaza.crearUnidadPropia('A', segundoJugador);
		aldeano.reparar(cuartel);
		cuartel.actualizar();
		cuartel.avanzarTurno();
	
	
		assertEquals (cuartel.getVida(),250);
	}
	
	@Test
	public void test05CreoUnAldeanoYComoEstaConstruyendoAvanzarTurnoDevuelveCero() {
		Aldeano aldeano = new Aldeano();
		aldeano.construir('C');
		int cero = 0;
		int oroGenerado = 0;
		
		oroGenerado = aldeano.avanzarTurno();
	
		assertEquals (cero,oroGenerado);
	}


	@Test
	public void test06CreoDosCuartelesConJugador1YJugador2YAvanzarTurnoJugador1YGetTurnosConstruccionDeCuartel1Devuelve2() throws PosicionOcupadaError, PosicionFueraDelMapaError{
		Mapa mapa = new Mapa();
		Jugador jugador1 = new Jugador(mapa,"Jorge", "Pablo");
		Jugador jugador2 = jugador1.jugadorSiguiente();
		jugador1.crearCastilloDesde(10, 10);	
		
		Posicionable aldeano1 = new Aldeano();
		Posicionable aldeano2 = new Aldeano();
		aldeano1.posicionarEnFilaColumna(15,15);
		aldeano2.posicionarEnFilaColumna(17,5);
		jugador1.agregarPosicionableEnFilaColumna(aldeano1, 15, 15);
		jugador2.agregarPosicionableEnFilaColumna(aldeano2, 17, 5);
		
		Posicion posicionAldeano = new Posicion(15,15);
		Posicion posicionConstruccion = new Posicion(16,16);

		jugador1.construirEdificioPropio(posicionAldeano,posicionConstruccion,'C');

		Edificio cuartel = (Edificio) mapa.obtenerPosicionableEn(posicionConstruccion);
		jugador1.avanzarTurno();


		assertEquals (2,(cuartel.getTurnosConstruccion()));
		
		posicionAldeano = new Posicion(17,5);
		posicionConstruccion = new Posicion(18,6);
		jugador2.construirEdificioPropio(posicionAldeano,posicionConstruccion,'C');
		Edificio cuartel2 = (Edificio) mapa.obtenerPosicionableEn(posicionConstruccion);
		
		assertEquals (3,(cuartel2.getTurnosConstruccion()));
			
	}


	@Test
	public void test07CreoDosCuartelesConJugador1YJugador2YAvanzarTurnoJugador2YGetTurnosConstruccionDeCuartel2Devuelve2() throws PosicionFueraDelMapaError, PosicionOcupadaError{
		Mapa mapa = new Mapa();
		Jugador jugador1 = new Jugador(mapa,"Jorge", "Pablo");
		Jugador jugador2 = jugador1.jugadorSiguiente();
		jugador1.crearCastilloDesde(5, 5);
		jugador2.crearCastilloDesde(15, 15);
		
		Posicionable aldeano1 = new Aldeano();
		Posicionable aldeano2 = new Aldeano();
		aldeano1.posicionarEnFilaColumna(10,10);
		aldeano2.posicionarEnFilaColumna(17,5);
		jugador1.agregarPosicionableEnFilaColumna(aldeano1, 10, 10);
		jugador2.agregarPosicionableEnFilaColumna(aldeano2, 17, 5);
		
		Posicion posicionAldeano = new Posicion(10,10);
		Posicion posicionConstruccion = new Posicion(11,11);
				
		jugador1.construirEdificioPropio(posicionAldeano,posicionConstruccion,'C');
			
		Edificio cuartel = (Edificio) mapa.obtenerPosicionableEn(posicionConstruccion);
		jugador1.avanzarTurno();
		
		assertEquals (2,(cuartel.getTurnosConstruccion()));
		
		posicionAldeano = new Posicion(17,5);
		posicionConstruccion = new Posicion(18,6);
		jugador2.construirEdificioPropio(posicionAldeano,posicionConstruccion,'C');
		Edificio cuartel2 = (Edificio) mapa.obtenerPosicionableEn(posicionConstruccion);
		jugador2.avanzarTurno();
		
		assertEquals (2,(cuartel2.getTurnosConstruccion()));
	}

	
	@Test
	public void test08CreoUnAldeanoAvanzarTurnoDevuelve20() {
		Aldeano aldeano = new Aldeano();
		int oroGenerado = 0;
		
		oroGenerado = aldeano.avanzarTurno();
	
		assertEquals (20,oroGenerado);
	}

	@Test
	public void test09CreoUnAldeanoQueConstruyaUnaPlazaCentralYComoEstaConstruidaCreoUnAldeanoQueReparaUnCuartelDaniadoRestaurandoSuVidaA250()  {
		
		Mapa mapa = new Mapa ();
		Jugador primerJugador = new Jugador (mapa, "Mateo", "Mia");
		Jugador segundoJugador = new Jugador (mapa, "Mia", "Mayeo");		
		
		Aldeano aldeano = new Aldeano();
		Arquero arquero = new Arquero(5,5, primerJugador);
		PlazaCentral plaza = (PlazaCentral) aldeano.construir('P');
		plaza.avanzarTurno();
		plaza.actualizar();
		plaza.avanzarTurno();
		plaza.actualizar();
		plaza.avanzarTurno();
		plaza.actualizar();
		
		Aldeano otroAldeano = (Aldeano)plaza.crearUnidadPropia('A', segundoJugador);
		

		primerJugador.agregarPosicionableEnFilaColumna(arquero, 5, 5);

		
		Edificio cuartelDaniado = new Cuartel(7,4,8,5, segundoJugador);
		segundoJugador.agregarPosicionableEnFilaColumna(cuartelDaniado, 7, 4);
		cuartelDaniado.avanzarTurno();
		cuartelDaniado.actualizar();
		cuartelDaniado.avanzarTurno();
		cuartelDaniado.actualizar();
		cuartelDaniado.avanzarTurno();
		cuartelDaniado.actualizar();
		arquero.atacar(cuartelDaniado, new Posicion (7,4));
		
		otroAldeano.reparar(cuartelDaniado);
		cuartelDaniado.actualizar();
		cuartelDaniado.avanzarTurno();
		cuartelDaniado.actualizar();
		
		assertEquals (250,cuartelDaniado.getVida());
		
	}
	
	
	@Test
	public void test10CreoUnAldeanoYComoTerminoDeRepararDevuelve20() {
		
		Mapa mapa = new Mapa ();
		Jugador primerJugador = new Jugador (mapa, "Anto", "Juan");
		Jugador segundoJugador = new Jugador (mapa, "Juan", "Anto");
		
		Aldeano aldeano = new Aldeano();
		Arquero arquero = new Arquero(5,5, primerJugador);
		Edificio cuartel = new Cuartel(7,4,8,5, segundoJugador);
		int oroGenerado;
		
		cuartel.avanzarTurno();
		cuartel.actualizar();
		cuartel.avanzarTurno();
		cuartel.actualizar();
		cuartel.avanzarTurno();
		cuartel.actualizar();

		
		arquero.atacar(cuartel, new Posicion (7,4));
		aldeano.reparar(cuartel);
		aldeano.avanzarTurno();

		
		oroGenerado = aldeano.avanzarTurno();
	
		assertEquals (20,oroGenerado);
	}
	
	@Test
	public void test11CreoUnCuartelCon200DeVidaYComoEstaReparadoGetVidaDevuelve250() {
		
		Mapa mapa = new Mapa ();
		Jugador primerJugador = new Jugador (mapa, "anto", "juan");
		Jugador segundoJugador = new Jugador (mapa, "juan", "anto");
		
		Aldeano aldeano = new Aldeano();
		Edificio cuartel = new Cuartel(7,4,8,5, segundoJugador);
		Arquero arquero = new Arquero(5,5, primerJugador);
		
		cuartel.avanzarTurno();		//construccion cuartel - 3 turnos
		cuartel.actualizar();
		cuartel.avanzarTurno();	    //construccion cuartel - 3 turnos	
		cuartel.actualizar();
		cuartel.avanzarTurno();		//construccion cuartel - 3 turnos
		cuartel.actualizar();
		
		arquero.atacar(cuartel, new Posicion (7,4));
		aldeano.reparar(cuartel);
		cuartel.actualizar();
		cuartel.avanzarTurno();
		
	
		assertEquals (250,cuartel.getVida());
	}

	@Test
	public void test12CreoUnAldeanoQueConstruyaUnCuartelYComoEstaConstruidoCreaUnArqueroQueAtacaUnAldeanoRestandole15DeVida() {
		
		Mapa mapa = new Mapa ();
		Jugador primerJugador = new Jugador ( mapa, "anto", "juan");
		Jugador segundoJugador = new Jugador (mapa, "juan", "anto");
		
		Aldeano aldeano = new Aldeano(4, 4, primerJugador);
		Cuartel cuartel = (Cuartel)aldeano.construirPropio('C', primerJugador);
		
		cuartel.avanzarTurno();
		cuartel.actualizar();
		cuartel.avanzarTurno();
		cuartel.actualizar();
		cuartel.avanzarTurno();
		cuartel.actualizar();

		
		Arquero arquero = (Arquero)cuartel.crearUnidadPropia('A', segundoJugador);
		arquero.posicionarEnFilaColumna(5, 5);
		
		arquero.atacar(aldeano, new Posicion (4,4));
		assertEquals (35,aldeano.getVida());
	}
	
	@Test
	public void test13CreoDosCuartelesConJugador1YJugador2YAvanzarTurnoJugador1YGetTurnosConstruccionDeCuartel2Devuelve3(){
		Mapa mapa = new Mapa();
		Jugador jugador1 = new Jugador(mapa,"Jorge", "Pablo");
		Jugador jugador2 = jugador1.jugadorSiguiente();
		jugador1.crearCastilloDesde(5, 5);
		jugador2.crearCastilloDesde(15, 15);
		
		Posicionable aldeano1 = new Aldeano();
		Posicionable aldeano2 = new Aldeano();
		aldeano1.posicionarEnFilaColumna(10,10);
		aldeano2.posicionarEnFilaColumna(17,5);
		jugador1.agregarPosicionableEnFilaColumna(aldeano1, 10, 10);
		jugador2.agregarPosicionableEnFilaColumna(aldeano2, 17, 5);
		
		Posicion posicionAldeano = new Posicion(10,10);
		Posicion posicionConstruccion = new Posicion(11,11);
				
		jugador1.construirEdificioPropio(posicionAldeano,posicionConstruccion,'C');
			
		Edificio cuartel = (Edificio) mapa.obtenerPosicionableEn(posicionConstruccion);
		jugador2.avanzarTurno();
		
		assertEquals (3,(cuartel.getTurnosConstruccion()));
		
	}
	
	@Test(expected=AldeanoOcupadoException.class)
	public void test14CreoUnAldeanoYComoEstaOcupadoConstruirCuartelDevuelveException() {
		Mapa mapa = new Mapa ();
		Jugador primerJugador = new Jugador (mapa, "anto", "juan");
		Jugador segundoJugador = new Jugador (mapa, "juan", "anto");
		
		Aldeano aldeano = new Aldeano();
		Edificio cuartelDaniado = new Cuartel(7,4,8,5, segundoJugador);
		Arquero arquero = new Arquero(5,5, primerJugador);
		
		cuartelDaniado.avanzarTurno();
		cuartelDaniado.actualizar();
		cuartelDaniado.avanzarTurno();
		cuartelDaniado.actualizar();
		cuartelDaniado.avanzarTurno();
		cuartelDaniado.actualizar();

		
		
		arquero.atacar(cuartelDaniado, new Posicion (7,4));
		aldeano.reparar(cuartelDaniado);
		aldeano.construir('C');
	}
	
	@Test(expected=AldeanoOcupadoException.class)
	public void test15CreoUnAldeanoYComoEstaOcupadoRepararCuartelDevuelveException()  {
		Mapa mapa = new Mapa ();
		Jugador primerJugador = new Jugador (mapa, "anto", "juan");
		Jugador segundoJugador = new Jugador (mapa, "juan", "anto");
		
		Aldeano aldeano = new Aldeano();
		Edificio cuartelDaniado = new Cuartel(7,4,8,5, segundoJugador);
		Edificio otroCuartelDaniado = new Cuartel(2,7,3,8, segundoJugador);
		Arquero arquero = new Arquero(5,5, primerJugador);
		
		cuartelDaniado.avanzarTurno();
		cuartelDaniado.actualizar();
		cuartelDaniado.avanzarTurno();
		cuartelDaniado.actualizar();
		cuartelDaniado.avanzarTurno();
		cuartelDaniado.actualizar();
		otroCuartelDaniado.avanzarTurno();
		otroCuartelDaniado.actualizar();
		otroCuartelDaniado.avanzarTurno();
		otroCuartelDaniado.actualizar();
		otroCuartelDaniado.avanzarTurno();
		otroCuartelDaniado.actualizar();
		
		arquero.atacar(cuartelDaniado, new Posicion (7,4));
		arquero.atacar(otroCuartelDaniado, new Posicion (2,7));

		aldeano.reparar(cuartelDaniado);
		aldeano.reparar(otroCuartelDaniado);
	}
	
	@Test(expected=EdificioOcupadoException.class)
	public void test15CreoUnCuartelYComoEstaEnConstruccionRepararCuartelDevuelveException() {
		Aldeano aldeano = new Aldeano();
		Edificio cuartel = new Cuartel();
	
		aldeano.reparar(cuartel);
	}


}

package modelo.vacio;

import modelo.ataque.Ataque;
import modelo.unidad.Posicionable;

public class Vacio extends Posicionable{

	public Vacio(int i, int j) {
		super(i, j);
	}
	
	public Vacio() {
		
	}

	@Override
	public int avanzarTurno() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getVida() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void atacado(Ataque ataque) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean estaOcupado() {
		return false;		
	}
	

}

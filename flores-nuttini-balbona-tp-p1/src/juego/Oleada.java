package juego;

public class Oleada {
	private int numOleadaActual;
	private int murcielagosEnEstaOleada;
	private int murcielagosDerrotados;
	private int murcielagosGenerados;
	private int estadoOleada; ///0 - En espera a siguiente oleada
							  ///1 - Mobs apareciendo y atacando
							  ///2 - Todos los mobs de la oleada generados, esperando a ser todos derrotados
							  ///3 - Oleada finalizada
	
	private int contadorDescanso; ///Expresado en ticks (100 ticks = 1 segundo)
	private int tiempoDescansoEntreOleadas;
	
	private int cantMurcielagosIniciales; 		///En la primera oleada apareceran N murcielagos
	private int incrementoMurcielagosPorOleada; ///y cada oleada se iran incrementando
	
	private static int oleadaGanadora = 4;     ///Oleada que tiene que alcanzar el jugador para ganar
	
	private int dañoBaseMurcielago = 10;     ///Daño inicial en la primera oledada
	private static int incrementoDañoPorOleada = 5; ///Cuanto aumenta el daño por oleada
	private int dañoActualMurcielago;
	
	public Oleada(int tiempoDescansoEntreOleadas, int cantMurcielagosIniciales, int incrementoMurcielagos) {
		this.tiempoDescansoEntreOleadas = tiempoDescansoEntreOleadas;
		this.cantMurcielagosIniciales = cantMurcielagosIniciales;
		this.incrementoMurcielagosPorOleada = incrementoMurcielagos;
		this.numOleadaActual = 0;
		this.estadoOleada = 0;
		this.contadorDescanso = tiempoDescansoEntreOleadas;
		this.dañoActualMurcielago = dañoBaseMurcielago;
	}
	
	////////GETTERS Y SETTERS////////////
	public int getNumOleadaActual() {
		return numOleadaActual;
	}

	public void setNumOleadaActual(int numOleadaActual) {
		this.numOleadaActual = numOleadaActual;
	}

	public int getmurcielagosEnEstaOleada() {
		return murcielagosEnEstaOleada;
	}

	public void setmurcielagosEnEstaOleada(int murcielagosXoleada) {
		this.murcielagosEnEstaOleada = murcielagosXoleada;
	}

	public int getMurcielagosDerrotados() {
		return murcielagosDerrotados;
	}

	public void setMurcielagosDerrotados(int murcielagosDerrotados) {
		this.murcielagosDerrotados = murcielagosDerrotados;
	}

	public int getMurcielagosGenerados() {
		return murcielagosGenerados;
	}

	public void setMurcielagosGenerados(int murcielagosGenerados) {
		this.murcielagosGenerados = murcielagosGenerados;
	}

	public int getEstadoOleada() {
		return estadoOleada;
	}

	public void setEstadoOleada(int estadoOleada) {
		this.estadoOleada = estadoOleada;
	}

	public int getContadorDescanso() {
		return contadorDescanso;
	}

	public void setContadorDescanso(int contadorDescanso) {
		this.contadorDescanso = contadorDescanso;
	}

	public int getTiempoDescansoEntreOleadas() {
		return tiempoDescansoEntreOleadas;
	}

	public void setTiempoDescansoEntreOleadas(int tiempoDescansoEntreOleadas) {
		this.tiempoDescansoEntreOleadas = tiempoDescansoEntreOleadas;
	}

	public int getCantMurcielagosIniciales() {
		return cantMurcielagosIniciales;
	}

	public void setCantMurcielagosIniciales(int cantMurcielagosIniciales) {
		this.cantMurcielagosIniciales = cantMurcielagosIniciales;
	}

	public int getIncrementoMurcielagosPorOleada() {
		return incrementoMurcielagosPorOleada;
	}

	public void setIncrementoMurcielagosPorOleada(int incrementoMurcielagosPorOleada) {
		this.incrementoMurcielagosPorOleada = incrementoMurcielagosPorOleada;
	}
	
	public int getOleadaGanadora() {
		return oleadaGanadora;
	}
	public int getDañoActualMurcielagos() {
		return this.dañoActualMurcielago;
	}

	/////////////////////////////////////
	
	////////////////////////////////////////
	
	public void iniciarOleada() {
		this.numOleadaActual++;
		System.out.println("Inicia oleada:" + this.numOleadaActual);
		
		this.murcielagosEnEstaOleada = this.cantMurcielagosIniciales + (this.numOleadaActual - 1) * this.incrementoMurcielagosPorOleada;
		this.murcielagosGenerados = 0;
		this.murcielagosDerrotados = 0;
		
		this.dañoActualMurcielago = this.dañoBaseMurcielago + (this.numOleadaActual - 1) * this.incrementoMurcielagosPorOleada; ///Aumenta el daño por oleada
		this.estadoOleada = 1; ///Generando Mobs
		
	}
	
	public void actualizar(int mobsActivosEnPantalla) {
		switch(this.estadoOleada) {
			case 0: ///Descanso entre oleadas
				this.contadorDescanso--;
				if(this.contadorDescanso <= 0) {
					iniciarOleada();
				}
				break;
			case 1: ///Genera Mobs hasta que la cantidad Maxima sea alcanzada
				if(this.murcielagosGenerados >= this.murcielagosEnEstaOleada) {
					this.estadoOleada = 2; 
				}
				break;
			case 2: ///Esperando a que los mobs sean derrotados
				if(mobsActivosEnPantalla == 0) {
					this.estadoOleada = 0;
					this.contadorDescanso = this.tiempoDescansoEntreOleadas;
				}
				break;
			case 3: ///La oleada termino
				break;
		}
	}
	
	public boolean necesitaGenerarEnemigo() {
		return this.estadoOleada == 1 && this.murcielagosGenerados < this.murcielagosEnEstaOleada;
	}
	
	public void mobGenerado() {
		this.murcielagosGenerados++;
	}
	public void mobDerrotado() {
		this.murcielagosDerrotados++;
	}
	
	
	
}

class Barreira{

	final int N;
	Etapa etapa = new Etapa();

	static class Etapa{int waiting;}

	Barreira (int N) { this.N = N;}

	synchronized void espera() throws InterruptedException{
		
		Etapa minhaEtapa = etapa;
		minhaEtapa.waiting++;

		if (minhaEtapa.waiting == N) {
			notifyAll();
		    etapa = new Etapa();
		}else{
			while(etapa == minhaEtapa)
				wait();
		}
	}
}
class Barreira{

	final int N;
	int waiting;
	int etapa;

	Barreira (int N) { this.N = N;}

	synchronized void espera() throws InterruptedException{
		waiting ++;
		int minhaEtapa = etapa;

		if (waiting == N) {
			notifyAll();
		    waiting = 0;
		    etapa ++;
		}else{
			while(etapa == minhaEtapa)
				wait();
		}
	}
}
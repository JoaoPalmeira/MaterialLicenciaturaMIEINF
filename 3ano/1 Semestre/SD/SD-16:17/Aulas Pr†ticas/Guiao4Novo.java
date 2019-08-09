public class BoundedBuffer {
	int a[];
	int n; //tamanho atual do array
	int ip; //indice onde se faz o put
	int ig; //indice onde se faz o get

	BoundedBuffer (int N) {
        a = new int [N];
    } //definida a capacidade maxima do array
	
	synchronized int get (){
		while(n==0)
			wait();
		int v = a[ig];
		n--;
        ig = [ig + 1] % a.length();
	    notifyAll(); /*ha uma invocaçao do synchronized (ha um lock) antes e depois do wait por isso o notifyAll pode estar em qualquer lugar depois do wait. as instruçoes depois do wait sao como um so bloco que 
                     acontece todo ao mesmo tempo do ponto de vista das outras threads, logo nao faz diferença. isto e valido, pois estamos dentro de um bloco synchronized*/
        return v;
	}

	synchronized void put (int v){
		while(n==a.length())
			wait();
		a[ip] = v;
		ip = [ip + 1] % a.length();
	    n++;
	    notifyAll();
	}

    static class Produtor extends Thread{
        BoundedBuffer bb;
        Produtor (BoundedBuffer bb){
            this.bb = bb;
        }

        public void run(){
            try{
            int i = 0;
            while(true){
                bb.put(i);
                System.out.println("Fiz put:" + i);
                i++;
                Thread.sleep(1000);
                }   
            }
            catch (Exception e) {};
        }
    }

    static class Consumidor extends Thread{
        BoundedBuffer bb;
        Consumidor (BoundedBuffer bb){
            this.bb = bb;
        }

        public void run(){
            try{
            while(true){
                int i = bb.get();
                System.out.println("Fiz get de:" + i);
                Thread.sleep(1000);
                }
            }
            catch (Exception e) {};
        }
    }

    public static void main (String[] args) throws InterruptedException{
        BoundedBuffer bb = new BoundedBuffer [10];
        Produtor p = new Produtor (bb);
        Consumidor c = new Consumidor (bb);
        p.start();
        c.start();
        p.join();
        c.join();
    }


}


public class TabelaEstado{

        private int serverPort;
	private int rtt;
	private int Ram;
	private int Cpu;
	private int larguraBanda;

    public TabelaEstado(int serverPort, int rtt, int Ram, int Cpu, int larguraBanda) {
        this.serverPort = serverPort;
        this.rtt = rtt;
        this.Ram = Ram;
        this.Cpu = Cpu;
        this.larguraBanda = larguraBanda;
    }

    public int getServerPort() {
        return serverPort;
    }

    public int getRtt() {
        return rtt;
    }

    public int getRam() {
        return Ram;
    }

    public int getCpu() {
        return Cpu;
    }

    public int getLarguraBanda() {
        return larguraBanda;
    }


    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public void setRtt(int rtt) {
        this.rtt = rtt;
    }

    public void setRam(int Ram) {
        this.Ram = Ram;
    }

    public void setCpu(int Cpu) {
        this.Cpu = Cpu;
    }

    public void setLarguraBanda(int larguraBanda) {
        this.larguraBanda = larguraBanda;
    }
	
        

	
}
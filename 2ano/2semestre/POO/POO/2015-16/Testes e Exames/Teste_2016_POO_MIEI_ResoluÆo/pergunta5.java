public double qtsKmsTotal(){
	int sum =0;
	for(Object e : this.empregados.values()){
		if(e instanceof Motorista){
			sum += ((Motorista)e).getNKms();
		}
	}
	return sum;
}
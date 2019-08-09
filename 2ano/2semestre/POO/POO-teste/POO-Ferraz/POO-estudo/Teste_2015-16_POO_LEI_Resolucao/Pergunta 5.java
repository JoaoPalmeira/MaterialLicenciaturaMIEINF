public double qtsKmsTotal(){
	int sum =0;
	for(Object e : empregados.values){
		if(e instanceof Motorista){
			sum += getNKms;
		}
	}
	return sum;
}
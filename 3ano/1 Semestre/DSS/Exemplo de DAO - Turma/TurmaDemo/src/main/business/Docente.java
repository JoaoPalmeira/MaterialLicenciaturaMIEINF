package main.business;

public class Docente implements Identificavel {
	private String _nome;
	public Turma _unnamed_Turma_;
	public Curso _unnamed_Curso_;
	public RegistoCurricular _unnamed_RegistoCurricular_;

	public void addAluno(Aluno a, Turma t) {
		t.addAluno(a);
	}


	public String getId() {
		//...
		return "d12345";
	}
}
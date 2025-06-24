package model;

public class Aluno extends Usuario {
    private String curso;

    public Aluno(String nome, String matricula, String cpf, String email, String curso) {
        super(nome, matricula, cpf, email);
        this.curso = curso;
    }

    @Override
    public String getTipo() {
        return "Aluno";
    }

    public String getCurso() { return curso; }

    @Override
    public String toString() {
        return super.toString() + " | Curso: " + curso;
    }
}
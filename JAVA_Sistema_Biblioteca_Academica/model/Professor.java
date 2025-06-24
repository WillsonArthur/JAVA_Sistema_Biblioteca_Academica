package model;

public class Professor extends Usuario {
    private String departamento;

    public Professor(String nome, String matricula, String cpf, String email, String departamento) {
        super(nome, matricula, cpf, email);
        this.departamento = departamento;
    }

    @Override
    public String getTipo() {
        return "Professor";
    }

    public String getDepartamento() { return departamento; }

    @Override
    public String toString() {
        return super.toString() + " | Departamento: " + departamento;
    }
}
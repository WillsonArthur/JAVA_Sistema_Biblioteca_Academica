package model;

public abstract class Usuario {
    private String nome;
    private String matricula;
    private String cpf;
    private String email;

    public Usuario(String nome, String matricula, String cpf, String email) {
        this.nome = nome;
        this.matricula = matricula;
        this.cpf = cpf;
        this.email = email;
    }

    public String getNome() { return nome; }
    public String getMatricula() { return matricula; }
    public String getCpf() { return cpf; }
    public String getEmail() { return email; }

    public abstract String getTipo();

    @Override
    public String toString() {
        return String.format("Nome: %s | Matrícula: %s | CPF: %s | Email: %s | Tipo: %s",
                nome, matricula, cpf, email, getTipo());
    }
}
package model;

import java.util.Date;
import java.util.List;

public class Emprestimo {
    private int numero;
    private Usuario usuario;
    private List<Livro> livros;
    private Date dataEmprestimo;
    private Date dataDevolucao;
    private boolean finalizado;

    public Emprestimo(int numero, Usuario usuario, List<Livro> livros, Date dataEmprestimo, Date dataDevolucao) {
        this.numero = numero;
        this.usuario = usuario;
        this.livros = livros;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.finalizado = false;
    }

    public int getNumero() { return numero; }
    public Usuario getUsuario() { return usuario; }
    public List<Livro> getLivros() { return livros; }
    public Date getDataEmprestimo() { return dataEmprestimo; }
    public Date getDataDevolucao() { return dataDevolucao; }
    public boolean isFinalizado() { return finalizado; }

    public void finalizarEmprestimo() {
        this.finalizado = true;
        for (Livro livro : livros) {
            livro.setDisponivel(true);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Número: %d | Usuário: %s | Data Empréstimo: %s | Data Devolução: %s | Status: %s\n",
                numero, usuario.getNome(), dataEmprestimo, dataDevolucao, finalizado ? "Finalizado" : "Ativo"));
        sb.append("Livros:\n");
        for (Livro livro : livros) {
            sb.append("  - ").append(livro.getTitulo()).append("\n");
        }
        return sb.toString();
    }
}
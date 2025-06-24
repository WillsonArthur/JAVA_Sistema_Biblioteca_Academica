package dao;

import model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BibliotecaDAO {
    private List<Livro> livros;
    private List<Usuario> usuarios;
    private List<Emprestimo> emprestimos;
    private int proximoNumeroEmprestimo;

    public BibliotecaDAO() {
        this.livros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
        this.proximoNumeroEmprestimo = 1;
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public List<Livro> listarLivros() {
        return new ArrayList<>(livros);
    }

    public Livro buscarLivroPorIsbn(String isbn) {
        for (Livro livro : livros) {
            if (livro.getIsbn().equals(isbn)) {
                return livro;
            }
        }
        return null;
    }

    public List<Livro> buscarLivrosDisponiveis() {
        List<Livro> disponiveis = new ArrayList<>();
        for (Livro livro : livros) {
            if (livro.isDisponivel()) {
                disponiveis.add(livro);
            }
        }
        return disponiveis;
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return new ArrayList<>(usuarios);
    }

    public Usuario buscarUsuarioPorMatricula(String matricula) {
        for (Usuario usuario : usuarios) {
            if (usuario.getMatricula().equals(matricula)) {
                return usuario;
            }
        }
        return null;
    }

    public Emprestimo criarEmprestimo(Usuario usuario, List<Livro> livros, Date dataEmprestimo, Date dataDevolucao) {

        for (Livro livro : livros) {
            if (!livro.isDisponivel()) {
                return null;
            }
        }

        Emprestimo emprestimo = new Emprestimo(proximoNumeroEmprestimo++, usuario, livros, dataEmprestimo, dataDevolucao);

        for (Livro livro : livros) {
            livro.setDisponivel(false);
        }

        emprestimos.add(emprestimo);
        return emprestimo;
    }

    public List<Emprestimo> listarEmprestimosAtivos() {
        List<Emprestimo> ativos = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimos) {
            if (!emprestimo.isFinalizado()) {
                ativos.add(emprestimo);
            }
        }
        return ativos;
    }

    public boolean finalizarEmprestimo(int numeroEmprestimo) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getNumero() == numeroEmprestimo && !emprestimo.isFinalizado()) {
                emprestimo.finalizarEmprestimo();
                return true;
            }
        }
        return false;
    }
}
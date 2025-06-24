package util;

import model.*;
import dao.BibliotecaDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private Scanner scanner;
    private BibliotecaDAO bibliotecaDAO;
    private SimpleDateFormat dateFormat;

    public ConsoleUI() {
        this.scanner = new Scanner(System.in);
        this.bibliotecaDAO = new BibliotecaDAO();
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    }

    public void iniciar() {
        int opcao;
        do {
            exibirMenuPrincipal();
            opcao = lerInteiro("Por favor digite sua opção: ");
            processarOpcaoPrincipal(opcao);
        } while (opcao != 0);
    }

    private void exibirMenuPrincipal() {
        System.out.println("\n=== SISTEMA DE BIBLIOTECA ===");
        System.out.println("1. Gerenciar Livros");
        System.out.println("2. Gerenciar Usuários");
        System.out.println("3. Gerenciar Empréstimos");
        System.out.println("0. Sair");
    }

    private void processarOpcaoPrincipal(int opcao) {
        switch (opcao) {
            case 1:
                gerenciarLivros();
                break;
            case 2:
                gerenciarUsuarios();
                break;
            case 3:
                gerenciarEmprestimos();
                break;
            case 0:
                System.out.println("Saindo do sistema...");
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private void gerenciarLivros() {
        int opcao;
        do {
            exibirMenuLivros();
            opcao = lerInteiro("Digite sua opção: ");
            processarOpcaoLivros(opcao);
        } while (opcao != 0);
    }

    private void exibirMenuLivros() {
        System.out.println("\n=== GERENCIAMENTO DE LIVROS ===");
        System.out.println("1. Cadastrar Livro");
        System.out.println("2. Listar Todos os Livros");
        System.out.println("3. Listar Livros Disponíveis");
        System.out.println("4. Buscar Livro por ISBN");
        System.out.println("0. Voltar");
    }

    private void processarOpcaoLivros(int opcao) {
        switch (opcao) {
            case 1:
                cadastrarLivro();
                break;
            case 2:
                listarTodosLivros();
                break;
            case 3:
                listarLivrosDisponiveis();
                break;
            case 4:
                buscarLivroPorIsbn();
                break;
            case 0:
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private void cadastrarLivro() {
        System.out.println("\n=== CADASTRAR LIVRO ===");
        String isbn = lerString("ISBN: ");
        String titulo = lerString("Título: ");
        String autor = lerString("Autor: ");
        String editora = lerString("Editora: ");
        int ano = lerInteiro("Ano: ");

        Livro livro = new Livro(isbn, titulo, autor, editora, ano);
        bibliotecaDAO.adicionarLivro(livro);
        System.out.println("Livro cadastrado com sucesso!");
    }

    private void listarTodosLivros() {
        System.out.println("\n=== TODOS OS LIVROS ===");
        List<Livro> livros = bibliotecaDAO.listarLivros();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
        } else {
            for (Livro livro : livros) {
                System.out.println(livro);
            }
        }
    }

    private void listarLivrosDisponiveis() {
        System.out.println("\n=== LIVROS DISPONÍVEIS ===");
        List<Livro> livros = bibliotecaDAO.buscarLivrosDisponiveis();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro disponível no momento.");
        } else {
            for (Livro livro : livros) {
                System.out.println(livro);
            }
        }
    }

    private void buscarLivroPorIsbn() {
        System.out.println("\n=== BUSCAR LIVRO POR ISBN ===");
        String isbn = lerString("Digite o ISBN: ");
        Livro livro = bibliotecaDAO.buscarLivroPorIsbn(isbn);
        if (livro != null) {
            System.out.println(livro);
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    private void gerenciarUsuarios() {
        int opcao;
        do {
            exibirMenuUsuarios();
            opcao = lerInteiro("Digite sua opção: ");
            processarOpcaoUsuarios(opcao);
        } while (opcao != 0);
    }

    private void exibirMenuUsuarios() {
        System.out.println("\n=== GERENCIAMENTO DE USUÁRIOS ===");
        System.out.println("1. Cadastrar Aluno");
        System.out.println("2. Cadastrar Professor");
        System.out.println("3. Listar Todos os Usuários");
        System.out.println("4. Buscar Usuário por Matrícula");
        System.out.println("0. Voltar");
    }

    private void processarOpcaoUsuarios(int opcao) {
        switch (opcao) {
            case 1:
                cadastrarAluno();
                break;
            case 2:
                cadastrarProfessor();
                break;
            case 3:
                listarTodosUsuarios();
                break;
            case 4:
                buscarUsuarioPorMatricula();
                break;
            case 0:
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private void cadastrarAluno() {
        System.out.println("\n=== CADASTRAR ALUNO ===");
        String nome = lerString("Nome: ");
        String matricula = lerString("Matrícula: ");
        String cpf = lerString("CPF: ");
        String email = lerString("Email: ");
        String curso = lerString("Curso: ");

        Aluno aluno = new Aluno(nome, matricula, cpf, email, curso);
        bibliotecaDAO.adicionarUsuario(aluno);
        System.out.println("Aluno cadastrado com sucesso!");
    }

    private void cadastrarProfessor() {
        System.out.println("\n=== CADASTRAR PROFESSOR ===");
        String nome = lerString("Nome: ");
        String matricula = lerString("Matrícula: ");
        String cpf = lerString("CPF: ");
        String email = lerString("Email: ");
        String departamento = lerString("Departamento: ");

        Professor professor = new Professor(nome, matricula, cpf, email, departamento);
        bibliotecaDAO.adicionarUsuario(professor);
        System.out.println("Professor cadastrado com sucesso!");
    }

    private void listarTodosUsuarios() {
        System.out.println("\n=== TODOS OS USUÁRIOS ===");
        List<Usuario> usuarios = bibliotecaDAO.listarUsuarios();
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            for (Usuario usuario : usuarios) {
                System.out.println(usuario);
            }
        }
    }

    private void buscarUsuarioPorMatricula() {
        System.out.println("\n=== BUSCAR USUÁRIO POR MATRÍCULA ===");
        String matricula = lerString("Digite a matrícula: ");
        Usuario usuario = bibliotecaDAO.buscarUsuarioPorMatricula(matricula);
        if (usuario != null) {
            System.out.println(usuario);
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    private void gerenciarEmprestimos() {
        int opcao;
        do {
            exibirMenuEmprestimos();
            opcao = lerInteiro("Digite sua opção: ");
            processarOpcaoEmprestimos(opcao);
        } while (opcao != 0);
    }

    private void exibirMenuEmprestimos() {
        System.out.println("\n=== GERENCIAMENTO DE EMPRÉSTIMOS ===");
        System.out.println("1. Novo Empréstimo");
        System.out.println("2. Listar Empréstimos Ativos");
        System.out.println("3. Finalizar Empréstimo (Devolução)");
        System.out.println("0. Voltar");
    }

    private void processarOpcaoEmprestimos(int opcao) {
        switch (opcao) {
            case 1:
                criarEmprestimo();
                break;
            case 2:
                listarEmprestimosAtivos();
                break;
            case 3:
                finalizarEmprestimo();
                break;
            case 0:
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private void criarEmprestimo() {
        System.out.println("\n=== NOVO EMPRÉSTIMO ===");
        
        // Listar usuários
        List<Usuario> usuarios = bibliotecaDAO.listarUsuarios();
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado. Cadastre um usuário primeiro.");
            return;
        }
        
        System.out.println("Usuários disponíveis:");
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println((i + 1) + ". " + usuarios.get(i).getNome() + " (" + usuarios.get(i).getMatricula() + ")");
        }
        
        int usuarioIndex = lerInteiro("Selecione o usuário (número): ") - 1;
        if (usuarioIndex < 0 || usuarioIndex >= usuarios.size()) {
            System.out.println("Seleção inválida.");
            return;
        }
        Usuario usuario = usuarios.get(usuarioIndex);
        
        // Listar livros disponíveis
        List<Livro> livrosDisponiveis = bibliotecaDAO.buscarLivrosDisponiveis();
        if (livrosDisponiveis.isEmpty()) {
            System.out.println("Nenhum livro disponível para empréstimo.");
            return;
        }
        
        System.out.println("Livros disponíveis:");
        for (int i = 0; i < livrosDisponiveis.size(); i++) {
            System.out.println((i + 1) + ". " + livrosDisponiveis.get(i).getTitulo());
        }
        
        List<Livro> livrosSelecionados = new ArrayList<>();
        boolean continuarSelecionando = true;
        while (continuarSelecionando && !livrosDisponiveis.isEmpty()) {
            int livroIndex = lerInteiro("Selecione um livro (número) ou 0 para finalizar: ") - 1;
            if (livroIndex == -1) {
                continuarSelecionando = false;
            } else if (livroIndex >= 0 && livroIndex < livrosDisponiveis.size()) {
                Livro livroSelecionado = livrosDisponiveis.get(livroIndex);
                livrosSelecionados.add(livroSelecionado);
                livrosDisponiveis.remove(livroIndex);
                
                System.out.println("Livro adicionado: " + livroSelecionado.getTitulo());
                
                if (!livrosDisponiveis.isEmpty()) {
                    System.out.println("Livros disponíveis restantes:");
                    for (int i = 0; i < livrosDisponiveis.size(); i++) {
                        System.out.println((i + 1) + ". " + livrosDisponiveis.get(i).getTitulo());
                    }
                } else {
                    System.out.println("Todos os livros disponíveis foram selecionados.");
                    continuarSelecionando = false;
                }
            } else {
                System.out.println("Seleção inválida.");
            }
        }
        
        if (livrosSelecionados.isEmpty()) {
            System.out.println("Nenhum livro selecionado. Empréstimo cancelado.");
            return;
        }
        
        Date dataEmprestimo = lerData("Data de empréstimo (dd/MM/aaaa): ");
        Date dataDevolucao = lerData("Data de devolução (dd/MM/aaaa): ");
        
        Emprestimo emprestimo = bibliotecaDAO.criarEmprestimo(usuario, livrosSelecionados, dataEmprestimo, dataDevolucao);
        if (emprestimo != null) {
            System.out.println("Empréstimo realizado com sucesso!");
            System.out.println("Número do empréstimo: " + emprestimo.getNumero());
        } else {
            System.out.println("Erro ao realizar empréstimo. Verifique se todos os livros estão disponíveis.");
        }
    }

    private void listarEmprestimosAtivos() {
        System.out.println("\n=== EMPRÉSTIMOS ATIVOS ===");
        List<Emprestimo> emprestimos = bibliotecaDAO.listarEmprestimosAtivos();
        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo ativo no momento.");
        } else {
            for (Emprestimo emprestimo : emprestimos) {
                System.out.println(emprestimo);
            }
        }
    }

    private void finalizarEmprestimo() {
        System.out.println("\n=== FINALIZAR EMPRÉSTIMO ===");
        List<Emprestimo> emprestimosAtivos = bibliotecaDAO.listarEmprestimosAtivos();
        
        if (emprestimosAtivos.isEmpty()) {
            System.out.println("Nenhum empréstimo ativo para finalizar.");
            return;
        }
        
        System.out.println("Empréstimos ativos:");
        for (Emprestimo emprestimo : emprestimosAtivos) {
            System.out.println("Número: " + emprestimo.getNumero() + " | Usuário: " + emprestimo.getUsuario().getNome());
        }
        
        int numeroEmprestimo = lerInteiro("Digite o número do empréstimo a ser finalizado: ");
        boolean sucesso = bibliotecaDAO.finalizarEmprestimo(numeroEmprestimo);
        
        if (sucesso) {
            System.out.println("Empréstimo finalizado com sucesso. Livros devolvidos.");
        } else {
            System.out.println("Empréstimo não encontrado ou já finalizado.");
        }
    }

    private String lerString(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    private int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
            }
        }
    }

    private Date lerData(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                String dataStr = scanner.nextLine();
                return dateFormat.parse(dataStr);
            } catch (ParseException e) {
                System.out.println("Formato de data inválido. Use dd/MM/aaaa.");
            }
        }
    }
}
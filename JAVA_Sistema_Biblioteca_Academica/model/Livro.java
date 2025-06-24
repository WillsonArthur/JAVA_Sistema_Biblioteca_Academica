package model;

public class Livro {
    private String isbn;
    private String titulo;
    private String autor;
    private String editora;
    private int ano;
    private boolean disponivel;

    public Livro(String isbn, String titulo, String autor, String editora, int ano) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.ano = ano;
        this.disponivel = true;
    }

    
    public String getIsbn() { return isbn; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getEditora() { return editora; }
    public int getAno() { return ano; }
    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }

    @Override
    public String toString() {
        return String.format("ISBN: %s | Título: %s | Autor: %s | Editora: %s | Ano: %d | Status: %s",
                isbn, titulo, autor, editora, ano, disponivel ? "Disponível" : "Emprestado");
    }
}
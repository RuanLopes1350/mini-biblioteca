package service

import model.*
import java.time.LocalDate

class BibliotecaService {
    private val autores = mutableListOf<Autor>()
    private val livros = mutableListOf<Livro>()
    private val usuarios = mutableListOf<Usuario>()

    // Métodos para Autor
    fun cadastrarAutor(nome: String, dataNascimento: LocalDate): Autor {
        val autor = Autor(nome, dataNascimento)
        autores.add(autor)
        return autor
    }

    fun buscarAutorPorNome(nome: String): Autor? {
        return autores.find { it.nome.equals(nome, ignoreCase = true) }
    }

    fun listarAutores(): List<Autor> = autores.toList()

    // Métodos para Livro
    fun cadastrarLivro(titulo: String, isbn: String, autor: Autor): Livro {
        val livro = Livro(titulo, isbn, autor)
        livros.add(livro)
        return livro
    }

    fun buscarLivroPorTitulo(titulo: String): Livro? {
        return livros.find { it.titulo.equals(titulo, ignoreCase = true) }
    }

    fun buscarLivroPorIsbn(isbn: String): Livro? {
        return livros.find { it.isbn == isbn }
    }

    fun listarLivros(): List<Livro> = livros.toList()

    // Métodos para Usuário
    fun cadastrarUsuario(nome: String, dataNascimento: LocalDate, id: String): Usuario {
        val usuario = Usuario(nome, dataNascimento, id)
        usuarios.add(usuario)
        return usuario
    }

    fun buscarUsuarioPorId(id: String): Usuario? {
        return usuarios.find { it.id == id }
    }

    fun listarUsuarios(): List<Usuario> = usuarios.toList()

    // Métodos para Empréstimo
    fun realizarEmprestimo(usuario: Usuario, livro: Livro): Emprestimo? {
        return usuario.pegarLivroEmprestado(livro)
    }

    fun devolverLivro(usuario: Usuario, emprestimo: Emprestimo): Boolean {
        return usuario.devolverLivro(emprestimo)
    }

    fun verificarEmprestimosAtrasados() {
        val hoje = LocalDate.now()

        usuarios.forEach { usuario ->
            usuario.getEmprestimosAtivos().forEach { emprestimo ->
                // Considerando que um empréstimo está atrasado após 14 dias
                if (emprestimo.dataEmprestimo.plusDays(14).isBefore(hoje) &&
                    emprestimo.status == StatusEmprestimo.ATIVO) {
                    emprestimo.marcarComoAtrasado()
                }
            }
        }
    }
}
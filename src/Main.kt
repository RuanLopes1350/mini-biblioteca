import model.*
import service.BibliotecaService
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun main() {
    val biblioteca = BibliotecaService()

    // Cadastrando autores
    val autor1 = biblioteca.cadastrarAutor("Machado de Assis", LocalDate.of(1839, 6, 21))
    val autor2 = biblioteca.cadastrarAutor("Clarice Lispector", LocalDate.of(1920, 12, 10))

    // Cadastrando livros
    val livro1 = biblioteca.cadastrarLivro("Dom Casmurro", "9788574801223", autor1)
    val livro2 = biblioteca.cadastrarLivro("Memórias Póstumas de Brás Cubas", "9788572326645", autor1)
    val livro3 = biblioteca.cadastrarLivro("A Hora da Estrela", "9788520937426", autor2)

    // Cadastrando usuários
    val usuario1 = biblioteca.cadastrarUsuario("João Silva", LocalDate.of(1990, 5, 15), "USR001")
    val usuario2 = biblioteca.cadastrarUsuario("Maria Santos", LocalDate.of(1985, 10, 22), "USR002")

    println("===== SISTEMA DE BIBLIOTECA =====")
    println("\nAutores cadastrados:")
    biblioteca.listarAutores().forEach { println(it) }

    println("\nLivros cadastrados:")
    biblioteca.listarLivros().forEach { println(it) }

    println("\nUsuários cadastrados:")
    biblioteca.listarUsuarios().forEach { println(it) }

    // Realizando empréstimos
    println("\nRealizando empréstimos:")
    val emprestimo1 = biblioteca.realizarEmprestimo(usuario1, livro1)
    if (emprestimo1 != null) {
        println("Empréstimo realizado com sucesso: ${emprestimo1}")
    } else {
        println("Erro ao realizar empréstimo.")
    }

    val emprestimo2 = biblioteca.realizarEmprestimo(usuario1, livro2)
    if (emprestimo2 != null) {
        println("Empréstimo realizado com sucesso: ${emprestimo2}")
    } else {
        println("Erro ao realizar empréstimo.")
    }

    // Tentando fazer mais de 3 empréstimos
    println("\nTentativa de fazer mais de 3 empréstimos:")
    val emprestimo3 = biblioteca.realizarEmprestimo(usuario1, livro3)
    if (emprestimo3 != null) {
        println("Empréstimo realizado com sucesso: ${emprestimo3}")
    } else {
        println("Erro ao realizar empréstimo.")
    }

    val emprestimo4 = biblioteca.realizarEmprestimo(usuario1, livro3)
    if (emprestimo4 != null) {
        println("Empréstimo realizado com sucesso: ${emprestimo4}")
    } else {
        println("Não foi possível realizar o empréstimo: usuário atingiu o limite de 3 empréstimos simultâneos.")
    }

    // Devolvendo um livro
    println("\nDevolvendo livro:")
    if (emprestimo1 != null) {
        val devolucao = biblioteca.devolverLivro(usuario1, emprestimo1)
        if (devolucao) {
            println("Livro '${livro1.titulo}' devolvido com sucesso.")
        } else {
            println("Erro ao devolver livro.")
        }
    }

    // Tentando emprestar um livro após devolução
    println("\nEmprestando livro após devolução:")
    val emprestimo5 = biblioteca.realizarEmprestimo(usuario1, livro1)
    if (emprestimo5 != null) {
        println("Empréstimo realizado com sucesso: ${emprestimo5}")
    } else {
        println("Erro ao realizar empréstimo.")
    }

    // Mostrando empréstimos ativos do usuário
    println("\nEmpréstimos ativos de ${usuario1.nome}:")
    usuario1.getEmprestimosAtivos().forEach { println(it) }

    println("\n===== FIM DO PROGRAMA =====")
}
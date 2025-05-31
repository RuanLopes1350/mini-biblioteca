package model

import java.time.LocalDate

class Usuario(
    nome: String,
    dataNascimento: LocalDate,
    val id: String
) : Pessoa(nome, dataNascimento) {
    private val listaEmprestimos = mutableListOf<Emprestimo>()

    fun pegarLivroEmprestado(livro: Livro): Emprestimo? {
        // Verificar se o usuário já possui 3 empréstimos ativos
        val emprestimosAtivos = listaEmprestimos.count {
            it.status == StatusEmprestimo.ATIVO || it.status == StatusEmprestimo.ATRASADO
        }

        if (emprestimosAtivos >= 3) {
            return null // Não pode pegar mais livros emprestados
        }

        val emprestimo = Emprestimo(
            id = "EMP-${System.currentTimeMillis()}",
            livro = livro
        )

        listaEmprestimos.add(emprestimo)
        return emprestimo
    }

    fun devolverLivro(emprestimo: Emprestimo): Boolean {
        if (!listaEmprestimos.contains(emprestimo)) {
            return false // O empréstimo não pertence a este usuário
        }

        return emprestimo.devolver()
    }

    fun getEmprestimosAtivos(): List<Emprestimo> {
        return listaEmprestimos.filter {
            it.status == StatusEmprestimo.ATIVO || it.status == StatusEmprestimo.ATRASADO
        }
    }

    fun getTodosEmprestimos(): List<Emprestimo> = listaEmprestimos.toList()

    override fun toString(): String {
        return "Usuario(nome='$nome', id='$id', emprestimosAtivos=${getEmprestimosAtivos().size})"
    }
}
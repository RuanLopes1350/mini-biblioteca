package model

import java.time.LocalDate

class Emprestimo(
    val id: String,
    val livro: Livro,
    val dataEmprestimo: LocalDate = LocalDate.now()
) {
    var dataDevolucao: LocalDate? = null
        private set

    var status: StatusEmprestimo = StatusEmprestimo.ATIVO
        private set

    fun devolver(): Boolean {
        if (status != StatusEmprestimo.ATIVO) {
            return false // Não pode devolver um livro que não está emprestado
        }

        dataDevolucao = LocalDate.now()
        status = StatusEmprestimo.DEVOLVIDO
        return true
    }

    fun marcarComoAtrasado() {
        if (status == StatusEmprestimo.ATIVO) {
            status = StatusEmprestimo.ATRASADO
        }
    }

    override fun toString(): String {
        return "Emprestimo(id='$id', livro=${livro.titulo}, dataEmprestimo=$dataEmprestimo, dataDevolucao=$dataDevolucao, status=$status)"
    }
}
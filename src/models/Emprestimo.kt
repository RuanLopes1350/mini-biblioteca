package models
import java.time.LocalDate
import java.util.Date

class Emprestimo(val livro: Livro, val usuario: Usuario, val dataEmprestimo: LocalDate, val dataDevolucao: LocalDate, var status: StatusEmprestimo) {
    fun atualizarStatus(novoStatus: StatusEmprestimo){
        this.status = novoStatus
    }
}
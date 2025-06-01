package models
import java.time.LocalDate
import java.util.Date
import java.util.UUID

class Usuario (
    override val nome: String,
    override val CPF: String,
    override val dataNascimento: Date,
    override val endereco: String,
    private val telefone: String
): Pessoa(nome, CPF, dataNascimento, endereco ){
    private val id: String = gerarId()
    private val emprestimos = mutableListOf<Emprestimo>()
    private fun gerarId():String{
        return UUID.randomUUID().toString()
    }
    fun Devolucao(emprestimo: Emprestimo){
        emprestimo.atualizarStatus(StatusEmprestimo.DEVOLVIDO)
    }
    fun listarEmprestimo () {
        println(emprestimos.toList())
    }
    fun locarLivro(livro: Livro){
        val novo_emprestimo:Emprestimo = Emprestimo(livro, this, LocalDate.now(), LocalDate.now().plusWeeks(1), StatusEmprestimo.PEDENTE)
        emprestimos.add(novo_emprestimo)
    }
}
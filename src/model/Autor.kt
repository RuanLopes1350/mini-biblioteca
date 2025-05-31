package model

import java.time.LocalDate

class Autor(
    nome: String,
    dataNascimento: LocalDate
) : Pessoa(nome, dataNascimento) {
    private val livrosEscritos = mutableListOf<Livro>()

    fun adicionarLivro(livro: Livro) {
        livrosEscritos.add(livro)
    }

    fun getLivrosEscritos(): List<Livro> = livrosEscritos.toList()

    override fun toString(): String {
        return "Autor(nome='$nome', dataNascimento=$dataNascimento)"
    }
}
package model

class Livro(
    val titulo: String,
    val isbn: String,
    val autor: Autor
) {
    init {
        autor.adicionarLivro(this)
    }

    override fun toString(): String {
        return "Livro(titulo='$titulo', isbn='$isbn', autor=${autor.nome})"
    }
}
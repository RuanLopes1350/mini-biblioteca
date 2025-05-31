package model

import java.time.LocalDate

abstract class Pessoa (
    val nome: String,
    val dataNascimento: LocalDate
)
package models

import java.util.Date

abstract class Pessoa (
    open val nome: String,
    open val CPF: String,
    open val dataNascimento: Date,
    open val endereco: String
){

}
// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

class Usuario {
    var progressoConteudos = mutableMapOf<ConteudoEducacional, Int>()

    fun registrarProgresso(conteudo: ConteudoEducacional, progresso: Int) {
        progressoConteudos[conteudo] = progresso
    }
}

data class ConteudoEducacional(
    var nome: String,
    val duracao: Int = 60,
    val descricao: String,
    val nivelDificuldade: Nivel
)

class Formacao(val nome: String, var conteudos: List<ConteudoEducacional>) {

    val inscritos = mutableListOf<Usuario>()

    fun matricular(usuario: Usuario) {
        inscritos.add(usuario)
    }

    fun progressoMedio(): Double {
        return if (inscritos.isNotEmpty()) {
            inscritos.flatMap { it.progressoConteudos.values }.average()
        } else {
            0.0
        }
    }

    fun formacaoCompleta(): Boolean {
        return inscritos.all { usuario ->
            conteudos.all { conteudo ->
                usuario.progressoConteudos.containsKey(conteudo)
            }
        }
    }
}

fun main() {
    val usuario1 = Usuario()
    val usuario2 = Usuario()

    val conteudo1 = ConteudoEducacional("Introdução a Kotlin", descricao = "Fundamentos da Linguagem", nivelDificuldade = Nivel.BASICO)
    val conteudo2 = ConteudoEducacional("Coroutines em Kotlin", descricao = "Programação assíncrona", nivelDificuldade = Nivel.INTERMEDIARIO)

    val formacao = Formacao("Desenvolvimento em Kotlin", listOf(conteudo1, conteudo2))

    formacao.matricular(usuario1)
    formacao.matricular(usuario2)

    usuario1.registrarProgresso(conteudo1, 50)
    usuario1.registrarProgresso(conteudo2, 75)

    usuario2.registrarProgresso(conteudo1, 30)
    usuario2.registrarProgresso(conteudo2, 60)

    println("Progresso médio da formação: ${formacao.progressoMedio()}")
    println("Formação completa? ${formacao.formacaoCompleta()}")
}

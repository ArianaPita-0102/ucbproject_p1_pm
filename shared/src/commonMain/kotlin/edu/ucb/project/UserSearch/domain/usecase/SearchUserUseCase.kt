package edu.ucb.project.UserSearch.domain.usecase

class SearchUserUseCase {
    private val users = listOf(
        "Ana Pérez", "Carlos Mamani", "Lucía Quispe",
        "Jorge Flores", "María Choque", "Usuario de Prueba"
    )

    suspend operator fun invoke(query: String): Result<List<String>> =
        Result.success(users.filter { it.contains(query.trim(), ignoreCase = true) })
}
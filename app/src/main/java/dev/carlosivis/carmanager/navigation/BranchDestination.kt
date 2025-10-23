package dev.carlosivis.carmanager.navigation


sealed class BranchDestination(private val name: String) {
    val route = "${name}_branch"

    object Main : BranchDestination(name = "main")
}
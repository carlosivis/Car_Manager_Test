package dev.carlosivis.carmanager.navigation


import androidx.navigation.NamedNavArgument
import dev.carlosivis.carmanager.navigation.Arg
import dev.carlosivis.carmanager.navigation.NavArg
import dev.carlosivis.carmanager.navigation.formatArgValue
import dev.carlosivis.carmanager.navigation.getNamedNavArguments
import dev.carlosivis.carmanager.navigation.mapArgsId


open class LeafDestination(
    val root: BranchDestination,
    private val route: String,
    private val args: List<NavArg> = emptyList()
) {

    val arguments: List<NamedNavArgument> = args.getNamedNavArguments()

    open fun createRoute(): String = "${root.route}/$route${args.mapArgsId()}"

    internal fun putArgs(vararg args: Pair<String, Any?>): String {
        var finalRoute = createRoute()
        val validArgs: MutableList<Arg> = mutableListOf()

        args.forEach { pair ->
            pair.second?.let { value ->
                if (this.args.any { navArg -> navArg.id == pair.first }) {
                    validArgs.add(Arg(id = pair.first, value = value))
                } else {
                    println("Warning: Argument '${pair.first}' not defined for route $route")
                }
            }
        }

        validArgs.forEach { arg ->
            finalRoute = finalRoute.replace("{${arg.id}}", arg.value.formatArgValue())
        }

        finalRoute = finalRoute.replace(Regex("\\?.*=\\{.*\\}"), "")
            .replace(Regex("\\&.*=\\{.*\\}"), "")


        return finalRoute
    }
}
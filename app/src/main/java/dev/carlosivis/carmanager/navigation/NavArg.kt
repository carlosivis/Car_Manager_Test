package dev.carlosivis.carmanager.navigation


import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import java.net.URLDecoder
import java.net.URLEncoder

fun String.encode(): String = URLEncoder.encode(this, "UTF-8")
fun String.decode(): String = URLDecoder.decode(this, "UTF-8")


data class NavArg(
    val id: String,
    val type: NavType<*>,
    val defaultValue: Any? = null,
    val isNullable: Boolean = false
)

internal fun List<NavArg>.mapArgsId(): String {
    val mandatoryArgs = filter { !it.isNullable && it.defaultValue == null }.map { it.id }
    val optionalArgs = filter { it.isNullable || it.defaultValue != null }.map { it.id }

    val mandatoryArgsRoute = mandatoryArgs.formatMandatoryArgsId()
    val optionalArgsRoute = optionalArgs.formatOptionalArgsId()

    return "$mandatoryArgsRoute$optionalArgsRoute"
}

internal fun List<NavArg>.getNamedNavArguments(): List<NamedNavArgument> {
    return map {
        navArgument(name = it.id) {
            type = it.type
            defaultValue?.let { value -> defaultValue = value }
            nullable = it.isNullable
        }
    }
}

private fun List<String>.formatMandatoryArgsId(): String = joinToString(separator = "") { "/{$it}" }
private fun List<String>.formatOptionalArgsId(): String {
    val args = joinToString(separator = "&") { "$it={$it}" } // Usa '&' para opcionais
    return if (args.isEmpty()) args else "?$args"
}

internal data class Arg(
    val id: String,
    var value: Any
)

internal fun Any.formatArgValue(): String {
    return when (this) {
        is String -> this.encode()
        else -> this.toString()
    }
}
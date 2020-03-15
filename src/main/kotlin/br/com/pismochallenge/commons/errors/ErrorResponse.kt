package br.com.pismochallenge.commons.errors

data class ErrorResponse(
    val message: String?,
    val details: Map<String, Any>
) {
    companion object {
        fun create(message: String?, vararg details: Pair<String, Any> = emptyArray()) = ErrorResponse(
            message = message,
            details = details.associate { it.first to it.second }
        )
    }
}

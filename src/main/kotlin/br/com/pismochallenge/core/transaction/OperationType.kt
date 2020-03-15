package br.com.pismochallenge.core.transaction

enum class OperationType(val value: String) {

    CASH_PURCHASE("1"),
    INSTALLMENT("2"),
    WITHDRAWAL("3"),
    PAYMENT("4");

    companion object {
        fun of(value: String) = values()
            .firstOrNull { it.value == value }
            ?: throw Exception(
                value
            )
    }
}

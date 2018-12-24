package io.axonif.pizzola.transaction

import java.util.NoSuchElementException

class TransactionRepository {
    val transactions = mutableMapOf<String, Transaction>()

    fun add(t: Transaction){
        transactions.put(t.id, t)
    }

    fun getById(id: String) : Transaction {
        return transactions.get(id) ?: throw NoSuchElementException("Transtaction with $id not found")
    }

    fun getAllTransactionsForUser(userId: String): List<Transaction> {
        return transactions.values
                .filter { it.userId.equals(userId)}
    }
}
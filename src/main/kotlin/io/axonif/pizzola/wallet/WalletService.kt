package io.axonif.pizzola.wallet

import io.axonif.pizzola.transaction.Transaction
import io.axonif.pizzola.transaction.TransactionRepository
import java.math.BigDecimal

class WalletService private constructor(val transactionRepo: TransactionRepository) {

    fun balance(userId: String): BigDecimal{
        val txs = transactionRepo.getAllTransactionsForUser(userId)
        return txs.map { it.amount }.fold(BigDecimal.ZERO) { acc, i -> acc.plus(i) }
    }

    fun deposit(user: String, amount : BigDecimal, note : String) {
        val trans = Transaction.createDeposit(user, amount, note)
        transactionRepo.add(trans)
    }

    fun withdraw( user: String, amount : BigDecimal, note : String) {
        val trans = Transaction.createWithdrawl(user, amount, note)
        transactionRepo.add(trans)
    }

    companion object {
        val service : WalletService =  WalletService(TransactionRepository());

        fun getInstance(): WalletService {
            return service
        }
    }
}

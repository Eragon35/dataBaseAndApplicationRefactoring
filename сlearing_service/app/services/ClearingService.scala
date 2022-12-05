package services

import entities.{DebtDTO, TransactionDTO}

trait ClearingService {
    def calculateDebts(bankId: String): Seq[DebtDTO]

    def handlePayment(transaction: TransactionDTO): Unit
}

class ClearingServiceImpl extends ClearingService{
    override def calculateDebts(bankId: String): Seq[DebtDTO] = Seq[DebtDTO]()

    def handlePayment(transaction: TransactionDTO): Unit = println("")
}

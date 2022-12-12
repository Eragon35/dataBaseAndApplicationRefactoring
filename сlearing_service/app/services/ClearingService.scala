package services

import com.typesafe.config.ConfigFactory
import entities.{BankTable, DebtDTO, TransactionDTO, TransactionTable}
import models.UuidHelper
import slick.jdbc.PostgresProfile.api._
import scala.language.postfixOps
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.language.postfixOps
import play.api.libs.ws._
import slick.util.ClassLoaderUtil

import javax.inject.Inject


trait ClearingService {
    def calculateDebts(bankId: String): Seq[DebtDTO]

    def handlePayment(transaction: TransactionDTO): String
}

class ClearingServiceImpl @Inject()(ws: WSClient) extends ClearingService{
    val mydb = Database.forConfig("mydb")

    private val transactionTable = TableQuery[TransactionTable]
    private val bankTable = TableQuery[BankTable]

    override def calculateDebts(bankId: String): Seq[DebtDTO] = {
        val result = collection.mutable.Map[String, Int]().withDefaultValue(0)
        val from = for {
            tr <- transactionTable if tr.bankSender === bankId
            bank <- bankTable if bank.id === tr.bankReceiver
        } yield (bank.title, tr.amount)
        val to = for {
            tr <- transactionTable if tr.bankReceiver === bankId
            bank <- bankTable if bank.id === tr.bankSender
        } yield (bank.title, tr.amount)
        val resultFrom = mydb.run(from.result).map(rows =>
            rows.map(row => result(row._1) += row._2))
        val resultTo = mydb.run(to.result).map(rows =>
            rows.map(row => result(row._1) -= row._2))
        Await.result(resultFrom, Duration.Inf)
        Await.result(resultTo, Duration.Inf)
        result.map(bank => DebtDTO(bank = bank._1, amount = bank._2)).toSeq
    }

    def handlePayment(transaction: TransactionDTO): String = {
        val url = ConfigFactory.load(ClassLoaderUtil.defaultClassLoader)
            .getConfig(transaction.bankReceiver).getString("url")
        ws.url(url).withBody(transaction).execute("POST").map { response =>
            if (response.status != 200) return "Ошибка соединения с банком получателем"
        }
        try {
            val query = DBIO.seq(
                transactionTable += (
                    UuidHelper.randomUuid,
                    transaction.bankReceiver,
                    transaction.bankSender,
                    transaction.userReceiver,
                    transaction.userSender,
                    transaction.amount
                ),
            )
            mydb.run(query)
        }
        ""
    }
}

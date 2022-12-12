package entities

import slick.lifted.Tag
import slick.jdbc.PostgresProfile.api._

case class TransactionDTO(bankReceiver: String,
    bankSender: String,
    userReceiver: String,
    userSender: String,
    amount: Int
)

class TransactionTable(tag:Tag) extends Table[(String, String, String, String, String, Int)](tag, "transactions") {
    def id = column[String]("id", O.PrimaryKey)
    def bankReceiver = column[String]("bankReceiver")
    def bankSender = column[String]("bankSender")
    def userReceiver = column[String]("userReceiver")
    def userSender = column[String]("userSender")
    def amount = column[Int]("amount")

    override def * = (id, bankReceiver, bankSender, userReceiver, userSender, amount)
}

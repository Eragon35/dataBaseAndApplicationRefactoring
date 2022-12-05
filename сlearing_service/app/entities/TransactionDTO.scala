package entities

case class TransactionDTO(id: String,
                          bankReceiver: String,
                          bankSender: String,
                          userReceiver: String,
                          userSender: String,
                          amount: Int)
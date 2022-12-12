package entities

import slick.lifted.Tag
import slick.jdbc.PostgresProfile.api._


case class BankDTO(title: String, ogrn: String, inn: String)

class BankTable(tag:Tag) extends Table[(String, String, String, String)](tag, "bank") {
    def id = column[String]("id", O.PrimaryKey)
    def title = column[String]("title")
    def ogrn = column[String]("ogrn")
    def inn = column[String]("inn")

    override def * = (id, title, ogrn, inn)
}


package entities

import play.api.libs.json.{Json, Writes}

case class DebtDTO(bank: String, amount: Int)

object DebtDTO {
    implicit val debtWrites: Writes[DebtDTO] = Json.writes[DebtDTO]
}

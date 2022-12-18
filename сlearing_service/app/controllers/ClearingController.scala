package controllers

import entities.{DebtDTO, TransactionDTO}
import play.api.libs.json.{Format, Json}
import play.api.Logging


import javax.inject._
import play.api.mvc._
import services.ClearingService


class ClearingController @Inject()(cc: ControllerComponents, clearingService: ClearingService) extends AbstractController(cc) with Logging {

    def handlePayment(): Action[AnyContent] = Action { request =>
      request.body.asJson.flatMap(Json.fromJson[TransactionDTO](_).asOpt)
          .fold(BadRequest("Ошибка в теле запроса")) { data =>
              logger.info("Request is " + data)
              clearingService.handlePayment(data) match {
                  case str if str.isEmpty => Ok("Транзакция прошла успешно")
                  case error => BadRequest(error)
              }
          }
    }


    def calculateDebts(bankId: String): Action[AnyContent] = Action { _ =>
        logger.info("calculate debts for " + bankId)
        Ok(Json.toJson(clearingService.calculateDebts(bankId)))
    }

    implicit val transactionFormat: Format[TransactionDTO] = Json.format[TransactionDTO]
    implicit val debtFormat: Format[DebtDTO] = Json.format[DebtDTO]
}

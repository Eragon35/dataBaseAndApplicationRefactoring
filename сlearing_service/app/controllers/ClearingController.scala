package controllers

import entities.TransactionDTO

import javax.inject._
import play.api.mvc._
import services.ClearingService


class ClearingController @Inject()(cc: ControllerComponents, clearingService: ClearingService) extends AbstractController(cc) {

  def handlePayment(): Action[AnyContent] = Action { _ =>
    clearingService.handlePayment(TransactionDTO("1", "2", "3", "4", "5", 6))
    Ok("handlePayment")
  }


  def calculateDebts(bankId: String): Action[AnyContent] = Action { _ =>
    val result = clearingService.calculateDebts(bankId)
    Ok("calculateDebts")
  }

}

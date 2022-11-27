package controllers

import javax.inject._
import play.api.mvc._
import services.ClearingService


class ClearingController @Inject()(cc: ControllerComponents, clearingService: ClearingService) extends AbstractController(cc) {

  def handlePayment(): Action[AnyContent] = Action { _ =>
    val result = clearingService.handlePayment("1", "2")
    Ok(result)
  }


  def calculateDebts(bankId: String): Action[AnyContent] = Action { _ =>
    val result = clearingService.calculateDebts(bankId)
    Ok("calculateDebts")
  }

}

package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.User

object Users extends Controller {
	val form = Form(
		tuple(
			"username" -> text,
			"password" -> text
		)
	)
  
	def login = Action {
		Ok(views.html.login())
	}

	def auth = Action { implicit request =>
		val (username, password) = form.bindFromRequest.get
		Redirect(routes.Discographies.newCD).withSession("connected" -> username)
	}

	def logout = Action {
		Redirect(routes.Users.login).withNewSession.flashing(
			"success" -> "You've been logged out"
		)
        }
}

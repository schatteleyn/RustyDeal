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

	def submit = Action { implicit request =>
		val (username, password) = form.bindFromRequest.get
		Ok("Hi %s %s".format(username, password))
	}
}

package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
//import models.Discography

object Discographies extends Controller {
	val form = Form(
		tuple(
			"title" -> text,
			"content" -> text
		)
	)
  
	def index = Action {
		Ok(views.html.discography())
	}

	def newCD = Action {
		Ok(views.html.Admin.discography())
	}

	def add = Action { implicit request =>
		val (title, content) = form.bindFromRequest.get
		Ok("Hi %s %s".format(title, content))
	}
}

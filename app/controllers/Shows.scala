package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
//import models.Show

object Shows extends Controller {
	//val form = Form(
	//	tuple(
	//		"title" -> text,
	//		"content" -> text
	//	)
	//)
  
	def index = Action {
		Ok(views.html.show())
	}

	//def newShow = Action {
	//	Ok(views.html.Admin.show())
	//}

	//def add = Action { implicit request =>
	//	val (title, content) = form.bindFromRequest.get
	//	Ok("Hi %s %s".format(title, content))
	//}
}

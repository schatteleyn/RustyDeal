package controllers

import org.ancelin.play2.couchbase.CouchbaseController
import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.libs.json.Json
import models.Discography
import models.Discographys
import models.Discographys._
import java.util.UUID

object Discographies extends Controller with CouchbaseController {
	val form = Form(
		tuple(
			"title" -> text,
			"content" -> text
		)
	)

	def updateDisco() = {
	//	Discographys.findAll().map()
	}

	def index = Action { 
		Async {
			Discographys.findAll().map(cds => Ok(views.html.discography(cds)))
		}
	}

	def newCD = Action {
		Ok(views.html.Admin.discography())
	}

	def add = Action { implicit request =>
		val (title, content) = form.bindFromRequest.get
		val album = Discography(Some(UUID.randomUUID().toString), title, "", content)
		Discographys.save(album)
		Ok("%s %s".format(title, content))
	}

	def delete(id: String) = Action {
		Async {
			Discographys.remove(id).map { status =>
				updateDisco()
				Ok(Json.obj( "success" -> status.isSuccess,"message" -> status.getMessage))
			}
		}
	}
}

package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {
  
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def listen = Action {
    Ok(views.html.listen)
  }
  
  def band = Action {
    Ok(views.html.band)
  }
  
  def links = Action {
    Ok(views.html.links)
  }
}

package models

import play.api.libs.json.Json
import org.ancelin.play2.couchbase.Couchbase
import com.couchbase.client.protocol.views.{Stale, ComplexKey, Query}
import play.api.Play.current
import scala.concurrent.Future
import java.security.MessageDigest

case class User(username: String, password: String)

object Users {
	implicit val peopleReader = Json.reads[User]
	implicit val peopleWriter = Json.writes[User]
	implicit val ec = Couchbase.couchbaseExecutor

	def bucket = Couchbase.bucket("users")

	def findById(id: String): Future[Option[User]] = {
		bucket.get[User](id)	
	}

	def login(username: String, password: String): Future[Option[User]] = {
		val hashed = MessageDigest.getInstance("SHA-1").digest(password.getBytes)
		val query = new Query().setIncludeDocs(true).setLimit(1)
			.setRangeStart(ComplexKey.of(username))
			.setRangeEnd(ComplexKey.of(s"$username\uefff")).setStale(Stale.FALSE)
		bucket.find[User]("user", "by_username")(query).map(_.headOption.filter(_.password == hashed))
	}
}

package models

import play.api.libs.json.Json
import org.ancelin.play2.couchbase.Couchbase
import com.couchbase.client.protocol.views.{Stale, ComplexKey, Query}
import play.api.Play.current
import scala.concurrent.Future
import net.spy.memcached.ops.OperationStatus

case class Discography(id: Option[String], title: String, date: String, content: String)

object Discographys {
	implicit val discographyReader = Json.reads[Discography]
	implicit val discographyWriter = Json.writes[Discography]
	implicit val ec = Couchbase.couchbaseExecutor

	def bucket = Couchbase.bucket("discographies")

	def findById(id: String): Future[Option[Discography]] = {
		bucket.get[Discography](id)	
	}

	def findAll(): Future[List[Discography]] = {
		bucket.find[Discography]("discography", "by_date")(new Query().setIncludeDocs(true).setStale(Stale.FALSE))
	}

	def save(discography: Discography): Future[OperationStatus] = {
		bucket.set[Discography]( discography.id.get, discography )
	}

	def remove(id: String): Future[OperationStatus] = {
		bucket.delete(id)
	}

}

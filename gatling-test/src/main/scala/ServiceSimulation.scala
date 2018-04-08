
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class TestSimulation extends Simulation {
  var name=""
  var delay=200
  var repeatTimes=10
  var pageName = ""
  var url="http://localhost:8080"
  var users = 1

  if(System.getProperty("u") != null )
    users = System.getProperty("u").toInt


  def startup(){
    val httpProtocol = http.baseURL(url)

    val page = repeat(repeatTimes, "n") {
      exec(http(name)
        .get("/"+ name +"/" + delay )
      )
    }
    val scn = scenario(pageName).exec(page)
    setUp(scn.inject(atOnceUsers(users))).protocols(httpProtocol)
  }
}

class ReactiveDelaySimulation extends TestSimulation {
  name="reactive"
  pageName = "Reactive Stack"
  startup()
}

class BlockingDelaySimulation extends TestSimulation {
  name="blocking"
  pageName = "Servlet stack"
  startup()
}


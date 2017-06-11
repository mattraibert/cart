package hello

import com.positiondev.cart.CartApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

@SpringBootTest(classes = CartApplication, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloControllerSpec extends Specification {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate template;

  void "should return Greetings from Spring Boot!"() {
    when:
      ResponseEntity entity = template.getForEntity("http://localhost:${port}/hello", String.class)

    then:
      entity.statusCode == HttpStatus.OK
      entity.body == 'Greetings from Spring Boot!'
  }

  def "should return 2 from first element of list"() {
    given:
      List<Integer> list = new ArrayList<>()
    when:
      list.add(2)
    then:
      2 == list.get(0)
  }
}
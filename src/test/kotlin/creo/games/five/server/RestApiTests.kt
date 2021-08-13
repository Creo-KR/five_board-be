package creo.games.five.server

import com.fasterxml.jackson.databind.ObjectMapper
import creo.games.five.server.security.AuthenticationToken
import org.assertj.core.api.Assertions.assertThat
import org.json.JSONObject
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RestApiTests(@Autowired val restTemplate: TestRestTemplate) {

    private var session: String? = null
    private var authToken: String? = null

    @BeforeAll
    fun setup() {
        println(">> Setup")
    }

    @Test
    fun `main`() {
        val entity = restTemplate.getForEntity<String>("/")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains("main")
    }

    @Test
    fun `Login`() {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val json = JSONObject()
        json.put("id", "user1")
        json.put("pw", "ps112312312")
        val body = json.toString()
        val entity = restTemplate.postForEntity<String>("/user/login", HttpEntity<String>(body, headers))
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)

        session = entity.headers.getFirst(HttpHeaders.SET_COOKIE)?.split(";")?.get(0);
        println(session)

        val responseBody = entity.body

        val mapper = ObjectMapper()
        val responseJson = mapper.readValue(responseBody, AuthenticationToken::class.java)
        println(responseJson.token)
        authToken = responseJson.token
    }

    @Test
    fun `Sign up`() {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val json = JSONObject()
        json.put("id", "user1")
        json.put("nick", "NICK")
        json.put("pw", "ps112312312")
        val body = json.toString()
        val entity = restTemplate.postForEntity<String>("/user/signup", HttpEntity<String>(body, headers))
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
    }

    @Test
    fun `Create a game room`() {
        `Login`()

        val headers = HttpHeaders()
        headers[HttpHeaders.COOKIE] = session
        headers.contentType = MediaType.APPLICATION_JSON
        headers[HttpHeaders.AUTHORIZATION] = "Bearer ${authToken}"
        val json = JSONObject()

        val body = json.toString()
        var entity = restTemplate.postForEntity<String>("/api/game/create", HttpEntity<String>(body, headers))
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
    }

    @Test
    fun `Join the game room`() {
        `Login`()

        val roomSeq = 7

        val headers = HttpHeaders()
        headers[HttpHeaders.COOKIE] = session
        headers.contentType = MediaType.APPLICATION_JSON
        val json = JSONObject()

        val body = json.toString()
        val entity = restTemplate.postForEntity<String>("/api/game/join/${roomSeq}", HttpEntity<String>(body, headers))
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
    }

    @AfterAll
    fun teardown() {
        println(">> Tear down")
    }

}
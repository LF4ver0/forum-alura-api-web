package br.com.alura.forum

import br.com.alura.forum.configuration.DatabaseContainerConfiguration
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@SpringBootTest(
    properties = ["spring.flyway.enabled=false"]
)
class ForumApplicationTests: DatabaseContainerConfiguration() {
	@Test
	fun contextLoads() {
	}

}

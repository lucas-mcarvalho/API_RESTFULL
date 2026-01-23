package com.udemyCourse.course.integrationtests.swagger;

import com.udemyCourse.course.config.TestConfigs;
import com.udemyCourse.course.integrationtests.testcontainers.AbstractIntegrationClass;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SwaggerIntegrationTest extends AbstractIntegrationClass {

	@Test
	void shouldDisplaySwaggerUIPage() {
		var contente = given().basePath("/swagger-ui/index.html")
				.port(TestConfigs.SERVER_PORT)
				.when().get().then().statusCode(200)
				.extract().body()
				.asString();
		assertTrue(contente.contains("Swagger UI"));
	}
}

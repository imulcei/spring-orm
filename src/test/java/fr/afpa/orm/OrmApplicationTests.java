package fr.afpa.orm;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.afpa.orm.web.controllers.AccountRestController;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class OrmApplicationTests {

	@Test
	@Order(1)
	@DisplayName("AccountRestController is marked as @RestController")
	void accountRestControllerAnnotation() {
		RestController restController = AccountRestController.class.getAnnotation(RestController.class);

		assertNotNull(restController);
	}

	@Test
	@Order(2)
	@DisplayName("AccountRestController is specified in @RequestMapping")
	void accountRestControllerRequestMapping() {
		RequestMapping requestMapping = AccountRestController.class.getAnnotation(RequestMapping.class);

		assertNotNull(requestMapping);
		assertThat(requestMapping.value().length).isEqualTo(1);
		assertThat(requestMapping.value()).contains("/accounts");
	}
}

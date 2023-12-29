package com.github.manoeljr.placeservice;

import com.github.manoeljr.placeservice.api.PlaceRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PlaceserviceApplicationTests {

	@Autowired
	WebTestClient webTestClient;

	@Test
	public void testCreatePlaceSuccess() {
		final String name = "Valid Name";
		final String state = "Valid State";
		final String slug = "valid-name";

		webTestClient
				.post()
				.uri("/places")
				.bodyValue(
						new PlaceRequest(name, state)
				)
				.exchange()
				.expectBody()
				.jsonPath("name").isEqualTo(name)
				.jsonPath("state").isEqualTo(state)
				.jsonPath("slug").isEqualTo(slug)
				.jsonPath("createdAt").isNotEmpty()
				.jsonPath("updatedAt").isNotEmpty();
	}

	@Test
	public void testCreatePlaceFailure() {
		var name = "";
		var state = "";

		webTestClient
				.post()
				.uri("/places")
				.bodyValue(new PlaceRequest(name, state))
				.exchange()
				.expectStatus().isBadRequest();
	}

}

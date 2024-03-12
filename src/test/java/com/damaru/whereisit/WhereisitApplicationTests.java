package com.damaru.whereisit;

import com.damaru.whereisit.db.RoomRepository;
import com.damaru.whereisit.model.Room;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class WhereisitApplicationTests {


	private Logger log = LoggerFactory.getLogger(WhereisitApplicationTests.class);

	@Autowired
	private MockMvc mvc;

	@Autowired
	private RoomRepository roomRepository;

	@BeforeTestMethod
	public void reset() {
		log.info("Resetting.");
		roomRepository.deleteAll();
	}

	@Test
	void getRooms() throws Exception {
		roomRepository.save(new Room(1L, "Solarium"));
		log.info("Here we go.");
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/room").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Solarium")))
				.andReturn();
	}
}

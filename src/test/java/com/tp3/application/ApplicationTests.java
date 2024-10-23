package com.tp3.application;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import com.tp3.application.Controller.VoitureController;


@RunWith(SpringRunner.class)

@SpringBootTest
class ApplicationTests {
	@Autowired
	VoitureController voitureController;

	@Test
	void contextLoads() {
		//assertThat(voitureController).isNotNull();
	}

}

package com.example.game_library;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
		"spring.datasource.url=jdbc:sqlite:game-library.db",
		"spring.datasource.driver-class-name=org.sqlite.JDBC",
		"spring.jpa.database-platform=org.hibernate.community.dialect.SQLiteDialect",
		"spring.jpa.hibernate.ddl-auto=update"
})
class GameLibraryApplicationTests {

	@Test
	void contextLoads() {
	}

}
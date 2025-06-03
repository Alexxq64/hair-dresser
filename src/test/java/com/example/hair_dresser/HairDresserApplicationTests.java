package com.example.hair_dresser;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
		"spring.datasource.url=jdbc:sqlite:hair-dresser.db",
		"spring.datasource.driver-class-name=org.sqlite.JDBC",
		"spring.jpa.database-platform=org.hibernate.community.dialect.SQLiteDialect",
		"spring.jpa.hibernate.ddl-auto=none"
})
class HairDresserApplicationTests {

	@Test
	void contextLoads() {
	}
}

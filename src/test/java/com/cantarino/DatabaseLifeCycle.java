import java.util.HashMap;
import java.util.Map;

import org.testcontainers.containers.MySQLContainer;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class DatabaseLifeCycle implements QuarkusTestResourceLifecycleManager
{
    private static MySQLContainer<?> MYSQL = new MySQLContainer<>("mysql/mysql-server:8.0.19");

	@Override
	public Map<String, String> start() {

        MYSQL.start();

        Map<String, String> props = new HashMap<>();
        props.put("quarkus.datasource.url", MYSQL.getJdbcUrl());
        props.put("quarkus.datasource.username", MYSQL.getUsername());
        props.put("quarkus.datasource.password",  MYSQL.getPassword());



		return props;
	}

	@Override
	public void stop() {

        if(MYSQL != null)
            MYSQL.stop();
	}

}
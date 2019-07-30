package com.monese.api.banking.tests.integration;

import javax.sql.DataSource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

/**
 * @author C.SajjadHussain
 *
 */

public class TestUtils {

	public static HttpEntity<String> createHeadersForGet(String accessToken) {
		HttpHeaders headers = createHeaders(accessToken);
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		return entity;
	}

	public static HttpHeaders createHeaders(String accessToken) {
		HttpHeaders headers = new HttpHeaders();
		if (accessToken != null && accessToken != "") {
			headers.set("access_token", accessToken);
		}

		return headers;
	}

	public static void createDBAndData(DataSource datasource, boolean createData) {

		if (createData) {
			createDefaultData(datasource);
		}
	}

	public static void cleanUpData(DataSource datasource, boolean cleanup) {
		if (cleanup) {
			deleteDefaultData(datasource);
		}
	}

	private static boolean createDefaultData(DataSource datasource) {
		Resource resource = new ClassPathResource("dml-insert-data-script.sql");
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(resource);
		databasePopulator.execute(datasource);
		return true;
	}

	private static boolean deleteDefaultData(DataSource datasource) {
		Resource resource = new ClassPathResource("dml-delete-data-script.sql");
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(resource);
		databasePopulator.execute(datasource);
		return true;
	}
}

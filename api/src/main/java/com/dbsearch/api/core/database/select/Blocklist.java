package com.dbsearch.api.core.database.select;

import com.dbsearch.api.config.tenant.FilePath;
import org.springframework.data.repository.init.ResourceReader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Blocklist {
		private final List<String> blocklist = new ArrayList<>();

		public Blocklist() {
				ClassLoader classLoader = ResourceReader.class.getClassLoader();
				InputStream inputStream = classLoader.getResourceAsStream(FilePath.BLOCKLIST.getValue());
				if (inputStream == null) {
						System.err.println("File not found");
						return;
				}
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
				reader.lines().forEach(this.blocklist::add);
		}

		public List<Field> filter(List<Field> fields) {
				return fields.stream().filter(field->this.blocklist.contains(field.getName().toUpperCase())).toList();
		}

		public boolean IsBlocked(Field field) {
				return this.blocklist.stream().noneMatch(line->line.contains(field.getName().toUpperCase()));
		}
}

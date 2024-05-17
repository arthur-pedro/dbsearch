package com.dbsearch.api.service;

import java.util.ArrayList;
import java.util.List;

import com.dbsearch.api.core.dto.SchemaDTO;
import com.dbsearch.api.repository.database.DatabaseRepository;
import org.springframework.stereotype.Service;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;


@Service
@Transactional
public class DatabaseService {
		@PersistenceContext
		private EntityManager entityManager;

		public List<SchemaDTO> getSchemas(String database) {
				DatabaseRepository databaseRepository = new DatabaseRepository(entityManager);
				List<String> result = databaseRepository.getSchemas(database);
				return result.stream()
								.map(name->new SchemaDTO(name, new ArrayList<>()))
								.toList();

		}
}
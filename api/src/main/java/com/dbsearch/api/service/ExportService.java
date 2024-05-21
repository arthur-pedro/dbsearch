package com.dbsearch.api.service;

import com.dbsearch.api.core.dto.TableDataDTO;
import com.dbsearch.api.core.dto.TableSearchDTO;
import com.dbsearch.api.helper.CSVHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
@Transactional
public class ExportService {

		@PersistenceContext
		private EntityManager entityManager;

		@Autowired
		private TableService tableService;

		public ByteArrayInputStream export(TableSearchDTO search) {
				TableDataDTO tableData = tableService.searchInTable(search);
				return CSVHelper.toCSV(tableData);
		}
}

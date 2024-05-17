package com.dbsearch.api.controller;

import java.util.List;
import java.util.Map;

import com.dbsearch.api.core.dto.TableDataDTO;
import com.dbsearch.api.core.dto.TableInfoDTO;
import com.dbsearch.api.core.dto.TableSearchDTO;
import com.dbsearch.api.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import jakarta.servlet.http.HttpServletRequest;

@Controller
@Transactional
@RequestMapping("table")
public class TableController {

		@Autowired
		private TableService tableService;

		@RequestMapping(method = RequestMethod.GET)
		public ResponseEntity<List<TableInfoDTO>> findTableNames(HttpServletRequest request, @RequestParam String schema)
						throws Exception {
				List<TableInfoDTO> result = this.tableService.findTableNames(schema);
				return new ResponseEntity<List<TableInfoDTO>>(result, HttpStatus.OK);
		}

		@RequestMapping(value = "{tableName}/columns", method = RequestMethod.GET)
		public ResponseEntity<TableInfoDTO> findTableColumns(HttpServletRequest request,
		                                                     @PathVariable("tableName") String tableName,
		                                                     @RequestParam String schema) throws Exception {
				TableInfoDTO result = this.tableService.findTableColumns(tableName, schema);
				return new ResponseEntity<TableInfoDTO>(result, HttpStatus.OK);
		}

		@RequestMapping(value = "{tableName}/data", method = RequestMethod.POST)
		public ResponseEntity<TableDataDTO> search(
						HttpServletRequest request,
						@PathVariable("tableName") String tableName,
						@RequestParam(required = true) String schema,
						@RequestParam(required = false, defaultValue = "1") int page,
						@RequestParam(required = false, defaultValue = "10") int pageSize,
						@RequestParam(required = false) List<String> fields,
						@RequestBody(required = false) Map<String, String> where
		) throws Exception {
				TableSearchDTO search = new TableSearchDTO(tableName, schema, page, pageSize, fields, where);
				TableDataDTO result = this.tableService.searchInTable(search);
				return new ResponseEntity<TableDataDTO>(result, HttpStatus.OK);
		}
}
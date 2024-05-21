package com.dbsearch.api.controller;

import com.dbsearch.api.core.dto.TableDataDTO;
import com.dbsearch.api.core.dto.TableInfoDTO;
import com.dbsearch.api.core.dto.TableSearchDTO;
import com.dbsearch.api.core.exceptions.BusinessException;
import com.dbsearch.api.service.TableService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Transactional
@RequestMapping("table")
public class TableController {

		@Autowired
		private TableService tableService;

		@RequestMapping(method = RequestMethod.GET)
		public ResponseEntity<List<TableInfoDTO>> findTableNames(HttpServletRequest request, @RequestParam String schema)
						throws BusinessException {
				List<TableInfoDTO> result = this.tableService.findTableNames(schema);
				return new ResponseEntity<List<TableInfoDTO>>(result, HttpStatus.OK);
		}

		@RequestMapping(value = "{tableName}/columns", method = RequestMethod.GET)
		public ResponseEntity<TableInfoDTO> findTableColumns(HttpServletRequest request,
		                                                     @PathVariable("tableName") String tableName,
		                                                     @RequestParam String schema) throws BusinessException {
				TableInfoDTO result = this.tableService.findTableColumns(tableName, schema);
				return new ResponseEntity<TableInfoDTO>(result, HttpStatus.OK);
		}

		@RequestMapping(value = "data", method = RequestMethod.POST)
		public ResponseEntity<TableDataDTO> search(
						HttpServletRequest request,
						@RequestBody(required = false) TableSearchDTO search
		) throws BusinessException {
				TableDataDTO result = this.tableService.searchInTable(search);
				return new ResponseEntity<>(result, HttpStatus.OK);
		}
}
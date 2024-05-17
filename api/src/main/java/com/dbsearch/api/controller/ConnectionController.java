package com.dbsearch.api.controller;

import java.util.List;

import com.dbsearch.api.core.dto.ConnectionDTO;
import com.dbsearch.api.core.dto.DatabaseDTO;
import com.dbsearch.api.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@Transactional
@RequestMapping("connection")
public class ConnectionController {

		@Autowired
		private ConnectionService connectionService;

		@RequestMapping(method = RequestMethod.GET)
		public ResponseEntity<List<ConnectionDTO>> findConnections(HttpServletRequest request) throws Exception {
				List<ConnectionDTO> result = this.connectionService.list();
				return new ResponseEntity<List<ConnectionDTO>>(result, HttpStatus.OK);
		}

		@RequestMapping(value = "databases", method = RequestMethod.GET)
		public ResponseEntity<List<DatabaseDTO>> findTableColumns(HttpServletRequest request) throws Exception {
				List<DatabaseDTO> result = this.connectionService.getDatabases();
				return new ResponseEntity<List<DatabaseDTO>>(result, HttpStatus.OK);
		}
}

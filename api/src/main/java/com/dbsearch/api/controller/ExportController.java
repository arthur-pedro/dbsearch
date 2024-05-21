package com.dbsearch.api.controller;

import com.dbsearch.api.core.dto.TableSearchDTO;
import com.dbsearch.api.service.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Date;

@Controller
@Transactional
@RequestMapping("export")
public class ExportController {

		@Autowired
		private ExportService exportService;

		@RequestMapping(method = RequestMethod.POST)
		public ResponseEntity<Resource> export(@Valid @RequestBody TableSearchDTO search) {
				String todayInString = new Date().toString();
				String filename = todayInString + "_export.csv";
				InputStreamResource file = new InputStreamResource(exportService.export(search));
				return ResponseEntity.ok()
								.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
								.contentType(MediaType.parseMediaType("application/csv"))
								.body(file);
		}
}
package com.dbsearch.api.controller;

import java.util.List;

import com.dbsearch.api.dto.SchemaDTO;
import com.dbsearch.api.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import jakarta.servlet.http.HttpServletRequest;

@Controller
@Transactional
@RequestMapping("database")
public class DatabaseController {

    @Autowired
    private DatabaseService databaseService;

    @RequestMapping(value = "{database}/schemas", method = RequestMethod.GET)
    public ResponseEntity<List<SchemaDTO>> findTableColumns(HttpServletRequest request, @PathVariable("database") String database) throws Exception {
        List<SchemaDTO> result = this.databaseService.getSchemas(database);
        return new ResponseEntity<List<SchemaDTO>>(result, HttpStatus.OK);
    }
}

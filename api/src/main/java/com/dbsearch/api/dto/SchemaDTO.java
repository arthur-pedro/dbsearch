package com.dbsearch.api.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class SchemaDTO {
    private String name;
    private List<TableInfoDTO> tables;
}

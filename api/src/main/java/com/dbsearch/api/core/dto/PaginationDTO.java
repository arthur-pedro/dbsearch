package com.dbsearch.api.core.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PaginationDTO<T> {
		private List<T[]> data;
		private int total;
		private int page;
		private int size;
}

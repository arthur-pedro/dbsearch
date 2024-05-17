package com.dbsearch.api.core.dto;

import java.util.List;

import com.dbsearch.api.config.tenant.TenantEnvironment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class ConnectionDTO {
		private String id;
		private String name;
		private String description;
		private String host;
		private String driver;
		private TenantEnvironment environment;
		private List<DatabaseDTO> databases;
}

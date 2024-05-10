package com.dbsearch.api.config.database.select;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Field {
    private String name;
    private String alias;

    public Field(String name) {
        this.name = name;
        this.alias = this.buildAlias(this.name);
    }

    private String buildAlias(String name) {
        return "" + name.toUpperCase().charAt(0) + name.toUpperCase().charAt(name.length() - 1);
    }
}

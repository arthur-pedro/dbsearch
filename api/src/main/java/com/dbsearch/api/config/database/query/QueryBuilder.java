package com.dbsearch.api.config.database.query;

import com.dbsearch.api.config.database.from.From;
import com.dbsearch.api.config.database.select.Select;
import com.dbsearch.api.config.database.where.Where;

public class QueryBuilder {
    private Select select;
    private From from;
    private Where where;

    public QueryBuilder() {}

    public QueryBuilder select(Select value) {
        this.select = value;
        return this;
    }

    public QueryBuilder from(From value) {
        this.from = value;
        return this;
    }

    public  QueryBuilder where(Where value) {
        this.where = value;
        return this;
    }

    public String build() {
        return String.format("SELECT %s FROM %s WHERE 1=1 AND %s",
                select.value(),
                from.value(),
                where.value());
    }
}

package com.dbsearch.api.helper;

import com.dbsearch.api.core.dto.TableDataDTO;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CSVHelper {
		private static final String NOT_AVAILABLE = "N/A";
		private static final String INVALID_FORMAT = "Invalid Format";

		public static ByteArrayInputStream toCSV(TableDataDTO tableData) {
				final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

				try (ByteArrayOutputStream out = new ByteArrayOutputStream();
				     CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format)) {
						for (String header : tableData.getColumns()) {
								csvPrinter.print(header);
						}
						csvPrinter.println();
						for (Map<String, Object> data : tableData.getData()) {
								List<String> line = Arrays.asList(
												tableData.getColumns().stream()
																.map(data::get)
																.map(value->value == null ? NOT_AVAILABLE : parseValue(value))
																.toArray(String[]::new)
								);
								csvPrinter.printRecord(line);
						}

						csvPrinter.flush();
						return new ByteArrayInputStream(out.toByteArray());
				} catch (IOException e) {
						throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
				}
		}

		private static String parseValue(Object value) {
				if (value == null)
						return NOT_AVAILABLE;
				if (value instanceof String)
						return (String) value;
				if (value instanceof byte[])
						return new String((byte[]) value);
				if (value instanceof Boolean)
						return ((Boolean) value) ? "True" : "False";
				if (value instanceof BigDecimal)
						return String.format("%.2f", value);
				if (value instanceof Float)
						return String.format("%.2f", value);
				if (value instanceof Double)
						return String.format("%.2f", value);
				if (value instanceof Number)
						return value.toString();
				if (value instanceof Character)
						return value.toString();
				return INVALID_FORMAT;
		}
}

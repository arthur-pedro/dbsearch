package com.dbsearch.api.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class BusinessException extends RuntimeException {
		private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

		private Object data = null;

		public BusinessException() {
				super();
		}

		public BusinessException(
						HttpStatus status,
						String message,
						Object data
		) {
				this(
								status,
								message
				);
				this.data = data;
		}

		public BusinessException(
						HttpStatus status,
						String message
		) {
				this(message);
				this.status = status;
		}

		public BusinessException(
						String message
		) {
				super(message);
		}
}

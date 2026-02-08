package com.heuron.heuroncloud.api.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HttpResponseBody<T> {

    private Integer code;

    private String message;

    private T response;

    public static <T> HttpResponseBodyBuilder<T> builder() {
        return new HttpResponseBodyBuilder<>();
    }

    public static class HttpResponseBodyBuilder<T> {
        private HttpStatus code;

        private String message;

        private T response;

        HttpResponseBodyBuilder() {
        }

        public HttpResponseBodyBuilder<T> code(final HttpStatus code) {
            this.code = code;
            return this;
        }

        public HttpResponseBodyBuilder<T> message(final String message) {
            this.message = message;
            return this;
        }

        public HttpResponseBodyBuilder<T> response(final T response) {
            this.response = response;
            return this;
        }

        public ResponseEntity<Object> buildAndMapToResponseEntity() {
            return ResponseEntity
                .status(code)
                .body(new HttpResponseBody<>(this.code.value(), this.message, this.response));
        }

    }

}
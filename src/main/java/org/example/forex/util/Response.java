package org.example.forex.util;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class Response {

    private String message;
    private HttpStatus status;

    public Response() {
    }

    public Response(String message) {
        this.message = message;
        this.status = HttpStatus.BAD_REQUEST;
    }

}

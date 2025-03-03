package org.example.katemerek.util;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class Response {

    private String message;
    private HttpStatus status;

    public Response(String message) {
        this.message = message;
        this.status = HttpStatus.BAD_REQUEST;
    }

}

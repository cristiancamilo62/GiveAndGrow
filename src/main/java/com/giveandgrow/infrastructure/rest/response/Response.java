package com.giveandgrow.infrastructure.rest.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response<D> {

    private HttpStatus status;
    private List<D> data;
    private String message;

    public static <D> Response<D> of(HttpStatus status, List<D> data, String message) {
        return new Response<>(status,data,message);
    }

    public static <D> Response<D> success(HttpStatus status, String message) {
        return new Response<>(status,Collections.emptyList(),message);
    }

    public static <D> Response<D> error(HttpStatus status, String message) {
        return new Response<>(status, Collections.emptyList(),message);
    }
}

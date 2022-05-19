package com.bjit.recruitment.dto;

import com.bjit.recruitment.enumeration.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ResponseDto<T> {

    Response response;
    String massage;
    T data;
}

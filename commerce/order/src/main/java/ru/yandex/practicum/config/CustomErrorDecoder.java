package ru.yandex.practicum.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import jakarta.ws.rs.InternalServerErrorException;
import ru.yandex.practicum.error.ErrorResponse;
import ru.yandex.practicum.exception.NotAuthorizedUserException;
import ru.yandex.practicum.exception.AnotherServiceBadRequestException;
import ru.yandex.practicum.exception.AnotherServiceNotFoundException;

import java.io.IOException;
import java.io.InputStream;

public class CustomErrorDecoder implements ErrorDecoder {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private final ErrorDecoder defaultDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        ErrorResponse errorResponse = parseErrorResponse(response);

        return switch (response.status()) {
            case 400 -> new AnotherServiceBadRequestException(messageOrDefault(errorResponse, "Bad request"));
            case 401 -> new NotAuthorizedUserException(messageOrDefault(errorResponse, "Unauthorized"));
            case 404 -> new AnotherServiceNotFoundException(messageOrDefault(errorResponse, "Not found"));
            case 500 -> new InternalServerErrorException(messageOrDefault(errorResponse, "Internal server error"));
            default -> defaultDecoder.decode(methodKey, response);
        };
    }

    private ErrorResponse parseErrorResponse(Response response) {
        try (InputStream body = response.body().asInputStream()) {
            return MAPPER.readValue(body, ErrorResponse.class);
        } catch (IOException ex) {
            throw new RuntimeException("Failed to parse error response: " + ex.getMessage(), ex);
        }
    }

    private String messageOrDefault(ErrorResponse errorResponse, String defaultMsg) {
        return (errorResponse != null && errorResponse.getUserMessage() != null)
                ? errorResponse.getUserMessage()
                : defaultMsg;
    }
}

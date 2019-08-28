package info.ivicel.augmented.core.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * 返回的 JSON 包装, 忽略 null 值
 * @param <T>
 */
@Data
@JsonInclude(Include.NON_NULL)
public final class Response<T> implements Serializable {

    private static final long serialVersionUID = -2437301535822948206L;

    private String result;

    private T data;

    private String error;

    @JsonProperty("error_description")
    private String errorDescription;

    private Response(String result, T data) {
        this.result = result;
        this.data = data;
    }

    private Response(String error, String errorDescription) {
        this.error = error;
        this.errorDescription = errorDescription;
    }

    public static <T> Response error(String error, String errorDescription) {
        Response response = new Response<>(error, errorDescription);
        response.result = "error";
        return response;
    }

    public static <T> ResponseEntity<Response> success(T data) {
        return ResponseEntity.ok(new Response<>(Result.SUCCESS.getCode(), data));
    }

    public static ResponseEntity<Response> fail400() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Response.error(Result.INVALID_REQUEST.getCode(), Result.INVALID_REQUEST.getMsg()));
    }

    public static ResponseEntity<Response> fail500() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Response.error(Result.INTERNAL_SERVER_ERROR.getCode(),
                        Result.INTERNAL_SERVER_ERROR.getMsg()));
    }
}

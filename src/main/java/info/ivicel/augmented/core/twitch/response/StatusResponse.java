package info.ivicel.augmented.core.twitch.response;

import java.util.List;
import lombok.Data;

@Data
public class StatusResponse<T> {
    private String error;
    private Integer status;
    private String message;

    private List<T> data;
}

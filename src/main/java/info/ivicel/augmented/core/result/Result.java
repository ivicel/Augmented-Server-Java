package info.ivicel.augmented.core.result;

public enum Result {
    SUCCESS("success", "success"),
    INVALID_REQUEST("invalid_request", "Invalid request"),
    INTERNAL_SERVER_ERROR("internal_server_error", "Internal server error");

    private String code;
    private String msg;

    Result(String code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

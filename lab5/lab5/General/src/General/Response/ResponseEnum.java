package General.Response;

public enum ResponseEnum {
    OK(0),
    ERR(1),
    PRINT_TEXT(1)
    ;

    final int value;

    ResponseEnum(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }
}

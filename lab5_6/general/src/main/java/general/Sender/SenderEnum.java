package general.Sender;

public enum SenderEnum {
    GET_COLLECTION_INFO(0),
    GET_COLLECTION_ELEMENTS(1),
    ADD_ELEMENT(2),
    UPDATE_ELEMENT(3),
    CLEAR_COLLECTION(4),
    GET_COLLECTION_ELEMENTS_BY_TYPE(5),
    REMOVE_ELEMENT_BY_ID(6),
    SAVE_COLLECTION(7),
    GET_ASCENDING_COLLECTION_ELEMENTS(8),
    REMOVE_ALL_BY_ENGINE_POWER(9),

    PRINT_TEXT(10),
    ;



    /*UPDATE,
    REMOVE_BY_ID,
    CLEAR,
    SAVE,
    EXECUTE_SCRIPT,
    EXIT,
    ADD_IF_MAX,
    REMOVE_GREATER,
    REMOVE_LOWER,
    REMOVE_ALL_BY_ENGINE_POWER,
    FILTER_BY_TYPE,
    PRINT_ASCENDING;*/

    final int value;

    SenderEnum(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }
}

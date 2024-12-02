package org.sight.kiosk.enums;

public enum CODES
{
    // Error Codes
    SCARD_F_INTERNAL_ERROR("SCARD_F_INTERNAL_ERROR"),
    SCARD_E_CANCELLED("SCARD_E_CANCELLED"),
    SCARD_E_INVALID_HANDLE("SCARD_E_INVALID_HANDLE"),
    SCARD_E_INVALID_PARAMETER("SCARD_E_INVALID_PARAMETER"),
    SCARD_E_INVALID_TARGET("SCARD_E_INVALID_TARGET7"),
    SCARD_E_NO_MEMORY("SCARD_E_NO_MEMORY"),
    SCARD_F_WAITED_TOO_LONG("SCARD_F_WAITED_TOO_String"),
    SCARD_E_INSUFFICIENT_BUFFER("SCARD_E_INSUFFICIENT_BUFFER"),
    SCARD_E_UNKNOWN_READER("SCARD_E_UNKNOWN_READER"),
    SCARD_E_NO_READERS_AVAILABLE("SCARD_E_NO_READERS_AVAILABLE"),

    SCARD_E_TIMEOUT("SCARD_E_TIMEOUT"),
    SCARD_E_SHARING_VIOLATION("SCARD_E_SHARING_VIOLATION"),
    SCARD_E_NO_SMARTCARD("SCARD_E_NO_SMARTCARD"),
    SCARD_E_UNKNOWN_CARD("SCARD_E_UNKNOWN_CARD"),
    SCARD_E_CANT_DISPOSE("SCARD_E_CANT_DISPOSE"),
    SCARD_E_PROTO_MISMATCH("SCARD_E_PROTO_MISMATCH"),


    SCARD_E_NOT_READY("SCARD_E_NOT_READY"),
    SCARD_E_INVALID_VALUE("SCARD_E_INVALID_VALUE"),
    SCARD_E_SYSTEM_CANCELLED("SCARD_E_SYSTEM_CANCELLED"),
    SCARD_F_COMM_ERROR("SCARD_F_COMM_ERROR"),
    SCARD_F_UNKNOWN_ERROR("SCARD_F_UNKNOWN_ERROR"),
    SCARD_E_INVALID_ATR("SCARD_E_INVALID_ATR"),
    SCARD_E_NOT_TRANSACTED("SCARD_E_NOT_TRANSACTED"),
    SCARD_E_READER_UNAVAILABLE("SCARD_E_READER_UNAVAILABLE"),
    SCARD_P_SHUTDOWN("SCARD_P_SHUTDOWN8"),
    SCARD_E_PCI_TOO_SMALL("SCARD_E_PCI_TOO_SMALL"),

    SCARD_E_READER_UNSUPPORTED("SCARD_E_READER_UNSUPPORTED"),
    SCARD_E_DUPLICATE_READER("SCARD_E_DUPLICATE_READER"),
    SCARD_E_CARD_UNSUPPORTED("SCARD_E_CARD_UNSUPPORTED"),
    SCARD_E_NO_SERVICE("SCARD_E_NO_SERVICE"),
    SCARD_E_SERVICE_STOPPED("SCARD_E_SERVICE_STOPPED"),

    SCARD_W_UNSUPPORTED_CARD("SCARD_W_UNSUPPORTED_CARD"),
    SCARD_W_UNRESPONSIVE_CARD("SCARD_W_UNRESPONSIVE_CARD"),
    SCARD_W_UNPOWERED_CARD("SCARD_W_UNPOWERED_CARD"),
    SCARD_W_RESET_CARD("SCARD_W_RESET_CARD"),

    // From SCARD_W_REMOVED_CARD to SCARD_E_DIR_NOT_FOUND
    SCARD_E_DIR_NOT_FOUND("SCARD_E_DIR_NOT_FOUND"),
    SCARD_W_REMOVED_CARD("SCARD_W_REMOVED_CARD"),

    // NFC Library
    ACS_NFC_ERR_IO("ACS_NFC_ERR_IO"),
    ACS_NFC_ERR_INVALID("ACS_NFC_ERR_INVALID"),
    ACS_NFC_ERR_DEV_NOT_SUPP("ACS_NFC_ERR_DEV_NOT_SUPP"),
    ACS_NFC_ERR_NOT_SUCH_DEV("ACS_NFC_ERR_NOT_SUCH_DEV"),
    ACS_NFC_ERR_NOT_DATA("ACS_NFC_ERR_NOT_DATA"),
    ACS_NFC_ERR_TIMEOUT("ACS_NFC_ERR_TIMEOUT"),
    ACS_NFC_ERR_FAIL("ACS_NFC_ERR_FAIL"),
    ACS_NFC_ERR_RF_TRANS("ACS_NFC_ERR_RF_TRANS"),
    ACS_NFC_ERR_SOFT("ACS_NFC_ERR_SOFT"),

    SCARD_S_SUCCESS("SCARD_S_SUCCESS");

    private String _id;
    CODES(String id) {this._id = id;}
    public String getErrorCode() {return _id;}
    public static CODES getKeyNameErrorCode(String iErrorCode)
    {
        for (CODES ErrorCode : CODES.values()) {
            if (ErrorCode._id == iErrorCode) return ErrorCode;
        }
        return SCARD_S_SUCCESS;
    }
}
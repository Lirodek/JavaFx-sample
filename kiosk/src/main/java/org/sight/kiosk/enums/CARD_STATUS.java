package org.sight.kiosk.enums;

public enum CARD_STATUS
{
    CARD_FOUND(0),
    CARD_REMOVED(1),
    ERROR(2);

    private final int _id;
    CARD_STATUS(int id) {this._id = id;}
}
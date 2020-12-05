package es.urjc.cloudapps.library.domain;

public class Rating {

    private final int value;

    public Rating(int value) {
        assert value >= 0 && value <= 5;
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

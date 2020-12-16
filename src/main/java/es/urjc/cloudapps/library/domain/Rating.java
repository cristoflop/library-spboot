package es.urjc.cloudapps.library.domain;

import es.urjc.cloudapps.library.exception.FieldFormatException;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Rating {

    @Column(name = "rating")
    private int value;

    protected Rating(){ }

    public Rating(int value) {
        if (value < 0 || value > 5)
            throw new FieldFormatException("La puntuación debe estar entre 0 y 5");
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

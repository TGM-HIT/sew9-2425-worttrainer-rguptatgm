package org.rgupta;

import java.io.Serializable;

public class Statistics implements Serializable {
    private int correct;
    private int incorrect;

    public void addCorrect() {
        this.correct++;
    }

    public int getCorrect() {
        return this.correct;
    }

    public void addIncorrect() {
        this.incorrect++;
    }

    public int getIncorrect() {
        return this.incorrect;
    }

    public int getTotal() {
        return this.correct + this.incorrect;
    }

}

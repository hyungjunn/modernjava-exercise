package chap02;

import static chap02.Color.RED;

public class AppleRedColorPredicate implements ApplePredicate{
    @Override
    public boolean test(Apple apple) {
        return RED.equals(apple.getColor());
    }
}

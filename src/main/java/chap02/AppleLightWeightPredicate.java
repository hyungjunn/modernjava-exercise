package chap02;

public class AppleLightWeightPredicate implements ApplePredicate{
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() <= 200;
    }
}

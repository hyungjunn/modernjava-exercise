package chap02;

public class AppleWeightFormatter implements AppleFormatter{
    @Override
    public String print(Apple apple) {
        return "Apple의 무게: " + apple.getWeight();
    }
}

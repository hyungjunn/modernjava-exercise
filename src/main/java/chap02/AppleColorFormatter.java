package chap02;

public class AppleColorFormatter implements AppleFormatter {

    @Override
    public String print(Apple apple) {
        return "Apple의 색깔: " + apple.getColor();
    }

}

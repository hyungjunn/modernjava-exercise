package chap02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static chap02.Color.GREEN;
import static chap02.Color.RED;
import static org.assertj.core.api.Assertions.assertThat;

class FarmerTest {

    private List<Apple> apples;

    @BeforeEach
    void init() {
        //given
        apples = Arrays.asList(
                new Apple(150, RED),
                new Apple(200, GREEN),
                new Apple(300, RED),
                new Apple(500, GREEN)
        );
    }

    @DisplayName("녹색 사과 필터링")
    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    void filter_green_apples(int input) {
        //when
        List<Apple> greenApples = Farmer.filterGreenApples(apples);

        //then
        assertThat(greenApples.get(input).getColor()).isEqualTo(GREEN);
    }

    @DisplayName("주어진 색깔로 사과 필터링")
    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    void filter_apples_by_color(int input) {
        //when
        List<Apple> applesByColor = Farmer.filterAppleByColor(apples, RED);

        //then
        assertThat(applesByColor.get(input).getColor()).isEqualTo(RED);
    }

    @DisplayName("주어진 무게로 사과 필터링")
    @Test
    void filter_apples_by_weight() {
        //when
        List<Apple> applesByWeight = Farmer.filterAppleByWeight(apples, 200);

        //then
        assertThat(applesByWeight.get(0).getWeight()).isEqualTo(300);
        assertThat(applesByWeight.get(1).getWeight()).isEqualTo(500);
    }

    @DisplayName("색깔 또는 무게로 사과 필터링")
    @Test
    void filter_apples_by_color_and_weight() {
        //when: 무게가 200이상인 사과만 필터링, 색깔은 상관 무
        List<Apple> applesByWeightAndColor = Farmer.filterApples(apples, RED, 200, true);

        //then
        assertThat(applesByWeightAndColor.get(0).getColor()).isEqualTo(RED);
        assertThat(applesByWeightAndColor.get(0).getWeight()).isEqualTo(300);

        assertThat(applesByWeightAndColor.get(1).getColor()).isEqualTo(GREEN);
        assertThat(applesByWeightAndColor.get(1).getWeight()).isEqualTo(500);
    }

    @DisplayName("빨간 사과 필터링을 ApplePredicate를 이용해서 테스트한다")
    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    void filter_apples_by_predicate_color_red(int input) {
        //when
        List<Apple> applesByRed = Farmer.filterApples(apples, new AppleRedColorPredicate());

        //then
        assertThat(applesByRed.get(input).getColor()).isEqualTo(RED);
    }

    @DisplayName("녹색 사과 필터링을 ApplePredicate를 이용해서 테스트한다")
    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    void filter_apples_by_predicate_color_green(int input) {
        //when
        List<Apple> applesByRed = Farmer.filterApples(apples, new AppleGreenColorPredicate());

        //then
        assertThat(applesByRed.get(input).getColor()).isEqualTo(GREEN);
    }

    @DisplayName("무게가 200을 초과하는 사과 필터링을 ApplePredicate를 이용해서 테스트한다")
    @Test
    void filter_apples_by_predicate_weight() {
        //when
        List<Apple> applesByRed = Farmer.filterApples(apples, new AppleHeavyWeightPredicate());

        //then
        assertThat(applesByRed.get(0).getWeight()).isEqualTo(300);
        assertThat(applesByRed.get(1).getWeight()).isEqualTo(500);
    }

    @DisplayName("무게가 200이하의 사과 필터링을 ApplePredicate를 이용해서 테스트한다")
    @Test
    void filter_apples_by_predicate_weight2() {
        //when
        List<Apple> applesByRed = Farmer.filterApples(apples, new AppleLightWeightPredicate());

        //then
        assertThat(applesByRed.get(0).getWeight()).isEqualTo(150);
        assertThat(applesByRed.get(1).getWeight()).isEqualTo(200);
    }

    @DisplayName("주어진 사과들의 무게를 반환하는 메서드를 테스트한다")
    @ParameterizedTest
    @CsvSource(value = {"0:150", "1:200", "2:300", "3:500"}, delimiter = ':')
    void print_apples_weight(int input, int expected) {
        //when
        List<String> strings = Farmer.prettyPrintApple(apples, new AppleWeightFormatter());
        String actual = strings.get(input);

        //then
        assertThat(actual).isEqualTo("Apple의 무게: " + expected);
    }

    @DisplayName("주어진 사과들의 색깔을 반환하는 메서드를 테스트한다")
    @ParameterizedTest
    @CsvSource(value = {"0:RED", "1:GREEN", "2:RED", "3:GREEN"}, delimiter = ':')
    void print_apples_color(int input, Color expected) {
        //when
        List<String> strings = Farmer.prettyPrintApple(apples, new AppleColorFormatter());
        String actual = strings.get(input);

        //then
        assertThat(actual).isEqualTo("Apple의 색깔: " + expected);
    }
}
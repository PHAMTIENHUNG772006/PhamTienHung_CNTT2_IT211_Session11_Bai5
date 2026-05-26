package com.re.session11_bai1.test;

import com.re.session11_bai1.service.ShippingFeeCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ShippingFeeCalculatorTest {

    private ShippingFeeCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new ShippingFeeCalculator();
    }

    @Test
    @DisplayName("1. Cân nặng <= 1kg và Khoảng cách <= 10km")
    void testWeightLessThanOneKgAndDistanceLessThanTenKm() {
        double fee = calculator.calculateFee(1.0, 5.0);
        assertThat(fee).isEqualTo(50000.0);

        double feeSmall = calculator.calculateFee(0.5, 9.9);
        assertThat(feeSmall).isEqualTo(50000.0);
    }

    @Test
    @DisplayName("2. Cân nặng > 1kg (số nguyên) và Khoảng cách từ 10km đến dưới 50km")
    void testWeightIntegerGreaterThanOneAndDistanceBetweenTenAndFifty() {
        double fee = calculator.calculateFee(3.0, 30.0);
        assertThat(fee).isEqualTo(170000.0);
    }

    @Test
    @DisplayName("3. Cân nặng là số lẻ và Khoảng cách >= 50km")
    void testWeightDecimalAndDistanceGreaterThanFifty() {
        double fee1 = calculator.calculateFee(1.5, 60.0);
        assertThat(fee1).isEqualTo(300000.0);

        double fee2 = calculator.calculateFee(2.3, 55.5);
        assertThat(fee2).isEqualTo(292000.0);
    }

    @Test
    @DisplayName("4. Kiểm thử chính xác tại các điểm biên khoảng cách")
    void testDistanceAtExactBoundaries() {
        double feeAtTen = calculator.calculateFee(1.0, 10.0);
        assertThat(feeAtTen).isEqualTo(50000.0);

        double feeAtFifty = calculator.calculateFee(1.0, 50.0);
        assertThat(feeAtFifty).isEqualTo(250000.0);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 10",
            "-1.5, 20",
            "2, 0",
            "3, -5",
            "0, 0"
    })
    @DisplayName("5. Đầu vào không hợp lệ")
    void testInvalidInputsShouldThrowException(double weight, double distance) {
        assertThatThrownBy(() -> calculator.calculateFee(weight, distance))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Weight and distance must be positive");
    }
}

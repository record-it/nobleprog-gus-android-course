package pl.gus.app.temperature;

import java.util.function.DoubleFunction;

public enum TemperatureUnit {
    CELSIUS(c -> c, c -> c), //TemperaturUnit CELSIUS = new TempetratureUnit(,)
    FAHRENHEIT(f -> 5 * (f - 32.0)/9.0, c -> 32 + c * 9.0/5.0),
    KELVIN(k -> k - 273.15, c -> c + 273.15);

    private final DoubleFunction<Double> toCelsius;
    private final DoubleFunction<Double> toUnit;

    TemperatureUnit(DoubleFunction<Double> toCelsius, DoubleFunction<Double> toUnit) {
        this.toCelsius = toCelsius;
        this.toUnit = toUnit;
    }
    public double toUnit(double value, TemperatureUnit unit){
        double c = this.toCelsius.apply(value);
        return unit.toUnit.apply(c);
    }
}

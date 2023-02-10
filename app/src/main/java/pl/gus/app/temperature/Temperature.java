package pl.gus.app.temperature;

import java.util.Optional;

/**
 * Klasa typu ValueObject do przechowywania temperatury w jednostkach
 */
public class Temperature {
    private final double value;
    private final TemperatureUnit unit;

    private Temperature(double value, TemperatureUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    /**
     * Metoda fabryczna zwracająca Optional z obiektem temperatury dla value >= 0
     * @param value
     * @param unit
     * @return
     */
    public static Optional<Temperature> of(double value, TemperatureUnit unit){
        if (unit.toUnit(value, TemperatureUnit.KELVIN) >= 0){
            return Optional.of(new Temperature(value, unit));

        } else {
            return Optional.empty();
        }
    }

    /**
     * Metoda statyczna zwracająca obiekt temperatury dla value >= 0 i null dla value < 0
     * @param value wartość temperatury
     * @param unit jednostka temperatury
     * @return obiekt temperatury
     */
    public static Temperature ofNullable(double value, TemperatureUnit unit){
        if (unit.toUnit(value, TemperatureUnit.KELVIN) >= 0){
            return new Temperature(value, unit);

        }
        return null;
    }

    /**
     * Interfejs pomocniczy, który wymusza podanie jednostki po podaniu wartości
     */
    public interface RequireUnit{
        /**
         * Metdoa do określania jednostki temperatury
         * @param unit
         * @return
         */
        Temperature unit(TemperatureUnit unit);
    }

    /**
     * Metoda budowniczego obiektów temperatury, dla niepoprawnych wartości zgłasza wyjątek
     * @param value wartość temperatury
     * @return obiekt z temperaturą
     */
    public static RequireUnit value(double value){
        return unit ->{
            if (unit.toUnit(value, TemperatureUnit.KELVIN) < 0.0){
                throw new IllegalArgumentException("Temperature cannot be below 0 Kelvin");
            } else {
                return new Temperature(value, unit);
            }
        };
    }
    public Temperature toUnit(TemperatureUnit unit){
        if(unit == this.unit){
            return this;
        }
        return new Temperature(this.unit.toUnit(this.value, unit), unit);
    }

    public double getValue() {
        return value;
    }

    public TemperatureUnit getUnit() {
        return unit;
    }
}

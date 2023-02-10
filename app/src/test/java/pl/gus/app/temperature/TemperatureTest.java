package pl.gus.app.temperature;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class TemperatureTest {
    @Test
    public void toUnit() {
        //given
        Temperature testT = Temperature.of(0, TemperatureUnit.CELSIUS).get();
        //when
        Temperature actual = testT.toUnit(TemperatureUnit.FAHRENHEIT);
        //that
        Assert.assertEquals(32.0, actual.getValue(), 0.01);
        Assert.assertEquals(TemperatureUnit.FAHRENHEIT, actual.getUnit());
    }

    //Napisz metodę testowa sprawdzająca poprawnosc przeliczenia z K na F
    //273.15 K to 32 F

    @Test
    public void shouldThrowExceptionForNonPositiveKelvinTemperature(){
        //given, when and that
        Assert.assertThrows(IllegalArgumentException.class,
                () -> Temperature.value(-10).unit(TemperatureUnit.KELVIN));
    }
    @Test
    public void shouldThrowExceptionMinus274CelsiusTemperature(){
        //given, when and that
        Assert.assertThrows(IllegalArgumentException.class,
                () -> Temperature.value(-274).unit(TemperatureUnit.CELSIUS));
    }

    @Test
    public void shouldReturn100CelsiusTemperature(){
        //given, when
        Temperature temperature = Temperature.value(100).unit(TemperatureUnit.CELSIUS);
        //that
        Assert.assertEquals(100.0, temperature.getValue(), 0.001);
        Assert.assertEquals(TemperatureUnit.CELSIUS, temperature.getUnit());
    }


}
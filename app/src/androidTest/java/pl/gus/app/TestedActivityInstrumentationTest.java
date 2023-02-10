package pl.gus.app;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Context;
import android.content.Intent;

import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.gus.app.temperature.TestedActivity;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TestedActivityInstrumentationTest {
    public static final String MY_LABEL = "my label";
    public static final String LABEL = "label";
    public static final String CREATE = "create";
    public Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

    @Before
    public void intentsInit() {
        Intents.init();
    }

    /**
     * Przypadek, gdy aktywność otrzymuje poprawne parametry
     * Przycisk powinien być utworzony w aktywności
     */
    @Test
    public void shouldButtonBeCreated() {
        //given
        Intent intent = new Intent(appContext, TestedActivity.class);   //tworzymy intencję
        intent.putExtra(LABEL, MY_LABEL);                               //parametry aktywności
        intent.putExtra(CREATE, true);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //when
        appContext.startActivity(intent);                               //tworzymy nową aktywność
        //that
        onView(withId(TestedActivity.BUTTON_ID))                        //odwołanie do utworzonego przycisku
                .check(ViewAssertions.matches(withText(MY_LABEL)))      //czy przycisk na tekst z intencji
                .check(ViewAssertions.matches(isEnabled())              //czy przycisk jest aktywny
                );
    }

    /**
     * Przypadek, gdy nie przekazano pramaterów w inencji.
     * Testowana aktywność nie powinna autworzyć przycisku
     */
    @Test
    public void shouldButtonNotBeCreated() {
        //given
        Intent intent = new Intent(appContext, TestedActivity.class);
        intent.putExtra(LABEL, MY_LABEL);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //when
        appContext.startActivity(intent);
        //that
        onView(withId(TestedActivity.BUTTON_ID))
                .check(ViewAssertions.doesNotExist());          //przycisk o id=BUTTON_ID nie powinien istnieć
    }

    @After
    public void intentsTeardown() {
        Intents.release();
    }



}


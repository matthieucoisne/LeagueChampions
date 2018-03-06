package com.leaguechampions.ui.champions;

import android.content.Intent;
import android.support.v7.widget.Toolbar;

import com.leaguechampions.R;
import com.leaguechampions.ui.settings.SettingsActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class ChampionActivityTest {

    private ChampionsActivity activity;

    @Before
    public void setUp() {
        activity = Robolectric.buildActivity(ChampionsActivity.class)
                .create()
//                .start()
//                .resume()
                .visible()
                .get();
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testOnOptionsItemSelected_WhenItemIsSettings_StartSettingsActivity() {
        ShadowActivity shadowActivity = shadowOf(activity);
        shadowActivity.onCreateOptionsMenu(((Toolbar) activity.findViewById(R.id.activity_champions_toolbar)).getMenu());
        shadowActivity.clickMenuItem(R.id.menu_settings);

        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertNotNull(startedIntent);
        assertEquals(SettingsActivity.class, shadowIntent.getIntentClass());
    }

    @Test
    public void testOnOptionsItemSelected_WhenItemIsNotSettings_DoNothing() {
        ShadowActivity shadowActivity = shadowOf(activity);
        shadowActivity.onCreateOptionsMenu(((Toolbar) activity.findViewById(R.id.activity_champions_toolbar)).getMenu());
        shadowActivity.clickMenuItem(0);

        Intent startedIntent = shadowActivity.getNextStartedActivity();
        assertNull(startedIntent);
    }
}

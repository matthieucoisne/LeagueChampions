package com.leaguechampions.ui.championdetails;

import android.content.Intent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowActivity;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class ChampionDetailsActivityTest {

    private ChampionDetailsActivity activity;

    @Before
    public void setUp() {
        Intent intent = ChampionDetailsActivity.getIntent(RuntimeEnvironment.application, "Riven");

        activity = Robolectric.buildActivity(ChampionDetailsActivity.class, intent)
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
    public void testOnOptionsItemSelected_WhenItemIsHome_ShouldFinish() {
        ShadowActivity shadowActivity = shadowOf(activity);
        shadowActivity.clickMenuItem(android.R.id.home);
        assertTrue(shadowActivity.isFinishing());
    }

    @Test
    public void testOnOptionsItemSelected_WhenItemIsNotHome_ShouldNotFinish() {
        ShadowActivity shadowActivity = shadowOf(activity);
        shadowActivity.clickMenuItem(0);
        assertFalse(shadowActivity.isFinishing());
    }
}

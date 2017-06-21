package com.leaguechampions.presenter;

import android.content.SharedPreferences;
import android.view.MenuItem;

import com.leaguechampions.BuildConfig;
import com.leaguechampions.utils.PrefUtils;
import com.leaguechampions.utils.ReflectionUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({BuildConfig.class, PrefUtils.class})
public class SettingsPresenterTest {

    private SettingsPresenter presenter;

    @Mock SettingsPresenter.SettingsViewable viewable;
    @Mock SharedPreferences sharedPreferences;

    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.initMocks(this);

        presenter = new SettingsPresenter(sharedPreferences);
        presenter.setViewable(viewable);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testOnActivityCreated() {
        PowerMockito.mockStatic(PrefUtils.class);
        when(PrefUtils.isMockMode(sharedPreferences)).thenReturn(true);
        ReflectionUtils.setField(presenter, "buildType", "debug");

        presenter.onActivityCreated(null, null);

        verify(viewable).setVersion(BuildConfig.VERSION_NAME);
        verify(viewable).showMockMode(true);
        verifyNoMoreInteractions(viewable);
    }

    @Test
    public void testOnActivityCreated_WhenBuildTypeRelease_OnlySetsVersion() {
        PowerMockito.mockStatic(PrefUtils.class);
        ReflectionUtils.setField(presenter, "buildType", "release");

        presenter.onActivityCreated(null, null);

        verify(viewable).setVersion(BuildConfig.VERSION_NAME);
        verifyNoMoreInteractions(viewable);
    }

    @Test
    public void testOnOptionsItemSelected() {
        MenuItem item = mock(MenuItem.class);
        when(item.getItemId()).thenReturn(android.R.id.home);

        presenter.onOptionsItemSelected(item);

        verify(viewable).doFinish();
        verifyNoMoreInteractions(viewable);
    }

    @Test
    public void testOnOptionsItemSelected_WhenItemNotHome_DoNothing() {
        MenuItem item = mock(MenuItem.class);
        when(item.getItemId()).thenReturn(0);

        presenter.onOptionsItemSelected(item);

        verifyNoMoreInteractions(viewable);
    }

    @Test
    public void testOnMockModeCheckedChanged() {
        PowerMockito.mockStatic(PrefUtils.class);
        when(PrefUtils.isMockMode(sharedPreferences)).thenReturn(true);

        presenter.onMockModeCheckedChanged(true);

        verifyNoMoreInteractions(viewable);
    }
}

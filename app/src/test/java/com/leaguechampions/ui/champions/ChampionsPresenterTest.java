package com.leaguechampions.ui.champions;

import android.os.Bundle;
import android.view.MenuItem;

import com.leaguechampions.R;
import com.leaguechampions.data.model.Champion;
import com.leaguechampions.data.model.RiotRealm;
import com.leaguechampions.data.model.RiotResponse;
import com.leaguechampions.data.remote.Api;
import com.leaguechampions.utils.ImmediateSchedulersRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class ChampionsPresenterTest {

    private ChampionsPresenter presenter;

    private final Map<String, Champion> data = new HashMap<String, Champion>() {{
        put("Riven", new Champion("Riven"));
        put("Jinx", new Champion("Jinx"));
    }};

    private final HttpException httpException = new HttpException(Response.error(400, ResponseBody.create(MediaType.parse("application/json"), "{\"error\":\"failure\"}")));
    private final IOException ioException = new IOException();

    @Mock ChampionsPresenter.ChampionsView view;
    @Mock Api api;

    @Mock RiotResponse riotResponse;
    @Mock RiotRealm riotRealm;

    @Rule ImmediateSchedulersRule rule = new ImmediateSchedulersRule();

    @Before
    public void setUp() {
        presenter = new ChampionsPresenter(view, api);

        when(riotRealm.getVersion()).thenReturn("1.0.0");
        when(riotResponse.getData()).thenReturn(data);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testOnActivityCreated() {
        Bundle arguments = mock(Bundle.class);
        when(api.getVersion()).thenReturn(Observable.just(riotRealm));
        when(api.getChampions(anyString())).thenReturn(Observable.just(riotResponse));

        presenter.onActivityCreated(null, arguments);

        verify(view).setAdapter(new ArrayList<>(data.values()));
        verifyNoMoreInteractions(view);
    }

    @Test
    public void testOnActivityCreated_WhenGetVersionThrowsError400_ShowsError() throws Exception {
        Bundle arguments = mock(Bundle.class);
        when(api.getVersion()).thenReturn(Observable.error(httpException));

        presenter.onActivityCreated(null, arguments);

        verify(view).showError(R.string.error_something_went_wrong);
        verifyNoMoreInteractions(view);
    }

    @Test
    public void testOnActivityCreated_WhenGetChampionsThrowsError400_ShowsError() throws Exception {
        Bundle arguments = mock(Bundle.class);
        when(api.getVersion()).thenReturn(Observable.just(riotRealm));
        when(api.getChampions(anyString())).thenReturn(Observable.error(httpException));

        presenter.onActivityCreated(null, arguments);

        verify(view).showError(R.string.error_something_went_wrong);
        verifyNoMoreInteractions(view);
    }

    @Test
    public void testOnActivityCreated_WhenGetVersionThrowsIOError_ShowsError() throws Exception {
        Bundle arguments = mock(Bundle.class);
        when(api.getVersion()).thenReturn(Observable.error(ioException));

        presenter.onActivityCreated(null, arguments);

        verify(view).showError(R.string.error_io);
        verifyNoMoreInteractions(view);
    }

    @Test
    public void testOnActivityCreated_WhenGetChampionsThrowsIOError_ShowsError() throws Exception {
        Bundle arguments = mock(Bundle.class);
        when(api.getVersion()).thenReturn(Observable.just(riotRealm));
        when(api.getChampions(anyString())).thenReturn(Observable.error(ioException));

        presenter.onActivityCreated(null, arguments);

        verify(view).showError(R.string.error_io);
        verifyNoMoreInteractions(view);
    }

    @Test
    public void testOnOptionsItemSelected() {
        MenuItem item = mock(MenuItem.class);
        when(item.getItemId()).thenReturn(R.id.menu_settings);

        boolean result = presenter.onOptionsItemSelected(item);

        assertThat(result).isEqualTo(true);
        verify(view).showSettings();
        verifyNoMoreInteractions(view);
    }

    @Test
    public void testOnOptionsItemSelected_WhenItemNotHome_DoNothing() {
        MenuItem item = mock(MenuItem.class);
        when(item.getItemId()).thenReturn(0);

        boolean result = presenter.onOptionsItemSelected(item);

        assertThat(result).isEqualTo(false);
        verifyNoMoreInteractions(view);
    }

    @Test
    public void testOnItemClick() {
        String championId = "Riven";
        Champion champion = mock(Champion.class);
        when(champion.getId()).thenReturn(championId);

        presenter.onItemClick(champion);

        verify(view).showDetails(championId);
        verifyNoMoreInteractions(view);
    }
}
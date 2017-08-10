package com.leaguechampions.presenter;

import android.os.Bundle;
import android.view.MenuItem;

import com.leaguechampions.R;
import com.leaguechampions.datasource.remote.Api;
import com.leaguechampions.model.Champion;
import com.leaguechampions.model.RiotResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class ChampionsPresenterTest {

    private ChampionsPresenter presenter;

    private Map<String, Champion> data = new HashMap<String, Champion>() {{
        put("Riven", new Champion("Riven"));
        put("Jinx", new Champion("Jinx"));
    }};

    @Mock ChampionsPresenter.ChampionsViewable viewable;
    @Mock Api api;
    @Mock Call<RiotResponse> call;
    @Mock RiotResponse riotResponse;

    @Captor ArgumentCaptor<Callback<RiotResponse>> argumentCaptor;

    @Before
    public void setUp() {
        presenter = new ChampionsPresenter(api);
        presenter.setViewable(viewable);

        when(api.getChampions()).thenReturn(call);
        when(riotResponse.getData()).thenReturn(data);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testOnActivityCreated() {
        Bundle arguments = mock(Bundle.class);

        presenter.onActivityCreated(null, arguments);

        verify(api.getChampions()).enqueue(argumentCaptor.capture());
        argumentCaptor.getValue().onResponse(call,
                Response.success(riotResponse)
        );
        verify(viewable).setAdapter(riotResponse);
        verifyNoMoreInteractions(viewable);
    }

    @Test
    public void testOnActivityCreated_WhenResponseError400_ShowsError() throws Exception {
        Bundle arguments = mock(Bundle.class);

        presenter.onActivityCreated(null, arguments);

        verify(api.getChampions()).enqueue(argumentCaptor.capture());
        argumentCaptor.getValue().onResponse(call,
                Response.<RiotResponse>error(400, ResponseBody.create(MediaType.parse("application/json"), "{\"error\":\"failure\"}"))
        );
        verify(viewable).showError(R.string.error_code, 400);
        verifyNoMoreInteractions(viewable);
    }

    @Test
    public void testOnActivityCreated_WhenIOFailure_ShowsError() throws Exception {
        Bundle arguments = mock(Bundle.class);

        presenter.onActivityCreated(null, arguments);

        verify(api.getChampions()).enqueue(argumentCaptor.capture());
        argumentCaptor.getValue().onFailure(call,
                new IOException()
        );
        verify(viewable).showError(R.string.error_io);
        verifyNoMoreInteractions(viewable);
    }

    @Test
    public void testOnActivityCreated_WhenFailure_ShowsError() throws Exception {
        Bundle arguments = mock(Bundle.class);

        presenter.onActivityCreated(null, arguments);

        verify(api.getChampions()).enqueue(argumentCaptor.capture());
        argumentCaptor.getValue().onFailure(call,
                new Throwable()
        );
        verify(viewable).showError(R.string.error_something_went_wrong);
        verifyNoMoreInteractions(viewable);
    }

    @Test
    public void testOnOptionsItemSelected() {
        MenuItem item = mock(MenuItem.class);
        when(item.getItemId()).thenReturn(R.id.menu_settings);

        boolean result = presenter.onOptionsItemSelected(item);

        assertThat(result).isEqualTo(true);
        verify(viewable).showSettings();
        verifyNoMoreInteractions(viewable);
    }

    @Test
    public void testOnOptionsItemSelected_WhenItemNotHome_DoNothing() {
        MenuItem item = mock(MenuItem.class);
        when(item.getItemId()).thenReturn(0);

        boolean result = presenter.onOptionsItemSelected(item);

        assertThat(result).isEqualTo(false);
        verifyNoMoreInteractions(viewable);
    }
}
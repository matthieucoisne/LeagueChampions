package com.leaguechampions.presenter;

import android.os.Bundle;
import android.view.MenuItem;

import com.leaguechampions.model.Champion;
import com.leaguechampions.datasource.remote.Api;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Response.class})
public class ChampionDetailsPresenterTest {

    private ChampionDetailsPresenter presenter;

    @Mock ChampionDetailsPresenter.ChampionDetailsViewable viewable;
    @Mock Bundle bundle;
    @Mock Api api;
    @Mock Call<Champion> call;
    @Mock Champion champion;

    @Captor ArgumentCaptor<Callback<Champion>> argumentCaptor;

    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.initMocks(this);

        presenter = new ChampionDetailsPresenter(api);
        presenter.setViewable(viewable);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testOnActivityCreated() {
        when(api.getChampion(anyInt())).thenReturn(call);

        presenter.onActivityCreated(null, bundle);

        verify(call).enqueue(argumentCaptor.capture());
        argumentCaptor.getValue().onResponse(call,
                Response.success(champion)
        );
        verify(viewable).showDetails(anyString(), eq(champion));
        verifyNoMoreInteractions(viewable);
    }

    @Test
    public void testOnActivityCreated_WhenResponseError400_ShowsError() throws Exception {
        when(api.getChampion(anyInt())).thenReturn(call);

        presenter.onActivityCreated(null, bundle);

        verify(call).enqueue(argumentCaptor.capture());
        argumentCaptor.getValue().onResponse(call,
                Response.<Champion>error(400, ResponseBody.create(MediaType.parse("application/json"), "{\"error\":\"failure\"}"))
        );
        verify(viewable).showError("error 400");
        verifyNoMoreInteractions(viewable);
    }

    @Test
    public void testOnActivityCreated_WhenIOFailure_ShowsError() throws Exception {
        when(api.getChampion(anyInt())).thenReturn(call);

        presenter.onActivityCreated(null, bundle);

        verify(call).enqueue(argumentCaptor.capture());
        argumentCaptor.getValue().onFailure(call,
                new IOException()
        );
        verify(viewable).showError("io failure");
        verifyNoMoreInteractions(viewable);
    }

    @Test
    public void testOnActivityCreated_WhenFailure_ShowsError() throws Exception {
        when(api.getChampion(anyInt())).thenReturn(call);

        presenter.onActivityCreated(null, bundle);

        verify(call).enqueue(argumentCaptor.capture());
        argumentCaptor.getValue().onFailure(call,
                new Throwable()
        );
        verify(viewable).showError("failure");
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
}

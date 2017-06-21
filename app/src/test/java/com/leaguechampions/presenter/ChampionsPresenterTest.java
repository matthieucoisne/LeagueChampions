package com.leaguechampions.presenter;

import android.view.MenuItem;

import com.leaguechampions.R;
import com.leaguechampions.model.Champion;
import com.leaguechampions.model.Champions;
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
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Response.class})
public class ChampionsPresenterTest {

    private ChampionsPresenter presenter;

    private Map<String, Champion> data = new HashMap<String, Champion>() {{
        put("Riven", new Champion("Riven"));
        put("Jinx", new Champion("Jinx"));
    }};

    @Mock ChampionsPresenter.ChampionsViewable viewable;
    @Mock Api api;
    @Mock Call<Champions> call;
    @Mock Champions champions;

    @Captor ArgumentCaptor<Callback<Champions>> argumentCaptor;

    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.initMocks(this);

        presenter = new ChampionsPresenter(api);
        presenter.setViewable(viewable);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testOnActivityCreated() {
        when(api.getChampions()).thenReturn(call);
        when(champions.getData()).thenReturn(data);

        presenter.onActivityCreated(null, null);

        verify(call).enqueue(argumentCaptor.capture());
        argumentCaptor.getValue().onResponse(call,
                Response.success(champions)
        );
        verify(viewable).setAdapter(champions);
        verifyNoMoreInteractions(viewable);
    }

    @Test
    public void testOnActivityCreated_WhenResponseError400_ShowsError() throws Exception {
        when(api.getChampions()).thenReturn(call);

        presenter.onActivityCreated(null, null);

        verify(call).enqueue(argumentCaptor.capture());
        argumentCaptor.getValue().onResponse(call,
                Response.<Champions>error(400, ResponseBody.create(MediaType.parse("application/json"), "{\"error\":\"failure\"}"))
        );
        verify(viewable).showError("error 400");
        verifyNoMoreInteractions(viewable);
    }

    @Test
    public void testOnActivityCreated_WhenIOFailure_ShowsError() throws Exception {
        when(api.getChampions()).thenReturn(call);

        presenter.onActivityCreated(null, null);

        verify(call).enqueue(argumentCaptor.capture());
        argumentCaptor.getValue().onFailure(call,
                new IOException()
        );
        verify(viewable).showError("io failure");
        verifyNoMoreInteractions(viewable);
    }

    @Test
    public void testOnActivityCreated_WhenFailure_ShowsError() throws Exception {
        when(api.getChampions()).thenReturn(call);

        presenter.onActivityCreated(null, null);

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
        when(item.getItemId()).thenReturn(R.id.menu_settings);

        presenter.onOptionsItemSelected(item);

        verify(viewable).showSettings();
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
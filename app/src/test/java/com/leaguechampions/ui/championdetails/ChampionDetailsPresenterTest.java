package com.leaguechampions.ui.championdetails;

import android.os.Bundle;
import android.view.MenuItem;

import com.leaguechampions.R;
import com.leaguechampions.data.local.Const;
import com.leaguechampions.data.model.Champion;
import com.leaguechampions.data.model.RiotRealm;
import com.leaguechampions.data.model.RiotResponse;
import com.leaguechampions.data.remote.Api;
import com.leaguechampions.utils.ImmediateSchedulersRule;
import com.leaguechampions.utils.ReflectionUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class ChampionDetailsPresenterTest {

    private ChampionDetailsPresenter presenter;

    private final String fieldChampionId = "championId";
    private final String championId = "Riven";

    private final HttpException httpException = new HttpException(Response.error(400, ResponseBody.create(MediaType.parse("application/json"), "{\"error\":\"failure\"}")));
    private final IOException ioException = new IOException();

    @Mock ChampionDetailsPresenter.ChampionDetailsView view;
    @Mock Api api;

    @Mock RiotResponse riotResponse;
    @Mock RiotRealm riotRealm;
    @Mock Champion champion;
    @Mock Map<String, Champion> data;

    @Rule ImmediateSchedulersRule rule = new ImmediateSchedulersRule();

    @Before
    public void setUp() {
        presenter = new ChampionDetailsPresenter(view, api);

        when(riotRealm.getVersion()).thenReturn("1.0.0");
        when(riotResponse.getData()).thenReturn(data);
        when(riotResponse.getData().get(championId)).thenReturn(champion);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testOnActivityCreated() {
        Bundle arguments = mock(Bundle.class);
        when(arguments.getString(Const.KEY_CHAMPION_ID)).thenReturn(championId);
        when(api.getVersion()).thenReturn(Observable.just(riotRealm));
        when(api.getChampion(anyString(), eq(championId))).thenReturn(Observable.just(riotResponse));

        presenter.onActivityCreated(null, arguments);

        assertThat(ReflectionUtils.getField(presenter, fieldChampionId)).isEqualTo(championId);
        verify(view).showDetails(champion);
        verifyNoMoreInteractions(view);
    }

    @Test
    public void testOnActivityCreated_WhenHasSavedInstanceState_RetrievesValuesFromSavedState() {
        Bundle arguments = mock(Bundle.class);
        Bundle savedInstanceState = mock(Bundle.class);
        when(savedInstanceState.getString(Const.KEY_CHAMPION_ID)).thenReturn(championId);
        when(api.getVersion()).thenReturn(Observable.just(riotRealm));
        when(api.getChampion(anyString(), eq(championId))).thenReturn(Observable.just(riotResponse));

        presenter.onActivityCreated(savedInstanceState, arguments);

        assertThat(ReflectionUtils.getField(presenter, fieldChampionId)).isEqualTo(championId);
        verify(view).showDetails(champion);
        verifyNoMoreInteractions(view);
    }

    @Test
    public void testOnActivityCreated_WhenGetVersionThrowsError400_ShowsError() throws Exception {
        Bundle arguments = mock(Bundle.class);
        when(arguments.getString(Const.KEY_CHAMPION_ID)).thenReturn(championId);
        when(api.getVersion()).thenReturn(Observable.error(httpException));

        presenter.onActivityCreated(null, arguments);

        assertThat(ReflectionUtils.getField(presenter, fieldChampionId)).isEqualTo(championId);
        verify(view).showError(R.string.error_something_went_wrong);
        verifyNoMoreInteractions(view);
    }

    @Test
    public void testOnActivityCreated_WhenGetChampionDetailsThrowsError400_ShowsError() throws Exception {
        Bundle arguments = mock(Bundle.class);
        when(arguments.getString(Const.KEY_CHAMPION_ID)).thenReturn(championId);
        when(api.getVersion()).thenReturn(Observable.just(riotRealm));
        when(api.getChampion(anyString(), eq(championId))).thenReturn(Observable.error(httpException));

        presenter.onActivityCreated(null, arguments);

        assertThat(ReflectionUtils.getField(presenter, fieldChampionId)).isEqualTo(championId);
        verify(view).showError(R.string.error_something_went_wrong);
        verifyNoMoreInteractions(view);
    }

    @Test
    public void testOnActivityCreated_WhenGetVersionThrowsIOError_ShowsError() throws Exception {
        Bundle arguments = mock(Bundle.class);
        when(arguments.getString(Const.KEY_CHAMPION_ID)).thenReturn(championId);
        when(api.getVersion()).thenReturn(Observable.error(ioException));

        presenter.onActivityCreated(null, arguments);

        assertThat(ReflectionUtils.getField(presenter, fieldChampionId)).isEqualTo(championId);
        verify(view).showError(R.string.error_io);
        verifyNoMoreInteractions(view);
    }

    @Test
    public void testOnActivityCreated_WhenGetChampionDetailsThrowsIOError_ShowsError() throws Exception {
        Bundle arguments = mock(Bundle.class);
        when(arguments.getString(Const.KEY_CHAMPION_ID)).thenReturn(championId);
        when(api.getVersion()).thenReturn(Observable.just(riotRealm));
        when(api.getChampion(anyString(), eq(championId))).thenReturn(Observable.error(ioException));

        presenter.onActivityCreated(null, arguments);

        assertThat(ReflectionUtils.getField(presenter, fieldChampionId)).isEqualTo(championId);
        verify(view).showError(R.string.error_io);
        verifyNoMoreInteractions(view);
    }

    @Test
    public void testOnSaveInstanceState() {
        ReflectionUtils.setField(presenter, fieldChampionId, championId);
        Bundle bundle = mock(Bundle.class);

        presenter.onSaveInstanceState(bundle);

        verify(bundle).putString(Const.KEY_CHAMPION_ID, championId);
        verifyNoMoreInteractions(bundle, view);
    }

    @Test
    public void testOnOptionsItemSelected() {
        MenuItem item = mock(MenuItem.class);
        when(item.getItemId()).thenReturn(android.R.id.home);

        boolean result = presenter.onOptionsItemSelected(item);

        assertThat(result).isEqualTo(true);
        verify(view).doFinish();
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
}

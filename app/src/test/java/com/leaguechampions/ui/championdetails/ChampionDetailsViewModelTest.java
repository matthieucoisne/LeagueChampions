package com.leaguechampions.ui.championdetails;

import android.arch.lifecycle.MutableLiveData;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class ChampionDetailsViewModelTest {

    private ChampionDetailsViewModel viewModel;

    private final String fieldChampionId = "championId";
    private final String fieldVersion = "version";

    private final MutableLiveData<String> championId = new MutableLiveData<>();
    private final String version = "1.0";

//    @Rule public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();
//
//    @Mock ChampionRepository championRepository;
//    @Mock Api api;
//    @Mock Call<RiotResponse> call;
//    @Mock RiotResponse riotResponse;
//    @Mock Champion champion;
//    @Mock Map<String, Champion> data;
//
//    @Captor
//    ArgumentCaptor<Callback<RiotResponse>> argumentCaptor;

    @Before
    public void setUp() {
//        viewModel = new ChampionDetailsViewModel(championRepository);
//
//        when(api.getChampion(anyString())).thenReturn(call);
//        when(riotResponse.getData()).thenReturn(data);
//        when(riotResponse.getData().get(championId)).thenReturn(champion);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testSetChampionId() {
//        assertThat(viewModel.getRiotResponse(), notNullValue());
//        verify(championRepository, never()).getChampionDetails(anyString());
//        viewModel.setChampionId("Riven");
//        verify(championRepository, never()).getChampionDetails(anyString());
//
////        Assertions.assertThat(ReflectionUtils.getField(viewModel, fieldChampionId)).isEqualTo("Riven");
    }

//    @Test
//    public void testOnActivityCreated() {
//        Bundle arguments = mock(Bundle.class);
//        when(arguments.getString(Const.KEY_CHAMPION_ID)).thenReturn(championId);
//        when(arguments.getString(Const.KEY_VERSION)).thenReturn(version);
//
//        presenter.onActivityCreated(null, arguments);
//
//        assertThat(ReflectionUtils.getField(presenter, fieldChampionId)).isEqualTo(championId);
//        assertThat(ReflectionUtils.getField(presenter, fieldVersion)).isEqualTo(version);
//        verify(api.getChampion(anyString())).enqueue(argumentCaptor.capture());
//        argumentCaptor.getValue().onResponse(call,
//                Response.success(riotResponse)
//        );
//        verify(view).showDetails(anyString(), eq(champion));
//        verifyNoMoreInteractions(view);
//    }
//
//    @Test
//    public void testOnActivityCreated_WhenHasSavedInstanceState_RetrievesValuesFromSavedState() {
//        Bundle arguments = mock(Bundle.class);
//        Bundle savedInstanceState = mock(Bundle.class);
//        when(savedInstanceState.getString(Const.KEY_CHAMPION_ID)).thenReturn(championId);
//        when(savedInstanceState.getString(Const.KEY_VERSION)).thenReturn(version);
//
//        presenter.onActivityCreated(savedInstanceState, arguments);
//
//        assertThat(ReflectionUtils.getField(presenter, fieldChampionId)).isEqualTo(championId);
//        assertThat(ReflectionUtils.getField(presenter, fieldVersion)).isEqualTo(version);
//        verify(api.getChampion(anyString())).enqueue(argumentCaptor.capture());
//        argumentCaptor.getValue().onResponse(call,
//                Response.success(riotResponse)
//        );
//        verify(view).showDetails(anyString(), eq(champion));
//        verifyNoMoreInteractions(view);
//    }
//
//    @Test
//    public void testOnActivityCreated_WhenResponseError400_ShowsError() throws Exception {
//        Bundle arguments = mock(Bundle.class);
//        when(arguments.getString(Const.KEY_CHAMPION_ID)).thenReturn(championId);
//        when(arguments.getString(Const.KEY_VERSION)).thenReturn(version);
//
//        presenter.onActivityCreated(null, arguments);
//
//        assertThat(ReflectionUtils.getField(presenter, fieldChampionId)).isEqualTo(championId);
//        assertThat(ReflectionUtils.getField(presenter, fieldVersion)).isEqualTo(version);
//        verify(api.getChampion(anyString())).enqueue(argumentCaptor.capture());
//        argumentCaptor.getValue().onResponse(call,
//                Response.<RiotResponse>error(400, ResponseBody.create(MediaType.parse("application/json"), "{\"error\":\"failure\"}"))
//        );
//        verify(view).showError(R.string.error_code, 400);
//        verifyNoMoreInteractions(view);
//    }
//
//    @Test
//    public void testOnActivityCreated_WhenIOFailure_ShowsError() throws Exception {
//        Bundle arguments = mock(Bundle.class);
//        when(arguments.getString(Const.KEY_CHAMPION_ID)).thenReturn(championId);
//        when(arguments.getString(Const.KEY_VERSION)).thenReturn(version);
//
//        presenter.onActivityCreated(null, arguments);
//
//        assertThat(ReflectionUtils.getField(presenter, fieldChampionId)).isEqualTo(championId);
//        assertThat(ReflectionUtils.getField(presenter, fieldVersion)).isEqualTo(version);
//        verify(api.getChampion(anyString())).enqueue(argumentCaptor.capture());
//        argumentCaptor.getValue().onFailure(call,
//                new IOException()
//        );
//        verify(view).showError(R.string.error_io);
//        verifyNoMoreInteractions(view);
//    }
//
//    @Test
//    public void testOnActivityCreated_WhenFailure_ShowsError() throws Exception {
//        Bundle arguments = mock(Bundle.class);
//        when(arguments.getString(Const.KEY_CHAMPION_ID)).thenReturn(championId);
//        when(arguments.getString(Const.KEY_VERSION)).thenReturn(version);
//
//        presenter.onActivityCreated(null, arguments);
//
//        assertThat(ReflectionUtils.getField(presenter, fieldChampionId)).isEqualTo(championId);
//        assertThat(ReflectionUtils.getField(presenter, fieldVersion)).isEqualTo(version);
//        verify(api.getChampion(anyString())).enqueue(argumentCaptor.capture());
//        argumentCaptor.getValue().onFailure(call,
//                new Throwable()
//        );
//        verify(view).showError(R.string.error_something_went_wrong);
//        verifyNoMoreInteractions(view);
//    }
//
//    @Test
//    public void testOnSaveInstanceState() {
//        ReflectionUtils.setField(presenter, fieldChampionId, championId);
//        ReflectionUtils.setField(presenter, fieldVersion, version);
//        Bundle bundle = mock(Bundle.class);
//
//        presenter.onSaveInstanceState(bundle);
//
//        verify(bundle).putString(Const.KEY_CHAMPION_ID, championId);
//        verify(bundle).putString(Const.KEY_VERSION, version);
//        verifyNoMoreInteractions(bundle, view);
//    }
//
//    @Test
//    public void testOnOptionsItemSelected() {
//        MenuItem item = mock(MenuItem.class);
//        when(item.getItemId()).thenReturn(android.R.id.home);
//
//        boolean result = presenter.onOptionsItemSelected(item);
//
//        assertThat(result).isEqualTo(true);
//        verify(view).doFinish();
//        verifyNoMoreInteractions(view);
//    }
//
//    @Test
//    public void testOnOptionsItemSelected_WhenItemNotHome_DoNothing() {
//        MenuItem item = mock(MenuItem.class);
//        when(item.getItemId()).thenReturn(0);
//
//        boolean result = presenter.onOptionsItemSelected(item);
//
//        assertThat(result).isEqualTo(false);
//        verifyNoMoreInteractions(view);
//    }
}

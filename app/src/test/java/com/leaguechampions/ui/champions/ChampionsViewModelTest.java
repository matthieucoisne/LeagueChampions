package com.leaguechampions.ui.champions;

import com.leaguechampions.data.model.Champion;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(PowerMockRunner.class)
public class ChampionsViewModelTest {

    private ChampionsViewModel viewModel;

    private Map<String, Champion> data = new HashMap<String, Champion>() {{
        put("Riven", new Champion("Riven"));
        put("Jinx", new Champion("Jinx"));
    }};

//    @Rule
//    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();
//
//    @Mock ChampionRepository championRepository;
//    @Mock Api api;
//    @Mock Call<RiotResponse> call;
//    @Mock RiotResponse riotResponse;
//
//    @Captor ArgumentCaptor<Callback<RiotResponse>> argumentCaptor;

    @Before
    public void setUp() {
//        viewModel = new ChampionsViewModel(championRepository);
//
//        when(api.getChampions()).thenReturn(call);
//        when(riotResponse.getData()).thenReturn(data);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testSetChampionId() {
//        verify(championRepository).getChampions();
    }

//    @Test
//    public void testOnActivityCreated() {
//        Bundle arguments = mock(Bundle.class);
//
//        presenter.onActivityCreated(null, arguments);
//
//        verify(api.getChampions()).enqueue(argumentCaptor.capture());
//        argumentCaptor.getValue().onResponse(call,
//                Response.success(riotResponse)
//        );
//        verify(view).setAdapter(riotResponse);
//        verifyNoMoreInteractions(view);
//    }
//
//    @Test
//    public void testOnActivityCreated_WhenResponseError400_ShowsError() throws Exception {
//        Bundle arguments = mock(Bundle.class);
//
//        presenter.onActivityCreated(null, arguments);
//
//        verify(api.getChampions()).enqueue(argumentCaptor.capture());
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
//
//        presenter.onActivityCreated(null, arguments);
//
//        verify(api.getChampions()).enqueue(argumentCaptor.capture());
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
//
//        presenter.onActivityCreated(null, arguments);
//
//        verify(api.getChampions()).enqueue(argumentCaptor.capture());
//        argumentCaptor.getValue().onFailure(call,
//                new Throwable()
//        );
//        verify(view).showError(R.string.error_something_went_wrong);
//        verifyNoMoreInteractions(view);
//    }
//
//    @Test
//    public void testOnOptionsItemSelected() {
//        MenuItem item = mock(MenuItem.class);
//        when(item.getItemId()).thenReturn(R.id.menu_settings);
//
//        boolean result = presenter.onOptionsItemSelected(item);
//
//        assertThat(result).isEqualTo(true);
//        verify(view).showSettings();
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
//
//    @Test
//    public void testOnItemClick() {
//        String version = "7.15.1";
//        String championId = "Riven";
//        Champion champion = mock(Champion.class);
//        when(champion.getId()).thenReturn(championId);
//
//        presenter.onItemClick(version, champion);
//
//        verify(view).showDetails(version, championId);
//        verifyNoMoreInteractions(view);
//    }
}
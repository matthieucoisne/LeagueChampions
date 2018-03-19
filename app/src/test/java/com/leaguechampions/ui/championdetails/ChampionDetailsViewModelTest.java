package com.leaguechampions.ui.championdetails;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Observer;

import com.leaguechampions.data.repository.Resource;
import com.leaguechampions.data.model.RiotResponse;
import com.leaguechampions.data.repository.ChampionRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ChampionDetailsViewModelTest {

    @Rule public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Mock ChampionRepository championRepository;
    @Mock Observer<Resource<RiotResponse>> observer;

    @Captor ArgumentCaptor<String> stringCaptor;

    private ChampionDetailsViewModel viewModel;
    private String championId = "Riven";

    @Before
    public void setUp() {
        viewModel = new ChampionDetailsViewModel(championRepository);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testSetChampionId_WhenChampionIdIsNull_DoNothing() {
        viewModel.getRiotResponse().observeForever(observer);

        viewModel.setChampionId(null);

        verify(championRepository, never()).getChampionDetails(anyString());
    }

    @Test
    public void testSetChampionId_WhenChampionIdIsEqual_GetChampionDetailsOnlyOnce() {
        viewModel.getRiotResponse().observeForever(observer);

        viewModel.setChampionId(championId);
        viewModel.setChampionId(championId);

        verify(championRepository, times(1)).getChampionDetails(stringCaptor.capture());
        assertThat(stringCaptor.getValue(), is(championId));
    }

    @Test
    public void testSetChampionId_WhenChampionIdIsDifferent_GetChampionDetails() {
        viewModel.getRiotResponse().observeForever(observer);

        viewModel.setChampionId(championId);

        verify(championRepository).getChampionDetails(stringCaptor.capture());
        assertThat(stringCaptor.getValue(), is(championId));

        reset(championRepository);

        viewModel.setChampionId("Jinx");

        verify(championRepository).getChampionDetails(stringCaptor.capture());
        assertThat(stringCaptor.getValue(), is("Jinx"));
    }
}

package com.leaguechampions.ui.championdetails;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import com.leaguechampions.data.repository.ChampionRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ChampionDetailsViewModelTest {

    private ChampionDetailsViewModel viewModel;

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Mock ChampionRepository championRepository;

//    @Captor
//    ArgumentCaptor<Callback<RiotResponse>> argumentCaptor;

    @Before
    public void setUp() {
        viewModel = new ChampionDetailsViewModel(championRepository);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testSetChampionId() {
        assertThat(viewModel.getRiotResponse(), notNullValue());
        verify(championRepository, never()).getChampionDetails(anyString());

        viewModel.setChampionId("Riven");

        verify(championRepository, never()).getChampionDetails(anyString());
    }
}

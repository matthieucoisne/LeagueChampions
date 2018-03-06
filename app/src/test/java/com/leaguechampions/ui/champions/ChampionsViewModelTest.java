package com.leaguechampions.ui.champions;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import com.leaguechampions.data.repository.ChampionRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
public class ChampionsViewModelTest {

    @Rule public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Mock ChampionRepository championRepository;

    private ChampionsViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = spy(new ChampionsViewModel(championRepository));
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testConstructor_WhenRiotResponseIsNull_GetChampions() {
        verify(championRepository).getChampions();
        verifyNoMoreInteractions(viewModel, championRepository);
    }
}

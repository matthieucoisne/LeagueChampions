package com.leaguechampions.ui.champions;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import com.leaguechampions.data.model.Champion;
import com.leaguechampions.data.repository.ChampionRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ChampionsViewModelTest {

    private ChampionsViewModel viewModel;

    private Map<String, Champion> data = new HashMap<String, Champion>() {{
        put("Riven", new Champion("Riven"));
        put("Jinx", new Champion("Jinx"));
    }};

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Mock ChampionRepository championRepository;

//    @Captor ArgumentCaptor<Callback<RiotResponse>> argumentCaptor;

    @Before
    public void setUp() {
        viewModel = new ChampionsViewModel(championRepository);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testSetChampionId() {
        verify(championRepository).getChampions();
    }
}
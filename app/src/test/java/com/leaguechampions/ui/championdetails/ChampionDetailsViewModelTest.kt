package com.leaguechampions.ui.championdetails

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.leaguechampions.data.model.Champion
import com.leaguechampions.data.repository.ChampionRepository
import com.leaguechampions.utils.Resource
import com.leaguechampions.utils.Status
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(ChampionRepository::class, Champion::class)
class ChampionDetailsViewModelTest {

    private val championId = "Riven"
    private val champion = Champion(championId, "", "", "", "")
    private val error = "error"
    private val viewStateLoading = ChampionDetailsViewModel.ViewState(status = Status.LOADING)
    private val viewStateSuccess = ChampionDetailsViewModel.ViewState(status = Status.SUCCESS, champion = champion)
    private val viewStateError = ChampionDetailsViewModel.ViewState(status = Status.ERROR, error = error)

    private lateinit var viewModel: ChampionDetailsViewModel

    @Mock private lateinit var repository: ChampionRepository
    @Mock private lateinit var observerViewState: Observer<ChampionDetailsViewModel.ViewState>

    @Rule var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = ChampionDetailsViewModel(repository)
        viewModel.viewState.observeForever(observerViewState)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun testSetChampionId_loading() {
        val resource = MutableLiveData<Resource<Champion>>()
        resource.value = Resource.loading()
        `when`(repository.getChampionDetails(championId)).thenReturn(resource)

        viewModel.setChampionId(championId)

        verify(repository).getChampionDetails(championId)
        assertThat(viewModel.viewState.value).isEqualTo(viewStateLoading)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun testSetChampionId_success() {
        val resource = MutableLiveData<Resource<Champion>>()
        resource.value = Resource.success(champion)
        `when`(repository.getChampionDetails(championId)).thenReturn(resource)

        viewModel.setChampionId(championId)

        verify(repository).getChampionDetails(championId)
        assertThat(viewModel.viewState.value).isEqualTo(viewStateSuccess)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun testSetChampionId_error() {
        val resource = MutableLiveData<Resource<Champion>>()
        resource.value = Resource.error(error)
        `when`(repository.getChampionDetails(championId)).thenReturn(resource)

        viewModel.setChampionId(championId)

        verify(repository).getChampionDetails(championId)
        assertThat(viewModel.viewState.value).isEqualTo(viewStateError)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun testSetChampionId_null() {
        viewModel.setChampionId(null)

        verify(repository, never()).getChampionDetails(anyString())
        assertThat(viewModel.viewState.value).isEqualTo(null)
        verifyNoMoreInteractions(repository)
    }
}
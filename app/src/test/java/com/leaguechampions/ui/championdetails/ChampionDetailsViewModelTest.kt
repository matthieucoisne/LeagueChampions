package com.leaguechampions.ui.championdetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.leaguechampions.core.data.model.Champion
import com.leaguechampions.core.data.repository.ChampionRepository
import com.leaguechampions.core.utils.Resource
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
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ChampionDetailsViewModelTest {

    private val championId = "Riven"
    private val champion = com.leaguechampions.core.data.model.Champion(championId, "", "", "", "")
    private val error = "error"
    private val viewStateLoading = com.leaguechampions.features.champions.championdetails.ChampionDetailsViewModel.ViewState(status = Status.LOADING)
    private val viewStateSuccess = com.leaguechampions.features.champions.championdetails.ChampionDetailsViewModel.ViewState(status = Status.SUCCESS, champion = champion)
    private val viewStateError = com.leaguechampions.features.champions.championdetails.ChampionDetailsViewModel.ViewState(status = Status.ERROR, error = error)

    private lateinit var viewModel: com.leaguechampions.features.champions.championdetails.ChampionDetailsViewModel

    @Mock private lateinit var repository: com.leaguechampions.core.data.repository.ChampionRepository
    @Mock private lateinit var observerViewState: Observer<com.leaguechampions.features.champions.championdetails.ChampionDetailsViewModel.ViewState>

    @get:Rule var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = com.leaguechampions.features.champions.championdetails.ChampionDetailsViewModel(repository)
        viewModel.viewState.observeForever(observerViewState)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun testSetChampionId_loading() {
        val resource = MutableLiveData<com.leaguechampions.core.utils.Resource<com.leaguechampions.core.data.model.Champion>>()
        resource.value = com.leaguechampions.core.utils.Resource.loading()
        `when`(repository.getChampionDetails(championId)).thenReturn(resource)

        viewModel.setChampionId(championId)

        verify(repository).getChampionDetails(championId)
        assertThat(viewModel.viewState.value).isEqualTo(viewStateLoading)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun testSetChampionId_success() {
        val resource = MutableLiveData<com.leaguechampions.core.utils.Resource<com.leaguechampions.core.data.model.Champion>>()
        resource.value = com.leaguechampions.core.utils.Resource.success(champion)
        `when`(repository.getChampionDetails(championId)).thenReturn(resource)

        viewModel.setChampionId(championId)

        verify(repository).getChampionDetails(championId)
        assertThat(viewModel.viewState.value).isEqualTo(viewStateSuccess)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun testSetChampionId_error() {
        val resource = MutableLiveData<com.leaguechampions.core.utils.Resource<com.leaguechampions.core.data.model.Champion>>()
        resource.value = com.leaguechampions.core.utils.Resource.error(error)
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

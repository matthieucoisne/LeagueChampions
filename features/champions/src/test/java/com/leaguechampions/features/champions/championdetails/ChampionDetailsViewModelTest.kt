package com.leaguechampions.features.champions.championdetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.leaguechampions.libraries.core.data.model.Champion
import com.leaguechampions.libraries.core.data.repository.ChampionRepository
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ChampionDetailsViewModelTest {

    private val championId = "Riven"
    private val champion = Champion(championId, "", "", "", "")
    private val error = "error"
//    private val viewStateLoading = ChampionDetailsViewModel.ViewState(status = Status.LOADING)
//    private val viewStateSuccess = ChampionDetailsViewModel.ViewState(status = Status.SUCCESS, champion = champion)
//    private val viewStateError = ChampionDetailsViewModel.ViewState(status = Status.ERROR, error = error)

    private lateinit var viewModel: ChampionDetailsViewModel

    @Mock private lateinit var repository: ChampionRepository
    @Mock private lateinit var observerViewState: Observer<ChampionDetailsViewModel.ViewState>

    @get:Rule var rule: TestRule = InstantTaskExecutorRule()

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
//        val resource = MutableLiveData<Resource<Champion>>()
//        resource.value = Resource.Loading()
//        `when`(repository.getChampion(championId)).thenReturn(resource)
//
//        viewModel.setChampionId(championId)
//
//        verify(repository).getChampion(championId)
//        assertThat(viewModel.viewState.value).isEqualTo(viewStateLoading)
//        verifyNoMoreInteractions(repository)
    }

//    @Test
//    fun testSetChampionId_success() {
//        val resource = MutableLiveData<Resource<Champion>>()
//        resource.value = Resource.success(champion)
//        `when`(repository.getChampion(championId)).thenReturn(resource)
//
//        viewModel.setChampionId(championId)
//
//        verify(repository).getChampion(championId)
//        assertThat(viewModel.viewState.value).isEqualTo(viewStateSuccess)
//        verifyNoMoreInteractions(repository)
//    }
//
//    @Test
//    fun testSetChampionId_error() {
//        val resource = MutableLiveData<Resource<Champion>>()
//        resource.value = com.leaguechampions.libraries.core.utils.Resource.error(error)
//        `when`(repository.getChampion(championId)).thenReturn(resource)
//
//        viewModel.setChampionId(championId)
//
//        verify(repository).getChampion(championId)
//        assertThat(viewModel.viewState.value).isEqualTo(viewStateError)
//        verifyNoMoreInteractions(repository)
//    }
//
//    @Test
//    fun testSetChampionId_null() {
//        viewModel.setChampionId(null)
//
//        verify(repository, never()).getChampion(anyString())
//        assertThat(viewModel.viewState.value).isEqualTo(null)
//        verifyNoMoreInteractions(repository)
//    }
}
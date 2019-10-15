package com.leaguechampions.features.champions.domain.usecase

import com.leaguechampions.features.champions.domain.repository.ChampionRepository
import com.leaguechampions.libraries.core.domain.repository.RiotRepository
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetChampionDetailsUserCaseTest {

    private lateinit var useCase: GetChampionDetailsUseCase

    @Mock private lateinit var riotRepository: RiotRepository
    @Mock private lateinit var championRepository: ChampionRepository

    @Before
    fun setUp() {
        useCase = GetChampionDetailsUseCase(riotRepository, championRepository)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun `test usecase`() {
//        useCase.execute("")
    }
}

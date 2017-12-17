package com.leaguechampions.ui.champions

import android.os.Bundle
import android.view.MenuItem
import com.leaguechampions.R
import com.leaguechampions.data.model.Champion
import com.leaguechampions.data.model.RiotResponse
import com.leaguechampions.data.remote.Api
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

@RunWith(PowerMockRunner::class)
@PrepareForTest(RiotResponse::class, Champion::class)
class ChampionsPresenterTest {

    private val data = hashMapOf(
            "Riven" to Champion("", "Riven", "", ""),
            "Jinx" to Champion("", "Jinx", "", "")
    )

    private lateinit var presenter: ChampionsPresenter

    @Mock private lateinit var view: ChampionsPresenter.ChampionsView
    @Mock private lateinit var api: Api
    @Mock private lateinit var call: Call<RiotResponse>
    @Mock private lateinit var riotResponse: RiotResponse
    @Mock private lateinit var champion: Champion

    @Captor lateinit var argumentCaptor: ArgumentCaptor<Callback<RiotResponse>>

    @Before
    fun setUp() {
        presenter = ChampionsPresenter(view, api)

        `when`(api.getChampions()).thenReturn(call)
        `when`(riotResponse.data).thenReturn(data)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun testOnActivityCreated() {
        val arguments = mock(Bundle::class.java)

        presenter.onActivityCreated(null, arguments)

        verify(api.getChampions()).enqueue(argumentCaptor.capture())
        argumentCaptor.value.onResponse(call,
                Response.success(riotResponse)
        )
        verify(view).setAdapter(riotResponse)
        verifyNoMoreInteractions(view)
    }

    @Test
    fun testOnActivityCreated_WhenResponseError400_ShowsError() {
        val arguments = mock(Bundle::class.java)

        presenter.onActivityCreated(null, arguments)

        verify(api.getChampions()).enqueue(argumentCaptor.capture())
        argumentCaptor.value.onResponse(call,
                Response.error(400, ResponseBody.create(MediaType.parse("application/json"), "{\"error\":\"failure\"}"))
        )
        verify(view).showError(R.string.error_code, 400)
        verifyNoMoreInteractions(view)
    }

    @Test
    fun testOnActivityCreated_WhenIOFailure_ShowsError() {
        val arguments = mock(Bundle::class.java)

        presenter.onActivityCreated(null, arguments)

        verify(api.getChampions()).enqueue(argumentCaptor.capture())
        argumentCaptor.value.onFailure(call,
                IOException()
        )
        verify(view).showError(R.string.error_io)
        verifyNoMoreInteractions(view)
    }

    @Test
    fun testOnActivityCreated_WhenFailure_ShowsError() {
        val arguments = mock(Bundle::class.java)

        presenter.onActivityCreated(null, arguments)

        verify(api.getChampions()).enqueue(argumentCaptor.capture())
        argumentCaptor.value.onFailure(call,
                Throwable()
        )
        verify(view).showError(R.string.error_something_went_wrong)
        verifyNoMoreInteractions(view)
    }

    @Test
    fun testOnOptionsItemSelected() {
        val item = mock(MenuItem::class.java)
        `when`(item.itemId).thenReturn(R.id.menu_settings)

        val result = presenter.onOptionsItemSelected(item)

        assertThat(result).isEqualTo(true)
        verify(view).showSettings()
        verifyNoMoreInteractions(view)
    }

    @Test
    fun testOnOptionsItemSelected_WhenItemNotHome_DoNothing() {
        val item = mock(MenuItem::class.java)
        `when`(item.itemId).thenReturn(0)

        val result = presenter.onOptionsItemSelected(item)

        assertThat(result).isEqualTo(false)
        verifyNoMoreInteractions(view)
    }

    @Test
    fun testOnItemClick() {
        val version = "7.15.1"
        val championId = "Riven"
        `when`(champion.id).thenReturn(championId)

        presenter.onItemClick(version, champion)

        verify(view).showDetails(version, championId)
        verifyNoMoreInteractions(view)
    }
}
package com.leaguechampions.ui.championdetails

import org.junit.After
import org.junit.Before
import org.junit.Test

//@RunWith(PowerMockRunner::class)
//@PrepareForTest(RiotResponse::class, Champion::class)
class ChampionDetailsPresenterTest {

//    private val fieldChampionId = "championId"
//    private val fieldVersion = "version"
//
//    private val championId = "Riven"
//    private val version = "1.0"
//
//    private lateinit var presenter: ChampionDetailsPresenter
//
//    @Mock private lateinit var view: ChampionDetailsPresenter.ChampionDetailsView
//    @Mock private lateinit var api: Api
//    @Mock private lateinit var call: Call<RiotResponse>
//    @Mock private lateinit var riotResponse: RiotResponse
//    @Mock private lateinit var champion: Champion
//    @Mock private lateinit var data: Map<String, Champion>
//
//    @Captor private lateinit var argumentCaptor: ArgumentCaptor<Callback<RiotResponse>>
//
    @Before
    fun setUp() {
//        presenter = ChampionDetailsPresenter(view, api)
//
//        `when`(api.getChampion(anyString())).thenReturn(call)
//        `when`(riotResponse.data).thenReturn(data)
//        `when`(riotResponse.data[championId]).thenReturn(champion)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun delete_me() {

    }

//    @Test
//    fun testOnActivityCreated() {
//        val arguments = mock(Bundle::class.java)
//        `when`(arguments.getString(Const.KEY_CHAMPION_ID)).thenReturn(championId)
//        `when`(arguments.getString(Const.KEY_VERSION)).thenReturn(version)
//
//        presenter.onActivityCreated(null, arguments)
//
//        assertThat(ReflectionUtils.getField(presenter, fieldChampionId)).isEqualTo(championId)
//        assertThat(ReflectionUtils.getField(presenter, fieldVersion)).isEqualTo(version)
//        verify(api.getChampion(championId)).enqueue(argumentCaptor.capture())
//        argumentCaptor.value.onResponse(call,
//                Response.success(riotResponse)
//        )
//        verify(view).showDetails(version, champion)
//        verifyNoMoreInteractions(view)
//    }
//
//    @Test
//    fun testOnActivityCreated_WhenHasSavedInstanceState_RetrievesValuesFromSavedState() {
//        val arguments = mock(Bundle::class.java)
//        val savedInstanceState = mock(Bundle::class.java)
//        `when`(savedInstanceState.getString(Const.KEY_CHAMPION_ID)).thenReturn(championId)
//        `when`(savedInstanceState.getString(Const.KEY_VERSION)).thenReturn(version)
//
//        presenter.onActivityCreated(savedInstanceState, arguments)
//
//        assertThat(ReflectionUtils.getField(presenter, fieldChampionId)).isEqualTo(championId)
//        assertThat(ReflectionUtils.getField(presenter, fieldVersion)).isEqualTo(version)
//        verify(api.getChampion(championId)).enqueue(argumentCaptor.capture())
//        argumentCaptor.value.onResponse(call,
//                Response.success(riotResponse)
//        )
//        verify(view).showDetails(version, champion)
//        verifyNoMoreInteractions(view)
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun testOnActivityCreated_WhenResponseError400_ShowsError() {
//        val arguments = mock(Bundle::class.java)
//        `when`(arguments.getString(Const.KEY_CHAMPION_ID)).thenReturn(championId)
//        `when`(arguments.getString(Const.KEY_VERSION)).thenReturn(version)
//
//        presenter.onActivityCreated(null, arguments)
//
//        assertThat(ReflectionUtils.getField(presenter, fieldChampionId)).isEqualTo(championId)
//        assertThat(ReflectionUtils.getField(presenter, fieldVersion)).isEqualTo(version)
//        verify(api.getChampion(championId)).enqueue(argumentCaptor.capture())
//        argumentCaptor.value.onResponse(call,
//                Response.error(400, ResponseBody.create(MediaType.parse("application/json"), "{\"error\":\"failure\"}"))
//        )
//        verify(view).showError(R.string.error_code, 400)
//        verifyNoMoreInteractions(view)
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun testOnActivityCreated_WhenIOFailure_ShowsError() {
//        val arguments = mock(Bundle::class.java)
//        `when`(arguments.getString(Const.KEY_CHAMPION_ID)).thenReturn(championId)
//        `when`(arguments.getString(Const.KEY_VERSION)).thenReturn(version)
//
//        presenter.onActivityCreated(null, arguments)
//
//        assertThat(ReflectionUtils.getField(presenter, fieldChampionId)).isEqualTo(championId)
//        assertThat(ReflectionUtils.getField(presenter, fieldVersion)).isEqualTo(version)
//        verify(api.getChampion(championId)).enqueue(argumentCaptor.capture())
//        argumentCaptor.value.onFailure(call,
//                IOException()
//        )
//        verify(view).showError(R.string.error_io)
//        verifyNoMoreInteractions(view)
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun testOnActivityCreated_WhenFailure_ShowsError() {
//        val arguments = mock(Bundle::class.java)
//        `when`(arguments.getString(Const.KEY_CHAMPION_ID)).thenReturn(championId)
//        `when`(arguments.getString(Const.KEY_VERSION)).thenReturn(version)
//
//        presenter.onActivityCreated(null, arguments)
//
//        assertThat(ReflectionUtils.getField(presenter, fieldChampionId)).isEqualTo(championId)
//        assertThat(ReflectionUtils.getField(presenter, fieldVersion)).isEqualTo(version)
//        verify(api.getChampion(championId)).enqueue(argumentCaptor.capture())
//        argumentCaptor.value.onFailure(call,
//                Throwable()
//        )
//        verify(view).showError(R.string.error_something_went_wrong)
//        verifyNoMoreInteractions(view)
//    }
//
//    @Test
//    fun testOnSaveInstanceState() {
//        ReflectionUtils.setField(presenter, fieldChampionId, championId)
//        ReflectionUtils.setField(presenter, fieldVersion, version)
//        val bundle = mock(Bundle::class.java)
//
//        presenter.onSaveInstanceState(bundle)
//
//        verify(bundle).putString(Const.KEY_CHAMPION_ID, championId)
//        verify(bundle).putString(Const.KEY_VERSION, version)
//        verifyNoMoreInteractions(bundle, view)
//    }
//
//    @Test
//    fun testOnOptionsItemSelected() {
//        val item = mock(MenuItem::class.java)
//        `when`(item.itemId).thenReturn(android.R.id.home)
//
//        val result = presenter.onOptionsItemSelected(item)
//
//        assertThat(result).isEqualTo(true)
//        verify(view).doFinish()
//        verifyNoMoreInteractions(view)
//    }
//
//    @Test
//    fun testOnOptionsItemSelected_WhenItemNotHome_DoNothing() {
//        val item = mock(MenuItem::class.java)
//        `when`(item.itemId).thenReturn(0)
//
//        val result = presenter.onOptionsItemSelected(item)
//
//        assertThat(result).isEqualTo(false)
//        verifyNoMoreInteractions(view)
//    }
}

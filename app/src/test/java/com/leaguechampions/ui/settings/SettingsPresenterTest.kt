package com.leaguechampions.ui.settings

import com.leaguechampions.BuildConfig
import com.leaguechampions.utils.PrefUtils
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(BuildConfig::class, PrefUtils::class)
class SettingsPresenterTest {

//    private val fieldBuildType = "buildType"
//
//    private lateinit var presenter: SettingsPresenter
//
//    @Mock private lateinit var view: SettingsPresenter.SettingsView
//    @Mock private lateinit var sharedPreferences: SharedPreferences
//
    @Before
    fun setUp() {
//        presenter = SettingsPresenter(view, sharedPreferences)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun delete_me() {

    }

//    @Test
//    fun testOnActivityCreated() {
//        val mockMode = true
//        PowerMockito.mockStatic(PrefUtils::class.java)
//        `when`(PrefUtils.isMockMode(sharedPreferences)).thenReturn(mockMode)
//        ReflectionUtils.setField(presenter, fieldBuildType, "debug")
//
//        presenter.onActivityCreated(null, null)
//
//        verify(view).setVersion(BuildConfig.VERSION_NAME)
//        verify(view).showDeveloperOptions()
//        verify(view).showMockMode(mockMode)
//        verifyNoMoreInteractions(view)
//    }
//
//    @Test
//    fun testOnActivityCreated_WhenBuildTypeRelease_OnlySetsVersion() {
//        PowerMockito.mockStatic(PrefUtils::class.java)
//        ReflectionUtils.setField(presenter, fieldBuildType, "release")
//
//        presenter.onActivityCreated(null, null)
//
//        verify(view).setVersion(BuildConfig.VERSION_NAME)
//        verifyNoMoreInteractions(view)
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
//
//    @Test
//    fun testOnMockModeCheckedChanged() {
//        val mockMode = true
//        val editor = mock(SharedPreferences.Editor::class.java)
//        PowerMockito.mockStatic(PrefUtils::class.java)
//        `when`(sharedPreferences.edit()).thenReturn(editor)
//        `when`(editor.putBoolean(Const.PREF_KEY_MOCK_MODE, mockMode)).thenReturn(editor)
//
//        presenter.onMockModeCheckedChanged(mockMode)
//
//        verifyNoMoreInteractions(view)
//    }
}

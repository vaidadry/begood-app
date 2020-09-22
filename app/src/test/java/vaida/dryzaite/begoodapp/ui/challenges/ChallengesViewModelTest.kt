package vaida.dryzaite.begoodapp.ui.challenges

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import vaida.dryzaite.begoodapp.MainCoroutineRule
import vaida.dryzaite.begoodapp.getOrAwaitValueTest
import vaida.dryzaite.begoodapp.repository.FakeChallengesRepository
import vaida.dryzaite.begoodapp.utils.Constants
import vaida.dryzaite.begoodapp.utils.Status

@ExperimentalCoroutinesApi
class ChallengesViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: ChallengesViewModel

    @Before
    fun setup() {
        viewModel = ChallengesViewModel(FakeChallengesRepository())
    }

    /**
     * INPUT is not valid if
     * ..if title fields are empty
     * ..if title name already exists
     * ..if title shorter that 3 symbols
     * ..if title longer than 30 symbols
     * ..if desc longer than 200 symbols
     * ..if desc is empty we use title instead to pass
     * */

    @Test
    fun insertChallengeWithEmptyTitle_returnsError () {
        viewModel.insertChallenge("", "desc")

        val value = viewModel.insertChallengeStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun insertChallengeWithTitleThatAlreadyExist_returnsError () {

        viewModel.insertChallenge("title", "desc1")
        val value1 = viewModel.insertChallengeStatus.getOrAwaitValueTest()
        assertThat(value1.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)

        viewModel.insertChallenge("title", "desc2")
        val value2 = viewModel.insertChallengeStatus.getOrAwaitValueTest()
        assertThat(value2.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun insertChallengeWithTitleTooShort_returnsError () {

        val string = buildString {
            for (i in 1 until Constants.MIN_TITLE_LENGTH) {
            append(1)
            }
        }

        viewModel.insertChallenge(string, "desc")

        val value = viewModel.insertChallengeStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun insertChallengeWithTitleTooLong_returnsError () {

        val string = buildString {
            for (i in 1..Constants.MAX_TITLE_LENGTH + 1) {
                append(1)
            }
        }

        viewModel.insertChallenge(string, "desc")

        val value = viewModel.insertChallengeStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun insertChallengeWithDescTooLong_returnsError () {

        val string = buildString {
            for (i in 1..Constants.MAX_DESC_LENGTH + 1) {
                append(1)
            }
        }

        viewModel.insertChallenge("title", string)

        val value = viewModel.insertChallengeStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun insertChallengeWithEmptyDesc_returnsSuccess () {
        viewModel.insertChallenge("title", "")

        val value = viewModel.insertChallengeStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }

    @Test
    fun insertChallengeCorrectly_returnsSuccess () {

        viewModel.insertChallenge("titled", "description")

        val value = viewModel.insertChallengeStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }

    @Test
    fun afterInsertingChallengeResetCurrentUrl_returnsEmptyString() {
        val curURL = "url"
        viewModel.setCurImageUrl(curURL)
        viewModel.insertChallenge("title", "desc")

        val value = viewModel.currentImageUrl.getOrAwaitValueTest()
        assertThat(value).isEmpty()
    }

}
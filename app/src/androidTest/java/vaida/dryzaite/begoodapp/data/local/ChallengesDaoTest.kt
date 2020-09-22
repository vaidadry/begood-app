package vaida.dryzaite.begoodapp.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import vaida.dryzaite.begoodapp.getOrAwaitValue
import vaida.dryzaite.begoodapp.model.Challenge

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
@SmallTest
class ChallengesDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: ChallengesDatabase
    private lateinit var dao: ChallengesDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ChallengesDatabase::class.java
        ).allowMainThreadQueries()  //multiple threads lead to flaky code
         .build()
        dao = database.challengesDao()
    }

    @After
    fun teardown(){
        database.close()
    }


    @Test
    fun insertItem() = runBlockingTest {
        val item = Challenge("title", "desc", id = 1)
        dao.insertItem(item)

        val observedItem = dao.observeItemById(item.id!!).getOrAwaitValue()

        assertThat(observedItem).isEqualTo(item)
    }

    @Test
    fun insertItemsList() = runBlockingTest {
        val item = Challenge("title", "desc", id = 1)
        val item2 = Challenge("title2", "desc2", id = 2)
        val itemList = listOf(item, item2)
        dao.insertItemsList(itemList)

        val observedItem = dao.getItems()

        assertThat(observedItem).isEqualTo(itemList)
    }

    @Test
    fun deleteItem() = runBlockingTest{
        val item = Challenge("title", "desc", id = 1)
        dao.insertItem(item)

        dao.deleteItem(item)
        val allItems = dao.observeItems().getOrAwaitValue()

        assertThat(allItems).doesNotContain(item)
    }

    @Test
    fun deleteAllItems() = runBlockingTest{
        val item = Challenge("title", "desc", id = 1)
        val item2 = Challenge("title2", "desc2", id = 2)
        dao.insertItem(item)
        dao.insertItem(item2)

        dao.deleteAll()
        val allItems = dao.getItems()

        assertThat(allItems).isEmpty()
    }

    @Test
    fun updateCompleted()  = runBlockingTest {
        val item = Challenge("title", "desc", isCompleted = false, id = 1)
        dao.insertItem(item)

        dao.updateCompleted(item.id!!, true)

        val updatedItem = dao.getItemById(item.id!!)

        assertThat(updatedItem.isCompleted).isTrue()
    }
}
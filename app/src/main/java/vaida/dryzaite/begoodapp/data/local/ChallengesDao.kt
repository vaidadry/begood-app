package vaida.dryzaite.begoodapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import vaida.dryzaite.begoodapp.model.Challenge

@Dao
interface ChallengesDao {

    @Query("SELECT * FROM challenge")
    fun observeItems(): LiveData<List<Challenge>>

    @Query("SELECT * FROM challenge WHERE id = :id")
    fun observeItemById(id: Int): LiveData<Challenge>


    @Query("SELECT * FROM challenge")
    suspend fun getItems(): List<Challenge>

    @Query("SELECT * FROM challenge WHERE id = :id")
    suspend fun getItemById(id: Int): Challenge


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(challenge: Challenge)


    //not tested yet!!
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItemsList(challenges: List<Challenge>)

    @Query("UPDATE challenge SET completed = :completed WHERE id = :id")
    suspend fun updateCompleted(id: Int, completed: Boolean)

    @Delete
    suspend fun deleteItem(challenge: Challenge)

    @Query("DELETE FROM challenge")
    suspend fun deleteAll()


}
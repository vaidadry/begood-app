package vaida.dryzaite.begoodapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import vaida.dryzaite.begoodapp.model.Challenge

@Database(entities = [Challenge::class], version = 2, exportSchema = false)
abstract class ChallengesDatabase: RoomDatabase() {

    abstract fun challengesDao(): ChallengesDao

    //how to test it???

/**
    //callback to pre-populate database at it's creation (from json file)
    private class ChallengesDatabaseCallback(
        private val scope: CoroutineScope,
        private val resources: Resources
    ): RoomDatabase.Callback () {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val challengesDao = database.challengesDao()
                    prePopulateDatabase(challengesDao)
                }
            }
        }

        private suspend fun prePopulateDatabase(challengesDao: ChallengesDao) {
            val jsonString = resources.openRawResource(R.raw.challenges)
                .bufferedReader()
                .use {
                    it.readText()
                }
            val typeToken = object : TypeToken<List<Challenge>>() {}.type
            val challenges = Gson().fromJson<List<Challenge>>(jsonString, typeToken)

            challengesDao.insertItemsList(challenges)
        }
    }

    companion object {

        @Volatile
        private var INSTANCE: ChallengesDatabase? = null

        fun getInstance(
            context: Context,
            coroutineScope: CoroutineScope,
            resources: Resources): ChallengesDatabase {
            //syncronized makes sure the database only gets initialized once.
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ChallengesDatabase::class.java,
                        "challenges_database")
                        .addCallback(ChallengesDatabaseCallback(coroutineScope, resources))
                        .build()
                    INSTANCE = instance
                }
                return instance

            }
        }
    }

*/
}
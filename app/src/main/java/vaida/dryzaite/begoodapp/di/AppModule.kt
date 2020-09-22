package vaida.dryzaite.begoodapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import vaida.dryzaite.begoodapp.data.local.ChallengesDao
import vaida.dryzaite.begoodapp.data.local.ChallengesDatabase
import vaida.dryzaite.begoodapp.data.remote.responses.PixabayApi
import vaida.dryzaite.begoodapp.repository.ChallengeRepo
import vaida.dryzaite.begoodapp.repository.ChallengesRepository
import vaida.dryzaite.begoodapp.utils.Constants.BASE_URL
import vaida.dryzaite.begoodapp.utils.Constants.DATABASE_NAME
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class) //components will have application lifecycle
object AppModule {

    private lateinit var database: ChallengesDatabase

    @Singleton
    @Provides
    fun provideChallengesDatabase(
        @ApplicationContext context: Context
//        scope: CoroutineScope,
//        resources: Resources
    ) = Room.databaseBuilder(
        context,
        ChallengesDatabase::class.java,
        DATABASE_NAME
    )
     .build()

//        .addCallback(ChallengesDatabaseCallback(scope, resources))


    @Singleton
    @Provides
    fun provideChallengesDao(
        database: ChallengesDatabase
    ) = database.challengesDao()


    @Singleton
    @Provides
    fun providePixabayApi(): PixabayApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PixabayApi::class.java)
    }

    @Singleton
    @Provides
    fun provideChallengesRepository(
        dao: ChallengesDao,
        api: PixabayApi
    ) = ChallengesRepository(dao, api) as ChallengeRepo


//    //callback to pre-populate database at it's creation (from json file)
//    private class ChallengesDatabaseCallback(
//        private val scope: CoroutineScope,
//        val resources: Resources
//    ) : RoomDatabase.Callback() {
//
//        override fun onCreate(db: SupportSQLiteDatabase) {
//            super.onCreate(db)
//                scope.launch {
//                    val challengesDao = database.challengesDao()
//                    prePopulateDatabase(challengesDao)
//                }
//            }
//
//
//        private suspend fun prePopulateDatabase(challengesDao: ChallengesDao) {
//            val jsonString = resources.openRawResource(R.raw.challenges)
//                .bufferedReader()
//                .use {
//                    it.readText()
//                }
//            val typeToken = object : TypeToken<List<Challenge>>() {}.type
//            val challenges = Gson().fromJson<List<Challenge>>(jsonString, typeToken)
//
//            challengesDao.insertItemsList(challenges)
//        }
//    }
}
package tsisyk.app.babysleeptracker

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import tsisyk.app.babysleeptracker.database.SleepDatabase
import tsisyk.app.babysleeptracker.database.SleepDatabaseDao
import tsisyk.app.babysleeptracker.database.SleepNight
import org.junit.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class SleepDatabaseTest {

    private lateinit var sleepDao: SleepDatabaseDao
    private lateinit var db: SleepDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, SleepDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        sleepDao = db.sleepDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetNight() {
        val night = SleepNight()
        sleepDao.insert(night)
        val tonight = sleepDao.getTonight()
        assertEquals(tonight?.sleepQuality, -1)
    }
}

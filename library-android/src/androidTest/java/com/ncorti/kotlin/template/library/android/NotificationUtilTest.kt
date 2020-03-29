package com.ncorti.kotlin.template.library.android

import android.app.Notification
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class NotificationUtilTest {

    @Test
    fun createCorrectNotification() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        val notification = NotificationUtil(context).showNotification(context, "test title", "test message")

        assertEquals("test title", notification.extras.getCharSequence(Notification.EXTRA_TITLE))
        assertEquals("test message", notification.extras.getCharSequence(Notification.EXTRA_TEXT))
    }
}

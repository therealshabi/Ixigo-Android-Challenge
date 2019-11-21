package technolifestyle.com.ixigosearch

import android.app.Application
import timber.log.Timber

/**
 * BaseApplication to do the initial setups
 */
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Planted a debug tree for Timber logs
        Timber.plant(Timber.DebugTree())
    }
}
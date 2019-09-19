package david.barbaran.savetheamazon.app

import android.app.Application
import david.barbaran.savetheamazon.feature.home.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(homeModule)
        }
    }
}
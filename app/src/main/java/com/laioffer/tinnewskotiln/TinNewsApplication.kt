package com.laioffer.tinnewskotiln

import android.app.Application
import com.ashokvarma.gander.Gander
import com.ashokvarma.gander.imdb.GanderIMDB

class TinNewsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Gander.setGanderStorage(GanderIMDB.getInstance())
    }

}
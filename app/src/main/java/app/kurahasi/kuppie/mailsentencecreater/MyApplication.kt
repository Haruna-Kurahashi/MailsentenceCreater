package app.kurahasi.kuppie.mailsentencecreater

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        Realm.init(this) //設定(初期化)
        val realmConfiguration = RealmConfiguration.Builder().build()
        Realm.deleteRealm(realmConfiguration) // Delete Realm between app restarts.
        Realm.setDefaultConfiguration(realmConfiguration)
    }
}
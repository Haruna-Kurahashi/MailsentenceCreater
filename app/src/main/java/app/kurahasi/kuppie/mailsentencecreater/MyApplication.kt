package app.kurahasi.kuppie.mailsentencecreater

import android.app.Application
import io.realm.Realm

class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        Realm.init(this) //設定(初期化)

    }
}
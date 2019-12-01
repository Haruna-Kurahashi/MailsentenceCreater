package app.kurahasi.kuppie.mailsentencecreater

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class PlanModel(
    var id: Int = 0,
    open var time: String? = "",
    open var place: String? = "",
    open var content: String? = "",
    open var date: String? = ""
    ) : RealmObject()

{
}
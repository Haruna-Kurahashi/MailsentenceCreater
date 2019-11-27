package app.kurahasi.kuppie.mailsentencecreater


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.activity_main.*
import io.realm.RealmResults
import io.realm.RealmQuery
import android.content.Intent
import android.net.Uri
import android.net.Uri.fromParts




class MainActivity : AppCompatActivity() {

    private lateinit var mRealm: Realm

    lateinit var adapter: CustomRealmRecyclerViewAdapter


    override fun onStart() {
        super.onStart()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mRealm = Realm.getDefaultInstance()

        val realmResults = mRealm.where(PlanModel::class.java).findAll()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CustomRealmRecyclerViewAdapter(applicationContext)
        recyclerView.adapter = adapter


        savebutton.setOnClickListener {
                mRealm.executeTransaction {
                    var plan = mRealm.createObject(PlanModel::class.java)
                    plan.time =  timespinner.selectedItem as String
                    plan.place = placespinner.selectedItem as String
                    plan.content = contentspinner.selectedItem as String
                    mRealm.copyToRealm(plan)

                    roadalldata()
                }
        }

        roadalldata()

        sendbutton.setOnClickListener {
            // Create the text message with a string
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_SUBJECT, "練習計画")
                putExtra(Intent.EXTRA_TEXT, "本文")
                type = "text/plain"
            }


            if (sendIntent.resolveActivity(packageManager) != null) {
                startActivity(sendIntent)
            }

        }

        val spinnerItems = arrayOf(
            "16:30~19:30",
            "18:00~21:00",
            "18:30~21:30"
        )

        val spinnerItems2 = arrayOf(
            "大ホール",
            "スタジオ",
            "南大沢文化会館"
        )

        val spinnerItems3 = arrayOf(
            "自主弦",
            "自主総合",
            "中ソロ合わせ"
        )


        val timespinner = findViewById<Spinner>(R.id.timespinner)
        val placespinner = findViewById<Spinner>(R.id.placespinner)
        val contentspinner = findViewById<Spinner>(R.id.contentspinner)


        // ArrayAdapter
        val adapter = ArrayAdapter(
            applicationContext,
            android.R.layout.simple_spinner_item, spinnerItems
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // spinner に adapter をセット
        // Kotlin Android Extensions
        timespinner.adapter = adapter

        // リスナーを登録
        timespinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                //　アイテムが選択された時
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?, position: Int, id: Long
                ) {
                    val spinnerParent = parent as Spinner
                    val item = spinnerParent.selectedItem as String
                    //Kotlin Android Extensions
                   //  timepreview.text = item
                }

                //　アイテムが選択されなかった
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }


        // ArrayAdapter
        val adapter2 = ArrayAdapter(
            applicationContext,
            android.R.layout.simple_spinner_item, spinnerItems2
        )

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // spinner に adapter をセット
        // Kotlin Android Extensions
        placespinner.adapter = adapter2

        // リスナーを登録
        placespinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                //　アイテムが選択された時
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?, position: Int, id: Long
                ) {
                    val spinnerParent = parent as Spinner
                    val item2 = spinnerParent.selectedItem as String
                    //Kotlin Android Extensions
                 //   placepreview.text = item2
                }

                //　アイテムが選択されなかった
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }

        // ArrayAdapter
        val adapter3 = ArrayAdapter(
            applicationContext,
            android.R.layout.simple_spinner_item, spinnerItems3
        )

        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // spinner に adapter をセット
        // Kotlin Android Extensions
        contentspinner.adapter = adapter3

        // リスナーを登録
        contentspinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                //　アイテムが選択された時
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?, position: Int, id: Long
                ) {
                    val spinnerParent = parent as Spinner
                    val item3 = spinnerParent.selectedItem as String
                    //Kotlin Android Extensions
                   // contentpreview.text = item3
                }

                //　アイテムが選択されなかった
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }


    }

    fun roadalldata() {
        val query = mRealm.where(PlanModel::class.java)
        val result = query.findAll()
        adapter.clear()

        for (item in result) {
            adapter.additem(item)
        }

        adapter.notifyDataSetChanged()
    }


}


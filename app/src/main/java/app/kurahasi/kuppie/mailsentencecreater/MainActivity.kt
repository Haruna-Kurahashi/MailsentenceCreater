package app.kurahasi.kuppie.mailsentencecreater


import android.app.DatePickerDialog
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
import android.graphics.Color
import android.net.Uri
import android.net.Uri.fromParts
import android.util.Log
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var mRealm: Realm
    private var mYear: Int = 0
    private var mMonth: Int = 0
    private var mDay: Int = 0

    //lateinit var adapter: CustomRealmRecyclerViewAdapter


    override fun onStart() {
        super.onStart()

    }

    private val mOnDateClickListener = View.OnClickListener {
        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                mYear = year
                mMonth = monthOfYear
                mDay = dayOfMonth
                val dateString = mYear.toString() + "/" + String.format(
                    "%02d",
                    mMonth + 1
                ) + "/" + String.format("%02d", mDay)
                dateButton.setText(dateString)
            }, mYear, mMonth, mDay
        )
        datePickerDialog.show()

    }


    var selectedItems: Array<String> = arrayOf("", "", "")
    var titleItems: Array<String> = arrayOf("時間", "場所", "内容")


    private val mOnItemSelectedListener = object : AdapterView.OnItemSelectedListener {

        // アイテムが選択されなかったとき
        override fun onNothingSelected(parent: AdapterView<*>?) {
        }

        // アイテムが選択されたとき
        override fun onItemSelected(parent: AdapterView<*>, view: View, postion: Int, id: Long) {
            val item = parent.getItemAtPosition(postion)

            // 初回起動時の動作
            if (!parent.isFocusable) {
                parent.isFocusable = true
                return
            }

            // 初回以降の動作
            parent.findViewById<TextView>(android.R.id.text1)
                .setTextColor(Color.parseColor("#edffff"))

            when (parent.id) {
                R.id.timespinner -> {
                    selectedItems[0] = item as String
                    Log.d("item[0]", item)
                }
                R.id.placespinner -> {
                    selectedItems[1] = item as String
                    Log.d("item[1]", item)
                }
                R.id.contentspinner -> {
                    selectedItems[2] = item as String
                    Log.d("item[2]", item)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSpinnerAdapter()

        mRealm = Realm.getDefaultInstance()

        val realmResults = mRealm.where(PlanModel::class.java).findAll()
        /* val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
         recyclerView.layoutManager = LinearLayoutManager(this)
         adapter = CustomRealmRecyclerViewAdapter(applicationContext)
         recyclerView.adapter = adapter*/

        dateButton.setOnClickListener(mOnDateClickListener)

        // 新規作成の場合 カレンダーから現在のを取得
        val calendar = Calendar.getInstance()
        mYear = calendar.get(Calendar.YEAR)
        mMonth = calendar.get(Calendar.MONTH)
        mDay = calendar.get(Calendar.DAY_OF_MONTH)


      /*val spinnerItems = arrayOf(
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

            }*/


        val stringBuilder = StringBuilder()


        savebutton.setOnClickListener {
            mRealm.executeTransaction {
                var plan = mRealm.createObject(PlanModel::class.java)
                plan.time = timespinner.selectedItem as String
                plan.place = placespinner.selectedItem as String
                plan.content = contentspinner.selectedItem as String
                mRealm.copyToRealm(plan)

                // roadalldata()
            }

            stringBuilder.append("${mYear}/${mMonth + 1}/${mDay}\n")

            for ((index : Int, value : String) in selectedItems.withIndex()) {
                stringBuilder.append("${titleItems[index]} : ${value}\n")
            }
            Log.d("stringBuilder", stringBuilder.toString())
            stringBuilder.append("          \n")
            planTextView.text = stringBuilder.toString()

        }

//        roadalldata()

        scrollView.isScrollbarFadingEnabled = false

        sendbutton.setOnClickListener {
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_SUBJECT, "練習計画")
                putExtra(Intent.EXTRA_TEXT, stringBuilder.toString())
                type = "text/plain"
            }
            if (sendIntent.resolveActivity(packageManager) != null) {
                startActivity(sendIntent)
            }

        }


    }


    private fun setSpinnerAdapter() {
        val timeAdapter = ArrayAdapter<String>(
            this,
            R.layout.custom_spinner,
            resources.getStringArray(R.array.timeList)
        )
        val placeAdapter = ArrayAdapter<String>(
            this,
            R.layout.custom_spinner,
            resources.getStringArray(R.array.placeList)
        )
        val contentsAdapter = ArrayAdapter<String>(
            this,
            R.layout.custom_spinner,
            resources.getStringArray(R.array.contentList)
        )
        timeAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown)
        timespinner.adapter = timeAdapter
        placeAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown)
        placespinner.adapter = placeAdapter
        contentsAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown)
        contentspinner.adapter = contentsAdapter

        timespinner.isFocusable = false
        placespinner.isFocusable = false
        contentspinner.isFocusable = false

        timespinner.onItemSelectedListener = mOnItemSelectedListener
        placespinner.onItemSelectedListener = mOnItemSelectedListener
        contentspinner.onItemSelectedListener = mOnItemSelectedListener

    }


    /* fun roadalldata() {
         val query = mRealm.where(PlanModel::class.java)
         val result = query.findAll()
         adapter.clear()

         for (item in result) {
             adapter.additem(item)
         }

         adapter.notifyDataSetChanged()
     }*/


}


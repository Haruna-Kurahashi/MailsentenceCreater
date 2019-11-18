package app.kurahasi.kuppie.mailsentencecreater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        val adapter = ArrayAdapter(applicationContext,
            android.R.layout.simple_spinner_item, spinnerItems)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // spinner に adapter をセット
        // Kotlin Android Extensions
        timespinner.adapter = adapter

        // リスナーを登録
        timespinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            //　アイテムが選択された時
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?, position: Int, id: Long
            ) {
                val spinnerParent = parent as Spinner
                val item = spinnerParent.selectedItem as String
                //Kotlin Android Extensions
                timepreview.text = item
            }

            //　アイテムが選択されなかった
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }


        // ArrayAdapter
        val adapter2 = ArrayAdapter(applicationContext,
            android.R.layout.simple_spinner_item, spinnerItems2)

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // spinner に adapter をセット
        // Kotlin Android Extensions
        placespinner.adapter = adapter2

        // リスナーを登録
        placespinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            //　アイテムが選択された時
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?, position: Int, id: Long
            ) {
                val spinnerParent = parent as Spinner
                val item = spinnerParent.selectedItem as String
                //Kotlin Android Extensions
                placepreview.text = item
            }

            //　アイテムが選択されなかった
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

        // ArrayAdapter
        val adapter3 = ArrayAdapter(applicationContext,
            android.R.layout.simple_spinner_item, spinnerItems3)

        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // spinner に adapter をセット
        // Kotlin Android Extensions
        contentspinner.adapter = adapter3

        // リスナーを登録
        contentspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            //　アイテムが選択された時
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?, position: Int, id: Long
            ) {
                val spinnerParent = parent as Spinner
                val item = spinnerParent.selectedItem as String
                //Kotlin Android Extensions
                contentpreview.text = item
            }

            //　アイテムが選択されなかった
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }



    }
}

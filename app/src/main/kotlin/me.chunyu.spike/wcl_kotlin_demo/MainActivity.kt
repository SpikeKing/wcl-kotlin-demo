package me.chunyu.spike.wcl_kotlin_demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Kotlin的主类, 添加设置属性.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main_tv_message.text = "Hello Kotlin"
        main_tv_message.textSize = 20.0f
    }
}

package me.chunyu.spike.wcl_kotlin_demo

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.LayoutParams
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Kotlin的主类, 添加设置属性.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main_tv_message.text = getString(R.string.hello_kotlin)
        main_tv_message.textSize = 20.0f

        // 自定义LinearLayout
        val view = v<LinearLayout>(applicationContext) {
            layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            orientation = LinearLayout.VERTICAL


            v<TextView>(this) {
                layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
                text = "Hello"
                setTextColor(ContextCompat.getColor(applicationContext, R.color.colorAccent))
            }

            v<TextView>(this) {
                layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
                text = "World"
                setTextColor(ContextCompat.getColor(applicationContext, R.color.colorAccent))
            }
        }

        main_ll_container.addView(view)
    }


    // TextView的构建
    inline fun <reified TV : View> v(context: Context, init: TV.() -> Unit): TV {
        val constr = TV::class.java.getConstructor(Context::class.java);
        val view = constr.newInstance(context);
        view.init();
        return view;
    }

    // ViewGroup的构建
    inline fun <reified V : View> v(parent: ViewGroup, init: V.() -> Unit): V {
        val constr = V::class.java.getConstructor(Context::class.java);
        val view = constr.newInstance(parent.context);
        parent.addView(view)
        view.init();
        return view;
    }
}

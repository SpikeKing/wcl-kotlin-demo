package org.wangchenlong.wcl_kotlin_demo

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.TypedValue
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

        // 自定义LinearLayout, val是不可改变immutable, var是可以改变mutable.
        val view = v<LinearLayout> {
            layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            orientation = LinearLayout.VERTICAL

            // 设置属性
            v<TextView> {
                layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
                text = "Hello"
                setTextColor(ContextCompat.getColor(applicationContext, R.color.colorAccent))
                padLeft = dp_i(20.0f)
            }

            v<TextView> {
                layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
                text = "World"
                setTextColor(ContextCompat.getColor(applicationContext, R.color.colorAccent))
            }
        }

        main_ll_container.addView(view)
    }


    // View的模板
    inline fun <reified TV : View> Context.v(init: TV.() -> Unit): TV {
        val cns = TV::class.java.getConstructor(Context::class.java)
        val view = cns.newInstance(this)
        view.init()
        return view
    }

    // ViewGroup的模板
    inline fun <reified V : View> ViewGroup.v(init: V.() -> Unit): V {
        val cns = V::class.java.getConstructor(Context::class.java)
        val view = cns.newInstance(context)
        addView(view)
        view.init()
        return view
    }

    // 使用扩展函数(extension function)View.
    fun View.dp_f(dp: Float): Float {
        // 引用View的context
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics)
    }

    // 转换Int
    fun View.dp_i(dp: Float): Int {
        return dp_f(dp).toInt()
    }

    // 使用扩展属性(extension property)
    var View.padLeft: Int
        set(value) {
            setPadding(value, paddingTop, paddingRight, paddingBottom)
        }
        get() {
            return paddingLeft
        }
}

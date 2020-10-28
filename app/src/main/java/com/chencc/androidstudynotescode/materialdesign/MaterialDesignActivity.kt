package com.chencc.androidstudynotescode.materialdesign

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chencc.androidstudynotescode.R
import com.chencc.androidstudynotescode.materialdesign.cardview.CardViewActivity
import com.chencc.androidstudynotescode.materialdesign.coordinator.CoordinatorActivity
import com.chencc.androidstudynotescode.materialdesign.toolbar.ToolbarActivity
import com.chencc.androidstudynotescode.materialdesign.viewpager2.ViewPager2Activity
import kotlinx.android.synthetic.main.activity_material_design.*


/**
 * MaterialDesign 控件练习
 */
class MaterialDesignActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_design)

        button1.setOnClickListener {
            startActivity(Intent(this@MaterialDesignActivity, CoordinatorActivity::class.java))
        }
        button2.setOnClickListener {
            startActivity(Intent(this@MaterialDesignActivity, ToolbarActivity::class.java))
        }
        button3.setOnClickListener {
            startActivity(Intent(this@MaterialDesignActivity, ViewPager2Activity::class.java))
        }
        button4.setOnClickListener {
            startActivity(Intent(this@MaterialDesignActivity, CardViewActivity::class.java))
        }
    }
}
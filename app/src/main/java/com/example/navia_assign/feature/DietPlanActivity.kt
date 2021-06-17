package com.example.navia_assign.feature

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.navia_assign.R
import com.example.navia_assign.core.base.BaseActivity
import com.example.navia_assign.core.base.BaseFragment

class DietPlanActivity : BaseActivity() {
    override fun layoutRes() = R.layout.activity_layout


    override fun fragment() = DietPlanFragment()
}
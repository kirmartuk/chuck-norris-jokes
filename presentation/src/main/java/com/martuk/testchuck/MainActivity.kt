package com.martuk.testchuck

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.martuk.testchuck.adapters.SectionsPagerAdapter
import com.martuk.testchuck.fragments.ApiDocumentationFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_api_documentation.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(
            this, supportFragmentManager, arrayOf(
                R.string.tab_title_1,
                R.string.tab_title_2
            )
        )
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
    }

    override fun onBackPressed() {
        // support onBackPressed in ApiDocumentationFragment`s WebView
        with(supportFragmentManager.fragments[view_pager.currentItem]) {
            when (this) {
                is ApiDocumentationFragment -> {
                    if (this.wv_api.canGoBack()) {
                        this.wv_api.goBack()
                    } else {
                        super.onBackPressed()
                    }
                }
                else -> {
                    super.onBackPressed()
                }
            }
        }
    }
}
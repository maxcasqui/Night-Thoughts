package com.adriand.nightthoughts.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.adriand.nightthoughts.R
import com.adriand.nightthoughts.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTabLayout()
    }

    private fun initTabLayout(){
        adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

        binding.quoteTabLayout.addTab(binding.quoteTabLayout.newTab().setText(R.string.app_quote_section_1))
        binding.quoteTabLayout.addTab(binding.quoteTabLayout.newTab().setText(R.string.app_quote_section_2))

        binding.quoteViewPager.adapter = adapter

        binding.quoteTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let { binding.quoteViewPager.currentItem = tab.position }
            }
            override fun onTabUnselected(p0: TabLayout.Tab?) {}
            override fun onTabReselected(p0: TabLayout.Tab?) {}
        })

        binding.quoteViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.quoteTabLayout.selectTab(binding.quoteTabLayout.getTabAt(position))
            }
        })
    }
}
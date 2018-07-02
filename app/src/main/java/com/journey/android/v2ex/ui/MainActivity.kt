package com.journey.android.v2ex.ui

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import com.zzhoujay.richtext.RichText
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import com.journey.android.v2ex.R


class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener,
        TopicListFragment.OnListFragmentInteractionListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        nav_view.getHeaderView(0)
                .setOnClickListener {
                    startActivity(Intent(this, LoginActivity::class.java))
                }

        initViewPager()
    }

    private fun initViewPager() {
        val myPagerAdapter = MainPagerAdapter(supportFragmentManager)
        val fragments = ArrayList<Fragment>()
        fragments.add(TopicListFragment.newInstance(TopicListFragment.TOPIC_NODE_LAST))
        fragments.add(TopicListFragment.newInstance(TopicListFragment.TOPIC_NODE_HOT))
        fragments.add(TopicListFragment.newInstance(TopicListFragment.TOPIC_NODE_TEST))
        myPagerAdapter.setFragments(fragments)
        main_viewpager.adapter = myPagerAdapter
        main_tab.addTab(main_tab.newTab())
        main_tab.setupWithViewPager(main_viewpager)

        // TabLayout指示器添加文本
        main_tab.getTabAt(0)?.setText("最新")
        main_tab.getTabAt(1)?.setText("热门")
        main_tab.getTabAt(2)?.setText("苹果")
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {

            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        RichText.recycle()
    }

    override fun onListFragmentInteraction(id: Int) {
        TopicDetailActivity.start(id, this)
    }

    inner class MainPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        private var mFragmentList: List<Fragment>? = null

        fun setFragments(fragments: ArrayList<Fragment>) {
            mFragmentList = fragments
        }

        override fun getItem(position: Int): Fragment {
            return mFragmentList!![position]
        }

        override fun getCount(): Int {
            return mFragmentList!!.size
        }
    }
}
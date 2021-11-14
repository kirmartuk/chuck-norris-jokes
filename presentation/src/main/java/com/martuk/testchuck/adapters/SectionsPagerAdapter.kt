package com.martuk.testchuck.adapters

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.martuk.testchuck.fragments.ApiDocumentationFragment
import com.martuk.testchuck.fragments.JokesFragment


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(
    private val mContext: Context,
    fm: FragmentManager,
    private val mTabTitles: Array<Int>
) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int) = when (position) {
        0 -> JokesFragment.newInstance()
        else -> ApiDocumentationFragment.newInstance()
    }


    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(mTabTitles[position])
    }

    override fun getCount() = mTabTitles.size
}
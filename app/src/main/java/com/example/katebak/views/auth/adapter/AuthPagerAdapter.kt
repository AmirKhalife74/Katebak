package com.example.katebak.views.auth.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.katebak.utils.AuthNavigationListener
import com.example.katebak.views.auth.LoginFragment
import com.example.katebak.views.auth.OtpFragment

class AuthPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val listener = fragment as? AuthNavigationListener
        ?: throw IllegalStateException("Parent fragment must implement AuthNavigationListener")
    override fun getItemCount(): Int = 2 // Login and Register

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> LoginFragment(listener)
            1 -> OtpFragment(listener)
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}
package com.syoon.mybookie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.syoon.mybookie.databinding.ActivityMainBinding
import com.syoon.mybookie.ui.FavoriteFragment
import com.syoon.mybookie.ui.SearchFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var searchFragment : SearchFragment
    private lateinit var favoriteFragment : FavoriteFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment

        // 네비게이션 그래프 정보를 바탕으로 네비게이션 간 이동 담당 - 차후 그래프와 함께 활용
        // https://devlog-h.tistory.com/29
//        val navController = navHostFragment.navController
//        val bottomNavigation = findViewById<BottomNavigationView>(R.id.nav_bar)
//        bottomNavigation.setupWithNavController(navController)

        // 네이게이션 메뉴 -> 해당 프래그먼트로 이동
        binding.navBar.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.search_item -> {
                    searchFragment = SearchFragment.newInstance()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host, searchFragment).commit()
                }
                R.id.favorite_item -> {
                    favoriteFragment = FavoriteFragment.newInstance()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host, favoriteFragment).commit()
                }
            }
            true
        }
    }

}
package com.acoders.readnetic.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.acoders.readnetic.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReadneticActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
  /*  @Inject lateinit var repository: BookRepository
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }*/
}

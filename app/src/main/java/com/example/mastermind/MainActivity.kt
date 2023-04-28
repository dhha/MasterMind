package com.example.mastermind

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.example.mastermind.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var navController : NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var listener : NavController.OnDestinationChangedListener

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostMainFragment) as NavHostFragment

        navController = navHostFragment.navController

        drawerLayout = findViewById(R.id.drawer_layout)

        binding.navigationView.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)


        setupActionBarWithNavController(navController, appBarConfiguration)

        listener = NavController.OnDestinationChangedListener{
            controller, destination, arguments ->
            //            if(destination.id == R.id.addCourseFragment){
            //                Toast.makeText(this, "Add Course", Toast.LENGTH_LONG).show()
            //            }else{
            //                Toast.makeText(this, "Course List", Toast.LENGTH_LONG).show()
            //            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {

        val navController = findNavController(R.id.navHostMainFragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onPause() {
        super.onPause()
        navController.removeOnDestinationChangedListener(listener)
    }

    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(listener)
    }
    override fun onDestroy() {
        super.onDestroy()
        navController.removeOnDestinationChangedListener(listener)
    }
}
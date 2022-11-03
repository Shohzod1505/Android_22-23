package ru.itis.kpfu.homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import ru.itis.kpfu.homework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        val controller = (supportFragmentManager.findFragmentById(R.id.host_fragment) as NavHostFragment).navController

        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.settingFragment,
                R.id.homeFragment,
                R.id.searchFragment,
                R.id.messageFragment,
                R.id.helpFragment
            ),
            fallbackOnNavigateUpListener = ::onSupportNavigateUp
        )
        findViewById<Toolbar>(androidx.appcompat.R.id.action_bar)
            .setupWithNavController(controller, appBarConfiguration)

        binding?.run {
            bnvMenu.setupWithNavController(controller)
        }
    }

}



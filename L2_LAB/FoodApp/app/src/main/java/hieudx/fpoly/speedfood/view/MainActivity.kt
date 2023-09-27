package hieudx.fpoly.speedfood.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isInvisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import hieudx.fpoly.speedfood.R
import hieudx.fpoly.speedfood.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val host: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragContView) as NavHostFragment? ?: return
        val navController = host.navController
        binding.toolbar.setupWithNavController(
            navController,
            AppBarConfiguration(navController.graph)
        )

        binding.tvNext.setOnClickListener {
            when (navController.currentDestination?.id) {
                R.id.mainCourseFrag ->
                    navController.navigate(R.id.action_mainCourseFrag_to_sideDishesFrag)

                R.id.sideDishesFrag ->
                    navController.navigate(R.id.action_sideDishesFrag_to_dessertFrag)

                R.id.dessertFrag ->
                    navController.navigate(R.id.action_dessertFrag_to_beveragesFrag)

                R.id.beveragesFrag -> navController.navigate(R.id.action_beveragesFrag_to_detailOrderFrag)

                R.id.detailOrderFrag -> navController.navigate(R.id.action_detailOrderFrag_to_mainCourseFrag)

            }
        }

        binding.tvBack.setOnClickListener {
            navController.navigateUp()
        }

        val item1 = AHBottomNavigationItem(
            R.string.deltail_order_label,
            R.drawable.ic_detail_order,
            R.color.detail_order
        )
        val item2 =
            AHBottomNavigationItem(R.string.profile_label, R.drawable.ic_profile, R.color.profile)
        binding.navBtn.addItem(item1)
        binding.navBtn.addItem(item2)

        binding.navBtn.setOnTabSelectedListener(object : AHBottomNavigation.OnTabSelectedListener {
            override fun onTabSelected(position: Int, wasSelected: Boolean): Boolean {
                when (position) {
                    0 -> navController.navigate(R.id.detailOrderFrag)
                    1 -> {
                        navController.navigate(R.id.profileFragment)
                        binding.tvNext.isInvisible
                        binding.tvBack.isInvisible
                    }
                }
                return true
            }
        })

    }
}
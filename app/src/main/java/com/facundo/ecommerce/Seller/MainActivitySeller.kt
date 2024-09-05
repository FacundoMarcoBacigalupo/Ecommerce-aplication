package com.facundo.ecommerce.Seller
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.facundo.ecommerce.R
import com.facundo.ecommerce.Seller.Bottom_Nav_Fragments_Seller.FragmentMyProductsV
import com.facundo.ecommerce.Seller.Bottom_Nav_Fragments_Seller.FragmentOrdersV
import com.facundo.ecommerce.Seller.Nav_Fragments_Seller.FragmentHomeV
import com.facundo.ecommerce.Seller.Nav_Fragments_Seller.FragmentMyShopV
import com.facundo.ecommerce.Seller.Nav_Fragments_Seller.FragmentReviewsV
import com.facundo.ecommerce.databinding.ActivityMainSellerBinding
import com.google.android.material.navigation.NavigationView


class MainActivitySeller : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {
   private lateinit var binding : ActivityMainSellerBinding

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = ActivityMainSellerBinding.inflate(layoutInflater)
      setContentView(binding.root)

      val toolbar = findViewById<Toolbar>(R.id.toolbar)
      setSupportActionBar(toolbar)

      binding.navigationView.setNavigationItemSelectedListener(this)

      val toggle = ActionBarDrawerToggle(
         this,
         binding.drawerLayout,
         toolbar,
         R.string.open_drawer,
         R.string.close_drawer
      )

      binding.drawerLayout.addDrawerListener(toggle)
      toggle.syncState()

      replaceFragment(FragmentHomeV())
      binding.navigationView.setCheckedItem(R.id.op_home_v)
   }

   private fun replaceFragment(fragment: Fragment) {
      supportFragmentManager
         .beginTransaction()
         .replace(R.id.navFragment, fragment)
         .commit()
   }

   override fun onNavigationItemSelected(item: MenuItem): Boolean {
      when (item.itemId){
         //Navigation Drawer
         R.id.op_home_v -> {
            replaceFragment(FragmentHomeV())
         }

         R.id.op_my_shop_v -> {
            replaceFragment(FragmentMyShopV())
         }

         R.id.op_review_v -> {
            replaceFragment(FragmentReviewsV())
         }

         R.id.op_close_sesion_v -> {
            Toast.makeText(applicationContext, "You exited the application", Toast.LENGTH_SHORT).show()
         }

         //Bottom Navigation Bar
         R.id.op_my_products_v -> {
            replaceFragment(FragmentMyProductsV())
         }

         R.id.op_my_orders_v -> {
            replaceFragment(FragmentOrdersV())
         }
      }
      binding.drawerLayout.closeDrawer(GravityCompat.START)
      return true
   }
}
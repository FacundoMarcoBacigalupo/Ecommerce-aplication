package com.facundo.ecommerce.Seller.Nav_Fragments_Seller
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.facundo.ecommerce.R
import com.facundo.ecommerce.Seller.Bottom_Nav_Fragments_Seller.FragmentMyProductsV
import com.facundo.ecommerce.Seller.Bottom_Nav_Fragments_Seller.FragmentOrdersV
import com.facundo.ecommerce.databinding.FragmentHomeVBinding


class FragmentHomeV : Fragment() {

   private lateinit var binding : FragmentHomeVBinding
   private lateinit var mContext : Context

   override fun onAttach(context: Context) {
      mContext = context
      super.onAttach(context)
   }

   override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
      binding = FragmentHomeVBinding.inflate(inflater, container, false)

      binding.bottomNavigation.setOnItemSelectedListener {
         when (it.itemId){
            R.id.op_my_products_v -> {
               replaceFragment(FragmentMyProductsV())
            }

            R.id.op_my_orders_v -> {
               replaceFragment(FragmentOrdersV())
            }
         }
         true
      }

      replaceFragment(FragmentMyProductsV())
      binding.bottomNavigation.selectedItemId = R.id.op_my_products_v

      binding.addFab.setOnClickListener {
         Toast.makeText(
            mContext,
            "You touch the floating bottom",
            Toast.LENGTH_SHORT
         ).show()
      }

      return binding.root
   }

   private fun replaceFragment(fragment: Fragment) {
      parentFragmentManager
         .beginTransaction()
         .replace(R.id.bottomFragment, fragment)
         .commit()
   }

}
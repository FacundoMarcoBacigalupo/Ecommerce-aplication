package com.facundo.ecommerce.Seller.Bottom_Nav_Fragments_Seller
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.facundo.ecommerce.R


class FragmentMyProductsV : Fragment() {

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {
      // Inflate the layout for this fragment
      return inflater.inflate(R.layout.fragment_my_products_v, container, false)
   }

}
package com.example.balmapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import androidx.viewbinding.ViewBindings
import com.example.balmapp.databinding.LModoBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [f_modo3.newInstance] factory method to
 * create an instance of this fragment.
 */
class f_modo : Fragment() {
    // TODO: Rename and change types of parameters

    private var _binding: LModoBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
   private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
    fun replaceFragment(someFragment: Fragment?) {
        val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
        if (someFragment != null) {
            transaction.replace(R.id.fl_modo, someFragment)
        }
        transaction.addToBackStack(null)
        transaction.commit()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = LModoBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnmodoLibre.setOnClickListener(){
            val intent=Intent(activity, a_mapa::class.java)
            activity?.finish()
            startActivity(intent)
        }
        binding.btnmodoGuiado.setOnClickListener(){
            val fragment:Fragment=f_modo()
            replaceFragment(fragment)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
package com.rozan.liquordeliveryapplication.ui.account

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import com.rozan.liquordeliveryapplication.R
import com.rozan.liquordeliveryapplication.api.ServiceBuilder
import com.rozan.liquordeliveryapplication.ui.order.OrderViewModel

class AccountFragment : Fragment() {

    private lateinit var accountViewModel: AccountViewModel
    private lateinit var profileImage: ImageView
    private lateinit var tvFullname:TextView
    private lateinit var tvName:TextView
    private lateinit var tvUsername:TextView
    private lateinit var tvMail:TextView
    private lateinit var tvDOB:TextView
    private lateinit var btnEdit: Button
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        accountViewModel =
                ViewModelProvider(this).get(AccountViewModel::class.java)
        val root = inflater.inflate(R.layout.account_fragment, container, false)

        accountViewModel.text.observe(viewLifecycleOwner, Observer {
            val context = root.context
            val data = ServiceBuilder.data!!  // fetching user data from servicebuilder
            profileImage = root.findViewById(R.id.profile)
            tvFullname = root.findViewById(R.id.tvFullname)
            tvName = root.findViewById(R.id.tvName)
            tvUsername = root.findViewById(R.id.tvUsername)
            tvMail = root.findViewById(R.id.tvMail)
            tvDOB= root.findViewById(R.id.tvDOB)
            profileImage = root.findViewById(R.id.profile)

            tvFullname.text=data[0].firstName +" "+ data[0].lastName
            tvName.text=data[0].firstName +""+ data[0].lastName
            tvUsername.text=data[0].username
            tvMail.text=data[0].email
            tvDOB.text=data[0].dob
        })
        return root
    }

}
package com.rozan.liquordeliveryapplication.ui.account

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import com.rozan.liquordeliveryapplication.R
import com.rozan.liquordeliveryapplication.api.ServiceBuilder
import com.rozan.liquordeliveryapplication.entity.Users
import com.rozan.liquordeliveryapplication.repository.UserRepository
import com.rozan.liquordeliveryapplication.ui.order.OrderViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AccountFragment : Fragment() {

    private lateinit var accountViewModel: AccountViewModel
    private lateinit var profileImage: ImageView
    private lateinit var tvFullname:TextView
    private lateinit var tvName:TextView
    private lateinit var tvUsername:TextView
    private lateinit var tvMail:TextView
    private lateinit var tvDOB:TextView
    private lateinit var btnEdit: Button

    lateinit var popAddPost: Dialog

    private lateinit var etFirstName: EditText
    private lateinit var etLastName: EditText
    private lateinit var etUsername: EditText
    private lateinit var etEmail: EditText
    private lateinit var etDOB: EditText
    private lateinit var btnSave:Button
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

            popupWindow(root,context)
            iniPopup(root,context,data)
        })
        return root
    }
    private fun popupWindow(root:View,context: Context) {
        btnEdit = root.findViewById(R.id.btnEdit)
        btnEdit.setOnClickListener {
            popAddPost.show()
        }
    }

    private fun iniPopup(root: View,context: Context,data:MutableList<Users>) {
        popAddPost = Dialog(context)
        popAddPost.setContentView(R.layout.popup_edit)
        popAddPost.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        popAddPost.window?.setLayout(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.WRAP_CONTENT);
        popAddPost.window?.attributes?.gravity = Gravity.CENTER;

        etFirstName = popAddPost.findViewById(R.id.etFirstName)
        etLastName = popAddPost.findViewById(R.id.etLastName)
        etUsername = popAddPost.findViewById(R.id.etUsername)
        etEmail = popAddPost.findViewById(R.id.etEmail)
        etDOB = popAddPost.findViewById(R.id.etDOB)
        btnSave=popAddPost.findViewById(R.id.btnSave)


        etFirstName.setText(data[0].firstName)
        etLastName.setText(data[0].lastName)
        etUsername.setText(data[0].username)
        etEmail.setText(data[0].email)
        etDOB.setText(data[0].dob)


        btnSave.setOnClickListener {
            updateUser()
            popAddPost.dismiss()

        }
    }

    private fun updateUser() {
        val userId=ServiceBuilder.userId!!
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userRepository = UserRepository()
                val user = Users(
                       firstName =  etFirstName.text.toString(), lastName = etLastName.text.toString(),
                        email = etEmail.text.toString(), username = etUsername.text.toString(),dob = etDOB.text.toString()
                )
                val response = userRepository.updateUser(userId, user)
                if (response.success == true) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                                context,
                                "Updated successfully",
                                Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                            context,
                            ex.localizedMessage,
                            Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
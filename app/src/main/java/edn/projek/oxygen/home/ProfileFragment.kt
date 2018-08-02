package edn.projek.oxygen.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edn.projek.oxygen.R
import edn.projek.oxygen.home.presenter.ProfileContract
import edn.projek.oxygen.home.presenter.ProfilePresenter
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment(), ProfileContract.View {



    lateinit var presenter : ProfilePresenter

    companion object {
        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = ProfilePresenter(requireActivity(), this)
        presenter.getDataProfile()
    }
    override fun setDataProfile(data: HashMap<String, String>) {
        custNo.text =data.getValue("no_customer")
        custName.text = data.getValue("name")
        email.text = data.getValue("email")
        phone.text = data.getValue("phone")
        identityNo.text = data.getValue("iden_no")
        identyfyType.text = data.getValue("iden_type")
        address.text = data.getValue("address")
        packageCus.text = data.getValue("package")
        device.text = data.getValue("device_type")
        serial_number.text= data.getValue("serial_no")
    }

}

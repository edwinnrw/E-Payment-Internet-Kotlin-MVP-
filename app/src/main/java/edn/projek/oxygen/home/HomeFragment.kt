package edn.projek.oxygen.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import edn.projek.oxygen.R
import edn.projek.oxygen.home.adapter.BillAdapter
import edn.projek.oxygen.home.presenter.HomeContract
import edn.projek.oxygen.home.presenter.HomePresenter
import edn.projek.oxygen.model.Bill
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(), HomeContract.View {


    lateinit var presenter : HomePresenter
    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = HomePresenter(requireActivity(),this)
        presenter.getBill()

    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun dismisProgress() {
        progress.visibility = View.GONE
    }

    override fun createList(data: List<Bill>) {
        var billAdapter : BillAdapter = BillAdapter(data,requireActivity())
        txtEmpty.visibility = View.GONE
        recycler_view.visibility = View.VISIBLE
        recycler_view.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = billAdapter
        }
    }

    override fun showEmpty() {
        recycler_view.visibility = View.GONE
        txtEmpty.visibility = View.VISIBLE
    }

    override fun showMessageFail(message: String) {
        Toast.makeText(requireContext(), message,Toast.LENGTH_LONG).show()
    }



}

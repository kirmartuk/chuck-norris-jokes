package com.martuk.testchuck.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.martuk.testchuck.R
import kotlinx.android.synthetic.main.fragment_api_documentation.*

class ApiDocumentationFragment : Fragment() {

    companion object {

        const val BASE_URL = "http://www.icndb.com/api/"

        fun newInstance(): ApiDocumentationFragment {
            return ApiDocumentationFragment()
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wv_api.webViewClient = WebViewClient()
        wv_api.loadUrl(BASE_URL)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_api_documentation, container, false)
    }


}
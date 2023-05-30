package com.example.mycasino2.view

import android.app.DownloadManager
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.PersistableBundle
import android.view.WindowManager
import android.webkit.*
import android.widget.Toast
import com.example.mycasino2.R

class WebViewActivity : AppCompatActivity() {

    private lateinit var webView: WebView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        webView = findViewById(R.id.id_webview)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        if(savedInstanceState == null){
            val url = intent.getStringExtra("key")
            webView.webViewClient = WebViewClient()
            webView.loadUrl(url!!)
            webView.settings.javaScriptEnabled = true
            webView.settings.setSupportZoom(true)
            webView.settings.builtInZoomControls = true
            webView.settings.displayZoomControls = true
            webView.settings.allowFileAccess = true
            webView.settings.allowFileAccessFromFileURLs = true

            webView.setWebChromeClient(object: WebChromeClient() {
                override fun onShowFileChooser(webView:WebView, filePathCallback: ValueCallback<Array<Uri>>, fileChooserParams:FileChooserParams):Boolean {
                    var mFilePathCallback = filePathCallback
                    val intent = Intent(Intent.ACTION_GET_CONTENT)
                    intent.setType("*/*")
                    val PICKFILE_REQUEST_CODE = 100
                    startActivityForResult(intent, PICKFILE_REQUEST_CODE)
                    return true
                }
            })

            fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent, mFilePathCallback: Any): Boolean {
                var PICKFILE_REQUEST_CODE = null
                if (requestCode == PICKFILE_REQUEST_CODE){
                    val result = if (intent == null || resultCode != RESULT_OK)
                        null
                    else
                        intent.getData()
                    val resultsArray = arrayOfNulls<Uri>(1)
                    resultsArray[0] = result
                    mFilePathCallback.onReceiveValue(resultsArray)
                }
                return true
            }



            webView.setDownloadListener(object : DownloadListener {
                override fun onDownloadStart(url: String, userAgent: String,
                                             contentDisposition: String, mimetype: String,
                                             contentLength: Long) {
                    val request = DownloadManager.Request(Uri.parse(url))
                    request.allowScanningByMediaScanner()

                    request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, mimetype)
                    val webview = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
                    webview.enqueue(request)
                    Toast.makeText(getApplicationContext(), "Downloading File", Toast.LENGTH_LONG).show()
                }
            })

        }

    }








    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        webView.saveState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        webView.restoreState(savedInstanceState)
    }

    override fun onBackPressed() {
        if (webView.canGoBack())
            webView.goBack()
        else finishAffinity()
    }

    private fun Any.onReceiveValue(resultsArray: Array<Uri?>) {}
}
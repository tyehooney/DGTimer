package com.example.dgtimer.activities.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Rect
import android.graphics.drawable.AnimatedVectorDrawable
import android.net.ConnectivityManager
import android.net.Network
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.pm.PackageInfoCompat
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.example.dgtimer.AppRater
import com.example.dgtimer.R
import com.example.dgtimer.activities.timer.TimerActivity
import com.example.dgtimer.databinding.ActivityMainBinding
import com.example.dgtimer.utils.Extensions.getPackageInfoCompat
import com.example.dgtimer.utils.Extensions.readUpdateNote
import com.example.dgtimer.utils.Extensions.setSearchFocus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var appRater: AppRater

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    private val mainCapsulesAdapter: CapsuleAdapter by lazy {
        CapsuleAdapter(
            this::onCapsuleItemClick,
            this::onCapsuleItemStarClick
        )
    }
    private val searchedCapsulesAdapter: CapsuleAdapter by lazy {
        CapsuleAdapter(
            this::onCapsuleItemClick,
            this::onCapsuleItemStarClick
        )
    }

    private val connectivityManager by lazy {
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
    private val networkCallback by lazy {
        object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                viewModel.updateCapsulesFromServer()
            }
        }
    }

    private var updateVersionNoteDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onBackPressedDispatcher.addCallback(this) {
            if (viewModel.isSearchModeOn.value) {
                viewModel.setSearchMode(false)
            } else {
                appRater.set(this@MainActivity)
                isEnabled = false
            }
        }

        initView()
        initObservers()
        connectivityManager.registerDefaultNetworkCallback(networkCallback)
        checkAppVersion()
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateCapsulesFromServer()
    }

    private fun initObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.capsules.collect {
                        mainCapsulesAdapter.submitList(it)
                        updateNetworkDisconnectedText()
                    }
                }
                launch {
                    viewModel.isInitialized.collectLatest {
                        updateNetworkDisconnectedText()
                    }
                }
                launch {
                    viewModel.searchedCapsules.collectLatest {
                        searchedCapsulesAdapter.submitList(it)
                    }
                }
                launch {
                    viewModel.isSearchModeOn.collect {
                        setSearchMode(it)
                    }
                }
                launch {
                    viewModel.showFab.collect() {
                        toggleScrollTopFab(it)
                    }
                }
            }
        }
    }

    private suspend fun updateNetworkDisconnectedText() {
        val capsules = viewModel.capsules.stateIn(lifecycleScope).value
        binding.tvNetworkDisconnected.isVisible =
            capsules?.isEmpty() ?: true && viewModel.isInitialized.value
    }

    private fun initView() {
        with(binding) {
            fabUp.setOnClickListener { scrollUpToTop() }
            ivBtnSearch.setOnClickListener {
                val isSearchModeOn = viewModel.isSearchModeOn.value
                viewModel.setSearchMode(!isSearchModeOn)
            }
            ivBtnSearchTextCancel.setOnClickListener {
                etSearch.setText("")
            }
            etSearch.doOnTextChanged { text, _, _, _ ->
                searchCapsules(text.toString())
            }
            rvCapsules.adapter = mainCapsulesAdapter
            rvSearchedCapsules.adapter = searchedCapsulesAdapter
            rvCapsules.addOnScrollListener(
                object: RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        viewModel.setScrollYForShowingFab(dy)
                    }
                }
            )
        }
    }

    private fun checkAppVersion() {
        try {
            val packageInfo = packageManager.getPackageInfoCompat(packageName)
            val currentVersionCode = PackageInfoCompat.getLongVersionCode(packageInfo).toInt()
            if (viewModel.savedVersionCode != currentVersionCode) {
                readUpdateNote(packageInfo.versionName)?.let { updateNote ->
                    updateVersionNoteDialog = AlertDialog.Builder(this)
                        .setMessage(updateNote)
                        .setPositiveButton(R.string.check, null)
                        .create().apply {
                            show()
                        }
                }
                viewModel.saveVersionCode(currentVersionCode)
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
    }

    private fun searchCapsules(text: String) {
        viewModel.searchCapsules(text)
    }

    private fun onCapsuleItemClick(capsuleId: Int) {
        val intent = TimerActivity.createTimerActivityIntent(this, capsuleId)
        startActivity(intent)
    }

    private fun onCapsuleItemStarClick(capsuleId: Int) {
        viewModel.updateCapsuleMajor(capsuleId)
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.actionMasked == MotionEvent.ACTION_DOWN) {
            val focusedView = currentFocus
            if (focusedView is EditText) {
                val searchRect = Rect()
                binding.llSearch.getGlobalVisibleRect(searchRect)
                if (!searchRect.contains(ev.rawX.toInt(), ev.rawY.toInt())) {
                    focusedView.clearFocus()
                    hideKeyboard(focusedView)
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun hideKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun setSearchMode(isOn: Boolean) {
        binding.rvCapsules.isVisible = !isOn
        binding.rvSearchedCapsules.isVisible = isOn
        binding.llSearch.isVisible = isOn
        binding.etSearch.setSearchFocus(isOn)
        binding.ivBtnSearch.setSearchButtonAnimation(isOn)
    }

    private fun ImageView.setSearchButtonAnimation(on: Boolean) {
        setImageDrawable(
            if (on) {
                ContextCompat.getDrawable(context, R.drawable.avd_anim_search_to_back)
            } else {
                ContextCompat.getDrawable(context, R.drawable.avd_anim_back_to_search)
            }
        )

        if (drawable is AnimatedVectorDrawableCompat) {
            drawable as AnimatedVectorDrawableCompat
        } else {
            drawable as AnimatedVectorDrawable
        }.start()
    }

    private fun toggleScrollTopFab(showButton: Boolean) {
        binding.fabUp.isVisible = showButton
    }

    private fun scrollUpToTop() {
        binding.rvCapsules.smoothScrollToPosition(0)
    }

    override fun onDestroy() {
        updateVersionNoteDialog?.dismiss()
        updateVersionNoteDialog = null
        connectivityManager.unregisterNetworkCallback(networkCallback)
        super.onDestroy()
    }

    companion object {
        fun createMainActivityIntent(
            callerActivity: Activity
        ) = Intent(callerActivity, MainActivity::class.java)
    }
}
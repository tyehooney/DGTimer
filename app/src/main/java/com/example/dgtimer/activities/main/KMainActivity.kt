package com.example.dgtimer.activities.main

import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.AnimatedVectorDrawable
import android.net.ConnectivityManager
import android.net.Network
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.example.dgtimer.R
import com.example.dgtimer.activities.timer.KTimerActivity
import com.example.dgtimer.databinding.ActivityMainBinding
import com.example.dgtimer.utils.Extensions.setSearchFocus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class KMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: KMainViewModel by viewModels()

    private val mainCapsulesAdapter: KCapsuleAdapter by lazy {
        KCapsuleAdapter(
            this::onCapsuleItemClick,
            this::onCapsuleItemStarClick
        )
    }
    private val searchedCapsulesAdapter: KCapsuleAdapter by lazy {
        KCapsuleAdapter(
            this::onCapsuleItemClick,
            this::onCapsuleItemStarClick
        )
    }

    private var searchJob: Job? = null

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

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initObservers()
        connectivityManager.registerDefaultNetworkCallback(networkCallback)
    }

    private fun initObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.capsules.collect {
                        mainCapsulesAdapter.submitList(it)
                        binding.tvNetworkDisconnected.isVisible = it?.isEmpty() ?: true
                    }
                }
                launch {
                    viewModel.searchedCapsules.collectLatest {
                        searchedCapsulesAdapter.submitList(it)
                    }
                }
                launch {
                    viewModel.isSearchModeOn.collect {
                        toggleSearchMode(it)
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
            rvCapsules.addOnScrollListener(
                object: RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        viewModel.setScrollYForShowingFab(dy)
                    }
                }
            )
        }
    }

    private fun searchCapsules(text: String) {
        if (searchJob != null) return
        searchJob = lifecycleScope.launch {
            viewModel.searchCapsules(text)
            searchJob = null
        }
    }

    private fun onCapsuleItemClick(capsuleId: Int) {
        val intent = KTimerActivity.createTimerActivityIntent(this, capsuleId)
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

    private fun toggleSearchMode(isOn: Boolean) {
        val needToShowSearchedCapsules =
            isOn && (viewModel.searchedCapsules.value?.isNotEmpty() ?: false)
        binding.rvCapsules.isVisible = !needToShowSearchedCapsules
        binding.rvSearchedCapsules.isVisible = needToShowSearchedCapsules
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
        connectivityManager.unregisterNetworkCallback(networkCallback)
        super.onDestroy()
    }

    override fun onBackPressed() {
        if (viewModel.isSearchModeOn.value) {
            viewModel.setSearchMode(false)
        } else {
            // todo AppRater
            super.onBackPressed()
        }
    }
}
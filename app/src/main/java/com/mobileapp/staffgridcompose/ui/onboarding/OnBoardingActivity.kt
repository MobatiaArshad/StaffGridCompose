package com.mobileapp.staffgridcompose.ui.onboarding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mobileapp.staffgridcompose.R
import com.mobileapp.staffgridcompose.ui.onboarding.model.PassingData
import com.mobileapp.staffgridcompose.ui.onboarding.screens.StepOne
import com.mobileapp.staffgridcompose.ui.onboarding.screens.StepTwo
import com.mobileapp.staffgridcompose.ui.onboarding.ui.theme.StaffGridComposeTheme
import kotlinx.coroutines.launch

class OnBoardingActivity : ComponentActivity() {

    private lateinit var viewModel: OnboardingViewModel
    private var passingData: PassingData? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[OnboardingViewModel::class.java]
        observers()
        setContent {
            StaffGridComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val isLoaded by viewModel.isDataLoaded.observeAsState(false)
                    val step by viewModel.onBoardingStep.observeAsState()
                    if (isLoaded) NewUi(step) else Test()
                }
            }
        }
    }
private fun observers() {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.isLoadedFlow.collect { isLoaded ->
                // Update UI based on the new isLoaded value
                if (isLoaded) viewModel.loadData(true)
            }
        }
    }

    viewModel.passingData.observe(this) {
        println("DATA = $it")
        passingData = it
    }
}

@Composable
fun NewUi(step: Int?) {
    Box(
        modifier = Modifier
            .background(
                Color(0xFFFFFFFF)
            )
    ) {
        Column {
            Box(modifier = Modifier
                .padding(start = 25.dp, top = 22.dp)
                .clickable {
                    viewModel.returnScreen()
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back_btn),
                    contentDescription = "image description",
                    contentScale = ContentScale.None
                )
            }
            when (step) {
                1 -> StepOne(data = passingData, click = {
                    println("DATA = $it")
                    viewModel.changeScreen(2)
                })

                2 -> StepTwo(data = passingData, click = {
                    println("DATA = $it")
                })
            }
        }

    }

}

@Composable
fun Test() {
    Text(text = "Loading....... ")
}

@Preview(showSystemUi = true)
@Composable
fun ScreenPreview() {
    NewUi(step = 1)
}
}
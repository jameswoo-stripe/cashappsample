package com.stripe.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.gson.jsonBody
import com.github.kittinunf.fuel.gson.responseObject
import com.google.gson.Gson
//import com.stripe.android.PaymentConfiguration
//import com.stripe.android.model.ConfirmPaymentIntentParams
//import com.stripe.android.model.PaymentMethodCreateParams
//import com.stripe.android.payments.paymentlauncher.PaymentLauncher
//import com.stripe.android.payments.paymentlauncher.rememberPaymentLauncher
import com.stripe.example.model.PaymentIntentRequest
import com.stripe.example.model.PaymentIntentResponse
import com.stripe.example.ui.theme.CashAppSampleTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONObject

class MainActivity : ComponentActivity() {
    private val settings by lazy {
        Settings(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FuelManager.instance.basePath = settings.backendUrl
//        PaymentConfiguration.init(this, settings.publishableKey)

        setContent {
            var isProcessing by remember { mutableStateOf(false) }
            var status by remember { mutableStateOf("") }

            LaunchedEffect(Unit) {
                delay(1000)
                status = "ok"
            }

//            val paymentLauncher = rememberPaymentLauncher(publishableKey = settings.publishableKey) {
//                isProcessing = false
//                status = it.toString()
//            }
//
            CashAppPayScreen(
                isProcessing = isProcessing,
                status = status,
                onButtonPressed = {
//                    isProcessing = true
//                    val params = PaymentMethodCreateParams.createCashAppPay()
//                    val request = PaymentIntentRequest(
//                        country = "US",
//                        supportedPaymentMethods = "cashapp"
//                    )
//                    Fuel.post("create_payment_intent")
//                        .jsonBody(request)
//                        .responseObject<PaymentIntentResponse> { _, _, result ->
//                            val paymentIntent = result.get()
//                            val clientSecret = paymentIntent.secret
//                            val paymentIntentParams = ConfirmPaymentIntentParams.createWithPaymentMethodCreateParams(
//                                paymentMethodCreateParams = params,
//                                clientSecret = clientSecret,
//                            )
//                            paymentLauncher.confirm(paymentIntentParams)
//                        }
                },
            )
        }
    }

    @Composable
    private fun CashAppPayScreen(
        isProcessing: Boolean,
        status: String,
        onButtonPressed: () -> Unit,
    ) {
        CashAppSampleTheme {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = onButtonPressed,
                        enabled = !isProcessing,
                        modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    ) {
                        Text("Pay with Cash App")
                        if (isProcessing) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(16.dp),
                            )
                        }
                    }
                }

                if (status.isNotBlank()) {
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                    )

                    Text(
                        text = status,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                    )
                }
            }
        }
    }
}

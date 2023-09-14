package com.stripe.example.model

import com.google.gson.annotations.SerializedName

data class PaymentIntentRequest(
    @SerializedName("country") val country: String,
    @SerializedName("supported_payment_methods") val supportedPaymentMethods: String? = null
)
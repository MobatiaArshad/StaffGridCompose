package com.mobileapp.staffgridcompose.ui.onboarding.model

data class PassingData(
    var firstName: String? = null,
    var lastName: String? = null,
    var city: String? = null,
    var state: String? = null,
    var cellNo: Long = 0,
    var postalCode: Int = 0,
    var email: String? = null,
    var isChecked: Boolean = false,
    var streetAddress: String? = null,
    var streetAddress2: String? = null,
    var emergencyContactNumber: Long = 0,
    var relationToApplicant: List<String> = emptyList(),
    var deviceType: List<String> = emptyList(),
    var appliedBefore: Boolean = false,
    var eligibleOrNOt: Boolean = false,
    )
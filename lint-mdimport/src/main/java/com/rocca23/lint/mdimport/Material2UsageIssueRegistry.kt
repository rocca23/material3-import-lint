package com.rocca23.lint.mdimport

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.client.api.Vendor
import com.android.tools.lint.detector.api.CURRENT_API

class Material2UsageIssueRegistry : IssueRegistry() {
    override val issues = listOf(Material2UsageDetector.ISSUE)
    override val api: Int = CURRENT_API
    override val vendor = Vendor(
        vendorName = "Mattia Roccaforte",
        contact = "https://github.com/rocca23"
    )
}
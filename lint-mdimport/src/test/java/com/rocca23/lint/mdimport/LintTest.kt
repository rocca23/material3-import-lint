package com.rocca23.lint.mdimport

import com.android.tools.lint.checks.infrastructure.LintDetectorTest
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.Severity

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class LintTest : LintDetectorTest() {

    fun testMaterial2Usage() {
        lint()
            .allowMissingSdk()
            .files(
                java(
                    """
                    package test.pck;
                    import androidx.compose.material.*;
                    import androidx.compose.material3.*;
                    import androidx.compose.material.icons.*;
                """.trimIndent()
                )
            )
            .run()
            .expectCount(1, Severity.ERROR)
    }

    fun testMaterial2UsageKt() {
        lint()
            .allowMissingSdk()
            .files(
                kotlin(
                    """
                    package test.pck
                    import androidx.compose.material.*
                    import androidx.compose.material3.*
                    import androidx.compose.material.icons.*
                """.trimIndent()
                )
            )
            .run()
            .expectCount(1, Severity.ERROR)
    }

    override fun getDetector(): Detector {
        return Material2UsageDetector()
    }

    override fun getIssues(): MutableList<Issue> {
        return mutableListOf(Material2UsageDetector.ISSUE)
    }
}
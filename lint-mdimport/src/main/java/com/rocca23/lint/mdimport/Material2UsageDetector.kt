package com.rocca23.lint.mdimport

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.LintFix
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import org.jetbrains.uast.UElement
import org.jetbrains.uast.UImportStatement

class Material2UsageDetector  : Detector(), Detector.UastScanner {
    val regex = "androidx\\.compose\\.material(?!(3|\\.icons))".toRegex()

    override fun getApplicableUastTypes(): List<Class<out UElement>> {
        return listOf(UImportStatement::class.java)
    }

    override fun createUastHandler(context: JavaContext): UElementHandler {
        return object : UElementHandler() {
            override fun visitImportStatement(node: UImportStatement) {
                if (node.asSourceString().contains(regex)) {
                    context.report(
                        ISSUE,
                        node,
                        context.getLocation(node),
                        "Use material3 instead of material.",
                        LintFix.create()
                            .replace()
                            .text("androidx.compose.material.")
                            .with("androidx.compose.material3.")
                            .build()
                    )
                }
            }
        }
    }

    companion object {
        val ISSUE = Issue.create(
            id = "Material2Usage",
            briefDescription = "Material2 usage",
            explanation = "It checks for usage of material2 instead of material3 in import statements.",
            category = Category.CORRECTNESS,
            priority = 8,
            severity = Severity.ERROR,
            implementation = Implementation(
                Material2UsageDetector::class.java,
                Scope.JAVA_FILE_SCOPE
            )
        )
    }
}
# Material Design import lint detector for Compose

If you are using Material Design 3 in your Android app, you might need to fall back to MD2 components that have not yet been implemented in MD3,
therefore both libraries need to be declared as dependencies.

Since many composables have the same name in both MD versions, it is way too easy while autocompleting to mistakenly pick the MD2 component instead of the MD3 one,
thus adding the `import androidx.compose.material.` statement instead of `import androidx.compose.material3.`.

This lint detector reports an error when it finds that `material` is used instead of `material3` in import statements.
When you actually need to use a MD2 component for the aforementioned reason, you can suppress the issue so that you at least _know what you are doing_.

It currently makes an exception for `androidx.compose.material.icons`, since the Material Icons are still needed with MD3 but are still under the `material` package at this time.

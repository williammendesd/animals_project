// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
//  adicionado para usar o SQLite:
    id("com.google.devtools.ksp") version "1.9.21-1.0.15" apply false
}
// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.

import org.jetbrains.intellij.build.IdeaCommunityProperties
import org.jetbrains.intellij.build.IdeaProjectLoaderUtil
import org.jetbrains.intellij.build.kotlin.KotlinPluginBuilder

object KotlinPluginBuildTarget {
  @JvmStatic
  fun main(args: Array<String>) {
    val communityHome = IdeaProjectLoaderUtil.guessCommunityHome(javaClass)
    KotlinPluginBuilder(communityHome, communityHome, KotlinIdeaProperties(communityHome)).build()
  }
}

// Google: exclude unnecessary plugins from the build, especially the Android plugin.
class KotlinIdeaProperties(home: java.nio.file.Path) : IdeaCommunityProperties(home) {
  init {
    // We only care about building the Kotlin plugin, nothing else.
    productLayout.bundledPluginModules.clear()
    productLayout.allNonTrivialPlugins = mutableListOf(KotlinPluginBuilder.kotlinPlugin())
  }
}

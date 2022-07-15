# Koremodding
MinecraftForge JavaScript coremods using Kotlin/JS (aka my worst idea yet)

<details>
<summary>Open if you dare</summary>

The main source code can be found inside the `koremod` subproject, [here](koremod/src/main/kotlin/koremod.kt)

This code is build with the kotlin/js nodejs target, then [processed by esbuild.](koremod/build.js)

esbuild includes [a loader](koremod/loader.js) that properly exposes the loaded kotlin/js module to the global scope.

The build setup is *incredibly* fragile so I wouldn't be surprised if it breaks.

</details>
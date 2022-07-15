const build = require("esbuild").build;

(async () => {
    await build({
        entryPoints: ["loader.js"],
        bundle: true,
        outfile: "coremod.js",
        target: "es5",
        treeShaking: true
    })
})()
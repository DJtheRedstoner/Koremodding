@JsName("initializeCoreMod")
fun initializeCoreMod(): dynamic {
    js("print('Hello, World')")
    val type = MethodTarget("METHOD", "net.minecraft.client.main.Main", "main", "([Ljava/lang/String;)V")

    return makeObj {
        test_transformer = makeObj {
            target = type
            transformer = ::transformMain
        }
    }
}

fun transformMain(method: dynamic): dynamic {
    val asmapi = javaType<ASMAPI>("net.minecraftforge.coremod.api.ASMAPI")
    val opcodes = Java.type("org.objectweb.asm.Opcodes")

    val list = asmapi.listOf(
        New(Java.type("org.objectweb.asm.tree.FieldInsnNode"),
            opcodes.GETSTATIC,
            "java/lang/System",
            "out",
            "Ljava/io/PrintStream;"
        ),
        New(Java.type("org.objectweb.asm.tree.LdcInsnNode"), "#forgeontop"),
        asmapi.buildMethodCall(
            "java/io/PrintStream",
            "println",
            "(Ljava/lang/String;)V",
            asmapi.MethodType.VIRTUAL
        )
    )

    method.instructions.insert(list)

    return method
}

class MethodTarget(val type: String, val `class`: String, val methodName: String, val methodDesc: String)

fun makeObj(action: dynamic.() -> Unit): dynamic {
    val o = js("{}")
    action(o)
    return o
}

fun <T> javaType(name: String): T {
    return Java.type(name).unsafeCast<T>()
}

external val Java: NashornJava

external interface NashornJava {
    fun type(name: String): dynamic
}

external interface ASMAPI {
    val MethodType: dynamic

    fun listOf(vararg nodes: dynamic)
    fun buildMethodCall(ownerName: String, methodName: String, methodDescriptor: String, type: dynamic)
}

fun New(type: dynamic, p0: dynamic): dynamic {
    return js("new type(p0)")
}

fun New(type: dynamic, p0: dynamic, p1: dynamic): dynamic {
    return js("new type(p0, p1)")
}

fun New(type: dynamic, p0: dynamic, p1: dynamic, p2: dynamic): dynamic {
    return js("new type(p0, p1, p2)")
}

fun New(type: dynamic, p0: dynamic, p1: dynamic, p2: dynamic, p3: dynamic): dynamic {
    return js("new type(p0, p1, p2, p3)")
}
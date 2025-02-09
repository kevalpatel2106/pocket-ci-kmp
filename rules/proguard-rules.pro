# Uncomment this to preserve the line number information for
# debugging stack traces.
-keepattributes SourceFile,LineNumberTable

# Auto generated missing rules
-dontwarn java.lang.invoke.StringConcatFactory

# Setting the build versions for dead code elemination.
# https://jakewharton.com/digging-into-d8-and-r8/
-assumevalues class android.os.Build$VERSION {
    int SDK_INT return 23..2147483647;
}

# Proguard configuration for removing logs
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
    public static *** w(...);
    public static *** e(...);
}
-assumenosideeffects class java.io.PrintStream {
     public void println(%);
     public void println(**);
 }

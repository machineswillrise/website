---
title: You're Underutilizing Java's Standard Library
date: 2026-05-29
---

Java has an incredibly powerful standard library, but you're probably not using enough of its features and resorting to heavy frameworks and external dependencies. Let's look at some examples where the standard library can replace popular third-party libraries.

Java has a built-in HTTP server called HttpServer that you can use to make web applications instead of using Javalin or other frameworks. One downside is that static file serving from the classpath can be annoying and you need to make your own routing infrastructure.

Java has a built-in logging framework called java.util.logging that you can use instead of SLF4J or other logging frameworks. NetBeans uses it internally. You'll need to wrap inefficient string concatenations in lambdas since it lacks {} substitution.

Java has a built-in smart card library called javax.smartcardio that you can use instead of external smart card libraries. If you deal with vanilla JavaCards instead of ones from a vendor, it can be a good choice.

Java has a built-in JSON processing library as part of Java EE called javax.json that you can use instead of GSON or Jackson. It can be kind of verbose but it works well. You unfortunetely need to manually map each JSON field to fields in a POJO.

Java similarly has a built-in XML processing library called javax.xml that you can use instead of Jackson. It's not as verbose as JSON processing. You can also encode beans for transmission over a network with javax.beans.XMLEncoder.

Java has a built-in HTTP client called java.net.http.HttpClient that you can use instead of Apache HttpClient or OkHttp. It lacks some features such as connection pooling and automatic retries. I wrap the HTTP methods with CompletableFuture in projects where I need async.

You can build manual dependency injection containers instead of using Guice. If you have a large app, you can consider making a container for each package.

Java has a built-in SQL client called java.sql that you can use if you don't need an ORM. This might not work well for complex applications.

Java is improving native access with Panama, which you can use instead of JNA, which is slow and uses extensive reflection.

Before you go to Maven Central, you should check if Java can do something and decide whether you need the extra features of a third-party library. I'm not saying you shouldn't use any libraries ever. But you should think about whether you really need a library in certain scenarios.

This blog is powered by the built-in HTTP server and has no dependencies besides Freemarker. The shaded JAR is 2.6 MB compared to the 500+ MB one that you might get when using Spring.

You can check out the code if you're interested:
https://github.com/machineswillrise/website
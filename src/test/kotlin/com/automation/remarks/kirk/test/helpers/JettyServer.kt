package com.automation.remarks.kirk.test.helpers

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.DefaultServlet
import org.eclipse.jetty.servlet.ServletContextHandler
import org.eclipse.jetty.servlet.ServletHolder
import org.eclipse.jetty.util.resource.Resource

/**
 * Created by sepi on 6/27/2017.
 */
class JettyServer(val port: Int) {

    val server = Server(port)

    val url = "http://localhost:$port/"

    fun runServer(): Int {

        val cl = JettyServer::class.java.classLoader
        val f = cl.getResource("start_page.html") ?: throw RuntimeException("Unable open find resource directory")

        // Resolve file open directory
        val webRootUri = f.toURI().resolve("./").normalize()
        System.err.println("WebRoot is " + webRootUri)


        val context = ServletContextHandler(ServletContextHandler.SESSIONS)
        context.contextPath = "/"
        context.baseResource = Resource.newResource(webRootUri)
        context.welcomeFiles = arrayOf("start_page.html")


        val holderPwd = ServletHolder("default", DefaultServlet::class.java)
        holderPwd.setInitParameter("dirAllowed", "true")
        context.addServlet(holderPwd, "/")

        server.handler = context

        server.start()
        return port
    }

    fun stop(){
        server.stop()
    }
}
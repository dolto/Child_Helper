package com.example.child_helper.client

import android.content.Context
import android.util.Log
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.InputStream
import java.lang.Integer.max
import java.security.KeyStore
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocket
import javax.net.ssl.TrustManagerFactory
import kotlin.math.min

fun Client(context: Context, mod: String, data: String): String?{
    //인증서 신뢰 구성
//    val keystorePath = Paths.get("keycode.jks").toAbsolutePath().toString()
//    val keystorePw = "chilehelper" //패스워드는 나중에 db에서 불러올 예정
//    System.setProperty("javax.net.ssl.trustStore", keystorePath)
//    System.setProperty("javax.net.ssl.trustStorePassword", keystorePw)

    val assetManager = context.assets
    val inputStream: InputStream = assetManager.open("keycode.p12")
    val keyStore = KeyStore.getInstance("PKCS12")
    val password = "chilehelper".toCharArray() // 키 스토어의 암호 입력

    keyStore.load(inputStream, password)

    //val certificateFactory = CertificateFactory.getInstance("X.509")
   // val certificate = certificateFactory.generateCertificate(inputStream) as X509Certificate

    //keyStore.setCertificateEntry("chilehelper", certificate)

    val trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
    trustManagerFactory.init(keyStore)

    val sslContext = SSLContext.getInstance("TLS")
    sslContext.init(null, trustManagerFactory.trustManagers, null)

    val serverHost = "192.168.0.23" //안드로이드가 localhost를 찾을 떄 쓰는 주소란다
    val serverPort = 55550

    Log.d("실행이 되었나","ㅇㅇ")

    val sslSocketFactory = sslContext.socketFactory
    val clientSocket = sslSocketFactory.createSocket(serverHost, serverPort) as SSLSocket
    val input = DataInputStream(clientSocket.getInputStream())
    val output = DataOutputStream(clientSocket.getOutputStream())

    Log.d("소켓과 연결했고, input 설정함","ㅇㅇ")
    // 클라이언트에서 서버에 메시지 전송

    val dataArray = data.toByteArray(Charsets.UTF_8)
    var size = dataArray.size
    var count = 0

    Log.d("데이터는 다음과같다", String(dataArray, Charsets.UTF_8))
    Log.d("크기는 다음과같다", size.toString())
    output.writeUTF(mod)
    //output.writeUTF(data)
    output.writeInt(size)

    while (count < size){
        output.write(dataArray, count, min(1024, size - count))
        count += 1024
    }

    println("데이터 전송 완료")

    size = input.readInt()
    val resultArray = ByteArray(size)

    count = 0
    while (count < size){
        input.read(resultArray, count, min(1024, size - count))
        count += 1024
    }
    // 서버로부터 응답 받기
    val response = String(resultArray, Charsets.UTF_8)
    println("Server response: $response")

    println("응답 받기 완료")
    if (!clientSocket.isClosed){
        input.close()
        //output.close()
    }
    clientSocket.close()
    return response;
}
fun getSocketRead(input: DataInputStream): String {
    return input.readUTF()
}
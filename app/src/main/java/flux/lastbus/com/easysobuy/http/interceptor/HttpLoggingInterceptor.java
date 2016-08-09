/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package flux.lastbus.com.easysobuy.http.interceptor;


import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.concurrent.TimeUnit;

import flux.lastbus.com.easysobuy.utils.NetUtil;
import flux.lastbus.com.easysobuy.utils.StringUtils;
import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Platform;
import okhttp3.internal.http.HttpEngine;
import okio.Buffer;
import okio.BufferedSource;


/**
 * An OkHttp interceptor which logs request and response information. Can be applied as an
 * {@linkplain OkHttpClient#interceptors() application interceptor} or as a {@linkplain
 * OkHttpClient#networkInterceptors() network interceptor}. <p> The format of the logs created by
 * this class should not be considered stable and may change slightly between releases. If you need
 * a stable logging format, use your own interceptor.
 */
public final class HttpLoggingInterceptor implements Interceptor {
  private static final Charset UTF8 = Charset.forName("UTF-8");


  public enum Level {
    /** No logs. */
    NONE,
    /**
     * Logs request and response lines.
     *
     * <p>Example:
     * <pre>{@code
     * --> POST /greeting http/1.1 (3-byte body)
     *
     * <-- 200 OK (22ms, 6-byte body)
     * }</pre>
     */
    BASIC,
    /**
     * Logs request and response lines and their respective headers.
     *
     * <p>Example:
     * <pre>{@code
     * --> POST /greeting http/1.1
     * Host: example.com
     * Content-Type: plain/text
     * Content-Length: 3
     * --> END POST
     *
     * <-- 200 OK (22ms)
     * Content-Type: plain/text
     * Content-Length: 6
     * <-- END HTTP
     * }</pre>
     */
    HEADERS,
    /**
     * Logs request and response lines and their respective headers and bodies (if present).
     *
     * <p>Example:
     * <pre>{@code
     * --> POST /greeting http/1.1
     * Host: example.com
     * Content-Type: plain/text
     * Content-Length: 3
     *
     * Hi?
     * --> END GET
     *
     * <-- 200 OK (22ms)
     * Content-Type: plain/text
     * Content-Length: 6
     *
     * Hello!
     * <-- END HTTP
     * }</pre>
     */
    BODY
  }

  public interface Logger {
    void log(String message);

    /** A {@link Logger} defaults output appropriate for the current platform. */
    Logger DEFAULT = new Logger() {
      @Override public void log(String message) {
        Platform.get().log(Platform.INFO,message, null);
      }
    };
  }

  public HttpLoggingInterceptor() {

    this(Logger.DEFAULT);
  }

  public HttpLoggingInterceptor(Logger logger) {
    this.logger = logger;
  }

  private final Logger logger;

  private volatile Level level = Level.BASIC;


  /** Change the level at which this interceptor logs. */
  public HttpLoggingInterceptor setLevel(Level level) {
    if (level == null) throw new NullPointerException("level == null. Use Level.NONE instead.");
    this.level = level;
    return this;
  }

  public Level getLevel() {
    return level;
  }

  @Override public Response intercept(Chain chain) throws IOException {
    Level level = this.level;

    Request request = chain.request();
    if (level == Level.NONE) {
      return chain.proceed(request);
    }

    boolean logBody = level == Level.BODY;
    boolean logHeaders = logBody || level == Level.HEADERS;

    RequestBody requestBody = request.body();
    boolean hasRequestBody = requestBody != null;

    Connection connection = chain.connection();
    Protocol protocol = connection != null ? connection.protocol() : Protocol.HTTP_1_1;
    String requestStartMessage = "--> " + request.method() + ' ' + request.url() + ' ' + protocol;
    if (!logHeaders && hasRequestBody) {
      requestStartMessage += " (" + requestBody.contentLength() + "-byte body)";
    }
    logger.log(" ");
    logger.log(" ");
    logger.log(requestStartMessage);

    if (logHeaders) {
      if (hasRequestBody) {
        // Request body headers are only present when installed as a network interceptor. Force
        // them to be included (when available) so there values are known.
        if (requestBody.contentType() != null) {
          logger.log("Content-Type: " + requestBody.contentType());
        }
        if (requestBody.contentLength() != -1) {
          logger.log("Content-Length: " + requestBody.contentLength());
        }
      }

      Headers headers = request.headers();
      for (int i = 0, count = headers.size(); i < count; i++) {
        String name = headers.name(i);
        // Skip headers from the request body as they are explicitly logged above.
        if (!"Content-Type".equalsIgnoreCase(name) && !"Content-Length".equalsIgnoreCase(name)) {
          logger.log(name + ": " + headers.value(i));
        }
      }



      if (!logBody || !hasRequestBody) {
        logger.log("--> END " + request.method());
      } else if (bodyEncoded(request.headers())) {
        logger.log("--> END " + request.method() + " (encoded body omitted)");
      } else {
        Buffer buffer = new Buffer();
        requestBody.writeTo(buffer);

        Charset charset = UTF8;
        MediaType contentType = requestBody.contentType();
        if (contentType != null) {
          charset = contentType.charset(UTF8);
        }

        logger.log("");
        if (isPlaintext(buffer)) {
          logger.log(System.getProperty("line.separator")+" ===========================  Body  ========================");
//          logger.log(buffer.readString(charset));
          logger.log(jsonFormatter(buffer.readString(charset)));
          logger.log("--> END " + request.method()
              + " (" + requestBody.contentLength() + "-byte body)");
        } else {
          logger.log("--> END " + request.method() + " (binary "
              + requestBody.contentLength() + "-byte body omitted)");
        }
      }
    }

    long startNs = System.nanoTime();
    if(!NetUtil.isNetworkConnected()){
      request = request.newBuilder()
              .build();
      com.orhanobut.logger.Logger.w("not network");
    }


    Response response;
    try {
      response = chain.proceed(request);
    } catch (Exception e) {
      logger.log("<-- HTTP FAILED: " + e);
      throw e;
//      return Response.Builder;
    }


    long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);

    ResponseBody responseBody = response.body();
    long contentLength = responseBody.contentLength();
    String bodySize = contentLength != -1 ? contentLength + "-byte" : "unknown-length";
    logger.log("<-- " + response.code() + ' ' + response.message() + ' '
        + response.request().url() + " (" + tookMs + "ms" + (!logHeaders ? ", "
        + bodySize + " body" : "") + ')');

    if (logHeaders) {
      Headers headers = response.headers();
      for (int i = 0, count = headers.size(); i < count; i++) {
        logger.log(headers.name(i) + ": " + headers.value(i));
      }

      if (!logBody || !HttpEngine.hasBody(response)) {
        logger.log("<-- END HTTP");
      } else if (bodyEncoded(response.headers())) {
        logger.log("<-- END HTTP (encoded body omitted)");
      } else {
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffer = source.buffer();




        Charset charset = UTF8;
        MediaType contentType = responseBody.contentType();
        if (contentType != null) {
          try {
            charset = contentType.charset(UTF8);
          } catch (UnsupportedCharsetException e) {
            logger.log("");
            logger.log("Couldn't decode the response body; charset is likely malformed.");
            logger.log("<-- END HTTP");

            return response;
          }
        }



        if (!isPlaintext(buffer)) {
          logger.log(" ");
          logger.log("******************* 非文本数据 ***************");
          logger.log(" ");

          //非图片，json格式。全部转成json格式返回
          if(!headers.get("Content-Type").startsWith("image/")&& !request.url().toString().contains("index.php?act=seccode&op=makecode&k=")){
            logger.log("解析错误：转化为Error 类型Json");
            logger.log("Content-Type:"+headers.get("Content-Type"));
            String error2 = "{\"code\":400}";
            buffer.clear();
            buffer.write(error2.getBytes());
          }

          logger.log("<-- END HTTP (binary " + buffer.size() + "-byte body omitted)");
          logger.log(" ");
          logger.log(" ");
          return response;
        }

        if (contentLength != 0) {
          logger.log("body: "+buffer.clone().readString(charset));
          logger.log(System.getProperty("line.separator")+" ===========================  Body  ========================");
          logger.log(jsonFormatter(buffer.clone().readString(charset)));
        }

        logger.log("<-- END HTTP (" + buffer.size() + "-byte body)");
        logger.log(" ");
        logger.log(" ");

      }
    }

    return response;
  }

  /**
   * Returns true if the body in question probably contains human readable text. Uses a small sample
   * of code points to detect unicode control characters commonly used in binary file signatures.
   */
  static boolean isPlaintext(Buffer buffer) throws EOFException {
    try {
      Buffer prefix = new Buffer();
      long byteCount = buffer.size() < 64 ? buffer.size() : 64;
      buffer.copyTo(prefix, 0, byteCount);
      for (int i = 0; i < 16; i++) {
        if (prefix.exhausted()) {
          break;
        }
        if (Character.isISOControl(prefix.readUtf8CodePoint())) {
          return false;
        }
      }
      return true;
    } catch (EOFException e) {
      return false; // Truncated UTF-8 sequence.
    }
  }

  private boolean bodyEncoded(Headers headers) {
    String contentEncoding = headers.get("Content-Encoding");
    return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
  }




  public static String jsonFormatter(String jsonStr) {
    jsonStr = StringUtils.decodeUnicode(jsonStr);
    int level = 0;
    StringBuffer jsonForMatStr = new StringBuffer();
    for(int i=0;i<jsonStr.length();i++){
      char c = jsonStr.charAt(i);
      if(level>0&&'\n'==jsonForMatStr.charAt(jsonForMatStr.length()-1)){
        jsonForMatStr.append(getLevelStr(level));
      }
      switch (c) {
        case '{':
        case '[':
          jsonForMatStr.append(c+"\n");
          level++;
          break;
        case ',':
          jsonForMatStr.append(c+"\n");
          break;
        case '}':
        case ']':
          jsonForMatStr.append("\n");
          level--;
          jsonForMatStr.append(getLevelStr(level));
          jsonForMatStr.append(c);
          break;
        default:
          jsonForMatStr.append(c);
          break;
      }
    }

    return jsonForMatStr.toString();

  }


  private static String getLevelStr(int level){
    StringBuffer levelStr = new StringBuffer();
    for(int levelI = 0;levelI<level ; levelI++){
      levelStr.append("\t");
    }
    return levelStr.toString();
  }


}

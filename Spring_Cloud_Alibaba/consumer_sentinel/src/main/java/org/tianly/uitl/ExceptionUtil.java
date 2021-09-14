package org.tianly.uitl;

import com.alibaba.cloud.sentinel.rest.SentinelClientHttpResponse;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;

/**
 * @ClassName: ExceptionUtil @Description:
 *
 * @author: tianly
 * @date: 2021/9/6 22:48
 */
public class ExceptionUtil {
  public static SentinelClientHttpResponse handleException(
      HttpRequest request, byte[] body, ClientHttpRequestExecution execution, BlockException ex) {
    System.err.println("Oops: " + ex.getClass().getCanonicalName());
    return new SentinelClientHttpResponse("custom block info");
  }

  public static SentinelClientHttpResponse fallback(
      HttpRequest request, byte[] body, ClientHttpRequestExecution execution, BlockException ex) {
    System.err.println("fallback: " + ex.getClass().getCanonicalName());
    return new SentinelClientHttpResponse("custom fallback info");
  }
}

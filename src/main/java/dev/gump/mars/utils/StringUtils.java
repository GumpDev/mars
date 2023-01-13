package dev.gump.mars.utils;

import java.net.URL;
import org.apache.commons.codec.binary.Base64;

public class StringUtils {
  public static boolean checkUrl(String str) {
    try {
      new URL(str).toURI();
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public static boolean checkBase64(String str) {
    return Base64.isBase64(str);
  }
}

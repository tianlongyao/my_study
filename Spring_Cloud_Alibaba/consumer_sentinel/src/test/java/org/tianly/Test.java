package org.tianly;

/**
 * @ClassName: Test @Description:
 *
 * @author: tianly
 * @date: 2021/9/3 1:01
 */
public class Test {
  public static void main(String[] args) {
    System.out.println(test(3));
  }
  public  static String test(int i){
      try {
          if( 0 > i){
              throw new IllegalArgumentException();
          }
          return "1";
      }catch (Exception e) {
          return "2";
      }finally{
          return "3";
      }
  }
}

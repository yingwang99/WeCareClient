package util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.util.Log;
 
/**
*
* ying
*/
public class CrashHandler implements UncaughtExceptionHandler {
 
     private Thread.UncaughtExceptionHandler mDefaultHandler;
     private static CrashHandler INSTANCE;
     private Context mContext;
     private Map<String, String> infos = new HashMap<String, String>();
 
     private CrashHandler() {
     }
 
    
     public static CrashHandler getInstance() {
          if (INSTANCE == null)
                INSTANCE = new CrashHandler();
                return INSTANCE;
          }
 
          /**
            *
            * @param context
            */
          public void init(Context context) {
               mContext = context;
               mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
               Thread.setDefaultUncaughtExceptionHandler(this);
          }
 
          @Override
          public void uncaughtException(Thread thread, Throwable ex) {
               if (!handleException(ex) && mDefaultHandler != null) {
                     mDefaultHandler.uncaughtException(thread, ex);
               } else {
                     try {
                           Thread.sleep(3000);
                     } catch (InterruptedException e) {
                           Log.e("",e.toString());
                     }
                     android.os.Process.killProcess(android.os.Process.myPid());
                     System.exit(1);
               }
         }
 
         /**
           *
           * @param ex
           * @return true:
           */
          private boolean handleException(Throwable ex) {
               if (ex == null) {
                     return false;
               }
               collectDeviceInfo(mContext);
               saveCrashInfo2File(ex);
               return true;
          }
 
          /**
            *
            * @param ctx
            */
          public void collectDeviceInfo(Context ctx) {
               try {
                     PackageManager pm = ctx.getPackageManager();
                     PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
                     if (pi != null) {
                           String versionName = pi.versionName == null ? "null" : pi.versionName;
                           String versionCode = pi.versionCode + "";
                           infos.put("versionName", versionName);
                           infos.put("versionCode", versionCode);
                     }
                } catch (NameNotFoundException e) {
                     Log.e("","CrashHandleran.NameNotFoundException---> error occured when collect package info", e);
                }
                Field[] fields = Build.class.getDeclaredFields();
                for (Field field : fields) {
                     try {
                           field.setAccessible(true);
                           infos.put(field.getName(), field.get(null).toString());
                     } catch (Exception e) {
                           Log.e("","CrashHandler.NameNotFoundException---> an error occured when collect crash info", e);
                     }
                }
          }
 
          /**
   *
            * @param ex
            * @return 
            */
           private String saveCrashInfo2File(Throwable ex) {
 
                 StringBuffer sb = new StringBuffer();
                 sb.append("---------------------sta--------------------------");
                 for (Map.Entry<String, String> entry : infos.entrySet()) {
                       String key = entry.getKey();
                       String value = entry.getValue();
                       sb.append(key + "=" + value + "\n");
                 }
 
                 Writer writer = new StringWriter();
                 PrintWriter printWriter = new PrintWriter(writer);
                 ex.printStackTrace(printWriter);
                 Throwable cause = ex.getCause();
                 while (cause != null) {
                       cause.printStackTrace(printWriter);
                       cause = cause.getCause();
                 }
                 printWriter.close();
                 String result = writer.toString();
                 sb.append(result);
                 sb.append("--------------------end---------------------------");
                 Log.e("",sb.toString());
                 return null;
         }
}
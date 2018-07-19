package com.newx.utils.mobile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.content.SharedPreferencesCompat;
import android.text.TextUtils;

import com.newx.utils.logger.NXLog;
import com.newx.utils.encrypt.DESUtil;
import com.newx.utils.encrypt.MD5Util;

import java.util.Map;
import java.util.Set;

/**
 * Created by xuzhijian on 2018/4/10 0010.
 */
@SuppressLint("ApplySharedPref")
public class SPUtil {

    private static int MODE = Context.MODE_PRIVATE;
    private static String DEF_FILE_NAME = "cache_share";
    private static Context sContext;

    private SPUtil() {
    }

    public static void init(Context context) {
        init(context, DEF_FILE_NAME);
    }

    public static void init(Context context, String defFileName) {
        if (context == null) {
            throw new NullPointerException("context is null !");
        }
        sContext = context;
        DEF_FILE_NAME = defFileName;
    }

    /**
     * get sharepreferences
     *
     * @param fileName sharepre file name
     * @return sharePreferences
     */
    private static SharedPreferences getSharePrefer(String fileName) {
        return sContext.getSharedPreferences(fileName, MODE);
    }

    /**
     * 使用默认文件名存储
     *
     * @param key    存储内容 key
     * @param object 存储的内容
     */
    public static void put(String key, Object object) {
        put(key, object, false);
    }

    public static void put(String key, Object object, boolean valueEncrypt) {
        put(DEF_FILE_NAME, key, object, valueEncrypt);
    }

    public static void put(String fileName, String key, Object object) {
        put(fileName, key, object, false);
    }

    /**
     * 保存数据，通过文件名，key。通过对象的类型来存储
     *
     * @param fileName 文件名
     * @param key      存储内容 key
     * @param object   存储的内容
     */
    public static void put(String fileName, String key, Object object, boolean valueEncrypt) {
        if (TextUtils.isEmpty(key)) {
            return;
        }
        String dataKey = convertKey(key);
        SharedPreferences sp = getSharePrefer(fileName);
        SharedPreferences.Editor editor = sp.edit();
        if (valueEncrypt) {
            try {
                String encryptValue = encryptValue(String.valueOf(object));
                editor.putString(dataKey, encryptValue);
            } catch (Exception e) {
                editor.putString(dataKey, (String) object);
            }
        } else {
            if (object instanceof String) {
                editor.putString(dataKey, (String) object);
            } else if (object instanceof Integer) {
                editor.putInt(dataKey, (Integer) object);
            } else if (object instanceof Boolean) {
                editor.putBoolean(dataKey, (Boolean) object);
            } else if (object instanceof Float) {
                editor.putFloat(dataKey, (Float) object);
            } else if (object instanceof Long) {
                editor.putLong(dataKey, (Long) object);
            } else {
                editor.putString(dataKey, object.toString());
            }
        }
        apply(editor);
    }


    /**
     * 加密key
     *
     * @param key 需要加密的key
     * @return MD5加密后的key
     */
    private static String convertKey(String key) {
        return MD5Util.MD5(key);
    }

    /**
     * 加密value
     *
     * @param value value
     * @return 加密后的 value
     */
    private static String encryptValue(String value) throws Exception {
        if (TextUtils.isEmpty(value)) {
            return value;
        }
        return new DESUtil().encrypt(value);
    }

    /**
     * 解密value
     *
     * @param value value
     * @return 解密后的 value
     */
    private static String decryptValue(String value) throws Exception {
        if (TextUtils.isEmpty(value)) {
            return value;
        }
        return new DESUtil().decrypt(value);
    }

    /**
     * 获取默认文件下，key 的值
     *
     * @param key           保存数据的key
     * @param defaultObject 默认值
     * @return 保存的数据
     */
    public static Object get(String key, Object defaultObject) {
        return get(DEF_FILE_NAME, key, defaultObject);
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     */
    public static Object get(String fileName, String key, Object defaultObject) {
        return get(fileName, key, defaultObject, false);
    }

    public static Object get(String key, Object defaultObject, boolean valueDecrypt) {
        return get(DEF_FILE_NAME, key, defaultObject, valueDecrypt);
    }

    /**
     * @param fileName      保存的文件名称
     * @param key           保存的key值
     * @param defaultObject 默认返回的值
     * @param valueDecrypt  是否需要解密
     * @return 获取保存到SP的数据
     */
    public static Object get(String fileName, String key, Object defaultObject, boolean valueDecrypt) {
        if (TextUtils.isEmpty(key)) {
            return defaultObject;
        }
        String dataKey = convertKey(key);
        SharedPreferences sp = getSharePrefer(fileName);
        if (valueDecrypt) {
            String dataValue = sp.getString(dataKey, "");
            if (TextUtils.isEmpty(dataValue)) {
                return defaultObject;
            }
            try {
                String decryptValue = decryptValue(dataValue);
                if (defaultObject instanceof Integer) {
                    return Integer.parseInt(decryptValue);
                } else if (defaultObject instanceof Boolean) {
                    return Boolean.parseBoolean(decryptValue);
                } else if (defaultObject instanceof Float) {
                    return Float.parseFloat(decryptValue);
                } else if (defaultObject instanceof Long) {
                    return Long.parseLong(decryptValue);
                } else {
                    return decryptValue;
                }
            } catch (Exception e) {
                return dataValue;
            }
        } else {
            if (defaultObject instanceof String) {
                return sp.getString(dataKey, (String) defaultObject);
            } else if (defaultObject instanceof Integer) {
                return sp.getInt(dataKey, (Integer) defaultObject);
            } else if (defaultObject instanceof Boolean) {
                return sp.getBoolean(dataKey, (Boolean) defaultObject);
            } else if (defaultObject instanceof Float) {
                return sp.getFloat(dataKey, (Float) defaultObject);
            } else if (defaultObject instanceof Long) {
                return sp.getLong(dataKey, (Long) defaultObject);
            }
        }
        return defaultObject;
    }

    // ======================================== String部分 ========================================
    public static String getString(String fileName, String key, final String defaultObject) {
        return getString(fileName, key, defaultObject, false);
    }

    public static String getString(String key, final String defaultObject, boolean valueDecrypt) {
        return getString(DEF_FILE_NAME, key, defaultObject, valueDecrypt);
    }


    public static String getString(String key, final String defaultObject) {
        return getString(DEF_FILE_NAME, key, defaultObject);
    }

    public static String getString(String fileName, String key, final String defaultObject, boolean valueDecrypt) {
        if (TextUtils.isEmpty(key)) {
            return defaultObject;
        }
        String dataKey = convertKey(key);
        SharedPreferences sp = getSharePrefer(fileName);
        if (valueDecrypt) {
            String dataValue = sp.getString(dataKey, "");
            if (TextUtils.isEmpty(dataValue)) {
                return defaultObject;
            }
            try {
                String decryptValue = decryptValue(dataValue);
                return decryptValue;
            } catch (Exception e) {
                return dataValue;
            }
        } else {
            return sp.getString(dataKey, defaultObject);
        }
    }
    // ============================================================================================

    // ======================================== Boolean部分 ========================================
    // boolean 不加密
    public static boolean getBoolean(String key, final boolean defaultObject) {
        return getBoolean(DEF_FILE_NAME, key, defaultObject);
    }

    public static boolean getBoolean(String fileName, String key, final boolean defaultObject) {
        if (TextUtils.isEmpty(key)) {
            return defaultObject;
        }
        String dataKey = convertKey(key);
        SharedPreferences sp = getSharePrefer(fileName);
        return sp.getBoolean(dataKey, defaultObject);
    }
    // ============================================================================================

    // ======================================== int  部分 ========================================
    // int 不加密
    public static int getInt(String key, final int defaultObject) {
        return getInt(DEF_FILE_NAME, key, defaultObject);
    }

    public static int getInt(String fileName, String key, final int defaultObject) {
        if (TextUtils.isEmpty(key)) {
            return defaultObject;
        }
        String dataKey = convertKey(key);
        SharedPreferences sp = getSharePrefer(fileName);
        return sp.getInt(dataKey, defaultObject);
    }
    // ============================================================================================

    // ======================================== long  部分 ========================================
    // long 不加密
    public static long getLong(String key, final long defaultObject) {
        return getLong(DEF_FILE_NAME, key, defaultObject);
    }

    public static long getLong(String fileName, String key, final long defaultObject) {
        if (TextUtils.isEmpty(key)) {
            return defaultObject;
        }
        String dataKey = convertKey(key);
        SharedPreferences sp = getSharePrefer(fileName);
        return sp.getLong(dataKey, defaultObject);
    }
    // ============================================================================================

    // ======================================== float 部分 ========================================
    // float 不加密
    public static float getLong(String key, final float defaultObject) {
        return getFloat(DEF_FILE_NAME, key, defaultObject);
    }

    public static float getFloat(String fileName, String key, final float defaultObject) {
        if (TextUtils.isEmpty(key)) {
            return defaultObject;
        }
        String dataKey = convertKey(key);
        SharedPreferences sp = getSharePrefer(fileName);
        return sp.getFloat(dataKey, defaultObject);
    }
    // ============================================================================================

    // ===================================== StringSet  部分 ======================================
    // StringSet 不加密
    public static Set<String> getStringSet(String key, final Set<String> defaultObject) {
        return getStringSet(DEF_FILE_NAME, key, defaultObject);
    }

    public static Set<String> getStringSet(String fileName, String key, final Set<String> defaultObject) {
        if (TextUtils.isEmpty(key)) {
            return defaultObject;
        }
        String dataKey = convertKey(key);
        SharedPreferences sp = getSharePrefer(fileName);
        return sp.getStringSet(dataKey, defaultObject);
    }
    // ============================================================================================


    /**
     * 移除某个key值已经对应的值
     */
    public static void remove(String key) {
        remove(DEF_FILE_NAME, key);
    }

    /**
     * 移除某个key值已经对应的值
     */
    public static void remove(String fileName, String key) {
        if (TextUtils.isEmpty(key)) {
            return;
        }
        String dataKey = convertKey(key);
        SharedPreferences sp = getSharePrefer(fileName);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(dataKey);
        apply(editor);
    }

    public static void clear() {
        clear(DEF_FILE_NAME);
    }

    /**
     * 清除所有数据
     */
    public static void clear(String fileName) {
        SharedPreferences sp = getSharePrefer(fileName);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        apply(editor);
        NXLog.e("请注意：清除了所有SharedPreferences");
    }

    public static boolean contains(String key) {
        return contains(DEF_FILE_NAME, key);
    }

    /**
     * 查询某个key是否已经存在
     */
    public static boolean contains(String fileName, String key) {
        if (TextUtils.isEmpty(key)) {
            return false;
        }
        String dataKey = convertKey(key);
        SharedPreferences sp = getSharePrefer(fileName);
        return sp.contains(dataKey);
    }

    public static Map<String, ?> getAll() {
        return getAll(DEF_FILE_NAME);
    }

    /**
     * 返回所有的键值对
     */
    public static Map<String, ?> getAll(String fileName) {
        SharedPreferences sp = getSharePrefer(fileName);
        return sp.getAll();
    }

    private static void apply(SharedPreferences.Editor editor) {
        SharedPreferencesCompat.EditorCompat.getInstance().apply(editor);
    }
}


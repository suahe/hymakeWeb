package com.hymake.mobileWeb.util;

/**
 * 比较工具类
 */
public class CompareUtil {

    /**
     * 比较版本号大小
     * @param v1
     * @param v2
     * @return
     */
    public static int compareVersion(String v1, String v2) {
        if (v1.equals(v2)) {
            return 0;
        } else {
            String[] version1 = v1.split("\\.");
            String[] version2 = v2.split("\\.");
            int index = 0;
            int minLen = Math.min(version1.length, version2.length);

            long diff;
            for(diff = 0L; index < minLen && (diff = Long.parseLong(version1[index]) - Long.parseLong(version2[index])) == 0L; ++index) {
            }

            if (diff == 0L) {
                int i;
                for(i = index; i < version1.length; ++i) {
                    if (Long.parseLong(version1[i]) > 0L) {
                        return 1;
                    }
                }

                for(i = index; i < version2.length; ++i) {
                    if (Long.parseLong(version2[i]) > 0L) {
                        return -1;
                    }
                }

                return 0;
            } else {
                return diff > 0L ? 1 : -1;
            }
        }
    }
}


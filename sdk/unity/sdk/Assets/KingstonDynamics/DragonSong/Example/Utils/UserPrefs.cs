using UnityEngine;

public class UserPrefs : PlayerPrefs
{

    public static void SetBool(string key, bool value)
    {
        SetInt(key, value ? 1 : 0);
    }

    public static bool GetBool(string key)
    {
        return GetInt(key, 0) == 1;
    }

    public static bool GetBool(string key, bool defaultValue)
    {
        if (!HasKey(key))
        {
            return defaultValue;
        }

        return GetInt(key, 0) == 1;
    }
}

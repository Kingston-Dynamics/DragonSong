using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class UserPrefs : PlayerPrefs
{

    public static void SetBool(string key, bool value)
    {
        PlayerPrefs.SetInt(key, value ? 1 : 0);
    }

    public static bool GetBool(string key)
    {
        return PlayerPrefs.GetInt(key, 0) == 1;
    }

    public static bool GetBool(string key, bool defaultValue)
    {
        if (!PlayerPrefs.HasKey(key))
        {
            return defaultValue;
        }

        return PlayerPrefs.GetInt(key, 0) == 1;
    }
}

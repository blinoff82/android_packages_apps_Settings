/*
 * Copyright (C) 2012 The CyanogenMod Project
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

package com.android.settings.aospextended.profiles.actions.item;

import android.content.Context;
import android.telephony.SubscriptionManager;

import com.android.settings.R;
import com.android.settings.aospextended.profiles.actions.ItemListAdapter;

import com.android.internal.util.lineageos.profiles.ConnectionSettings;
import com.android.settings.aospextended.profiles.utils.TelephonyUtils;

public class ConnectionOverrideItem extends BaseItem {
    int mConnectionId;
    ConnectionSettings mConnectionSettings;

    public static final int Lineage_MODE_SYSTEM_DEFAULT = -1;

    public ConnectionOverrideItem(int connectionId, ConnectionSettings settings) {
        mConnectionId = connectionId;
        if (settings == null) {
            settings = new ConnectionSettings(connectionId);
        }
        this.mConnectionSettings = settings;
    }

    @Override
    public ItemListAdapter.RowType getRowType() {
        return ItemListAdapter.RowType.CONNECTION_ITEM;
    }

    @Override
    public String getTitle() {
        return getConnectionTitle(getContext(), mConnectionSettings);
    }

    @Override
    public String getSummary() {
        return String.valueOf(getSummary(getContext()));
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static String getConnectionTitle(Context context, ConnectionSettings settings) {
        int r = 0;
        switch (settings.getConnectionId()) {
            case ConnectionSettings.PROFILE_CONNECTION_BLUETOOTH:
                r = R.string.sp_toggleBluetooth;
                break;
            case ConnectionSettings.PROFILE_CONNECTION_MOBILEDATA:
                r =R.string.sp_toggleData;
                break;
            case ConnectionSettings.PROFILE_CONNECTION_2G3G4G:
                if (settings.getSubId() != SubscriptionManager.INVALID_SUBSCRIPTION_ID) {
                    final String displayName = SubscriptionManager.from(context)
                            .getActiveSubscriptionInfo(settings.getSubId())
                            .getDisplayName()
                            .toString();
                    return context.getString(R.string.sp_toggle2g3g4g_msim, displayName);
                }
                r = R.string.sp_toggle2g3g4g;
                break;
            case ConnectionSettings.PROFILE_CONNECTION_GPS:
                r = R.string.sp_toggleGPS;
                break;
            case ConnectionSettings.PROFILE_CONNECTION_NFC:
                r = R.string.sp_toggleNfc;
                break;
            case ConnectionSettings.PROFILE_CONNECTION_SYNC:
                r = R.string.sp_toggleSync;
                break;
            case ConnectionSettings.PROFILE_CONNECTION_WIFI:
                r = R.string.sp_toggleWifi;
                break;
            case ConnectionSettings.PROFILE_CONNECTION_WIFIAP:
                r = R.string.sp_toggleWifiAp;
                break;
        }
        return context.getString(r);
    }

    public CharSequence getSummary(Context context) {
        int resId = -1;
        if (mConnectionSettings != null) {
            if (mConnectionId == ConnectionSettings.PROFILE_CONNECTION_2G3G4G) { // different options
                if (mConnectionSettings.isOverride()) {
                    return TelephonyUtils.getNetworkModeString(context,
                            mConnectionSettings.getValue(),
                            SubscriptionManager.getDefaultDataSubscriptionId());
                } else {
                    resId = R.string.sp_profile_action_none;
                }
            } else if (mConnectionSettings.isOverride()) { // enabled, disabled, or none
                if (mConnectionSettings.getValue() == 1) {
                    resId = R.string.sp_profile_action_enable;
                } else {
                    resId = R.string.sp_profile_action_disable;
                }
            } else {
                resId = R.string.sp_profile_action_none;
            }
        } else {
            resId = R.string.sp_profile_action_none;
        }
        return context.getString(resId);
    }

    public ConnectionSettings getSettings() {
        return mConnectionSettings;
    }

    public int getConnectionType() {
        return mConnectionId;
    }
}